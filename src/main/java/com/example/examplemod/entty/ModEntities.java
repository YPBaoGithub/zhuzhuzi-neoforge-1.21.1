package com.example.examplemod.entty;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entty.custom.bianpaoEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ExampleMod.MODID);



    public static final Supplier<EntityType<bianpaoEntity>> BIANPAO =
            ENTITY_TYPE.register("bianpao",() -> EntityType.Builder.<bianpaoEntity>of(bianpaoEntity::new,MobCategory.MISC)
                    .sized(0.5f,0.5f).build("bianpao"));




    public static void register(IEventBus eventBus) {
        ENTITY_TYPE.register(eventBus);
    }
}
