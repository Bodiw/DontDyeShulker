package me.bodiw.dds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;

public class Dds implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("dds");

	@Override
	public void onInitialize() {
		LOGGER.info("You can now paint on shulkers!");
	}
}
