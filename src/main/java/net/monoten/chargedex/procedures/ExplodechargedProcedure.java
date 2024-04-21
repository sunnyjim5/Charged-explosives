package net.monoten.chargedex.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

public class ExplodechargedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level && !_level.isClientSide())
			_level.explode(null, x, y, z, 20, Level.ExplosionInteraction.BLOCK);
		world.destroyBlock(BlockPos.containing(x, y, z), false);
	}
}
