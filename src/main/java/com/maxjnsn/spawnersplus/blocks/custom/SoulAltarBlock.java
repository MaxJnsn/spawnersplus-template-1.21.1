package com.maxjnsn.spawnersplus.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class SoulAltarBlock extends HorizontalFacingBlock {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    // Define voxel shape for the block
    private static final VoxelShape BASE_SHAPE = Block.createCuboidShape(2, 0, 2, 14, 2, 14);
    private static final VoxelShape LEG_SHAPE = Block.createCuboidShape(4.5, 2, 4.5, 11.5, 12, 11.5);
    private static final VoxelShape TOP_SHAPE = Block.createCuboidShape(0.5, 12, 0.5, 15.5, 16, 15.5);

    private static final VoxelShape SOUL_ALTAR_SHAPE = VoxelShapes.union(BASE_SHAPE, LEG_SHAPE, TOP_SHAPE);

    public SoulAltarBlock(Settings settings) {
        super(settings);
        // Set the default state to face north
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        // Set block state based on the player's horizontal facing direction
        return this.getDefaultState().with(FACING, context.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SOUL_ALTAR_SHAPE;
    }

}
