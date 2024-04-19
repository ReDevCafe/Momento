package org.momento.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.momento.Features.EmojiSystem;


public class ChatSystem implements Listener
{
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) 
    {
        String message = event.getMessage();
        event.setMessage(EmojiSystem.EmojiStringReplacer(message));
    }
}
