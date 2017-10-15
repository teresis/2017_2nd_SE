package faceduck.actors;

import faceduck.ai.FoxAI;
import faceduck.skeleton.interfaces.Actor;
import faceduck.skeleton.interfaces.Command;
import faceduck.skeleton.interfaces.Fox;
import faceduck.skeleton.interfaces.World;
import faceduck.skeleton.util.Direction;
import faceduck.skeleton.util.Location;

/**
 * @versoin vw 11 Oct 2017
 * 
 * @author SaeHan Lee
 * 
 * Rabbit is an {@link Actor}. Rabbit moves randomly if it can move.
 * When its energy is up to BREED_LIMIT, it breeds
 */
public class FoxImpl implements Fox {
	private static final int FOX_MAX_ENERGY = 160;
	private static final int FOX_VIEW_RANGE = 5;
	private static final int FOX_BREED_LIMIT = FOX_MAX_ENERGY * 2 / 3;
	private static final int FOX_COOL_DOWN = 2;
	private static final int FOX_INITAL_ENERGY = FOX_MAX_ENERGY * 1 / 2;
	private Command cmd;
	private int energy;
	private FoxAI foxAI;

	/**
	 * FoxImpl class constructor
	 * 
	 */
	public FoxImpl() {
		energy = FOX_INITAL_ENERGY;
		foxAI = new FoxAI();
		// TODO Auto-generated constructor stub
	}

	/**
	 * FoxImpl class constructor
	 * 
	 * @param energy
	 *            the number which you put in the energy value
	 */
	public FoxImpl(int energy) {
		this.energy = energy;
		foxAI = new FoxAI();
	}

	/**
	 * get energy value(int)
	 * 
	 * @return	energy value
	 */
	public int getEnergy() {
		// TODO Auto-generated method stub
		return energy;
	}

	/**
	 * get MaxEnergy value(int)
	 * 
	 * @return MAX_ENERGY
	 */

	public int getMaxEnergy() {
		// TODO Auto-generated method stub
		return FOX_MAX_ENERGY;
	}

	/**
	 * get Breed under Limit value(int)
	 * 
	 *  @return BREED_LIMIT 	
	 */
	public int getBreedLimit() {
		// TODO Auto-generated method stub
		return FOX_BREED_LIMIT;
	}

	/**
	 * Eat function
	 * @param world
	 *            world which is connected
	 * @param dir
	 *            direction where to eat
	 */
	public void eat(World world, Direction dir) {
		Location rab_loc = new Location(world.getLocation(this), dir);
		world.remove(world.getThing(rab_loc));
		this.energy += 20;
		if (this.energy >= FOX_MAX_ENERGY)
			this.energy = FOX_MAX_ENERGY;
		// TODO Auto-generated method stub

	}

	/**
	 * 	Move function
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
	 * Breed function
	 * 
	 * @param world
	 *            world which is connected
	 * @param dir
	 *            direction where to put child
	 */
	public void breed(World world, Direction dir) {
		// TODO Auto-generated method stub
		this.energy /= 2;
		FoxImpl child = new FoxImpl(this.getEnergy());
		world.add(child, new Location(world.getLocation(this), dir));
	}

	/** 
	 * 	Act function
	 * 
	 * @param world
	 *            world which is connected
	 */
	public void act(World world) {
		this.energy--;
		if (this.energy > 0) {
			cmd = foxAI.act(world, this);
			if (cmd != null) {
				cmd.execute(world, this);
			}
		} else
			world.remove(this);
		// TODO Auto-generated method stub

	}

	/**
	 * get viewRange
	 * 
	 * @return VIEW_RANGE
	 */

	public int getViewRange() {
		// TODO Auto-generated method stub
		return FOX_VIEW_RANGE;
	}
	/**
	 *  get CoolDown value
	 *  
	 *  @return CoolDown
	 */
	public int getCoolDown() {
		// TODO Auto-generated method stub
		return FOX_COOL_DOWN;
	}

}
