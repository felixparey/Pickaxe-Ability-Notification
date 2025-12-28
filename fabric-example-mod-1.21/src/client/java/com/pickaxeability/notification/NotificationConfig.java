package com.pickaxeability.notification;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class NotificationConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger("pickaxeabilitynotification");
	private static final String CONFIG_FILENAME = "pickaxeabilitynotification.properties";

	private static boolean enabled = true;
	private static ChatFormatting textColor = ChatFormatting.GOLD;
	private static boolean configLoaded = false;

	public static boolean isEnabled() {
		ensureConfigLoaded();
		return enabled;
	}

	public static void setEnabled(boolean enabled) {
		ensureConfigLoaded();
		NotificationConfig.enabled = enabled;
		saveConfig();
	}

	public static ChatFormatting getTextColor() {
		ensureConfigLoaded();
		return textColor;
	}

	public static void setTextColor(ChatFormatting color) {
		ensureConfigLoaded();
		NotificationConfig.textColor = color;
		saveConfig();
	}

	// Available colors for the command
	public static final ChatFormatting[] AVAILABLE_COLORS = {
		ChatFormatting.BLACK,
		ChatFormatting.DARK_BLUE,
		ChatFormatting.DARK_GREEN,
		ChatFormatting.DARK_AQUA,
		ChatFormatting.DARK_RED,
		ChatFormatting.DARK_PURPLE,
		ChatFormatting.GOLD,
		ChatFormatting.GRAY,
		ChatFormatting.DARK_GRAY,
		ChatFormatting.BLUE,
		ChatFormatting.GREEN,
		ChatFormatting.AQUA,
		ChatFormatting.RED,
		ChatFormatting.LIGHT_PURPLE,
		ChatFormatting.YELLOW,
		ChatFormatting.WHITE
	};

	public static ChatFormatting getColorByName(String name) {
		try {
			return ChatFormatting.valueOf(name.toUpperCase().replace(" ", "_"));
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	private static Path getConfigPath() {
		// Get Minecraft's game directory
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft != null && minecraft.gameDirectory != null) {
			return minecraft.gameDirectory.toPath().resolve("config").resolve(CONFIG_FILENAME);
		}
		// Fallback to current directory if Minecraft isn't available yet
		return Path.of("config", CONFIG_FILENAME);
	}

	private static void ensureConfigLoaded() {
		if (!configLoaded) {
			loadConfig();
		}
	}

	private static void loadConfig() {
		try {
			Path configPath = getConfigPath();
			if (Files.exists(configPath)) {
				Properties props = new Properties();
				try (InputStream input = Files.newInputStream(configPath)) {
					props.load(input);

					// Load enabled state
					String enabledStr = props.getProperty("enabled", "true");
					enabled = Boolean.parseBoolean(enabledStr);

					// Load color
					String colorStr = props.getProperty("color", "GOLD");
					ChatFormatting loadedColor = getColorByName(colorStr);
					if (loadedColor != null) {
						textColor = loadedColor;
					}

					LOGGER.info("Config loaded from {}: enabled={}, color={}", configPath, enabled, textColor.name());
				}
			} else {
				LOGGER.info("No config file found at {}, using defaults", configPath);
				saveConfig(); // Create default config
			}
			configLoaded = true;
		} catch (Exception e) {
			LOGGER.error("Failed to load config, using defaults", e);
			configLoaded = true; // Mark as loaded to avoid retry loops
		}
	}

	private static void saveConfig() {
		try {
			Path configPath = getConfigPath();
			// Create config directory if it doesn't exist
			Files.createDirectories(configPath.getParent());

			Properties props = new Properties();
			props.setProperty("enabled", String.valueOf(enabled));
			props.setProperty("color", textColor.name());

			try (OutputStream output = Files.newOutputStream(configPath)) {
				props.store(output, "Pickaxe Ability Notification Config");
			}

			LOGGER.info("Config saved to {}: enabled={}, color={}", configPath, enabled, textColor.name());
		} catch (Exception e) {
			LOGGER.error("Failed to save config", e);
		}
	}
}

