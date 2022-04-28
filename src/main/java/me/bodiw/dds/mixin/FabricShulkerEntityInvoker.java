package me.bodiw.dds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.DyeColor;

@Mixin(ShulkerEntity.class)
public interface FabricShulkerEntityInvoker {

    @Invoker("setColor")
    public void invokeSetColor(DyeColor color);

}