package org.momento.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.momento.Features.Item.Implements.ShieldFeature;
import org.momento.Momento;


public class GiveCommand implements CommandExecutor {

    public GiveCommand(Momento momento) {
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
        String shieldName = args[1];

        try {
            ItemStack shield = ShieldFeature.shieldList.get(shieldName);
            player.getInventory().addItem(shield);
            player.sendMessage("You've been given " + shieldName);
        } catch (Exception e) {
            player.sendMessage("Object not found.");
        }

        return true;
    }
}
