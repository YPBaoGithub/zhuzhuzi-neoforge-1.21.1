package com.example.examplemod.events;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.item.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

@EventBusSubscriber(modid = ExampleMod.MODID)
public class broomFlightHandler {

    // 飞行速度系数，可以调大或调小
    private static final double FLIGHT_SPEED = 1;

    @SubscribeEvent
    public static void onItemUseTick(LivingEntityUseItemEvent.Tick event) {
        // 只处理玩家
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        // 判断正在使用的物品是不是你的扫帚
        if (!event.getItem().is(ModItems.BROOM.get())) {
            return;
        }

        // 只在服务端执行逻辑（客户端会由服务端同步）
        if (player.level().isClientSide()) {
            return;
        }

        // 让玩家具备飞行能力（允许飞行，但不自动开启飞行状态）
        player.getAbilities().mayfly = true;

        // 获取玩家视角方向
        Vec3 look = player.getLookAngle();

        // 如果玩家当前没有在飞行状态，手动开启飞行
        if (!player.getAbilities().flying) {
            player.getAbilities().flying = true;
        }

        // 核心：把玩家的速度方向设置为视角方向
        player.setDeltaMovement(
                look.x * FLIGHT_SPEED,
                look.y * FLIGHT_SPEED,
                look.z * FLIGHT_SPEED
        );

        // 关键：防止玩家受到重力影响而下坠
        player.fallDistance = 0.0F;

        // 把速度变化同步给客户端，否则你会看到玩家“抽搐”
        player.hurtMarked = true;
    }
}