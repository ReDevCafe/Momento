package org.momento.Features;

import org.momento.Momento;
import java.util.Map;

public class EmojiSystem {
    public static String EmojiStringReplacer(String message) {
        Map<String, Object> overrideMap = Momento.config.getConfigurationSection("override").getValues(false);

        for (Map.Entry<String, Object> entry : overrideMap.entrySet()) 
        {
            String word = entry.getKey();
            String replacement = entry.getValue().toString();
            if (message.contains(word))
                message = message.replaceAll("(?i)" + word, replacement);
        }
        return message;
    }
}
