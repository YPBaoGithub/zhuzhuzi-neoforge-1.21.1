package com.example.examplemod.item;

import com.example.examplemod.ExampleMod;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExampleMod.MODID);

    public static final DeferredItem<Item> ZHUZHUZI = ITEMS.register("zhuzhuzi",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<ArmorItem> ZHUZHUZI_HELMET = ITEMS.register("zhuzhuzi_helmet",
            () -> new ArmorItem(ModArmor.ZHUZHUZI_ARMOR,ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));
    public static final DeferredItem<ArmorItem> ZHUZHUZI_CHESTPLATE = ITEMS.register("zhuzhuzi_chestplate",
            () -> new ArmorItem(ModArmor.ZHUZHUZI_ARMOR,ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19))));

    public static final DeferredItem<ArmorItem> ZHUZHUZI_LEGGINGS = ITEMS.register("zhuzhuzi_leggings",
            () -> new ArmorItem(ModArmor.ZHUZHUZI_ARMOR,ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(19))));

    public static final DeferredItem<ArmorItem> ZHUZHUZI_BOOTS = ITEMS.register("zhuzhuzi_boots",
            () -> new ArmorItem(ModArmor.ZHUZHUZI_ARMOR,ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(19))));


    public static final DeferredItem<Item> BROOM = ITEMS.register("broom",
            () -> new broomItem(new Item.Properties()));











    public static  void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
