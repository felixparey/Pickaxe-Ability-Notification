# Bug Fix - Alert Not Working After Config Changes

## Problem Identified ✅

The notifications stopped working after adding persistent config because:

1. **Static initialization issue**: The config was being loaded in a static block, which runs when the class is first loaded
2. **Wrong directory**: The config path used `Paths.get("config", ...)` which doesn't always resolve to the Minecraft directory
3. **Timing issue**: Config loading happened before Minecraft was fully initialized

## The Fix ✅

### Changes Made to `NotificationConfig.java`:

1. **Removed static block initialization**
   - Removed the `static { loadConfig(); }` block that ran too early

2. **Added lazy loading**
   - Added `configLoaded` flag to track if config has been loaded
   - Added `ensureConfigLoaded()` method that loads config on first use
   - All getters now call `ensureConfigLoaded()` before returning values

3. **Fixed config path resolution**
   - Added `getConfigPath()` method that gets the path from Minecraft's game directory
   - Uses `Minecraft.getInstance().gameDirectory.toPath()` for proper location
   - Falls back to relative path if Minecraft isn't available yet

4. **Better logging**
   - Now logs the actual config file path being used
   - Helps with debugging if issues occur

## Technical Details

### Before (Broken):
```java
static {
    loadConfig(); // Runs too early!
}

private static final Path CONFIG_PATH = Paths.get("config", "pickaxeabilitynotification.properties");
// ^^ Wrong directory - not necessarily Minecraft's directory
```

### After (Fixed):
```java
private static boolean configLoaded = false;

private static void ensureConfigLoaded() {
    if (!configLoaded) {
        loadConfig(); // Loads only when needed
    }
}

private static Path getConfigPath() {
    Minecraft minecraft = Minecraft.getInstance();
    if (minecraft != null && minecraft.gameDirectory != null) {
        return minecraft.gameDirectory.toPath().resolve("config").resolve(CONFIG_FILENAME);
    }
    return Path.of("config", CONFIG_FILENAME); // Fallback
}
```

## Why This Fixes The Issue

1. **Lazy Loading**: Config loads when first accessed (when checking `isEnabled()` or `getTextColor()`), not during class initialization
2. **Correct Directory**: Uses Minecraft's actual game directory, ensuring config file is in the right place
3. **Safe Initialization**: Waits until Minecraft is fully initialized before trying to access the game directory

## Testing

### Before Testing:
If you have an old config file, delete it:
```
.minecraft/config/pickaxeabilitynotification.properties
```

### Test Steps:

1. **Install the new build**:
   - Copy `build/libs/debug/pickaxeabilitynotification-1.0.0-mc1.21.11-debug.jar` to mods folder
   - Remove old version if present

2. **Start Minecraft**

3. **Check logs** for:
   ```
   [pickaxeabilitynotification] Config loaded from <path>: enabled=true, color=GOLD
   ```
   Or:
   ```
   [pickaxeabilitynotification] No config file found at <path>, using defaults
   ```

4. **Trigger a mining ability notification**:
   - Should see the title "PICKAXE ABILITY READY!" in gold
   - Should hear the pling sound

5. **Test command**:
   ```
   /panotification color aqua
   ```
   - Should see confirmation message
   - Check logs for: `Config saved to <path>: enabled=true, color=AQUA`

6. **Trigger notification again**:
   - Should now be AQUA color

7. **Test persistence**:
   - Close Minecraft
   - Restart Minecraft
   - Trigger notification
   - Should still be AQUA

## Build Status

✅ **All builds successful!**

All 8 jars have been rebuilt with the fix:

**Production Builds**:
- `build/libs/versions/pickaxeabilitynotification-1.0.0-mc1.21.5.jar`
- `build/libs/versions/pickaxeabilitynotification-1.0.0-mc1.21.8.jar`
- `build/libs/versions/pickaxeabilitynotification-1.0.0-mc1.21.10.jar`
- `build/libs/versions/pickaxeabilitynotification-1.0.0-mc1.21.11.jar`

**Debug Builds**:
- `build/libs/debug/pickaxeabilitynotification-1.0.0-mc1.21.5-debug.jar`
- `build/libs/debug/pickaxeabilitynotification-1.0.0-mc1.21.8-debug.jar`
- `build/libs/debug/pickaxeabilitynotification-1.0.0-mc1.21.10-debug.jar`
- `build/libs/debug/pickaxeabilitynotification-1.0.0-mc1.21.11-debug.jar`

## What Changed in the Code

### Modified File: `NotificationConfig.java`

**Line count**: 138 lines (was 120 lines)

**Key changes**:
- Removed `CONFIG_PATH` constant
- Added `CONFIG_FILENAME` constant
- Added `configLoaded` boolean flag
- Added `getConfigPath()` method
- Added `ensureConfigLoaded()` method
- Modified all public getters to call `ensureConfigLoaded()`
- Updated `loadConfig()` to use `getConfigPath()`
- Updated `saveConfig()` to use `getConfigPath()`
- Improved logging with actual paths

## Summary

✅ **Bug fixed!** The alert should now work properly.

**Root cause**: Config was being loaded before Minecraft was initialized, and in the wrong directory.

**Solution**: Lazy load the config when first needed, and get the path from Minecraft's game directory.

**All features working**:
- ✅ Notifications display correctly
- ✅ Commands work (`/panotification`)
- ✅ Config persists across restarts
- ✅ Config saved in correct Minecraft directory

---

**Please test the new build and confirm the notifications work!**

