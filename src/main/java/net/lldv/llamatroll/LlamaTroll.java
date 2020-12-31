package net.lldv.llamatroll;

import cn.nukkit.command.CommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import lombok.Getter;
import lombok.Setter;
import net.lldv.llamatroll.commands.*;
import net.lldv.llamatroll.components.language.Language;

public class LlamaTroll extends PluginBase {

    @Setter
    @Getter
    private boolean silentTroll;

    @Override
    public void onEnable() {
        Language.init(this);
        saveDefaultConfig();
        Config c = getConfig();

        silentTroll = c.getBoolean("silentTroll");

        CommandMap commandMap = getServer().getCommandMap();
        commandMap.register("slap", new SlapCommand(this, c.getSection("commands.slap")));
        commandMap.register("forcedrop", new ForcedropCommand(this, c.getSection("commands.forcedrop")));
        commandMap.register("dropall", new DropallCommand(this, c.getSection("commands.dropall")));
        commandMap.register("spin", new SpinCommand(this, c.getSection("commands.spin")));
        commandMap.register("silenttroll", new SilenttrollCommand(this, c.getSection("commands.silenttroll")));
        commandMap.register("drunken", new DrunkenCommand(this, c.getSection("commands.drunken")));
        commandMap.register("ignite", new IgniteCommand(this, c.getSection("commands.ignite")));
        commandMap.register("freeze", new FreezeCommand(this, c.getSection("commands.freeze")));
        commandMap.register("unfreeze", new UnfreezeCommand(this, c.getSection("commands.unfreeze")));
        commandMap.register("rocket", new RocketCommand(this, c.getSection("commands.rocket")));
    }

}
