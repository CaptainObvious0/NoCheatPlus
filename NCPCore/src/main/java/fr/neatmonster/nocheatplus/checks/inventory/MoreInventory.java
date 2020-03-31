package fr.neatmonster.nocheatplus.checks.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import fr.neatmonster.nocheatplus.checks.Check;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.checks.moving.MovingData;
import fr.neatmonster.nocheatplus.players.IPlayerData;

public class MoreInventory extends Check{
    public MoreInventory() {
        super(CheckType.INVENTORY_MOREINVENTORY);
    }

    public boolean check(final Player player, final MovingData movingdata, final IPlayerData pData, final InventoryType type, final Inventory inv, final boolean PoYdiff) {
        if (type == InventoryType.CRAFTING 
        && (player.isSprinting() || PoYdiff || player.isBlocking() || player.isSneaking() || movingdata.isusingitem)) {
            return true;
        }
        return false;
    }
}
