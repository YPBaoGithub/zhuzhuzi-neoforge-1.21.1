package com.example.examplemod.item;


import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class broomItem extends Item {
    public broomItem(Properties properties) {
        super(properties);
    }

    // 右键开始使用时调用，返回 CONSUME 表示进入“持续使用”状态
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        // 告诉游戏：我开始用这个物品了，并且要持续用下去
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    // 必须重写，返回一个大于 0 的值，否则无法持续使用
    @Override
    public int getUseDuration(ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
        return 72000; // 一个很大的数，相当于“直到你松开右键”
    }
}