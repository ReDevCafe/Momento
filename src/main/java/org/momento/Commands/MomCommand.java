package org.momento.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.momento.Features.Item.Implements.ItemFactory;
import org.momento.Momento;

import net.md_5.bungee.api.ChatColor;


public class MomCommand implements CommandExecutor {

    public MomCommand(Momento momento) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        if (args.length != 2) return false;
        
        Player player = (Player) sender;
        String itName = args[1];

        try {
            switch(args[0])
            {
                case "give":
                    ItemStack it = ItemFactory.itemsList.get(itName);
                    player.getInventory().addItem(it);
                    player.sendMessage("You've been given " + itName);
                    break;
                case "compare":
                    player.sendMessage(ChatColor.RED+"?? :"+ItemFactory.itemsList.get(itName).isSimilar(player.getInventory().getItem(EquipmentSlot.HAND)));
                    break;
                
            }
        } catch (IllegalArgumentException e) {
            player.sendMessage("Object not found.");
        }

        return true;
    }
}
