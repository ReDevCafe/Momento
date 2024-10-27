package org.momento.Features.Item.Component.Data;

import java.util.AbstractMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.momento.Features.Item.Implements.ItemFactory;
import org.momento.Features.Item.ItemComponent;
import org.momento.Momento;

import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftRecipe;

public class WorkbenchCraft extends ItemComponent {

    private String craftId;
    private String[] shape;
    private boolean shapped;
    private Map<Character, AbstractMap.SimpleEntry<ItemStack, Boolean>> materials;

    @Override
    public void param(ConfigurationSection section) {
        materials = new java.util.HashMap<>();
        ConfigurationSection ingredientSection = section.getConfigurationSection("ingredients");

        if (ingredientSection == null)
            throw new IllegalStateException("No ingredients");


        for (String name : ingredientSection.getKeys(false)) {
            char symbol = name.charAt(0);
            String materialName = ingredientSection.getString(name);
            Boolean isMomItem = materialName.startsWith("@");
            ItemStack material; 

            if(isMomItem)
                material = ItemFactory.itemsList.get(materialName.replaceFirst("@", ""));
            else material = new ItemStack(Material.getMaterial(materialName));
            
            if (material == null)
                throw new IllegalStateException("Invalid material");

            materials.put(symbol, new AbstractMap.SimpleEntry<>(material, isMomItem));
        }

        if (section.isList("shape")) {
            shape = section.getStringList("shape").toArray(new String[0]);
            shapped = true;
        } else
            shapped = false;

        craftId = section.getString("id");
        if (craftId == null)
            throw new IllegalArgumentException("craftId cannot be null");
    }

    @Override
    public ItemStack init(ItemStack itemStack) {
        NamespacedKey key = new NamespacedKey(Momento.plugin, "recipe_" + craftId);

        if (shapped) {
            ShapedRecipe recipe = new ShapedRecipe(key, itemStack);
            recipe.shape(shape);

            for (Map.Entry<Character, AbstractMap.SimpleEntry<ItemStack, Boolean>> entry : materials.entrySet()) 
            {
                ItemStack stack = entry.getValue().getKey();

                if(entry.getValue().getValue())
                    recipe.setIngredient(entry.getKey(), new ExactChoice(stack));
                else recipe.setIngredient(entry.getKey(), stack.getType());
            }

            Momento.plugin.getServer().addRecipe(recipe);
        } else {
            ShapelessRecipe recipe = new ShapelessRecipe(key, itemStack);

            for (Map.Entry<Character, AbstractMap.SimpleEntry<ItemStack, Boolean>> entry : materials.entrySet()) 
            {
                ItemStack stack = entry.getValue().getKey();

                if(entry.getValue().getValue())
                    recipe.addIngredient(new ExactChoice(stack));
                else recipe.addIngredient(stack.getType());
            }

            Momento.plugin.getServer().addRecipe(recipe);
        }
        return itemStack;
    }

    @Override
    public Boolean Serializable() {
        return false;  // will not be added to the save binary
    }
}
