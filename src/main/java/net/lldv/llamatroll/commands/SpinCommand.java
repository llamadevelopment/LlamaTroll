package net.lldv.llamatroll.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.utils.ConfigSection;
import net.lldv.llamatroll.LlamaTroll;
import net.lldv.llamatroll.components.language.Language;
import net.lldv.llamatroll.tasks.SpinTask;

public class SpinCommand extends PluginCommand<LlamaTroll> {

    public SpinCommand(LlamaTroll owner, ConfigSection c) {
        super(c.getString("name"), owner);
        setDescription(c.getString("description"));
        setPermission(c.getString("permission"));
        setUsage(Language.getNP("usage", "/" + getName() + " <player>"));
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

                Server.getInstance().getScheduler().scheduleDelayedTask(getPlugin(), new SpinTask(target), 1);

                if (!LlamaTroll.silentTroll) target.sendMessage(Language.getNP("spin"));
                sender.sendMessage(Language.get("spinned", target.getName()));

            } else sender.sendMessage(getUsage());

        } else sender.sendMessage(Language.getNP("no-permission"));
        return false;
    }
}
