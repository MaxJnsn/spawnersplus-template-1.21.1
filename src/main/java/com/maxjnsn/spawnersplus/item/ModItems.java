package com.maxjnsn.spawnersplus.item;

import com.maxjnsn.spawnersplus.SpawnersPlus;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item SPAWNER_FRAGMENT = registerItem("spawner_fragment", new Item(new Item.Settings()));

    public static final Item SOUL_BOTTLE = registerItem("soul_bottle", new Item(new Item.Settings()));

    public static final Item SKELETON_SOUL = registerItem("skeleton_soul", new Item(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SpawnersPlus.MOD_ID, name), item);
    }

    private static void customIngredients(FabricItemGroupEntries entries) {
        entries.add(SPAWNER_FRAGMENT);

    }

    public static void registerModItems() {
        SpawnersPlus.LOGGER.info("Registering Mod Items for " + SpawnersPlus.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::customIngredients);
    }
}
