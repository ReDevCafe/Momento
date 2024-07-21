package org.momento.Data;

import java.util.HashMap;
import java.util.Map;

import org.momento.Features.Item.ItemComponent;
import org.momento.Features.Item.Component.Data.DurabilityComponent;
import org.momento.Features.Item.Component.Data.ItemStackComponent;

public class ComponentRegistry {
    private static final Map<String, Class<? extends ItemComponent>> COMPONENT_CLASSES = new HashMap<>();
    
    public static void init() {
        COMPONENT_CLASSES.put("ItemStack", ItemStackComponent.class);
        COMPONENT_CLASSES.put("Durability", DurabilityComponent.class);
    }

    public static void register(String name, Class<? extends ItemComponent> component) {
        COMPONENT_CLASSES.put(name, component);
    }

    public static Map<String, Class<? extends ItemComponent>>  registry(){
        return COMPONENT_CLASSES;
    }
}
