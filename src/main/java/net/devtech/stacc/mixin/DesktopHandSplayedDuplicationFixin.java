package net.devtech.stacc.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Set;

@Mixin (ScreenHandler.class)
public class DesktopHandSplayedDuplicationFixin {
/*
	@Redirect (method = "calculateStackSize", at = @At (value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;floor(F)I"))
	private static int floor(float value) {
		return MathHelper.floor((double) stack.getCount() / (double) slots.size());
	}
*/



/*	//TODO: FIX
	@ModifyArgs(method = "calculateStackSize", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;floor(F)I"))
	private static void overstacking$useDoubleinFloor(Args args, Set<Slot> slots, ItemStack stack) {
		return MathHelper.floor((double) stack.getCount() / (double) slots.size());

	}*/



    @Unique
    @Inject(method = "calculateStackSize", at=@At("HEAD"), cancellable = true)
    private static void overstating$useDoublePrecision(Set<Slot> slots, int mode, ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (mode == 0) {
            cir.setReturnValue(MathHelper.floor((double) stack.getCount() / (double) slots.size()));
        }
    }
}

