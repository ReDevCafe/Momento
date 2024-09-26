package org.momento.Features.Item.Component.Data;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.momento.Momento;
import org.momento.Features.Item.ItemComponent;

import java.util.Map;

public class WorkbenchCraft extends ItemComponent
{
    private String craftId;
    private String[] shape;
    private Map<Character, Material> materials;
    

    @Override
    public void param(ConfigurationSection section) {
        materials = new java.util.HashMap<>();

        shape = section.getStringList("shape").toArray(new String[0]);
        craftId = section.getString("id").toString();

        if(craftId == null || shape == null) throw new IllegalArgumentException();

        ConfigurationSection ingredientSection = section.getConfigurationSection("ingredients");
        if(ingredientSection == null) throw new IllegalStateException("No ingredients");

        for(String name : ingredientSection.getKeys(false))
        {
            char symbol = name.charAt(0);
            Material material = Material.getMaterial(ingredientSection.getString(name));

            if(material == null) throw new IllegalStateException("Invalid material");
            materials.put(symbol, material);
        }
    }

    @Override
    public ItemStack init(ItemStack itemStack) {
        NamespacedKey key = new NamespacedKey(Momento.plugin, "recipe_"+craftId);
        ShapedRecipe recipe = new ShapedRecipe(key, itemStack);
        recipe.shape(shape);

        for(Map.Entry<Character, Material> entry : materials.entrySet())
            recipe.setIngredient(entry.getKey(), entry.getValue());

        Momento.plugin.getServer().addRecipe(recipe);
        return itemStack;
    }
}
