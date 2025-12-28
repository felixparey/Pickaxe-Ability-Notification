# 🚀 Quick Reference Card

## Build Commands

### Production Builds (Distribute These!)
```powershell
.\gradlew buildAllVersions
```
**Output:** `build/libs/versions/` (4 jars, ~11 KB each)  
**Contains:** Your mod code only  
**Requires:** Users must have Fabric API installed

---

### Debug Builds (Testing Only!)
```powershell
.\gradlew buildAllDebug
```
**Output:** `build/libs/debug/` (4 jars, ~95 MB each)  
**Contains:** Your mod + all dependencies  
**Requires:** Nothing, standalone

---

### Single Debug Jar (Quick Testing)
```powershell
.\gradlew jarDebug
```
**Output:** `build/libs/pickaxeabilitynotification-1.0.0-debug.jar`  
**Size:** ~95 MB with all dependencies

---

### Regular Build (Development)
```powershell
.\gradlew build
```
**Output:** `build/libs/pickaxeabilitynotification-1.0.0.jar`  
**Size:** ~11 KB

---

## Supported Versions

| Version  | Fabric Loader | Fabric API          |
|----------|---------------|---------------------|
| 1.21.5   | 0.16.10      | 0.139.4+1.21.5     |
| 1.21.8   | 0.16.10      | 0.139.4+1.21.8     |
| 1.21.10  | 0.16.10      | 0.139.4+1.21.10    |
| 1.21.11  | 0.16.10      | 0.139.4+1.21.11    |

---

## File Sizes

- **Production jar:** ~11 KB (Fabric API excluded)
- **Debug jar:** ~95 MB (Fabric API included)
- **Sources jar:** ~6 KB

---

## Distribution Checklist

✅ Run `.\gradlew buildAllVersions`  
✅ Jars are in `build/libs/versions/`  
✅ Upload to CurseForge/Modrinth  
✅ Mark **Fabric API as required dependency**  
✅ Test with Fabric API in mods folder  

---

## Important Notes

⚠️ **Production jars need Fabric API!**  
Users must download Fabric API separately.

✅ **Debug jars are standalone!**  
Perfect for testing without setup.

🚫 **Don't distribute debug jars!**  
They're 10x larger than production jars.

---

## Troubleshooting

**Build fails:**
```powershell
.\gradlew clean build --refresh-dependencies
```

**Need to clean:**
```powershell
.\gradlew clean
```

**See all tasks:**
```powershell
.\gradlew tasks --group=build
```

---

## Adding New Versions

Edit `build.gradle`:
```groovy
def mcVersions = [
    '1.21.5': [fabricApi: 'VERSION', loader: 'VERSION'],
    // ... add here
]
```

---

## Available Custom Tasks

- `buildAllVersions` - Production jars for all versions
- `buildAllDebug` - Debug jars for all versions  
- `jarDebug` - Single debug jar with dependencies

---

**See BUILD_GUIDE.md for detailed documentation**

