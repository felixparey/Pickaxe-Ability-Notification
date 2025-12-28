# Command Guide - Pickaxe Ability Notification

## Overview
The mod now includes client-side commands to customize your notification experience!

## Commands

### 1. Toggle Notifications On/Off
```
/panotification <enabled|disabled>
```

**Examples:**
- `/panotification enabled` - Turn notifications ON
- `/panotification disabled` - Turn notifications OFF

**Features:**
- ✅ Auto-completion suggests "enabled" and "disabled"
- ✅ Immediate feedback when toggled
- ✅ **Settings persist across game restarts**

**When to use:**
- Disable during non-mining activities
- Enable when mining in crystal hollows
- Toggle off for less visual clutter

---

### 2. Change Notification Color
```
/panotification color <color>
```

**Examples:**
- `/panotification color gold` (default)
- `/panotification color red`
- `/panotification color green`
- `/panotification color aqua`

**Available Colors:**
The command auto-completes with all 16 Minecraft colors:

| Color | Example Use |
|-------|-------------|
| `black` | `/panotification color black` |
| `dark_blue` | `/panotification color dark_blue` |
| `dark_green` | `/panotification color dark_green` |
| `dark_aqua` | `/panotification color dark_aqua` |
| `dark_red` | `/panotification color dark_red` |
| `dark_purple` | `/panotification color dark_purple` |
| `gold` | `/panotification color gold` ⭐ (Default) |
| `gray` | `/panotification color gray` |
| `dark_gray` | `/panotification color dark_gray` |
| `blue` | `/panotification color blue` |
| `green` | `/panotification color green` |
| `aqua` | `/panotification color aqua` |
| `red` | `/panotification color red` |
| `light_purple` | `/panotification color light_purple` |
| `yellow` | `/panotification color yellow` |
| `white` | `/panotification color white` |

**Features:**
- ✅ Auto-completion shows all available colors
- ✅ Case-insensitive (type `GOLD`, `gold`, or `Gold`)
- ✅ Color changes apply immediately to the next notification
- ✅ **Settings persist across game restarts**

**Tips:**
- Choose contrasting colors for better visibility
- Bright colors (yellow, aqua, green) work well in dark areas
- Dark colors (dark_red, dark_purple) may be less visible

---

## Usage Tips

### Quick Setup for Mining Sessions
1. Start game and join server
2. Set your preferred color: `/panotification color aqua`
3. Mine away! Notifications will appear when abilities are ready

### Disable During Boss Fights
```
/panotification disabled
```
Re-enable after: `/panotification enabled`

### Test Your Settings
1. Set a color: `/panotification color yellow`
2. Wait for any mining ability to become available
3. Check if the color looks good
4. Adjust if needed: `/panotification color red`

---

## Auto-Completion in Action

When you type `/panotification` and press TAB or SPACE:
- You'll see: `enabled` and `disabled` suggestions
- Also see: `color` for the color sub-command

When you type `/panotification color` and press SPACE:
- You'll see all 16 color options
- Tab through them or type the first few letters

---

## Technical Details

### Command Type
- These are **client-side commands** (Fabric Client Commands)
- They only affect YOUR game, not other players
- No server permissions required
- Work on any server with Fabric API

### Settings Persistence
- Settings are stored in `config/pickaxeabilitynotification.properties`
- **Settings persist across game restarts**
- Config file is automatically created on first launch
- Changes are saved immediately when you update settings

### Default Settings
- **Enabled**: `true` (notifications ON by default)
- **Color**: `gold` (classic Minecraft gold color)

---

## Troubleshooting

### Command Not Found
- Make sure Fabric API is installed
- Verify the mod jar is in your mods folder
- Check that Fabric Loader is properly installed

### Color Not Changing
- Make sure you spelled the color correctly
- Use underscores for multi-word colors (e.g., `dark_blue`, not `dark blue`)
- Check the feedback message confirms the color was set

### Notifications Still Appearing When Disabled
- Make sure you typed `disabled` not `disable`
- Check the feedback message says "DISABLED"
- Try toggling: `/pickaxeabilitynotification disabled` again

---

## Future Features (Planned)
- [ ] Persistent config file for settings
- [ ] Custom notification messages
- [ ] Sound volume control
- [ ] Title duration customization
- [ ] Multiple notification styles

---

## Examples in Practice

### Example 1: Start of Mining Session
```
Player: /panotification color aqua
System: ✓ Notification color set to AQUA

[Later, when ability is ready]
Title appears: "PICKAXE ABILITY READY!" (in aqua color)
```

### Example 2: Disable During Event
```
Player: /panotification disabled
System: ✗ Pickaxe Ability Notifications DISABLED

[Mining ability becomes available - no notification shown]

Player: /panotification enabled
System: ✓ Pickaxe Ability Notifications ENABLED
```

### Example 3: Finding Your Favorite Color
```
Player: /panotification color yellow
System: ✓ Notification color set to YELLOW

[Tests in game... too bright]

Player: /panotification color gold
System: ✓ Notification color set to GOLD

[Perfect!]
```

---

## Command Summary Cheat Sheet

| Command | Purpose | Values |
|---------|---------|--------|
| `/panotification <toggle>` | Enable/disable notifications | `enabled`, `disabled` |
| `/panotification color <color>` | Change text color | 16 Minecraft colors |

**Tip**: Use TAB for auto-completion on all commands!

