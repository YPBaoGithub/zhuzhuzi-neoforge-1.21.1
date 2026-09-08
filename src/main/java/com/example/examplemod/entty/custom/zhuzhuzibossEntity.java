package com.example.examplemod.entty.custom;
import com.example.examplemod.entty.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;


public class zhuzhuzibossEntity extends Animal {

    public  final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    public zhuzhuzibossEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    //实体的行为
    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(1,new PanicGoal(this,2.0));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)//血量
                .add(Attributes.MOVEMENT_SPEED, 0.3D)//基础移动速度
                .add(Attributes.FOLLOW_RANGE, 16.0);
    }
    @Nullable


    @Override
    public  AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.zhuzhuziboss.get().create(level);
    }

//动作
    private void setupAnimationState() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 80;
            this.idleAnimationState.start(this.tickCount);
        }else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();


        if(this.level().isClientSide()){
            this.setupAnimationState();
        }
    }
}


