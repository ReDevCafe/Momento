# Momento

![](https://github.com/MignonPetitXelow/Momento/blob/main/.assets/exa1.png)

## Configuration:

in plugin config.yml:
```yml
chat:
  "emoji":
    ":discord_emoji:": "\uE002" # replace with ur unicode symbole
    
  "sticker":                    
    "discord_sticker": "\uE102" # replace with ur unicode symbole
```

### Texture pack side
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