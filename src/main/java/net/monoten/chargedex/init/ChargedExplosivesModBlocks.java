
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.monoten.chargedex.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.monoten.chargedex.block.ChargedExplosiveBlock;
import net.monoten.chargedex.ChargedExplosivesMod;

import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

public class ChargedExplosivesModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK, ChargedExplosivesMod.MODID);
	public static final DeferredHolder<Block, Block> CHARGED_EXPLOSIVE = REGISTRY.register("charged_explosive", () -> new ChargedExplosiveBlock());

	// Start of user code block custom blocks
	// End of user code block custom blocks
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
			ChargedExplosiveBlock.blockColorLoad(event);
		}
	}
}
