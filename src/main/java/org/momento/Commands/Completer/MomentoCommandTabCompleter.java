package org.momento.Commands.Completer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.momento.Momento;

public class MomentoCommandTabCompleter implements TabCompleter
{

    public MomentoCommandTabCompleter(Momento plugin) {
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        switch (args.length) {
            case 1:
                completions.addAll(Arrays.asList(
                "give",
                 "setblock", 
                 "debug"
                ));

                break;
            case 2:
            {
                switch(args[0])
                {
                    case "give":
                        completions.addAll(Momento.factory_item.itemsList.keySet());
                        break;
                    case "setblock":
                        completions.addAll(Momento.factory_block.blocksList.keySet());
                        break;
                    case "debug":
                        completions.addAll(Arrays.asList(
                            // TODO: do a debuger feature
                            "list_b",
                            "list_i"
                        ));
                        break;
                }
                break;
            }
        }

        return completions;
    }
}
