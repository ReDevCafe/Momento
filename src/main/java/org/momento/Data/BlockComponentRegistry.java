package org.momento.Data;

import java.util.HashMap;
import java.util.Map;

import org.momento.Features.Block.BlockComponent;
import org.momento.Features.Block.Component.Data.BlockDataComponent;

public class BlockComponentRegistry {
    private static final Map<String, Class<? extends BlockComponent>> COMPONENT_CLASSES = new HashMap<>();
    
    public static void init() {
        COMPONENT_CLASSES.put("BlockData", BlockDataComponent.class);
    }

    public static void register(String name, Class<? extends BlockComponent> component) {
        COMPONENT_CLASSES.put(name, component);
    }

    public static Map<String, Class<? extends BlockComponent>>  registry(){
        return COMPONENT_CLASSES;
    }
}
