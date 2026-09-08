package com.example.examplemod.entty.event;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entty.client.bianpaoModel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = ExampleMod.MODID)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void  registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(bianpaoModel.LAYER_LOCATION,bianpaoModel::createBodyLayer);
    }


}
