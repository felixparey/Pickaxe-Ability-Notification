# Feature Update - Commands Added! 🎮

## What's New

Your Pickaxe Ability Notification mod now includes **two powerful commands** for customization with **persistent settings**!

---

## Command 1: Toggle Notifications

**Command**: `/panotification <enabled|disabled>`

**Purpose**: Turn notifications on or off without removing the mod

**Features**:
- ✅ Auto-completion suggests `enabled` and `disabled`
- ✅ Instant feedback in chat
- ✅ Perfect for disabling during boss fights or non-mining activities
- ✅ **Settings persist across game restarts**

**Examples**:
```
/panotification enabled   → Notifications ON
/panotification disabled  → Notifications OFF
```

---

## Command 2: Change Text Color

**Command**: `/panotification color <color>`

**Purpose**: Customize the title text color to your preference

**Features**:
- ✅ Auto-completion shows all 16 Minecraft colors
- ✅ Case-insensitive (type `gold`, `GOLD`, or `Gold`)
- ✅ Changes apply immediately
- ✅ Choose colors that match your texture pack or preference
- ✅ **Settings persist across game restarts**

**Available Colors**:
```
black, dark_blue, dark_green, dark_aqua, dark_red, dark_purple,
gold (default), gray, dark_gray, blue, green, aqua, red, 
light_purple, yellow, white
```

**Examples**:
```
/panotification color gold     → Classic gold (default)
/panotification color aqua     → Bright aqua
/panotification color red      → Bold red
/panotification color yellow   → Bright yellow
```

---

## How the Commands Work

### Auto-Completion
When you start typing the command, press **TAB** or **SPACE** to see suggestions:

1. Type `/panotification` → Shows: `enabled`, `disabled`, `color`
2. Type `/panotification color ` → Shows: all 16 colors

### Real-Time Feedback
Every command gives you immediate confirmation:
- **Enable**: `✓ Pickaxe Ability Notifications ENABLED` (green)
- **Disable**: `✗ Pickaxe Ability Notifications DISABLED` (red)
- **Color**: `✓ Notification color set to AQUA` (shows the color)

---

## Files Created

### New Java Files
1. **NotificationConfig.java** - Stores enabled/disabled state and color preference
2. **NotificationCommand.java** - Handles command registration and execution

### Updated Files
1. **PickaxeAbilityNotificationClient.java** - Integrated commands and config

### Documentation
1. **COMMAND_GUIDE.md** - Complete command documentation (171 lines)
2. **QUICK_COMMAND_REFERENCE.md** - Quick reference card
3. **README.md** - Updated with command information

---

## Technical Implementation

### Command Type
- **Client-side commands** using Fabric Client Commands API
- No server permissions required
- Works on any server with Fabric installed

### State Management
- Settings stored in `config/pickaxeabilitynotification.properties`
- **Settings persist across game restarts**
- Config file is automatically created on first run
- Changes are saved immediately when you update settings

### Color System
- Uses Minecraft's `ChatFormatting` enum
- All 16 standard Minecraft text colors supported
- Color is applied to the title text dynamically

---

## Testing Your Commands

### Step 1: Load the Mod
Use the debug build for testing:
```
build/libs/debug/pickaxeabilitynotification-1.0.0-mc1.21.11-debug.jar
```

### Step 2: Test Toggle Command
```
/panotification disabled
→ Trigger an ability → Should see NO notification

/panotification enabled
→ Trigger an ability → Should see notification
```

### Step 3: Test Color Command
```
/panotification color aqua
→ Trigger an ability → Title should be AQUA

/panotification color red
→ Trigger an ability → Title should be RED
```

### Step 4: Test Auto-Completion
```
Type: /panotification
Press: TAB
→ Should show: enabled, disabled, color

Type: /panotification color 
Press: TAB
→ Should show: all 16 colors
```

### Step 5: Test Persistence
```
/panotification color aqua
→ Close Minecraft
→ Restart Minecraft
→ Trigger an ability → Title should still be AQUA
```

---

## Build Status

✅ **All builds successful!**

### Production Jars (Fabric API NOT included)
```
build/libs/versions/
├── pickaxeabilitynotification-1.0.0-mc1.21.5.jar
├── pickaxeabilitynotification-1.0.0-mc1.21.8.jar
├── pickaxeabilitynotification-1.0.0-mc1.21.10.jar
└── pickaxeabilitynotification-1.0.0-mc1.21.11.jar
```

### Debug Jars (Fabric API included)
```
build/libs/debug/
├── pickaxeabilitynotification-1.0.0-mc1.21.5-debug.jar
├── pickaxeabilitynotification-1.0.0-mc1.21.8-debug.jar
├── pickaxeabilitynotification-1.0.0-mc1.21.10-debug.jar
└── pickaxeabilitynotification-1.0.0-mc1.21.11-debug.jar
```

---

## What Changed in the Code

### 1. New Config Class (`NotificationConfig.java`)
- Stores `enabled` state (default: `true`)
- Stores `textColor` (default: `GOLD`)
- Provides getters/setters
- Lists all available colors
- **Saves/loads from config/pickaxeabilitynotification.properties**
- **Persistent storage across game restarts**

### 2. New Command Class (`NotificationCommand.java`)
- Registers both commands
- Handles auto-completion
- Validates user input
- Sends feedback messages

### 3. Updated Client Class (`PickaxeAbilityNotificationClient.java`)
- Registers commands on initialization
- Checks `enabled` state before showing notifications
- Uses dynamic color from config
- Creates colored title component

---

## Default Settings

| Setting | Default Value | Description |
|---------|---------------|-------------|
| Enabled | `true` | Notifications are ON by default |
| Color | `GOLD` | Classic Minecraft gold color |

---

## Future Enhancements (Ideas)

- [x] Persistent config file (saves between restarts) ✅ **DONE!**
- [ ] Custom notification text
- [ ] Sound volume control
- [ ] Multiple notification styles
- [ ] Per-ability color customization
- [ ] Title duration settings

---

## Summary

✨ **Commands Added**: 2 new client-side commands
📝 **Files Created**: 2 new Java classes + documentation
🔧 **Files Updated**: 1 main client class
📦 **Builds**: All 4 versions built successfully
🎨 **Color Options**: 16 Minecraft colors
⚙️ **Toggle**: Can enable/disable notifications
📖 **Documentation**: Complete command guide created

---

## Quick Start

### Disable Notifications
```
/panotification disabled
```

### Enable Notifications
```
/panotification enabled
```

### Change to Aqua Color
```
/panotification color aqua
```

### Change to Red Color
```
/panotification color red
```

### Back to Default Gold
```
/panotification color gold
```

---

## Need Help?

- **Full Command Guide**: See `COMMAND_GUIDE.md` (171 lines, detailed examples)
- **Quick Reference**: See `QUICK_COMMAND_REFERENCE.md` (1 page cheat sheet)
- **General Info**: See updated `README.md`

---

**Enjoy your customizable pickaxe notifications!** 🎉

