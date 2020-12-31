package net.lldv.llamatroll.tasks;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.math.Vector3;
import net.lldv.llamatroll.LlamaTroll;
import net.lldv.llamatroll.components.language.Language;

public class SlapTask implements Runnable {

    private final int times;
    private final Player target;
    private int done = 0;
    private final LlamaTroll plugin;

    public SlapTask(Player target, int times, LlamaTroll plugin) {
        this.times = times;
        this.target = target;
        this.plugin = plugin;
    }

    @Override
    public void run() {

        target.setMotion(new Vector3((Math.random() * 2) - 1, Math.random(), (Math.random() * 2) - 1));
        if (!this.plugin.isSilentTroll()) target.sendMessage(Language.getNP("slap"));
        target.attack(0);

        done++;

        if (times > done)
            Server.getInstance().getScheduler().scheduleDelayedTask(this.plugin, this, 20);


    }

}
