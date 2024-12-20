package com.maxjnsn.spawnersplus.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InactiveSpawnerBlock extends Block {
    public InactiveSpawnerBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        // Get the item the player is holding (main hand first, then offhand)
        ItemStack itemStack = player.getMainHandStack();
        if (itemStack.isEmpty() || !(itemStack.getItem() == Items.BONE || itemStack.getItem() == Items.ROTTEN_FLESH || itemStack.getItem() == Items.SPIDER_EYE)) {
            itemStack = player.getOffHandStack();
        }

        // Determine the resulting entity type based on the held item
        EntityType<?> entityType = null;
        if (itemStack.getItem() == Items.BONE) {
            entityType = EntityType.SKELETON;
        } else if (itemStack.getItem() == Items.ROTTEN_FLESH) {
            entityType = EntityType.ZOMBIE;
        } else if (itemStack.getItem() == Items.SPIDER_EYE) {
            entityType = EntityType.SPIDER;
        }

        // If the item does not match any of the specified ones, return default behavior
        if (entityType == null) {
            return super.onUse(state, world, pos, player, hit);
        }

        // Only execute server-side logic
        if (!world.isClient()) {
            // Replace the block with a spawner block
            world.setBlockState(pos, Blocks.SPAWNER.getDefaultState(), 3);

            // Get the block entity at the position
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof MobSpawnerBlockEntity spawner) {
                // Get the spawner's logic and set it to spawn the selected entity type
                spawner.getLogic().setEntityId(entityType, world, world.getRandom(), pos);

                // Mark the block entity as dirty to ensure the changes are saved
                spawner.markDirty();
            }

            // Consume the item in Survival mode
            if (!player.isCreative()) {
                itemStack.decrement(1);
            }

            // Play a sound for feedback
            world.playSound(null, pos, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1.0f, 1.0f);

            return ActionResult.SUCCESS;
        }

        // Default behavior
        return ActionResult.PASS;
    }







}
