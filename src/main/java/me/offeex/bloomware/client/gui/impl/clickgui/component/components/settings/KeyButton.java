package me.offeex.bloomware.client.gui.impl.clickgui.component.components.settings;

import me.offeex.bloomware.Bloomware;
import me.offeex.bloomware.api.util.ColorUtils;
import me.offeex.bloomware.client.gui.impl.clickgui.component.Component;
import me.offeex.bloomware.client.gui.impl.clickgui.component.components.ModuleButton;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class KeyButton extends Component {

    private boolean binding;
    private final ModuleButton button;
    private boolean isHovered;
    private int offset;
    private int x;
    private int y;

    public KeyButton(final ModuleButton button, final int offset) {
        this.button = button;
        this.x = button.frame.getX() + button.frame.getWidth();
        this.y = button.frame.getY() + button.offset;
        this.offset = offset;
    }

    @Override
    public void setOffset(final int offset) {
        this.offset = offset;
    }

    @Override
    public void updateComponent(final double mouseX, final double mouseY) {
        isHovered = isHovered(mouseX, mouseY);
        y = button.frame.getY() + this.offset;
        x = button.frame.getX();
    }

    @Override
    public void mouseClicked(final double mouseX, final double mouseY, final int button) {
        if (isHovered(mouseX, mouseY) && button == 0 && this.button.open) {
            binding = !binding;
        }
    }

    @Override
    public void keyTyped(final int key) {
        if (this.binding) {
            if (key == GLFW.GLFW_KEY_DELETE) button.module.setKey(-1);
            else button.module.setKey(key);
            this.binding = false;
        }
    }

    @Override
    public void render() {
        DrawableHelper.fill(new MatrixStack(), button.frame.getX(), button.frame.getY() + offset, button.frame.getX() + button.frame.getWidth(), button.frame.getY() + offset + 12, isHovered ? new Color(0, 0, 0, 150).getRGB() : new Color(0, 0, 0, 130).getRGB());
        Bloomware.sFontRenderer.drawString("Key", button.frame.getX() + 3, button.frame.getY() + offset + 2, ColorUtils.getTextColor().getRGB(), true);
        if (binding) {
            Bloomware.sFontRenderer.drawString("...", button.frame.getX() + button.frame.getWidth() - 2 - Bloomware.sFontRenderer.getStringWidth("...", Bloomware.sFontRenderer.getFontsize()), button.frame.getY() + offset + 2, ColorUtils.getTextColor().getRGB(), true);
        } else {
            Text keyString;
            switch (button.module.getKey()) {
                case 345:
                    keyString = new LiteralText("RCtrl");
                    break;
                case 341:
                    keyString = new LiteralText("Ctrl");
                    break;
                case 346:
                    keyString = new LiteralText("RAlt");
                    break;
                case -1:
                    keyString = new LiteralText("NONE");
                    break;
                default:
                    keyString = InputUtil.fromKeyCode(button.module.getKey(), -1).getLocalizedText();
            }
            Bloomware.sFontRenderer.drawString(keyString.asString(), button.frame.getX() + button.frame.getWidth() - 2 - Bloomware.sFontRenderer.getStringWidth(keyString.asString(), Bloomware.sFontRenderer.getFontsize()), button.frame.getY() + offset + 2, ColorUtils.Colors.WHITE.getRGB(), true);
        }
    }

    public boolean isHovered(final double x, final double y) {
        return x > this.x && x < this.x + 110 && y > this.y && y < this.y + 12;
    }
}
