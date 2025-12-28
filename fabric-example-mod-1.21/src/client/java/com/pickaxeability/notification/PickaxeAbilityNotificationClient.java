package com.pickaxeability.notification;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PickaxeAbilityNotificationClient implements ClientModInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger("pickaxeabilitynotification");

	// All mining abilities that trigger the notification
	private static final String[] MINING_ABILITIES = {
		"Pickobulus",
		"Maniac Miner",
		"Sheer Force",
		"Gemstone Infusion",
		"Tunnel Vision",
		"Mining Speed Boost"
	};

	private static final int TITLE_FADE_IN = 5; // ticks
	private static final int TITLE_STAY = 60;   // ticks (3s at 20 TPS)
	private static final int TITLE_FADE_OUT = 5; // ticks

	@Override
	public void onInitializeClient() {
		LOGGER.info("Pickaxe Ability Notification mod initialized!");

		// Register commands
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			NotificationCommand.register(dispatcher, registryAccess);
		});

		// Listen to GAME messages (catches system/server messages)
		ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
			String messageText = message.getString();
			LOGGER.info("[GAME] Received message (overlay={}): '{}'", overlay, messageText);
			LOGGER.info("[GAME] Raw component: {}", message);
			checkAndTrigger(messageText);
		});

		// Also listen to CHAT messages (for regular chat)
		ClientReceiveMessageEvents.CHAT.register((message, signedMessage, sender, params, receptionTimestamp) -> {
			String messageText = message.getString();
			LOGGER.info("[CHAT] Received chat message: '{}'", messageText);
			LOGGER.info("[CHAT] Raw component: {}", message);
			checkAndTrigger(messageText);
		});
	}

	private void checkAndTrigger(String messageText) {
		// Check if notifications are enabled
		if (!NotificationConfig.isEnabled()) {
			return;
		}

		// Remove all whitespace and compare
		String normalizedMessage = messageText.replaceAll("\\s+", " ").trim();
		LOGGER.info("Normalized message: '{}'", normalizedMessage);

		// Check if message contains any mining ability name
		boolean containsMiningAbility = false;
		String matchedAbility = null;
		for (String ability : MINING_ABILITIES) {
			if (messageText.contains(ability) || normalizedMessage.contains(ability)) {
				containsMiningAbility = true;
				matchedAbility = ability;
				break;
			}
		}

		boolean containsAvailable = messageText.contains("is now available") || normalizedMessage.contains("is now available");

		if (containsMiningAbility && containsAvailable) {
			LOGGER.info("Trigger matched for ability: {}! Showing notification...", matchedAbility);
			triggerNotification();
		}
	}

	private void triggerNotification() {
		Minecraft client = Minecraft.getInstance();
		if (client.gui != null) {
			// Create title with configured color
			Component title = Component.literal("PICKAXE ABILITY READY!").withStyle(NotificationConfig.getTextColor());
			client.gui.setTitle(title);
			client.gui.setTimes(TITLE_FADE_IN, TITLE_STAY, TITLE_FADE_OUT);
		}

		if (client.level != null && client.player != null) {
			// Use getX(), getY(), getZ() instead of position() for better cross-version compatibility
			double x = client.player.getX();
			double y = client.player.getY();
			double z = client.player.getZ();
			client.level.playLocalSound(x, y, z, SoundEvents.NOTE_BLOCK_PLING.value(), SoundSource.MASTER, 1.0f, 1.0f, false);
		}
	}
}
