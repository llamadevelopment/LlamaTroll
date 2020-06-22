package net.lldv.llamatroll.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.ConfigSection;
import net.lldv.llamatroll.LlamaTroll;
import net.lldv.llamatroll.components.language.Language;
import net.lldv.llamatroll.tasks.DrunkenTask;

public class DrunkenCommand extends PluginCommand<LlamaTroll> {

    public DrunkenCommand(LlamaTroll owner, ConfigSection c) {
        super(c.getString("name"), owner);
        setDescription(c.getString("description"));
        setPermission(c.getString("permission"));
        setUsage(Language.getNP("usage", "/" + getName() + " <player> <seconds>"));
        commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("player", CommandParamType.TARGET, false),
                new CommandParameter("seconds", CommandParamType.INT, false)
        });
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender.hasPermission(getPermission())) {
            if (args.length >= 2) {

                Player target = Server.getInstance().getPlayer(args[0]);

                if (target == null) {
                    sender.sendMessage(Language.get("player-not-found"));
                    return true;
                }

                try {
                    int seconds = Integer.parseInt(args[1]);
                    sender.sendMessage(Language.get("drunken", target.getName()));
                    Server.getInstance().getScheduler().scheduleDelayedTask(getPlugin(), new DrunkenTask(target, seconds), 20);
                } catch (NumberFormatException ex) {
                    sender.sendMessage(Language.get("invalid-number"));
                }

            } else sender.sendMessage(getUsage());
        } else sender.sendMessage(Language.getNP("no-permission"));
        return true;
    }
}
