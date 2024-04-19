package org.momento.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.momento.Momento;
import java.util.List;

public class ChatSystem implements Listener
{
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        List<String> overrideWords = Momento.config.getStringList("override.words");

        for (String word : overrideWords)
            if (message.contains(word))
            {
                String replacement = Momento.config.getString("override.replacements." + word);
                if (replacement != null) message = message.replaceAll("(?i)" + word, replacement);
            }
        event.setMessage(message);
    }
}
