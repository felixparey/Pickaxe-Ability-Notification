package com.pickaxeability.notification;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PickaxeAbilityNotificationMod implements ModInitializer {
	public static final String MOD_ID = "pickaxeabilitynotification";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Pickaxe Ability Notification mod initialized");
	}
}