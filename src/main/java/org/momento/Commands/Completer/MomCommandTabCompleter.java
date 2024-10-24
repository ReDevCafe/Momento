package org.momento.Commands.Completer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.momento.Data.ComponentRegistry;
import org.momento.Features.Item.Implements.ItemFactory;
import org.momento.Momento;

public class MomCommandTabCompleter implements TabCompleter
{

    public MomCommandTabCompleter(Momento plugin) {
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        switch (args.length) {
            case 1:
                completions.addAll(Arrays.asList("give", "compare", "get", "binaries"));
                break;
            case 2:
            {
                switch(args[0])
                {
                    case "give":
                    case "compare":
                        completions.addAll(ItemFactory.itemsList.keySet());
                        break;

                    case "get":
                        completions.addAll(ComponentRegistry.registry().keySet());
                        break;

                    case "binaries":
                        completions.addAll(Arrays.asList("item"));
                        break;
                }
                break;
            }
        }

        return completions;
    }
}
