package fr.neatmonster.nocheatplus.checks.fight;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.neatmonster.nocheatplus.checks.Check;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.checks.net.NetConfig;
import fr.neatmonster.nocheatplus.checks.net.NetData;
import fr.neatmonster.nocheatplus.players.IPlayerData;

public class FightSync extends Check {
	
	public FightSync() {
        super(CheckType.NET_FIGHTSYNC);
    }

	public boolean check(Player player, IPlayerData pData, NetData data, Location packet, Location eventLoc, final NetConfig cc) {
		boolean cancel = false;
		if (eventLoc != null && packet != null) {
		double diff = Math.abs(eventLoc.getYaw() - packet.getYaw()) + Math.abs(eventLoc.getPitch() - packet.getPitch());	
		
		if (diff != 0) {
			if (data.fightSyncCount >= cc.fightSyncThreshold) {
				data.fightSyncVL += diff * 5;
				cancel = executeActions(player, data.fightSyncVL, Math.abs(diff * 5), pData.getGenericInstance(NetConfig.class).fightSyncActions).willCancel();	
				data.fightSyncCount = 0;
			} else {
				data.fightSyncCount += 1;
			}
		}
		}
		return cancel;
	}

}
