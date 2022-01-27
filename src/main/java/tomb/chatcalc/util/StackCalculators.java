package tomb.chatcalc.util;

import tomb.chatcalc.LiteModChatCalc;

public class StackCalculators {
    public static String calculateStack(String str) { //Calc stacks
        double count = Double.parseDouble(str);
        double stackCount = count / 64;

        if (count % 64 == 0 && count > 64) {
            String stackString;
            stackString = (stackCount == 1) ? LiteModChatCalc.stackString : LiteModChatCalc.stacksString;
            return (int) stackCount + stackString.substring(0, stackString.length() - 3);
        } else if (count <= 64) {
            if (count != 64) {
                String itemString;
                itemString = (count == 1) ? LiteModChatCalc.itemString : LiteModChatCalc.itemsString;
                return (int) count + itemString.substring(0, itemString.length() - 1);
            } else {
                String stackString = LiteModChatCalc.stackString;
                return 1 + stackString.substring(0, stackString.length() - 3);
            }
        } else {
            double flooredStackCount = Math.floor(stackCount);
            double items = (stackCount - flooredStackCount) * 64;

            String stackString = (flooredStackCount == 1) ? LiteModChatCalc.stackString : LiteModChatCalc.stacksString;

            String itemString = (items == 1) ? LiteModChatCalc.itemString : LiteModChatCalc.itemsString;

            return (int)flooredStackCount + stackString + (int)items + itemString.substring(0, itemString.length() - 1);
        }
    }

    public static String calculateShulker(String str) { // Calc shulker boxes
        double count = Double.parseDouble(str);
        double shulkerCount = count / 1728;
        if (count >= 1728) {
            if (count % 1728 == 0) {
                String shulkerString = LiteModChatCalc.shulkerBoxesString;
                return (int) shulkerCount + shulkerString.substring(0, shulkerString.length() - 3);
            } else {
                double flooredShulkerCount = Math.floor(shulkerCount);
                double remainder = Math.ceil((shulkerCount - flooredShulkerCount) * 1728);

                String shulkerString = (flooredShulkerCount == 1) ? LiteModChatCalc.shulkerboxString : LiteModChatCalc.shulkerBoxesString;

                return (int)flooredShulkerCount + shulkerString + StackCalculators.calculateStack(String.valueOf(remainder));
            }
        } else {
            return StackCalculators.calculateStack(str);
        }
    }

    public static String calculateDouble(String str) { //Calc double chest
        double count = Double.parseDouble(str);
        double doubleCount = count / 3456;
        if (count >= 3456) {
            if (count % 3456 == 0) {
                String doubleString = LiteModChatCalc.doubleString;
                return (int) doubleCount + doubleString.substring(0, doubleString.length() - 3);
            } else {
                double flooredDoubleCount = Math.floor(doubleCount);
                double remainder = Math.ceil((doubleCount - flooredDoubleCount) * 3456);

                String doubleString = (flooredDoubleCount == 1) ? LiteModChatCalc.doubleString : LiteModChatCalc.doublesString;

                return (int)flooredDoubleCount + doubleString + StackCalculators.calculateStack(String.valueOf(remainder));
            }
        } else {
            return StackCalculators.calculateStack(str);
        }
    }
}
