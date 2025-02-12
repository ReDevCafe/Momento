package org.momento.Commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.momento.Features.Block.Block;
import org.momento.Features.Block.BlockFactory;
import org.momento.Features.Item.Implements.ItemFactory;
import org.momento.Features.Item.Item;
import org.momento.Momento;


public class MomentoCommand implements CommandExecutor {

    public MomentoCommand(Momento momento) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        if (args.length < 2) return false;

        Player player = (Player) sender;
        String object = args[1];

        try {
            switch(args[0])
            {
                case "give":
                    ItemStack item = ItemFactory.itemsList.get(object).copy().getItemStack();
                    player.getInventory().addItem(item);
                    player.sendMessage("You've been given " + object);
                    break;
                case "setblock":
                    if(args.length != 5) 
                    {
                        player.sendMessage("Usage: /mom setblock <block> <x> <y> <z>");
                        break;
                    }

                    Block block = BlockFactory.blocksList.get(object);
                    try {

                        Location location = new Location(
                            player.getWorld(), 
                            Integer.parseInt(args[2]), 
                            Integer.parseInt(args[3]), 
                            Integer.parseInt(args[4])
                        );

                        block.clone(location);
                        player.sendMessage("Block placed at " + location.toString());
                    } catch (Exception e) {
                    }
                case "debug":
                    switch (args[1])
                    {
                        case "list_i":
                            for (Item mItem : Momento.items.items.values())
                                player.sendMessage(mItem.toString());
                    
                            break;
                        case "list_b":
                            for (Block mBlock : Momento.blocks.blocks.values()) 
                                player.sendMessage(mBlock.toString());
                        
                            break;
                        default:
                            break;
                    }
                default:
                    break;
            }
        } catch (Exception e) {
            player.sendMessage("Object not found.");
        }

        return true;
    }
}
