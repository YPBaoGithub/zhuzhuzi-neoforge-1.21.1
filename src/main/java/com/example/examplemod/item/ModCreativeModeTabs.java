package com.example.examplemod.item;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExampleMod.MODID);

    public static final Supplier<CreativeModeTab> GALAXY_tab = CREATIVE_MODE_TAB.register("galaxy_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ZHUZHUZI.get()))//自定义标签的样式
                    .title(Component.translatable("creativetab.examplemod.zhuzhuzi_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ZHUZHUZI.get());

                        output.accept(ModItems.ZHUZHUZI_HELMET.get());
                        output.accept(ModItems.ZHUZHUZI_CHESTPLATE.get());
                        output.accept(ModItems.ZHUZHUZI_LEGGINGS.get());
                        output.accept(ModItems.ZHUZHUZI_BOOTS.get());







                    })



                    .build());



    public static void register(IEventBus bus) {

        CREATIVE_MODE_TAB.register(bus);
    }
}


