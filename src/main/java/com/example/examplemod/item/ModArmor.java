package com.example.examplemod.item;

import com.example.examplemod.ExampleMod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmor {
    public static final Holder<ArmorMaterial> ZHUZHUZI_ARMOR = regiser("zhuzhuzi_armor",
            Util.make(new EnumMap<>(ArmorItem.Type.class),attribute -> {
                attribute.put(ArmorItem.Type.BOOTS,5);
                attribute.put(ArmorItem.Type.LEGGINGS,7);
                attribute.put(ArmorItem.Type.CHESTPLATE,9);
                attribute.put(ArmorItem.Type.HELMET,5);
                attribute.put(ArmorItem.Type.BODY,11);





            }),16,2f,0.1f,() -> ModItems.ZHUZHUZI.get());






    private static Holder<ArmorMaterial> regiser(String name, EnumMap<ArmorItem.Type,Integer> typeProtection,
                                                 int encantability, float toughness, float knockbackResistance,
                                                 Supplier<Item> ingredientItem){
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, name);
        Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_NETHERITE;
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        EnumMap<ArmorItem.Type,Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for(ArmorItem.Type type : ArmorItem.Type.values()){
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection,encantability,equipSound,ingredient,layers,toughness,knockbackResistance));
    }

}
