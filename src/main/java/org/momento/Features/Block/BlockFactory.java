package org.momento.Features.Block;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.momento.Data.BlockComponentRegistry;
import org.momento.Momento;

public class BlockFactory {
    public Map<String, Block> blocksList;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public BlockFactory() 
    {
        populateBlocks();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    protected Block getBlockFromConfig(String blockName, ConfigurationSection config) {
        ConfigurationSection blockData = config.getConfigurationSection(blockName.replace(" ", "_").toLowerCase());
        if (blockData == null) return null;

        List<BlockComponent> blockComponents = new ArrayList<>();

        for (String identifier : blockData.getKeys(false))
        {
            ConfigurationSection componentSection = blockData.getConfigurationSection(identifier);
            if (componentSection == null) continue;

            Class<? extends BlockComponent> componentClass = BlockComponentRegistry.registry().get(identifier);
            if (componentClass == null) continue;

            try {
                BlockComponent component = componentClass.getDeclaredConstructor().newInstance();
                component.param(componentSection);
                blockComponents.add(component);
            } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return new Block(blockName, blockComponents);
    }

    protected void populateBlocks()
    {
        blocksList = new HashMap<>();
        ConfigurationSection blConfig = Momento.config.getConfigurationSection("blocks");
        if (blConfig == null) return;

        for (String identifier : blConfig.getKeys(false)) {
            Block block = getBlockFromConfig(identifier, blConfig);

            if (block != null) {
                blocksList.put(identifier, block);
            }
        }
    }
}
