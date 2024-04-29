package org.momento.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.momento.Features.EmojiSystem;

public class SignEvent implements Listener {
    
    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        String[] lines = event.getLines();
        if(lines.length == 0) return;
        
        for(int i = 0; i < lines.length; i++)
            event.setLine(i, EmojiSystem.StringReplacer(lines[i], "emoji"));
    }
}
