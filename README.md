# Momento
Momento is a tool for servers that helps create items, emotes, and stickers (yes, really). For certain aspects, like items, it uses an Entity Component System (ECS).

![](https://github.com/ReDevCafe/Momento/blob/main/.assets/exa1.png?raw=true)
![](https://github.com/ReDevCafe/Momento/blob/main/.assets/exa3.png?raw=true)

## Configuration:

in plugin config.yml:
```yaml
chat:                           # Configuration for emojis and stickers
  emoji:                        # Emoji settings
    ":discord_emoji:": "\uE002" # Replace with your Unicode symbol
    
  sticker:                      # Sticker settings
    discord_sticker: "\uE102"   # Replace with your Unicode symbol

items:                          # Configuration for items
  item_name:                  
    ItemStack:                  # Definition of the item stack
      name: "diamond shield"    # The display name of the item
      material: "SHIELD"        # The initial material type
      model-data: 2             # Model data for the texture pack
    Durability:                 # Durability settings
      value: 10000              # Maximum durability of the item
    WorkbenchCraft:             # Crafting table configuration
      id: "diamond_shield"      # Unique identifier for the item
      shape:                    # Crafting shape layout
        - " A "               
        - "BCB"
        - " A "
      ingredients:              # Ingredients required for crafting
        A: "DIAMOND"  
        B: "COAL"
        C: "SHIELD"
```

## Texture pack side

### Emotis and stickers:
Use a additional texture pack to replace Unicode emojis by images <br>
```bash
${texturepack_folder}/assets/minecraft/font/include/default.json
```
```json
{
    "providers":[
        {
            "type": "bitmap",
            "file": "minecraft:font/custom/emojis/discord_emoji.png",
            "ascent": 7,
            "height": 8,
            "chars": ["\uE002"]
        },
        {
          "type": "bitmap",
          "file": "minecraft:font/custom/sticker/discord_sticker.png",
          "ascent": 7,
          "height": 8,
          "chars": ["\uE102"]
        }
    ]
}
```
```bash
${texturepack_folder}/assets/minecraft/textures/font/custom/emojis/
```
![](https://github.com/ReDevCafe/Momento/blob/main/.assets/exa2.png?raw=true)

### Shields:
It's a bit challenging, but you'll need to replace the shield override with a predicate check for the custom model data ('modelData' value in the Momento config) within:
```bash
${texturepack_foldeer}/assets/minecraft/models/item/shield.json
```
```json lines
"overrides": [ 
    {
        "predicate": {
        "custom_model_data": 1
        },
      "model": "item/shield_name"
    },
        {
        "predicate": {
        "blocking": 1
        },
        "model": "item/shield_blocking"
    },
    {
        "predicate": {
        "blocking": 1,
        "custom_model_data": 1
        },
        "model": "item/shield_name_blocking" // It's really important to have two separate models
                                             // One for blocking and another for normal use
    }
]
```
## ⚠️ Take a look on momento texture pack shield for the models 
