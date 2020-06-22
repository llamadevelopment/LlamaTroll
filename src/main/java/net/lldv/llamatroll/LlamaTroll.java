package net.lldv.llamatroll;

import cn.nukkit.plugin.PluginBase;
import net.lldv.llamatroll.components.language.Language;

public class LlamaTroll extends PluginBase {

    private static LlamaTroll instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        Language.init();
    }

    public static LlamaTroll getInstance() {
        return instance;
    }
}
