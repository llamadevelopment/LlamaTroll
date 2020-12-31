package net.lldv.llamatroll.tasks;

import cn.nukkit.Player;
import cn.nukkit.level.particle.Particle;
import cn.nukkit.level.particle.SmokeParticle;
import cn.nukkit.scheduler.Task;

/**
 * @author LlamaDevelopment
 * @project LlamaTroll
 * @website http://llamadevelopment.net/
 */
public class RocketTask extends Task {

    private final Player target;
    private int times = 0;

    public RocketTask(Player target) {
        this.target = target;
    }

    @Override
    public void onRun(int i) {
        target.teleport(target.getPosition().add(0, 1, 0));
        target.getLevel().addParticle(new SmokeParticle(target.getPosition()));
        target.getLevel().addParticle(new SmokeParticle(target.getPosition().add(-.1)));
        target.getLevel().addParticle(new SmokeParticle(target.getPosition().add(.1)));
        target.getLevel().addParticle(new SmokeParticle(target.getPosition().add(0, 0, .1)));
        target.getLevel().addParticle(new SmokeParticle(target.getPosition().add(0, 0, -.1)));

        times++;
        if (times > 50) getHandler().cancel();
    }
}
