package net.lldv.llamatroll.commands;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.ConfigSection;
import net.lldv.llamatroll.LlamaTroll;
import net.lldv.llamatroll.components.language.Language;

public class SilenttrollCommand extends PluginCommand<LlamaTroll> {

    public SilenttrollCommand(LlamaTroll owner, ConfigSection c) {
        super(c.getString("name"), owner);
        setDescription(c.getString("description"));
        setPermission(c.getString("permission"));
        setUsage(Language.getNP("usage", "/" + getName() + " <optional: on/off>"));
        commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("status", false, new String[]{"on", "off"}),
        });
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender.hasPermission(getPermission())) {
            if (args.length >= 1) {
                switch (args[0].toLowerCase()) {
                    case "on":
                        LlamaTroll.silentTroll = true;
                        sender.sendMessage(Language.get("silent"));
                        return true;
                    case "off":
                        LlamaTroll.silentTroll = false;
                        sender.sendMessage(Language.get("unsilent"));
                        return true;
                    default:
                        sender.sendMessage(getUsage());
                }
            } else {
                if (LlamaTroll.silentTroll) {
                    LlamaTroll.silentTroll = false;
                    sender.sendMessage(Language.get("unsilent"));
                } else {
                    LlamaTroll.silentTroll = true;
                    sender.sendMessage(Language.get("silent"));
                }
            }
        } else sender.sendMessage(Language.getNP("no-permission"));
        return false;
    }
}
