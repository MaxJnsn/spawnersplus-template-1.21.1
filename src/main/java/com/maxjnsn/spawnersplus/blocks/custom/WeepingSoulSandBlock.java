package com.maxjnsn.spawnersplus.blocks.custom;

import com.maxjnsn.spawnersplus.item.ModItems;
import com.maxjnsn.spawnersplus.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WeepingSoulSandBlock extends Block {
    private static final VoxelShape OUTLINE_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D); // Full block height (16px)
    private static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D); // 14px collision height

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // This controls the black outline (full block height)
        return OUTLINE_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // This controls collision for walking (14px height)
        return COLLISION_SHAPE;
    }


    public WeepingSoulSandBlock(Settings settings) {
        super(settings);
    }

    /**
     * Custom interaction with the block using a Glass Bottle.
     */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        // Check both hands for a Glass Bottle
        ItemStack itemStack = player.getMainHandStack();
        if (itemStack.getItem() != Items.GLASS_BOTTLE) {
            itemStack = player.getOffHandStack();
        }

        if (itemStack.getItem() != Items.GLASS_BOTTLE) {
            return super.onUse(state, world, pos, player, hit);
        }

        if (!world.isClient()) {
            // Replace block with regular Soul Sand
            world.setBlockState(pos, Blocks.SOUL_SAND.getDefaultState(), 3);

            // Transform Glass Bottle into a Honey Bottle
            if (!player.isCreative()) {
                itemStack.decrement(1);
                player.giveItemStack(new ItemStack(ModItems.SOUL_BOTTLE));
            }

            // Play a sound
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }

        return ActionResult.SUCCESS;
    }

    /**
     * Custom ambient effects during random ticks.
     */
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.01F) { // 1% chance
            float volume = 0.3F + random.nextFloat() * 0.1F; // 0.3F - 0.4F
            float pitch = 0.8F + random.nextFloat() * 0.2F;  // 0.8F - 1.0F

            world.playSound(
                    null,
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    ModSounds.WEEPING_SOUL_SAND,
                    SoundCategory.BLOCKS,
                    volume,
                    pitch
            );
        }
    }


    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (!world.isClient) { // Server-side only
            // Check if the tool has the Silk Touch enchantment
            boolean hasSilkTouch = tool != null &&
                    EnchantmentHelper.getLevel(
                            world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).entryOf(Enchantments.SILK_TOUCH),
                            tool) > 0;

            // Drop the appropriate block
            if (hasSilkTouch) {
                Block.dropStack(world, pos, new ItemStack(this));
            } else {
                Block.dropStack(world, pos, new ItemStack(Blocks.SOUL_SAND));

                // Play weeping sounds (only when NOT using Silk Touch)
                Random random = world.getRandom();
                float volume = 0.8F + random.nextFloat() * 0.2F; // 0.8F - 1.0F
                float pitch = 0.9F + random.nextFloat() * 0.2F;  // 0.9F - 1.1F

                world.playSound(
                        null,
                        pos,
                        ModSounds.WEEPING_SOUL_SAND,
                        SoundCategory.BLOCKS,
                        volume,
                        pitch
                );

                // Spawn particles (only when NOT using Silk Touch)
                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.SOUL,
                        pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
                        25, 0.3, 0.3, 0.3, 0.1
                );
            }
        }

        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }
}
