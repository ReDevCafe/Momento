package org.momento.Features;

import org.momento.Momento;
import java.util.Map;

public class EmojiSystem {

    public static Map<String, Map<String, Object>> emojiSystemData = new java.util.HashMap<>();

    public static String StringReplacer(String message, String sectionKey) {
        if(message.length() <= 1) return message;
        
        if(!emojiSystemData.containsKey(sectionKey))
            emojiSystemData.put(sectionKey, Momento.config.getConfigurationSection("chat." + sectionKey).getValues(false));

        for (Map.Entry<String, Object> entry : emojiSystemData.get(sectionKey).entrySet()) {
            String word = entry.getKey();
            String replacement = entry.getValue().toString();
            if (message.toLowerCase().contains(word.toLowerCase()))
                message = message.replaceAll("(?i)" + word, "§f" + replacement + "§r");
        }

        return message;
    }

    public static String EmojiAndStickerStringReplacer(String message) {
        String replacedMessage = StringReplacer(message, "emoji");
        
        if(message.equals(replacedMessage))
            replacedMessage = StringReplacer(replacedMessage, "sticker");

        return replacedMessage;
    }

}
