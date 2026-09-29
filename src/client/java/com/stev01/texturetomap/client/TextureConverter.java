package com.stev01.texturetomap.client;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;

import java.awt.image.BufferedImage;

/**
 * Handles texture conversion from block textures to map data
 */
public class TextureConverter {
    private static final int MAP_SIZE = 128;
    private static final int MAP_PALETTE_SIZE = 256;

    /**
     * Convert a block texture to map colors
     */
    public static ItemStack convertBlockToMap(Block block) {
        try {
            // Create a filled map item
            ItemStack mapStack = new ItemStack(Items.FILLED_MAP);
            NbtCompound mapTag = new NbtCompound();
            
            // Generate map data (placeholder)
            byte[] mapData = generateMapData(block);
            
            // Store map data in NBT
            mapTag.putByteArray("data", mapData);
            mapStack.setNbt(mapTag);
            
            return mapStack;
        } catch (Exception e) {
            System.err.println("Error converting texture: " + e.getMessage());
            e.printStackTrace();
            return new ItemStack(Items.AIR);
        }
    }

    /**
     * Generate map data from block texture
     */
    private static byte[] generateMapData(Block block) {
        byte[] mapData = new byte[MAP_SIZE * MAP_SIZE];
        
        // Fill with placeholder pattern (checkered pattern)
        for (int i = 0; i < mapData.length; i++) {
            int x = i % MAP_SIZE;
            int y = i / MAP_SIZE;
            mapData[i] = (byte) (((x / 8) + (y / 8)) % 2 == 0 ? 100 : 50);
        }
        
        return mapData;
    }

    /**
     * Downsample texture to 128x128 map size
     */
    public static BufferedImage downsampleTexture(BufferedImage original) {
        BufferedImage downsampled = new BufferedImage(MAP_SIZE, MAP_SIZE, BufferedImage.TYPE_INT_RGB);
        
        double scaleX = (double) original.getWidth() / MAP_SIZE;
        double scaleY = (double) original.getHeight() / MAP_SIZE;
        
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                int srcX = (int) (x * scaleX);
                int srcY = (int) (y * scaleY);
                
                int rgb = original.getRGB(srcX, srcY);
                downsampled.setRGB(x, y, rgb);
            }
        }
        
        return downsampled;
    }

    /**
     * Quantize RGB colors to Minecraft map palette colors
     */
    public static byte quantizeColor(int rgb) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        
        // Find closest Minecraft map color
        int gray = (r + g + b) / 3;
        
        // Convert to palette index (0-255)
        return (byte) (gray & 0xFF);
    }
}
