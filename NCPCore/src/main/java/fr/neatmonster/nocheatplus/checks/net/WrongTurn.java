package fr.neatmonster.nocheatplus.checks.net;

import org.bukkit.entity.Player;

import fr.neatmonster.nocheatplus.checks.Check;
import fr.neatmonster.nocheatplus.checks.CheckType;

public class WrongTurn extends Check {
	
	public WrongTurn() {
		super(CheckType.NET_WRONGTURN);
	}
	
	public boolean check(final Player player, final float pitch, final NetData data, final NetConfig cc) {
		boolean cancel = false;
		
		if (Math.abs(pitch) > 90.0 || pitch < -90.0) {
			data.wrongTurnVL++;
			
			if (executeActions(player, data.wrongTurnVL, 1, cc.wrongTurnActions).willCancel()) {
				cancel = true;
			}
			
		}
		
		return cancel;
	}

}
