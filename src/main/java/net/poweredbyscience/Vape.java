package net.poweredbyscience;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by lax on 9/23/16.
 */
public class Vape extends JavaPlugin {

    public static Vape instance;

    public void onEnable() {
        instance = this;
        getCommand("vape").setExecutor(new VapeCommand());
    }

    public void smokeVape(Player p) {
        smokeIt(p);
        p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 160, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 10));
    }

    //https://www.youtube.com/channel/UCIA3ywM1G19ZlIfD1HOKOjg
    public void smokeIt(final Player player){
        new BukkitRunnable(){
            double phi = 0;
            public void run(){
                phi = phi + Math.PI/8;
                double x, y, z;
                Location location1 = player.getLocation();
                for (double t = 0; t <= 2*Math.PI; t = t + Math.PI/16){
                    for (double i = 0; i <= 1; i = i + 1){
                        x = 0.4*(2*Math.PI-t)*0.5*Math.cos(t + phi + i*Math.PI);
                        y = 0.5*t;
                        z = 0.4*(2*Math.PI-t)*0.5*Math.sin(t + phi + i*Math.PI);
                        location1.add(x, y, z);
                        player.getWorld().spawnParticle(Particle.SMOKE_LARGE, player.getLocation(), 2);
                        location1.subtract(x,y,z);
                    }
                }
                if(phi > 10*Math.PI){
                    this.cancel();
                }
            }
        }.runTaskTimer(this, 0, 2);
    }

}
