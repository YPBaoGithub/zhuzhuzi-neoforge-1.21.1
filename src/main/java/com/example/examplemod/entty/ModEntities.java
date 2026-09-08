package com.example.examplemod.entty;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entty.custom.zhuzhuzibossEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ExampleMod.MODID);


    public static final Supplier<EntityType<zhuzhuzibossEntity>> zhuzhuziboss =
            ENTITY_TYPE.register("zhuzhuziboos",() -> EntityType.Builder.of(zhuzhuzibossEntity::new, MobCategory.CREATURE)
                    .sized(2f,1f).build("zhuzhuziboss"));//自定义碰撞箱




    public static void register(IEventBus eventBus) {
        ENTITY_TYPE.register(eventBus);
    }
}
