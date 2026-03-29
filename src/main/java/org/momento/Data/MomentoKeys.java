package org.momento.Data;

import org.bukkit.NamespacedKey;
import org.momento.Momento;

public class MomentoKeys
{
    public static final NamespacedKey SIGNATURE = new NamespacedKey(Momento.plugin, "uuid");
    public static final NamespacedKey PID = new NamespacedKey(Momento.plugin, "momento");
}
