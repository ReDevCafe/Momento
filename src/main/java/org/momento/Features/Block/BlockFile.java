package org.momento.Features.Block;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.bukkit.Location;
import org.momento.Momento;

public class BlockFile {
    public HashMap<Location, Block> blocks;

    /**
     * OKAY LETS REWORK THAT TO OPTIMIZE THIS SHIT:
     * 
     * WE NEED TO STORE THE BLOCK TYPE IN A HASHMAP AND AFTER THIS, STORE IN A HASHMAP THE BLOCK INSTANCE AND THE UUID 
     * LIKE THAT 
     * HashMap<String, <String, Block>> blocks 
     * 
     * 
     * NEVER MIND DO COMPRESSION WHEN SAVING AND DECOMPRESSION WHEN LOADING
     * COULD BE LONGER FOR STARTING AND DISABLING SERVER BUT FUCK IT
     */

    public BlockFile() {
        HashMap<Location, Block> i = loadBlocks();

        if (i == null)
            blocks = new HashMap<>();

        else blocks = i;
    }

    public void saveBlocks() {
        try {
            File dataFolder = Momento.plugin.getDataFolder();
            if (!dataFolder.exists()) {
                System.out.println("Data folder does not exist!");
                dataFolder.mkdirs();
            }

            File file = new File(dataFolder, "blocks.bin");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(blocks);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public HashMap<Location, Block> loadBlocks() {
        HashMap<Location, Block> hashMap = null;
        try {
            File dataFolder = Momento.plugin.getDataFolder();
            if (!dataFolder.exists()) {
                System.out.println("Data folder does not exist!");
                return null;
            }

            File file = new File(dataFolder, "blocks.bin");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            hashMap = (HashMap<Location, Block>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return hashMap;
    }
}
