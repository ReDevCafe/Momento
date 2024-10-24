package org.momento.Features.Item;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.momento.Momento;

public final class ItemFile {
    public final HashMap<String, Item> items;
    /*
    Coucou Ame du future, je sais que tu lis ca en ayant oublier cet notes,
    MAIS Tu es debile. si tu viens pour voir pourquoi ca put pas dans la list juste au dessus les items qui sont give /craft,
    C'EST TRES SIMPLE, tu as juste oublier de register l'item bouffonne. Sur-ce gros bisous <333 
    */

    public ItemFile() {
        HashMap<String, Item> i = loadItems();

        if (i == null)
            items = new HashMap<>();

        else items = i;
    }

    public void saveItems() {
        try {
            File dataFolder = Momento.plugin.getDataFolder();
            if (!dataFolder.exists()) {
                System.out.println("Data folder does not exist!");
                dataFolder.mkdirs();
            }

            File file = new File(dataFolder, "items.bin");
            if (!file.exists()) {
                file.createNewFile();
            }

            try (   
                    FileOutputStream fileOutputStream = new FileOutputStream(file); 
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
                ) {
                objectOutputStream.writeObject(items);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, Item> loadItems() {
        HashMap<String, Item> hashMap = null;
        try {
            File dataFolder = Momento.plugin.getDataFolder();
            if (!dataFolder.exists()) {
                System.out.println("Data folder does not exist!");
                return null;
            }

            File file = new File(dataFolder, "items.bin");
            if (!file.exists())
                file.createNewFile();
            

            try (FileInputStream fileInputStream = new FileInputStream(file); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                hashMap = (HashMap<String, Item>) objectInputStream.readObject();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return hashMap;
    }
}
