package fr.neatmonster.nocheatplus.checks.net.protocollib;

import java.util.Arrays;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;

import fr.neatmonster.nocheatplus.NCPAPIProvider;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.checks.net.NetConfig;
import fr.neatmonster.nocheatplus.checks.net.NetData;
import fr.neatmonster.nocheatplus.checks.net.WrongTurn;
import fr.neatmonster.nocheatplus.config.ConfPaths;
import fr.neatmonster.nocheatplus.config.ConfigManager;
import fr.neatmonster.nocheatplus.players.DataManager;
import fr.neatmonster.nocheatplus.players.IPlayerData;

public class WrongTurnAdapter extends BaseAdapter {
	
	private final WrongTurn wrongTurn = new WrongTurn(); 
	
	public WrongTurnAdapter(Plugin plugin) {
		super(plugin, ListenerPriority.LOW, PacketType.Play.Client.LOOK, PacketType.Play.Client.POSITION_LOOK);
		if (ConfigManager.isTrueForAnyConfig(ConfPaths.NET_WRONGTURN_ACTIVE)) {
			NCPAPIProvider.getNoCheatPlusAPI().addFeatureTags("checks", Arrays.asList(WrongTurn.class.getSimpleName()));
		}
	}
	
	@Override
	public void onPacketReceiving(final PacketEvent event) {
		final StructureModifier<Float> floats = event.getPacket().getFloat();
		final float pitch = floats.read(1);
		final Player player = event.getPlayer();
		
		final IPlayerData pData = DataManager.getPlayerData(player);
        final NetData data = pData.getGenericInstance(NetData.class);
        final NetConfig cc = pData.getGenericInstance(NetConfig.class);
		
		if (pData.isCheckActive(CheckType.NET_WRONGTURN, player) && wrongTurn.check(player, pitch, data, cc)) {
			event.setCancelled(true); // Is it a good idea to cancel or should we just reset the players pitch?
		}
	}

}
