package org.momento.Mixin;

import com.dragoncommissions.mixbukkit.api.shellcode.impl.api.CallbackInfo;
import com.google.common.base.Preconditions;
import net.minecraft.world.item.crafting.RecipeItemStack;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;

public class CraftRecipe {

    public static RecipeItemStack toNMS(RecipeChoice bukkit, boolean requireNotEmpty, CallbackInfo callbackInfo)
    {
        Bukkit.broadcastMessage("dfujdshfsdhfhkdsfhsdgkfhsdgfsdhfdgjksfhsd");
        return null;
        
        /*
        RecipeItemStack stack;
        if (bukkit == null) {
            stack = RecipeItemStack.a;
        } else if (bukkit instanceof RecipeChoice.MaterialChoice) {
            stack = new RecipeItemStack(((RecipeChoice.MaterialChoice)bukkit).getChoices().stream().map((mat) -> {
                return new RecipeItemStack.StackProvider(CraftItemStack.asNMSCopy(new ItemStack(mat)));
            }));
        } else {
            if (!(bukkit instanceof RecipeChoice.ExactChoice)) {
                throw new IllegalArgumentException("Unknown recipe stack instance " + bukkit);
            }

            stack = new RecipeItemStack(((RecipeChoice.ExactChoice)bukkit).getChoices().stream().map((mat) -> {
                return new RecipeItemStack.StackProvider(CraftItemStack.asNMSCopy(mat));
            }));
            stack.exact = true;
        }

        stack.a();
        if (requireNotEmpty) {
            Preconditions.checkArgument(stack.e.length != 0, "Recipe requires at least one non-air choice");
        }

        return stack;*/
    }

}
