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
package fr.neatmonster.nocheatplus.components.location;

/**
 * Allow setting both standard 3d coordinates (double) and looking direction
 * (float).
 * 
 * @author asofold
 *
 */
public interface ISetPositionWithLook extends ISetPosition, ISetLook {

    // public void setPositionWithLook(double x, double y, double z, float yaw, float pitch); 

}
