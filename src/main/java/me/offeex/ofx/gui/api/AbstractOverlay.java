package me.offeex.ofx.gui.api;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.event.events.EventDrawOverlay;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public abstract class AbstractOverlay extends AbstractDraggable {

    public AbstractOverlay(int x, int y, int width, int height) {
        super(x, y, width, height);
        Main.EVENTBUS.subscribe(listener);
    }

    @EventHandler
    private final Listener<EventDrawOverlay> listener = new Listener<>(e -> {
        this.draw(e.matrix, x, y, MinecraftClient.getInstance().getTickDelta());
    });

    protected int dragX, dragY;
    protected boolean dragging;
    MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public abstract void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta);

    @Override
    public abstract void mouseClicked(double mouseX, double mouseY, int mouseButton);

    public abstract void mouseReleased(double mouseX, double mouseY, int mouseButton);

    protected void startDragging(double mouseX, double mouseY, int mouseButton) {
        if (isMouseWithin(mouseX, mouseY)) {
            dragging = true;
            this.dragX = (int) mouseX - this.x;
            this.dragY = (int) mouseY - this.y;
        }
    }

    public void stopDragging(double mouseX, double mouseY, int mouseButton) {
        dragging = false;
    }

    // this should be called on render
    public void updateDragLogic(int mouseX, int mouseY) {
        if (dragging) {
            this.x = Math.max(0, (mouseX - dragX));
            this.x = Math.min(mc.getWindow().getScaledWidth() - this.width, this.x);

            this.y = Math.max(0, (mouseY - dragY));
            this.y = Math.min(mc.getWindow().getScaledHeight() - this.height, this.y);
        }
    }
}
