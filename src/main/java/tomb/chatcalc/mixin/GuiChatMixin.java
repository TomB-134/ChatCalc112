package tomb.chatcalc.mixin;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tomb.chatcalc.EventHandler;

@Mixin(GuiChat.class)
public class GuiChatMixin {
    @Shadow protected GuiTextField inputField;
    @Inject(at = @At("HEAD"), method = "keyTyped")
    protected void keyTyped(char typedChar, int keyCode, CallbackInfo info) {
        if (keyCode == 15) {
            EventHandler.runExpression(this.inputField);
        }
    }
}