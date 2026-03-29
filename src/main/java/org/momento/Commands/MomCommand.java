package org.momento.Commands;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.momento.Data.ComponentRegistry;
import org.momento.Data.MomentoKeys;
import org.momento.Features.Item.Implements.ItemFactory;
import org.momento.Features.Item.Item;
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

        if (args.length < 2) return false;
        
        Player player = (Player) sender;
        String secName = args[1];

        try {
            switch(args[0])
            {
                case "give":
                    ItemStack it = ItemFactory.itemsList.get(secName);
                    player.getInventory().addItem(it);
                    player.sendMessage("You've been given " + secName);
                    break;

                case "compare":
                    player.sendMessage(ChatColor.RED+"?? :"+ItemFactory.itemsList.get(secName).isSimilar(player.getInventory().getItem(EquipmentSlot.HAND)));
                    break;

                case "get":
                    GET(player, secName);
                    break;

                case "binaries":
                    BINARIES(player, secName);
                    break;
                
            }
        } catch (IllegalArgumentException e) {
            sender.sendMessage("Object not found.");
        }
        return true;
    }

    private Boolean GET(Player player, String secName)
    {
        ItemStack iq = player.getInventory().getItem(EquipmentSlot.HAND);
        if(iq.getType() == Material.AIR) {
            player.sendMessage("You're not holding an item.");
            return true;
        }

        Class component = ComponentRegistry.registry().get(secName);
        if ( component == null ) {
            player.sendMessage("Component not found.");
            return true;
        }

        PersistentDataContainer pdc;
        pdc = iq.getItemMeta().getPersistentDataContainer();

        String signature = pdc.get(MomentoKeys.SIGNATURE, PersistentDataType.STRING);
        if (signature == null) {
            player.sendMessage("This item is not a Momento item.");
            return true;
        }

        Item mit = Momento.items.items.get(signature);
        if (mit == null) {
            player.sendMessage("This item does not exist in the Momento database.");
            return true;
        }
        
        player.sendMessage("§dSignature §6{\n§b     signature§6: §c" + signature+"\n§6}§r");
        player.sendMessage(mit.findComponentByType(component).toString());
        return true;
    }

    public Boolean BINARIES(Player player, String secName)
    {
        player.sendMessage("§rReading from §d" + secName + "§r...");

        switch(secName)
        {
            case "item":
                for (Map.Entry<String, Item> elem : Momento.items.items.entrySet())
                    player.sendMessage("§d > §b" + elem.getKey() + "§6: §c" + elem.getValue()+"§r");
                break;

            default:
                player.sendMessage("Binary not found.");
                break;
        }
    
        return true;
    }
}