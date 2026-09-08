
package com.example.examplemod.entty.client;

import com.example.examplemod.ExampleMod;

import com.example.examplemod.entty.custom.bianpaoEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class bianpaoRenderer extends EntityRenderer<bianpaoEntity> {

    public bianpaoRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(bianpaoEntity entity) {
        // MODID必须是zhuzhuzi！！不要写.png后缀
        return ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "textures/entity/bianpao");
    }
}
