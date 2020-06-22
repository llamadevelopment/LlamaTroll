package net.lldv.llamatroll;

import cn.nukkit.command.CommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import net.lldv.llamatroll.commands.DropallCommand;
import net.lldv.llamatroll.commands.ForcedropCommand;
import net.lldv.llamatroll.commands.SlapCommand;
import net.lldv.llamatroll.commands.SpinCommand;
import net.lldv.llamatroll.components.language.Language;

public class LlamaTroll extends PluginBase {

    private static LlamaTroll instance;
    public static boolean silentTroll;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        Language.init();
        saveDefaultConfig();
        Config c = getConfig();

        silentTroll = c.getBoolean("silentTroll");

        CommandMap commandMap = getServer().getCommandMap();
        commandMap.register("slap", new SlapCommand(this, c.getSection("commands.slap")));
        commandMap.register("forcedrop", new ForcedropCommand(this, c.getSection("commands.forcedrop")));
        commandMap.register("dropall", new DropallCommand(this, c.getSection("commands.dropall")));
        commandMap.register("spin", new SpinCommand(this, c.getSection("commands.spin")));
    }

    public static LlamaTroll getInstance() {
        return instance;
    }
}
