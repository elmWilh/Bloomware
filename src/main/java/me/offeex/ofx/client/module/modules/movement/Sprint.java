package me.offeex.ofx.client.module.modules.movement;

import me.offeex.ofx.Bloomware;
import me.offeex.ofx.api.event.events.EventPlayerMove;
import me.offeex.ofx.api.util.MovementUtil;
import me.offeex.ofx.client.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", "Sprinting you", GLFW.GLFW_KEY_X, Category.MOVEMENT, false);
    }

    public void onEnable() {
        Bloomware.EVENTBUS.subscribe(listener);
    }

    public void onDisable() {
        onEnable();
        assert mc.player != null;
        mc.player.setSprinting(false);
        Bloomware.EVENTBUS.unsubscribe(listener);
    }

    @EventHandler
    private final Listener<EventPlayerMove> listener = new Listener<>(e -> {
        mc.player.setSprinting(true);
        final double[] dir = MovementUtil.forward2(0.01745329238474369);
        e.setVec3d(new Vec3d(dir[0] * 0.20000000298023224, e.getVec3d().getY(), dir[1] * 0.20000000298023224));
    });
}

