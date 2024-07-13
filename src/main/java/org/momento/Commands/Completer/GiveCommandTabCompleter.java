package org.momento.Commands.Completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.momento.Features.Item.Implements.ItemFactory;
import org.momento.Momento;

import java.util.ArrayList;
import java.util.List;

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
                completions.addAll(ItemFactory.itemsList.keySet());
                break;
            }
        }

        return completions;
    }
}
