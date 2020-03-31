/*
 * This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.neatmonster.nocheatplus.checks.blockplace;

import java.util.ArrayList;

import org.bukkit.plugin.Plugin;

import fr.neatmonster.nocheatplus.checks.access.ACheckData;
import fr.neatmonster.nocheatplus.utilities.ds.count.ActionFrequency;

/**
 * Player specific dataFactory for the block place checks.
 */
public class BlockPlaceData extends ACheckData {
	
	//public static NoCheatPlus plugin;

    // Violation levels.
    public double againstVL = 0;
    public double autoSignVL = 0;
    public double directionVL = 0;
    public double fastPlaceVL = 0;
    public double noSwingVL = 0;
    public double reachVL = 0;
    public double scaffoldVL = 0;
    public double speedVL = 0;

    // AutoSign.
    public long autoSignPlacedTime = 0;
    /** Using Material.SIGN . */
    public long autoSignPlacedHash = 0;
    
    // Scaffold data
    public long lastPlaceTime = 0;
	public  long lastPlaceAvg = 0;
	public ArrayList<Long> placeTime = new ArrayList<Long>();
	public long sneakTime = 0;
	public long sprintTime = 0;
	public boolean cancelNextPlace = false;

    // Data of the fast place check.
    public final ActionFrequency fastPlaceBuckets	= new ActionFrequency(2, 1000);
    public int fastPlaceShortTermTick				= 0;
    public int fastPlaceShortTermCount				= 0;

    // Data of the no swing check.
    public boolean noSwingArmSwung = true;

    // Data of the reach check.
    public double  reachDistance;

    // Data of the speed check;
    public boolean speedLastRefused;
    public long    speedLastTime;
    
    // Maybe not the best way to do this. Need to run a task later but need the instance of the main class. Need to figure something out in TiskTask.
    /*public static void addPlugin(NoCheatPlus plug) {
    	plugin = plug;
    } */
    

}
