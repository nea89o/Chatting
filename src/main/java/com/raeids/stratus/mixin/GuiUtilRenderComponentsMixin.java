package com.raeids.stratus.mixin;

import com.raeids.stratus.config.StratusConfig;
import com.raeids.stratus.hook.ChatTabs;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.List;

@Mixin(GuiUtilRenderComponents.class)
public class GuiUtilRenderComponentsMixin {
    @Inject(method = "splitText", at = @At("HEAD"), cancellable = true)
    private static void cancelText(IChatComponent k, int s1, FontRenderer chatcomponenttext, boolean l, boolean chatcomponenttext2, CallbackInfoReturnable<List<IChatComponent>> cir) {
        if (StratusConfig.INSTANCE.getChatTabs() && ChatTabs.INSTANCE.isDoing()) {
            if (!ChatTabs.INSTANCE.shouldRender(k)) {
                cir.setReturnValue(Collections.emptyList());
            }
            ChatTabs.INSTANCE.setDoing(false);
        }
    }
}