package dev.nolij.zume.primitive.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.nolij.zume.common.Zume;
import net.minecraft.class_555;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_555.class)
public class GameRendererMixin {
	
	@Inject(method = "method_1844", at = @At("HEAD"))
	public void zume$render$HEAD(CallbackInfo ci) {
		Zume.render();
	}
	
	@Inject(method = "method_1848", at = @At("TAIL"), cancellable = true)
	public void zume$getFov$TAIL(CallbackInfoReturnable<Float> cir) {
		if (Zume.shouldHookFOV()) {
			cir.setReturnValue((float) Zume.transformFOV(cir.getReturnValueF()));
		}
	}
	
	@ModifyExpressionValue(method = "method_1844", at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;cinematicMode:Z"))
	public boolean zume$updateMouse$smoothCameraEnabled(boolean original) {
		return Zume.transformCinematicCamera(original);
	}
	
	@ModifyExpressionValue(method = "method_1844", at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;mouseSensitivity:F"))
	public float zume$updateMouse$mouseSensitivity(float original) {
		return (float) Zume.transformMouseSensitivity(original);
	}
	
	@ModifyExpressionValue(method = "method_1851", at = @At(value = "FIELD", target = "Lnet/minecraft/class_555;field_2359:F"))
	public float zume$transformCamera$thirdPersonDistance(float original) {
		if (Zume.shouldHook())
			return (float) Zume.transformThirdPersonDistance(original);
		
		return original;
	}
	
	@ModifyExpressionValue(method = "method_1851", at = @At(value = "FIELD", target = "Lnet/minecraft/class_555;field_2360:F"))
	public float zume$transformCamera$lastThirdPersonDistance(float original) {
		if (Zume.shouldHook())
			return (float) Zume.transformThirdPersonDistance(original);
		
		return original;
	}
	
}
