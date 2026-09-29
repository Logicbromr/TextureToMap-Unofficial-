# TextureToMap — Minecraft 26.2 / Fabric

Clean-room rebuild targeting Minecraft 26.2, Fabric Loader 0.19.3, Java 25 and Fabric API 0.158.0+26.2.

## GitHub one-click build

1. Create a GitHub repository.
2. Upload this project's files.
3. Open **Actions**.
4. Choose **Build TextureToMap**.
5. Press **Run workflow**.
6. Download the `texturetomap-26.2` artifact from the completed run.

The workflow uses Java 25 and Gradle 9.5.1, matching Fabric's published 26.2 development guidance. Fabric's 26.2 toolchain is non-remapping and uses `net.fabricmc.fabric-loom`. See the official Fabric documentation for current toolchain details.

## Current command

On a client, run:

`/texturemap minecraft:block/stone`

The client reads the texture from its active resource manager, downsamples it to 128x128, quantizes it to a map palette, and sends the pixels to the logical server. The server creates a filled map and writes the pixel buffer through a small reflective compatibility adapter.

This rebuild intentionally does not copy or decompile the original JAR's implementation.
