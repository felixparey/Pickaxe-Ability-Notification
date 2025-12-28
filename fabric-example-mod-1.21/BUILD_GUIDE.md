# Pickaxe Ability Notification - Multi-Version Build Guide

## Overview
This mod supports building for Minecraft versions 1.21.5, 1.21.8, 1.21.10, and 1.21.11 with a single command!

## Build Types

### 1. Production Builds (Recommended for distribution)
**What:** Jars WITHOUT Fabric API included  
**Why:** Smaller file size, users must have Fabric API in their mods folder (standard practice)  
**Command:** `.\gradlew buildAllVersions`

Output location: `build/libs/versions/`  
Files:
- `pickaxeabilitynotification-1.0.0-mc1.21.5.jar`
- `pickaxeabilitynotification-1.0.0-mc1.21.8.jar`
- `pickaxeabilitynotification-1.0.0-mc1.21.10.jar`
- `pickaxeabilitynotification-1.0.0-mc1.21.11.jar`

### 2. Debug Builds (For development/testing)
**What:** Jars WITH Fabric API included  
**Why:** Standalone testing without needing to install Fabric API separately  
**Command:** `.\gradlew buildAllDebug`

Output location: `build/libs/debug/`  
Files:
- `pickaxeabilitynotification-1.0.0-mc1.21.5-debug.jar`
- `pickaxeabilitynotification-1.0.0-mc1.21.8-debug.jar`
- `pickaxeabilitynotification-1.0.0-mc1.21.10-debug.jar`
- `pickaxeabilitynotification-1.0.0-mc1.21.11-debug.jar`

## Quick Start

### Build Everything
```powershell
# Build production versions (no Fabric API - recommended for release)
.\gradlew buildAllVersions

# Build debug versions (with Fabric API - for testing)
.\gradlew buildAllDebug
```

### Build for Development (default 1.21.5)
```powershell
.\gradlew build
```

### Build Single Debug Version
```powershell
.\gradlew jarDebug
```

## Dependencies

The build system automatically uses the correct dependencies for each Minecraft version:

| Minecraft | Fabric Loader | Fabric API          |
|-----------|---------------|---------------------|
| 1.21.5    | 0.16.10       | 0.139.4+1.21.5     |
| 1.21.8    | 0.16.10       | 0.139.4+1.21.8     |
| 1.21.10   | 0.16.10       | 0.139.4+1.21.10    |
| 1.21.11   | 0.16.10       | 0.139.4+1.21.11    |
## Important Notes

### For Production Jars
⚠️ **Users MUST have Fabric API installed in their mods folder!**

The production jars are designed to be small and efficient by NOT including external dependencies. This is the standard practice for Fabric mods.

### For Debug Jars
✅ Debug jars are standalone and include all dependencies. Use these for:
- Testing without setting up a full mod environment
- Quick debugging
- Sharing with testers who may not have Fabric API installed

### Compatibility
The `fabric.mod.json` is configured to work with Minecraft `>=1.21 <1.22`, meaning:
- All 1.21.x versions are supported
- The same code works across all versions (thanks to Mojang mappings)
- One build, multiple versions!

## Distribution Recommendations

When releasing your mod:

1. **Upload production jars** (from `build/libs/versions/`) to CurseForge/Modrinth
2. **List Fabric API as a required dependency** in your mod page
3. **Optional:** Keep debug jars for troubleshooting user issues

## Troubleshooting

### "gradlew not recognized"
Use `.\gradlew` on Windows PowerShell, not `./gradlew`

### Build fails
```powershell
.\gradlew clean build --refresh-dependencies
```

### Need to add more versions?
Edit `build.gradle` and add entries to the `mcVersions` map:
```groovy
def mcVersions = [
	'1.21.6': [fabricApi: 'VERSION', loader: 'VERSION'],
	// ... existing versions
]
```

## File Structure After Building

```
build/
├── libs/
│   ├── versions/           # Production jars (distribute these)
│   │   ├── pickaxeabilitynotification-1.0.0-mc1.21.5.jar
│   │   ├── pickaxeabilitynotification-1.0.0-mc1.21.8.jar
│   │   ├── pickaxeabilitynotification-1.0.0-mc1.21.10.jar
│   │   └── pickaxeabilitynotification-1.0.0-mc1.21.11.jar
│   ├── debug/              # Debug jars (testing only)
│   │   ├── pickaxeabilitynotification-1.0.0-mc1.21.5-debug.jar
│   │   ├── pickaxeabilitynotification-1.0.0-mc1.21.8-debug.jar
│   │   ├── pickaxeabilitynotification-1.0.0-mc1.21.10-debug.jar
│   │   └── pickaxeabilitynotification-1.0.0-mc1.21.11-debug.jar
│   └── pickaxeabilitynotification-1.0.0.jar  # Default build (1.21.5)
```

## Questions?

- Production jars = Small, needs Fabric API = **Distribute these**
- Debug jars = Large, includes everything = **Test with these**

