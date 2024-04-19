# Momento

![](https://github.com/MignonPetitXelow/Momento/blob/main/.assets/exa1.png)

## Configuration:

in plugin config.yml:
```yml
override:
    words:
        ":discord_emoji:"
    replacements:
        ":discord_emoji:": "\uE002" # replace with ur unicode symbole
```

Use a additional texture pack to replace Unicode emojis by images
`${texturepack_folder}/assets/minecraft/font/include/default.json`
```json
{
    "providers":[
        {
            "type": "bitmap",
            "file": "minecraft:font/custom/emojis/discord_emoji.png",
            "ascent": 7,
            "height": 8,
            "chars": ["\uE002"]
        }
    ]
}
```
`${texturepack_folder}/assets/minecraft/textures/font/custom/emojis/`
![](https://github.com/MignonPetitXelow/Momento/blob/main/.assets/exa2.png)