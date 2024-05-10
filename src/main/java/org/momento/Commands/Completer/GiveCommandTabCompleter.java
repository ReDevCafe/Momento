package org.momento.Commands.Completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.inventory.ItemStack;
import org.momento.Features.ShieldFeature;
import org.momento.Momento;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GiveCommandTabCompleter implements TabCompleter
{

    public GiveCommandTabCompleter(Momento plugin) {
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        switch (args.length) {
            case 1:
                completions.add("give");
                break;
            case 2:
            {
                Map<String, ItemStack> shieldList = ShieldFeature.shieldList;

                completions.addAll(shieldList.keySet());
                break;
            }
        }

        return completions;
    }
}
