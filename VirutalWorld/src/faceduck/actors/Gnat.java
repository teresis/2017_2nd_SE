package faceduck.actors;

import faceduck.ai.GnatAI;
import faceduck.skeleton.interfaces.Animal;
import faceduck.skeleton.interfaces.Command;
import faceduck.skeleton.interfaces.World;
import faceduck.skeleton.util.Direction;
import faceduck.skeleton.util.Location;

/**
 * @versoin vw 11 Oct 2017
 * 
 * @author SaeHan Lee
 * 
 *         This is a simple implementation of a Gnat. It never loses energy and
 *         moves in random directions.
 */
public class Gnat implements Animal {
	private static final int MAX_ENERGY = 10;
	private static final int VIEW_RANGE = 1;
	private static final int BREED_LIMIT = 0;
	private static final int COOL_DOWN = 0;
	private Command cmd;
	private int energy;
	private GnatAI gnatAI;

	/**
	 * Gnat class constructor
	 * 
	 * @param int
	 *            num the number which you put in the energy value
	 */
	public Gnat(int num) {
		energy = num;
		gnatAI = new GnatAI();
	}

	/**
	 * Act function
	 * 
	 * @param world
	 *            world which is connected
	 */
	public void act(World world) {
		// TODO Auto-generated method stub
		cmd = gnatAI.act(world, this);
		if (cmd != null)
			cmd.execute(world, this);
	}

	/**
	 * get viewRange(not used)
	 * 
	 * @return VIEW_RANGE
	 */
	public int getViewRange() {
		// TODO Auto-generated method stub
		return VIEW_RANGE;
	}

	/**
	 * get CoolDown value
	 * 
	 * @return CoolDown
	 */
	public int getCoolDown() {
		// TODO Auto-generated method stub
		return COOL_DOWN;
	}

	/**
	 * get energy value(int)
	 * 
	 * @return energy value
	 */
	public int getEnergy() {
		// TODO Auto-generated method stub
		return energy;
	}

	/**
	 * get MaxEnergy value(int)(not used)
	 * 
	 * @return MAX_ENERGY
	 */

	public int getMaxEnergy() {
		// TODO Auto-generated method stub
		return MAX_ENERGY;
	}

	/**
	 * get Breed under Limit value(int)(not used)
	 * 
	 * @return BREED_LIMIT
	 */
	public int getBreedLimit() {
		// TODO Auto-generated method stub
		return BREED_LIMIT;
	}

	/**
	 * Eat function(not used)
	 * 
	 * @param world
	 *            world which is connected
	 * @param dir
	 *            direction where to eat
	 */
	public void eat(World world, Direction dir) {
		// TODO Auto-generated method stub

	}

	/**
	 * Move function
	 * 
	 * @param world
	 *            world which is connected
	 * @param dir
	 *            direction where to move
	 */
	public void move(World world, Direction dir) {
		Location prev = world.getLocation(this);
		world.remove(this);
		world.add(this, new Location(prev, dir));
		// TODO Auto-generated method stub

	}

	/**
	 * Breed function
	 * 
	 * @param world
	 *            world which is connected
	 * @param dir
	 *            direction where to put child
	 */
	public void breed(World world, Direction dir) {
		// TODO Auto-generated method stub

	}

}
