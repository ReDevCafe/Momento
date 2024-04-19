# Momento resource pack

![](https://github.com/MignonPetitXelow/Momento/blob/main/.assets/exa1.png)

## Configuration:

in plugin config.yml:
```yml
override:
    ":discord_emoji:": "\uE002" # replace with ur unicode symbole
```
### Texture pack
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
        }
    ]
}
```
```bash
${texturepack_folder}/assets/minecraft/textures/font/custom/emojis/
```
![](https://github.com/MignonPetitXelow/Momento/blob/main/.assets/exa2.png)