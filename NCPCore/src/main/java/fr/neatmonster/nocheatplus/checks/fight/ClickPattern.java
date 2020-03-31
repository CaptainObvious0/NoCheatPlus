package fr.neatmonster.nocheatplus.checks.fight;

import java.util.HashMap;

import org.bukkit.ChatColor;
// import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.neatmonster.nocheatplus.checks.Check;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.permissions.Permissions;
import fr.neatmonster.nocheatplus.players.IPlayerData;
import fr.neatmonster.nocheatplus.utilities.TickTask;
// import net.minecraft.server.v1_13_R2.EntityPlayer;

public class ClickPattern extends Check {
	
	/**
     * Instantiates a new click pattern check.
     */
    public ClickPattern() {
        super(CheckType.FIGHT_CLICKPATTERN);
    }
    
    /*public int getPing(Player p) { 
    	CraftPlayer cp = (CraftPlayer) p; EntityPlayer ep = cp.getHandle(); return ep.ping; 
    } */
    
    HashMap<String, Long> clickData = new HashMap<String, Long>();
    
    // Resets the click times to check for another click difference
    private void resetClickTime(HashMap<String, Long> clickData) {
    	clickData.put("thisAttackTime", 0L);
		clickData.put("lastAttackTime", 0L);
    }
    
    // Resets all the attack times once the check has been finished
    private void resetAllTimes(HashMap<String, Long> clickData) {
    	clickData.put("thisAttackTime", 0L);
		clickData.put("lastAttackTime", 0L);
		clickData.put("thisClickDifference", 0L);
		clickData.put("lastClickDifference", 0L);
    }
    
    /*
    * This check compares the players time between each click, and attempts to find a pattern,
    * Right now, this is bare-bone version, and more testing/fixing needs to be done to improve it.
    * This can detect AutoClickers or KillAuras that do not allow aps ranges.
    * 
    */
    public boolean check(final Player player, final FightData data, final IPlayerData pData, final FightConfig cc) {
    	
    	boolean cancel = false;
    	final long now = System.currentTimeMillis();
    	
    	// Adds the HashMap if it has not already been added
    	if (clickData.isEmpty()) {
    	clickData.putIfAbsent("lastAttackTime", 0L);
        clickData.putIfAbsent("thisAttackTime", 0L);
        clickData.putIfAbsent("lastClickDifference", 0L);
        clickData.putIfAbsent("thisClickDifference", 0L);	
    	}
    	
    	// Skip the player if they are lagging
    	if (TickTask.getLag(1000, true) > 1.3f) return false;
    	
    	// Setting the attack times
    	else if (clickData.get("lastAttackTime").equals(0L)) {
    		clickData.put("lastAttackTime", now);
    	}
    	else if (clickData.get("thisAttackTime").equals(0L)) {
    		clickData.put("thisAttackTime", now);
    	}
    	
    	// Calculating the difference
    	if (!clickData.get("lastAttackTime").equals(0L) && !clickData.get("thisAttackTime").equals(0L)) {
    		if (clickData.get("lastClickDifference").equals(0L)) {
    			clickData.put("lastClickDifference", Math.abs(clickData.get("thisAttackTime") - clickData.get("lastAttackTime")));
    			resetClickTime(clickData);
    		} else if (clickData.get("thisClickDifference").equals(0L)) {
    			clickData.put("thisClickDifference", Math.abs(clickData.get("thisAttackTime") - clickData.get("lastAttackTime")));
    			if (clickData.get("thisClickDifference") == clickData.get("lastClickDifference") || cc.clickRange > Math.max(clickData.get("thisClickDifference") - clickData.get("lastClickDifference"), clickData.get("lastClickDifference") - clickData.get("thisClickDifference"))) {
    				cancel = executeActions(player, data.clickPatternVL, 1D, pData.getGenericInstance(FightConfig.class).clickActions).willCancel();
    				data.clickPatternVL += 1D;
    			} else {
    				data.clickPatternVL *= 0.86D;
    			}
    			if (pData.isDebugActive(type) && pData.hasPermission(Permissions.ADMINISTRATION_DEBUG, player)) {
				    player.sendMessage(ChatColor.RED + "NCP Debug: " + ChatColor.RESET + "ThisClick Difference: " + clickData.get("thisClickDifference") + " Last Click Differnce: " + clickData.get("lastClickDifference") + ChatColor.RED + " false");
				}
    			resetAllTimes(clickData);
    		}
    	}
    	if (cancel) {
    		data.clicPatPenalty.applyPenalty(cc.clickPen);
    	}
    	return cancel;
    	}
    }

	/**
	 * Code to be touched up/fixed later, and the effectiveness of this check needs to be tested. 
	 * This part would get the average of their click difference and attempt to find a pattern there. 
	 * Could be useful to attempt to eliminate false positives with the click difference check.
	 * Requires Java 8 to calculate the average, but a for method could be used to keep cross compatibility
	 * 
	 * if (avgData.size() >= 8) {
	 *     player.sendMessage("Calculating avg:");
	 *     if (calcAvg(avgData) <= 25) {
	 *         player.sendMessage("Avg is " + ChatColor.GREEN + "true " + avgData);
	 *     } else {
	 *       player.sendMessage("Avg is " + ChatColor.RED + "false " + avgData);
	 *     }
	 * }
	 * 
	 * avgData.add(Math.max(clickData.get("thisClickDifference") - clickData.get("lastClickDifference"), clickData.get("lastClickDifference") - clickData.get("thisClickDifference")));
	 * 
	 * private double calcAvg(ArrayList <Long> avgData) {
	 *    double avg = avgData.stream().mapToInt(Long::intValue).average().getAsDouble();
	 *    int size = avgData.size();
	 *    Bukkit.getLogger().info("[NoCheatPlus] avg: " + avg + " size " + size);
	 * 
	 *    return avg / size;
	 * }
	 * 
	 * ArrayList<Long> avgData = new ArrayList<Long>();
	 */
   
