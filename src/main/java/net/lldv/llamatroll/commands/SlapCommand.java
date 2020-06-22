package net.lldv.llamatroll.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.utils.ConfigSection;
import net.lldv.llamatroll.LlamaTroll;
import net.lldv.llamatroll.components.language.Language;
import net.lldv.llamatroll.tasks.SlapTask;

public class SlapCommand extends PluginCommand<LlamaTroll> {

    public SlapCommand(LlamaTroll owner, ConfigSection c) {
        super(c.getString("name"), owner);
        setDescription(c.getString("description"));
        setPermission(c.getString("permission"));
        setUsage(Language.getNP("usage", "/" + getName() + " <player> <optional: times>"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender.hasPermission(getPermission())) {
            if (args.length >= 1) {

                Player target = Server.getInstance().getPlayer(args[0]);

                if (target == null) {
                    sender.sendMessage(Language.get("player-not-found"));
                    return false;
                }

                int times = 1;

                if (args.length >= 2) {
                    try {
                        times = Integer.parseInt(args[1]);
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(Language.get("invalid-number"));
                        return false;
                    }
                }

                Server.getInstance().getScheduler().scheduleDelayedTask(LlamaTroll.getInstance(), new SlapTask(target, times), 1);
                sender.sendMessage(Language.get("slapped", target.getName()));
            } else sender.sendMessage(getUsage());
        } else sender.sendMessage(Language.getNP("no-permission"));
        return true;
    }
}
