package me.bodiw.dds.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;

@Mixin(DyeItem.class)
public class FabricMixinDyeItem extends Item {

	protected FabricMixinDyeItem(Settings settings) {
		super(settings);
	}

	@Shadow
	@Final
	private DyeColor color;

	@Inject(at = @At("HEAD"), method = "useOnEntity()V", cancellable = true)
	private void useOnShulker(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand,
			CallbackInfoReturnable<ActionResult> cir) {
		ShulkerEntity shulkerEntity;
		if (entity instanceof ShulkerEntity && (shulkerEntity = (ShulkerEntity) entity).isAlive()
				&& shulkerEntity.getColor() != this.color) {
			shulkerEntity.world.playSoundFromEntity(user, shulkerEntity, SoundEvents.ITEM_DYE_USE,
					SoundCategory.PLAYERS, 1.0f, 1.0f);
			if (!user.world.isClient) {
				((FabricShulkerEntityInvoker) shulkerEntity).invokeSetColor(color);
				stack.decrement(1);
			}
			cir.setReturnValue((ActionResult.success(user.world.isClient)));
		}
	}
}