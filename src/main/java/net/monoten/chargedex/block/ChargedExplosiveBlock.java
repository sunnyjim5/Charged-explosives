
package net.monoten.chargedex.block;

import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.monoten.chargedex.procedures.ExplodechargedProcedure;
import net.monoten.chargedex.init.ChargedExplosivesModBlocks;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.BiomeColors;

public class ChargedExplosiveBlock extends Block {
	public ChargedExplosiveBlock() {
		super(BlockBehaviour.Properties.of().ignitedByLava()
				.sound(new DeferredSoundType(1.0f, 1.0f, () -> BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.grass.break")), () -> BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.sand.step")),
						() -> BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.azalea.step")), () -> BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.azalea.hit")),
						() -> BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.azalea_leaves.fall"))))
				.strength(0f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.or(box(9, 0, 9, 15, 17, 15), box(9, 0, 1, 15, 17, 7), box(1, 0, 9, 7, 17, 15), box(1, 0, 1, 7, 17, 7), box(1, 13, 15, 16, 15, 16), box(15, 13, 0, 16, 15, 15), box(0, 13, 0, 15, 15, 1), box(0, 13, 1, 1, 15, 16),
				box(1, 2, 15, 16, 4, 16), box(15, 2, 0, 16, 4, 15), box(0, 2, 0, 15, 4, 1), box(0, 2, 1, 1, 4, 16), box(7, 17, 2, 8, 18, 8), box(8, 17, 7, 14, 18, 8), box(13, 17, 1, 14, 18, 7), box(7, 17, 1, 13, 18, 2));
	}

	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
		return true;
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		if (world.getBestNeighborSignal(pos) > 0) {
			ExplodechargedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		}
	}

	@Override
	public void wasExploded(Level world, BlockPos pos, Explosion e) {
		super.wasExploded(world, pos, e);
		ExplodechargedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@OnlyIn(Dist.CLIENT)
	public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
		event.getBlockColors().register((bs, world, pos, index) -> {
			return world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor();
		}, ChargedExplosivesModBlocks.CHARGED_EXPLOSIVE.get());
	}
}
