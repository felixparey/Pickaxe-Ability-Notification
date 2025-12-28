package com.pickaxeability.notification;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;

import java.util.Arrays;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class NotificationCommand {

	public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext registryAccess) {
		dispatcher.register(literal("panotification")
			.then(argument("toggle", StringArgumentType.word())
				.suggests(toggleSuggestions())
				.executes(context -> toggleNotification(context)))
			.then(literal("color")
				.then(argument("color", StringArgumentType.word())
					.suggests(colorSuggestions())
					.executes(context -> setColor(context))))
		);
	}

	private static SuggestionProvider<FabricClientCommandSource> toggleSuggestions() {
		return (context, builder) -> {
			builder.suggest("enabled");
			builder.suggest("disabled");
			return builder.buildFuture();
		};
	}

	private static SuggestionProvider<FabricClientCommandSource> colorSuggestions() {
		return (context, builder) -> {
			for (ChatFormatting color : NotificationConfig.AVAILABLE_COLORS) {
				builder.suggest(color.name().toLowerCase());
			}
			return builder.buildFuture();
		};
	}

	private static int toggleNotification(CommandContext<FabricClientCommandSource> context) {
		String toggle = StringArgumentType.getString(context, "toggle");

		if (toggle.equalsIgnoreCase("enabled")) {
			NotificationConfig.setEnabled(true);
			context.getSource().sendFeedback(Component.literal("✓ Pickaxe Ability Notifications ")
				.withStyle(ChatFormatting.GREEN)
				.append(Component.literal("ENABLED").withStyle(ChatFormatting.BOLD, ChatFormatting.GREEN)));
			return 1;
		} else if (toggle.equalsIgnoreCase("disabled")) {
			NotificationConfig.setEnabled(false);
			context.getSource().sendFeedback(Component.literal("✗ Pickaxe Ability Notifications ")
				.withStyle(ChatFormatting.RED)
				.append(Component.literal("DISABLED").withStyle(ChatFormatting.BOLD, ChatFormatting.RED)));
			return 1;
		} else {
			context.getSource().sendError(Component.literal("Invalid argument! Use 'enabled' or 'disabled'")
				.withStyle(ChatFormatting.RED));
			return 0;
		}
	}

	private static int setColor(CommandContext<FabricClientCommandSource> context) {
		String colorName = StringArgumentType.getString(context, "color");
		ChatFormatting color = NotificationConfig.getColorByName(colorName);

		if (color != null && Arrays.asList(NotificationConfig.AVAILABLE_COLORS).contains(color)) {
			NotificationConfig.setTextColor(color);
			context.getSource().sendFeedback(Component.literal("✓ Notification color set to ")
				.withStyle(ChatFormatting.GREEN)
				.append(Component.literal(color.name()).withStyle(color, ChatFormatting.BOLD)));
			return 1;
		} else {
			context.getSource().sendError(Component.literal("Invalid color! Available colors: black, dark_blue, dark_green, dark_aqua, dark_red, dark_purple, gold, gray, dark_gray, blue, green, aqua, red, light_purple, yellow, white")
				.withStyle(ChatFormatting.RED));
			return 0;
		}
	}
}

