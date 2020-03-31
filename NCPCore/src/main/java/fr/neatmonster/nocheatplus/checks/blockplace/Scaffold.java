package fr.neatmonster.nocheatplus.checks.blockplace;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import fr.neatmonster.nocheatplus.checks.Check;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.components.registry.feature.TickListener;
import fr.neatmonster.nocheatplus.players.IPlayerData;
import fr.neatmonster.nocheatplus.utilities.TickTask;

public class Scaffold extends Check {
	
	public Scaffold() {
        super(CheckType.BLOCKPLACE_SCAFFOLD);
    }
	
	final static double MAX_ANGLE = Math.toRadians(90);

	public boolean check(Player player, BlockFace placedFace, final IPlayerData pData, final BlockPlaceData data, final BlockPlaceConfig cc) {
		boolean cancel = false;
		
		final Vector placedVector = new Vector(placedFace.getModX(), placedFace.getModY(), placedFace.getModZ());
	    float placedAngle = player.getLocation().getDirection().angle(placedVector);
	    long now = System.currentTimeMillis();
	    float pitchNow = player.getLocation().getPitch();
	    int current = player.getInventory().getHeldItemSlot();
	        
	    // Angle Check
	    if (cc.scaffoldAngle && placedAngle > MAX_ANGLE) {
	    	cancel = executeActions(player, data.scaffoldVL, 1D, pData.getGenericInstance(BlockPlaceConfig.class).scaffoldActions).willCancel();
			data.scaffoldVL += 1D;
	    }
	    
	    // Average time check
	    if (cc.scaffoldTime && data.sneakTime + 150 < now && !player.hasPotionEffect(PotionEffectType.SPEED)) {
			// TODO: need to make this more efficient
			if (data.placeTime.size() > 2) {
				long avg = 0;
				for (int i = 0; i < data.placeTime.size(); i++) {
					avg += data.placeTime.get(i);
				}
				avg = avg / data.placeTime.size();
				if (avg < 238L) {
					cancel = executeActions(player, data.scaffoldVL, 1D, pData.getGenericInstance(BlockPlaceConfig.class).scaffoldActions).willCancel();
					data.scaffoldVL += 1D;
				}
				data.lastPlaceAvg = avg;
				data.placeTime.clear();
				data.lastPlaceTime = 0L;
				
			} else {
				if (data.lastPlaceTime == 0) {
					data.lastPlaceTime = now;
				} else {
					long diff = now - data.lastPlaceTime;
					data.placeTime.add(diff);
					data.lastPlaceTime = now;
					if (diff <= data.lastPlaceAvg && data.lastPlaceAvg < 238L) {
						cancel = executeActions(player, data.scaffoldVL, 1D, pData.getGenericInstance(BlockPlaceConfig.class).scaffoldActions).willCancel();
						data.scaffoldVL += 1D;
					}
				}
			}
		}
	    
	    // Pitch Check
	    //Bukkit.getScheduler().runTaskLater(data.plugin, checkDiff(player, now, pData, data), 2L);
	    // Tool switch check
	   // Bukkit.getScheduler().runTaskLater(data.plugin, checkTool(player, current, pData, data), 1L);
	    
	    // Sprint check
	    long diff = System.currentTimeMillis() - data.sprintTime;
		if (cc.scaffoldSprint && diff < 400) {
			cancel = executeActions(player, data.scaffoldVL, 1D, pData.getGenericInstance(BlockPlaceConfig.class).scaffoldActions).willCancel();
			data.scaffoldVL += 1D;
		}
		
		return cancel;
	}
	
		// Check diff
		private void checkDiff(Player player, float now, final IPlayerData pData, final BlockPlaceData data) {
			float pitchNow = player.getLocation().getPitch();
			float diff = Math.abs(now - pitchNow);
			
			if (diff > 20F) {
				executeActions(player, data.scaffoldVL, 1D, pData.getGenericInstance(BlockPlaceConfig.class).scaffoldActions).willCancel();
				data.scaffoldVL += 1D;
			}

		}
		
		private void checkTool(Player player, int item, final IPlayerData pData, final BlockPlaceData data) {
				int newItem = player.getInventory().getHeldItemSlot();
				
				if (newItem != item) {
					executeActions(player, data.scaffoldVL, 1D, pData.getGenericInstance(BlockPlaceConfig.class).scaffoldActions).willCancel();
					data.scaffoldVL += 1D;
				}
		}

}
