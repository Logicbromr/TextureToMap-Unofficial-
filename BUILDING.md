# Building TextureToMap

## Prerequisites

- Java 25 or higher
- Gradle (included with `./gradlew`)
- Git

## Build Methods

### Method 1: GitHub Actions (Recommended)

1. Push your code to GitHub
2. Go to **Actions** tab
3. Click **Build TextureToMap**
4. Click **Run workflow**
5. Wait for the build to complete
6. Download the `texturetomap-26.2` artifact

### Method 2: Build Locally

```bash
# Clone the repository
git clone https://github.com/Logicbromr/TextureToMap-Unofficial-.git
cd TextureToMap-Unofficial-

# Build the mod
./gradlew build

# Built JAR will be in: build/libs/texturetomap-2.1.0-26.2.jar
```

### Method 3: Setup Development Environment

```bash
# Generate run configurations for development
./gradlew genSources
./gradlew ideaModule  # For IntelliJ IDEA

# Run in development
./gradlew runClient
```

## Project Structure

```
src/
├── main/
│   ├── java/com/stev01/texturetomap/
│   │   ├── TextureToMap.java          (Main mod class)
│   │   └── [Shared code]
│   └── resources/
│       ├── fabric.mod.json            (Mod metadata)
│       └── assets/texturetomap/
│
└── client/
    ├── java/com/stev01/texturetomap/client/
    │   ├── TextureToMapClient.java    (Client setup)
    │   ├── gui/BlockSelectorScreen.java (GUI)
    │   └── TextureConverter.java      (Conversion logic)
    └── resources/
        └── assets/texturetomap/lang/
            └── en_us.json            (Language file)
```

## Common Issues

### Gradle not found
```bash
chmod +x ./gradlew
./gradlew --version
```

### Java version mismatch
- Install Java 25: `java -version` should show 25.x

### Build fails
```bash
./gradlew clean build --stacktrace
```

## After Building

The compiled JAR file is located at:
```
build/libs/texturetomap-2.1.0-26.2.jar
```

Place this in your `.minecraft/mods/` folder to use the mod.
