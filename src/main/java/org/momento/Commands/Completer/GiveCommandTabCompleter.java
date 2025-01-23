package org.momento.Commands.Completer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.momento.Features.Item.Implements.ItemFactory;
import org.momento.Features.Block.BlockFactory;
import org.momento.Momento;

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
                completions.add("setblock");
                break;
            case 2:
            {
                switch(args[0])
                {
                    case "give":
                        completions.addAll(ItemFactory.itemsList.keySet());
                        break;
                    case "setblock":
                        completions.addAll(BlockFactory.blocksList.keySet());
                        break;
                }
                break;
            }
        }

        return completions;
    }
}
