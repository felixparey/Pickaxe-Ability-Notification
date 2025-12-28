# Pickaxe Ability Notification Mod

A Fabric mod for Minecraft that displays notifications when mining abilities become available.

## Features

✨ **Visual Notifications** - Large title display when pickaxe abilities are ready
🔊 **Audio Alerts** - Plays a note block pling sound
🎨 **Customizable Colors** - Choose from 16 Minecraft colors
⚙️ **Toggle On/Off** - Enable or disable notifications as needed
🎮 **Client-Side** - Works on any server, no server-side installation required

## Supported Minecraft Versions

- 1.21.5
- 1.21.8
- 1.21.10
- 1.21.11

## Commands

The mod includes powerful commands for customization:

### Toggle Notifications
```
/panotification <enabled|disabled>
```

### Change Color
```
/panotification color <color>
```

**Available colors**: black, dark_blue, dark_green, dark_aqua, dark_red, dark_purple, gold (default), gray, dark_gray, blue, green, aqua, red, light_purple, yellow, white

**Settings persist across game restarts** - stored in `config/pickaxeabilitynotification.properties`

📖 **Full Command Documentation**: See [COMMAND_GUIDE.md](COMMAND_GUIDE.md)

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for your Minecraft version
2. Download [Fabric API](https://modrinth.com/mod/fabric-api)
3. Download the appropriate mod jar for your Minecraft version
4. Place both jars in your `.minecraft/mods` folder
5. Launch Minecraft with the Fabric profile

## Building from Source

### Prerequisites
- Java 21 or higher
- Gradle (included via wrapper)

### Build Commands
```powershell
# Build all versions
.\gradlew.bat clean build buildAllVersions buildAllDebug
```

**Output locations**:
- Production builds: `build/libs/versions/`
- Debug builds (with Fabric API): `build/libs/debug/`

📖 **Build Documentation**: See [BUILD_GUIDE.md](BUILD_GUIDE.md)

## Detected Mining Abilities

The mod automatically detects these abilities:
- Pickobulus
- Maniac Miner
- Sheer Force
- Gemstone Infusion
- Tunnel Vision
- Mining Speed Boost

## Technical Details

- **Mod Type**: Client-side Fabric mod
- **Dependencies**: Fabric API (required)
- **Language**: Java 21
- **Build System**: Gradle with Fabric Loom

## Documentation

- 📘 [Command Guide](COMMAND_GUIDE.md) - Complete command reference
- 🔧 [Build Guide](BUILD_GUIDE.md) - How to build the mod
- 🐛 [Crash Fix Summary](CRASH_FIX_SUMMARY.md) - 1.21.11 compatibility fix
- 🧪 [Testing Guide](TESTING_GUIDE.md) - How to test the mod

## License

This template is available under the CC0 license. Feel free to learn from it and incorporate it in your own projects.
