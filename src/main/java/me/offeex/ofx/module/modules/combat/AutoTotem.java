package me.offeex.ofx.module.modules.combat;

import me.offeex.ofx.module.Module;
import org.lwjgl.glfw.GLFW;

public class AutoTotem extends Module {
    public AutoTotem() {
        super("Auto Totem", "Automatically takes totem in your offhand", GLFW.GLFW_KEY_UNKNOWN, Category.COMBAT, false);
    }
}
