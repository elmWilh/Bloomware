package me.offeex.bloomware.client.module.modules.render;

import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;

public class FullBright extends Module {

    private final Setting<Number> gamma = register("Gamma", 16, 1, 16, 1);

    public FullBright() {
        super("FullBright", "Boosts your brightness", Category.RENDER, false);
    }

    private double oldBrightness;
    private double newBrightness;

    @Override
    public void onEnable() {
        oldBrightness = mc.options.gamma;
        mc.options.gamma = gamma.getValue().intValue();
    }

    @Override
    public void onDisable() {
        mc.options.gamma = oldBrightness;
    }

    @Override
    public void onTick() {
        if(this.isEnabled()) {
            newBrightness = gamma.getValue().intValue();
            if (mc.options.gamma != newBrightness) {
                mc.options.gamma = newBrightness;
            }
        }
    }
}
