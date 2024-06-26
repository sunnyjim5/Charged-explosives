
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.monoten.chargedex.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.bus.api.IEventBus;

import net.monoten.chargedex.ChargedExplosivesMod;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.core.registries.BuiltInRegistries;

public class ChargedExplosivesModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM, ChargedExplosivesMod.MODID);
	public static final DeferredHolder<Item, Item> CHARGED_EXPLOSIVE = block(ChargedExplosivesModBlocks.CHARGED_EXPLOSIVE);

	// Start of user code block custom items
	// End of user code block custom items
	public static void register(IEventBus bus) {
		REGISTRY.register(bus);
	}

	private static DeferredHolder<Item, Item> block(DeferredHolder<Block, Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
