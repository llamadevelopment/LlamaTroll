package net.lldv.llamatroll.tasks;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.Location;
import net.lldv.llamatroll.LlamaTroll;

public class SpinTask implements Runnable {

    private final Player target;
    private int done = 0;
    private double playerYaw;

    public SpinTask(Player target) {
        this.target = target;
        this.playerYaw = target.getYaw();
    }

    @Override
    public void run() {
        playerYaw += 6;
        target.teleport(new Location(target.getX(), target.getY(), target.getZ(), playerYaw), null);

        done++;

        if (150 > done)
            Server.getInstance().getScheduler().scheduleDelayedTask(LlamaTroll.getInstance(), this, 1);
    }

}
