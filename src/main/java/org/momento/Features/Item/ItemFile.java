package org.momento.Features.Item;

import org.momento.Momento;

import java.io.*;
import java.util.HashMap;

public class ItemFile
{
    public final HashMap<String, Item> items;

    public ItemFile()
    {
        HashMap<String, Item> i = loadItems();

        if(i == null)
            items = new HashMap<>();

        else items = i;
    }

    public void saveItems()
    {
        try
        {
            File dataFolder = Momento.plugin.getDataFolder();
            File file = new File(dataFolder, "items.bin");

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(items);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public HashMap<String, Item> loadItems() {
        HashMap<String, Item> hashMap = null;
        try {
            File dataFolder = Momento.plugin.getDataFolder();
            if (!dataFolder.exists()) {
                System.out.println("Data folder does not exist!");
                return null;
            }

            File file = new File(dataFolder, "items.bin");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            hashMap = (HashMap<String, Item>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            System.out.println("HashMap has been deserialized from " + file.getAbsolutePath());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return hashMap;
    }

    public void signItem(String uuid, Item item)
    {
        items.put(uuid, item);

        // DEBUG
        System.out.println(items);
    }

    public Item getItem(String uuid)
    {
        return items.get(uuid);
    }
}
