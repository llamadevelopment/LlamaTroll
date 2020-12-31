package net.lldv.llamatroll.tasks;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.math.Vector3;
import cn.nukkit.potion.Effect;
import net.lldv.llamatroll.LlamaTroll;
import net.lldv.llamatroll.components.language.Language;

public class DrunkenTask implements Runnable {

    private final Player target;
    private final int seconds;
    private int done = 0;
    private final LlamaTroll plugin;

    public DrunkenTask(LlamaTroll plugin, Player target, int seconds) {
        this.target = target;
        this.seconds = seconds;
        this.plugin = plugin;
        target.addEffect(Effect.getEffect(Effect.NAUSEA).setDuration(seconds * 20));
        target.addEffect(Effect.getEffect(Effect.SLOWNESS).setDuration(seconds * 20));
        target.addEffect(Effect.getEffect(Effect.HUNGER).setDuration(seconds * 20));
        if (!this.plugin.isSilentTroll()) target.sendMessage(Language.getNP("drunk"));
    }

    @Override
    public void run() {
        target.setMotion(new Vector3((Math.random() * 0.5) - 0.25, 0, (Math.random() * 0.5) - 0.25));

        done++;

        if (seconds > done)
            Server.getInstance().getScheduler().scheduleDelayedTask(this.plugin, this, 20);


    }
}
