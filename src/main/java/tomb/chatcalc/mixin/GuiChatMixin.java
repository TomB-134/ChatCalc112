package tomb.chatcalc.mixin;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tomb.chatcalc.EventHandler;
import tomb.chatcalc.util.StackCalculators;

@Mixin(GuiChat.class)
public class GuiChatMixin {
    @Shadow protected GuiTextField inputField;
    @Inject(at = @At("HEAD"), method = "keyTyped")
    protected void keyTyped(char typedChar, int keyCode, CallbackInfo info) {
        if (keyCode == 15) {
            String str = inputField.getText();
            if(str.endsWith("s")) {
                str = str.substring(0, str.length() - 1);
                if (!isNumeric(str)) {return;}
                inputField.setText(StackCalculators.calculateStack(str));
            }
            if (str.endsWith("sb")) {
                str = str.substring(0, str.length() - 2);
                if (!isNumeric(str)) {return;}
                inputField.setText(StackCalculators.calculateShulker(str));
            }
            if (str.endsWith("dc")) {
                str = str.substring(0, str.length() - 2);
                if (!isNumeric(str)) {return;}
                inputField.setText(StackCalculators.calculateDouble(str));
            } else {
                EventHandler.runExpression(this.inputField);
            }
        }
    }

    private static boolean isNumeric(String str) {
        return str.matches("^[0-9]+$");
    }
}