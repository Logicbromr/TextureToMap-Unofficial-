# TextureToMap Usage Guide

## Installation

1. Download the latest JAR from [Releases](https://github.com/Logicbromr/TextureToMap-Unofficial-/releases)
2. Make sure you have **Fabric Loader 0.19.3+** installed
3. Place the JAR in your `.minecraft/mods/` folder
4. Launch Minecraft 26.2 with Fabric

## How to Use

### Opening the GUI

Press **T** to open the Block Selector GUI (default keybinding)

Or use the command:
```
/texturetomap_gui
```

### Selecting a Block

1. **Search** - Type the block name in the search box (e.g., "stone", "dirt", "grass")
2. **Browse** - Scroll through available blocks with mouse wheel
3. **Select** - Click on a block to select it (highlighted in green)
4. **Convert** - Click the "Convert Selected" button to convert the texture to a map

### Map Output

When you convert a block:
- A **Filled Map** item is created with the block's texture
- The map shows a downsampled (128x128) version of the texture
- Colors are quantized to Minecraft's map palette

## Features

✓ GUI-based block selection  
✓ Real-time texture search  
✓ Block texture preview  
✓ Easy-to-use interface  

## Keybindings

- **T** - Open TextureToMap GUI (configurable in controls)

## Commands

- `/texturetomap` - Show mod information
- `/texturetomap_gui` - Open the block selector

## Troubleshooting

**GUI won't open:**
- Check that Fabric Loader is installed
- Verify the mod JAR is in the mods folder
- Check the latest.log for errors

**Blocks not loading:**
- Restart Minecraft
- Update Fabric API to version 0.158.0+26.2

**Map shows wrong texture:**
- This is a placeholder; texture conversion is in development

## Support

For issues, please open a GitHub issue: https://github.com/Logicbromr/TextureToMap-Unofficial-/issues
