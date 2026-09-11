package com.example.examplemod.events;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber(modid = ExampleMod.MODID)
public class broomFlightHandler {

    private static final double ACCELERATION = 0.03;
    private static final double MAX_SPEED = 0.75;
    private static final double FRICTION = 0.1;
    private static final double MIN_SPEED = 0.08;

    private static final Map<UUID, Vec3> VELOCITIES = new HashMap<>();
    // 记录“我们是否替玩家开启过飞行”，用于精准还原
    private static final Map<UUID, Boolean> WE_ENABLED_FLY = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;

        UUID id = player.getUUID();
        Vec3 velocity = VELOCITIES.getOrDefault(id, Vec3.ZERO);

        boolean usingBroom = player.isUsingItem()
                && player.getUseItem().is(ModItems.BROOM.get());

        if (usingBroom) {
            // ---- 飞行中 ----
            // 首次使用记录原来的 mayfly 状态（这里只记“我们是否开启过”）
            if (!WE_ENABLED_FLY.containsKey(id)) {
                WE_ENABLED_FLY.put(id, !player.getAbilities().mayfly);
            }
            player.getAbilities().mayfly = true;
            player.getAbilities().flying = true;

            // 只在飞行中清零坠落距离
            player.fallDistance = 0.0F;

            // 加速度
            Vec3 look = player.getLookAngle();
            velocity = velocity.add(look.scale(ACCELERATION));
            if (velocity.length() > MAX_SPEED) {
                velocity = velocity.normalize().scale(MAX_SPEED);
            }
            VELOCITIES.put(id, velocity);
            player.setDeltaMovement(velocity);
            syncMotion(player);

        } else {
            // ---- 没在用扫帚 ----
            // 如果之前有速度，继续滑行减速
            if (!velocity.equals(Vec3.ZERO)) {
                velocity = velocity.scale(FRICTION);
                if (velocity.length() < MIN_SPEED) {
                    velocity = Vec3.ZERO;
                    VELOCITIES.remove(id);
                } else {
                    VELOCITIES.put(id, velocity);
                }
                player.setDeltaMovement(velocity);
                syncMotion(player);
            }

            // 还原飞行能力（只还原我们开启过的，不碰创造模式原有状态）
            Boolean weEnabled = WE_ENABLED_FLY.remove(id);
            if (Boolean.TRUE.equals(weEnabled)) {
                player.getAbilities().mayfly = false;
                player.getAbilities().flying = false;
                // 让客户端也知道 abilities 变了
                if (player instanceof ServerPlayer sp) {
                    sp.onUpdateAbilities();
                }
            }

            // 速度归零后不再干预 fallDistance
            // （重力自然会让 fallDistance 累加，落地就吃伤害）
        }
    }

    private static void syncMotion(Player player) {
        if (player instanceof ServerPlayer sp) {
            sp.hurtMarked = true;
        }
    }

    // 掉线清理，防止 UUID 残留 + 状态卡死
    @SubscribeEvent
    public static void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        UUID id = event.getEntity().getUUID();
        VELOCITIES.remove(id);
        WE_ENABLED_FLY.remove(id);
    }
}