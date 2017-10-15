package faceduck.actors;

import faceduck.ai.RabbitAI;
import faceduck.skeleton.interfaces.Actor;
import faceduck.skeleton.interfaces.Command;
import faceduck.skeleton.interfaces.Edible;
import faceduck.skeleton.interfaces.Rabbit;
import faceduck.skeleton.interfaces.World;
import faceduck.skeleton.util.Direction;
import faceduck.skeleton.util.Location;

/**
 * @versoin vw 11 Oct 2017
 * @author SaeHan Lee Rabbit is an {@link Edible} {@link Actor}. Rabbit moves
 *         randomly if it can move. when its energy is up to BREED_LIMIT, it
 *         breeds
 */
public class RabbitImpl implements Rabbit {
	private static final int RABBIT_MAX_ENERGY = 20;
	private static final int RABBIT_VIEW_RANGE = 3;
	private static final int RABBIT_BREED_LIMIT = RABBIT_MAX_ENERGY * 2 / 4;
	private static final int RABBIT_ENERGY_VALUE = 20;
	private static final int RABBIT_COOL_DOWN = 4;
	private static final int RABBIT_INITAL_ENERGY = RABBIT_MAX_ENERGY * 1 / 2;
	private Command cmd;
	private int energy;
	private RabbitAI rabbitAI;

	/**
	 * RabbitImpl class constructor
	 */
	public RabbitImpl() {
		// TODO Auto-generated constructor stub
		energy = RABBIT_INITAL_ENERGY;
		rabbitAI = new RabbitAI();
	}

	/**
	 * RabbitImpl class constructor
	 * 
	 * @param energy
	 *            the number which you put in the energy value
	 */
	public RabbitImpl(int energy) {
		this.energy = energy;
		rabbitAI = new RabbitAI();
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
	 * get MaxEnergy value(int)
	 * 
	 * @return MAX_ENERGY
	 */
	public int getMaxEnergy() {
		// TODO Auto-generated method stub
		return RABBIT_MAX_ENERGY;
	}

	/**
	 * get Breed under Limit value(int)
	 * 
	 * @return BREED_LIMIT
	 */
	public int getBreedLimit() {
		// TODO Auto-generated method stub
		return RABBIT_BREED_LIMIT;
	}

	/**
	 * Eat function
	 * 
	 * @param world
	 *            world which is connected
	 * @param dir
	 *            direction where to eat
	 */
	public void eat(World world, Direction dir) {
		Location grass_loc = new Location(world.getLocation(this), dir);
		world.remove(world.getThing(grass_loc));
		this.energy += 5;
		if (this.energy > RABBIT_MAX_ENERGY)
			this.energy = RABBIT_MAX_ENERGY;

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
		this.energy /= 2;
		RabbitImpl child = new RabbitImpl(this.getEnergy());
		world.add(child, new Location(world.getLocation(this), dir));

		// TODO Auto-generated method stub

	}

	/**
	 * @param world
	 *            world which is connected
	 */
	public void act(World world) {
		energy--;
		if (energy > 0) {
			cmd = rabbitAI.act(world, this);
			if (cmd != null)
				cmd.execute(world, this);
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
		return RABBIT_VIEW_RANGE;
	}

	/**
	 * get CoolDown value
	 * 
	 * @return CoolDown
	 */
	public int getCoolDown() {
		// TODO Auto-generated method stub
		return RABBIT_COOL_DOWN;
	}

	/**
	 * get EnergyValue
	 * 
	 * @return ENERGY_VALUE;
	 */
	public int getEnergyValue() {
		// TODO Auto-generated method stub
		return RABBIT_ENERGY_VALUE;
	}

}
