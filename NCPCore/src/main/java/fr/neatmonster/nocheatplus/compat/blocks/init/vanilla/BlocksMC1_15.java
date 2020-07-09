package fr.neatmonster.nocheatplus.compat.blocks.init.vanilla;

import org.bukkit.Material;

import fr.neatmonster.nocheatplus.compat.BridgeMaterial;
import fr.neatmonster.nocheatplus.compat.blocks.BlockPropertiesSetup;
import fr.neatmonster.nocheatplus.compat.blocks.init.BlockInit;
import fr.neatmonster.nocheatplus.config.WorldConfigProvider;
import fr.neatmonster.nocheatplus.logging.StaticLog;
import fr.neatmonster.nocheatplus.utilities.map.BlockProperties;

public class BlocksMC1_15 implements BlockPropertiesSetup{

	public BlocksMC1_15() {
        BlockInit.assertMaterialExists("BEEHIVE");
        BlockInit.assertMaterialExists("HONEY_BLOCK");
    }
	
	@SuppressWarnings("deprecation")
	@Override
	public void setupBlockProperties(WorldConfigProvider<?> worldConfigProvider) {
		BlockProperties.setBlockProps("BEE_NEST", new BlockProperties.BlockProps(BlockProperties.woodAxe, 0.3f, BlockProperties.secToMs(0.45, 0.25, 0.15, 0.1, 0.1, 0.05)));
		BlockProperties.setBlockProps("BEEHIVE", new BlockProperties.BlockProps(BlockProperties.woodAxe, 0.6f, BlockProperties.secToMs(0.9, 0.45, 0.25, 0.15, 0.15, 0.1)));
                BlockInit.setAs("HONEY_BLOCK", Material.TNT);
		BlockProperties.setBlockProps("HONEYCOMB_BLOCK", new BlockProperties.BlockProps(BlockProperties.woodAxe, 0.6f, BlockProperties.secToMs(0.9, 0.9, 0.9, 0.9, 0.9, 0.9)));
		StaticLog.logInfo("Added block-info for Minecraft 1.15 blocks.");
	}

}
