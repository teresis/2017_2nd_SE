package faceduck.actors;

import faceduck.skeleton.interfaces.Animal;
import faceduck.skeleton.interfaces.Command;
import faceduck.skeleton.interfaces.World;
import faceduck.skeleton.util.Direction;
import faceduck.skeleton.util.Location;

import faceduck.ai.ReleaserAI;

/**
 * @versoin vw 11 Oct 2017
 * 
 * @author SaeHan Lee
 */
public class Releaser implements Animal {

	private boolean whichrelease;
	private ReleaserAI releaserAI;
	private Command cmd;
	/**
	 * Releaser constructor
	 */
	public Releaser() {
		whichrelease = false;
		releaserAI = new ReleaserAI();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Act function
	 * 
	 * @param world
	 *            world which is connected
	 */
	public void act(World world) {
		// TODO Auto-generated method stub
		cmd = releaserAI.act(world, this);
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
		return 0;
	}

	/**
	 * get CoolDown value(not used)
	 * 
	 * @return CoolDown
	 */
	public int getCoolDown() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * get energy value(int)(not used)
	 * 
	 * @return energy value
	 */
	public int getEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * get MaxEnergy value(int)(not used)
	 * 
	 * @return MAX_ENERGY
	 */

	public int getMaxEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * get Breed under Limit value(int)(not used)
	 * 
	 * @return BREED_LIMIT
	 */
	public int getBreedLimit() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		Location prev = world.getLocation(this);
		world.remove(this);
		world.add(this, new Location(prev, dir));

	}

	/**
	 * Release function
	 * 
	 * @param world
	 *            world which is connected
	 * @param dir
	 *            direction where to release animal
	 */
	public void breed(World world, Direction dir) {
		if (whichrelease) {
			FoxImpl child = new FoxImpl();
			world.add(child, new Location(world.getLocation(this), dir));
		} else {
			RabbitImpl child = new RabbitImpl();
			world.add(child, new Location(world.getLocation(this), dir));
		}
		// TODO Auto-generated method stub

	}

	/**
	 * Determine which kinds of animal released
	 * 
	 * @param b
	 */
	public void setwhichrelease(Boolean b) {
		whichrelease = b;
	}

}
