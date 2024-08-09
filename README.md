# Momento

![](https://github.com/MignonPetitXelow/Momento/blob/main/.assets/exa1.png)

## Configuration:

in plugin config.yml:
```yml
chat:                           # for ur emojis and stickers:
  "emoji":
    ":discord_emoji:": "\uE002" # replace with ur unicode symbole
    
  "sticker":                    
    "discord_sticker": "\uE102" # replace with ur unicode symbole

items:
  dirt_shield:
    ItemStack:
      name: "dirt shield"
      material: "SHIELD"
      model-data: 1
    Durability:
      durability: 10000
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
![](https://github.com/MignonPetitXelow/Momento/blob/main/.assets/exa2.png)

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
