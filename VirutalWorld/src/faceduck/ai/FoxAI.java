package faceduck.ai;

import faceduck.commands.BreedCommand;
import faceduck.commands.EatCommand;
import faceduck.commands.MoveCommand;
import faceduck.skeleton.interfaces.AI;
import faceduck.skeleton.interfaces.Actor;
import faceduck.skeleton.interfaces.Command;
import faceduck.skeleton.interfaces.World;
import faceduck.skeleton.util.Direction;
import faceduck.skeleton.util.Location;
import faceduck.skeleton.util.Util;
import faceduck.skeleton.interfaces.Animal;
public class FoxAI extends AbstractAI implements AI {


	/**
	 * constructor for FoxAI
	 */
	public FoxAI() {
	}
	/** This function will determine action and return command class
	 * 
	 * @param world
	 * 		world which is connected
	 * 
	 * @param actor
	 * 		which fox do return command
	 * 
	 * @return
	 * 		actor value will execute this command
	 * 
	 */
	public Command act(World world, Actor actor) {
		int energy = ((Animal)actor).getEnergy();
		Boolean iseatable = false;
		Boolean ismovable = false;
		Location actor_loc = world.getLocation(actor);
		Direction nextdir;
		Location nextLoc;
		Object j = this;
		/*
		 * search around object
		 * if there are null value, which means actors can execute move or breed
		 * ismovable will be true
		 * and there exist rabbits
		 * iseatable will be true
		 */
		for (Direction i : Direction.values()) {
			if (world.isValidLocation(new Location(actor_loc, i))) {
				Object target = world.getThing(new Location(actor_loc, i));
				if (target == null) {
					ismovable = true;
					continue;
				} else if (target.getClass().getName() == "faceduck.actors.RabbitImpl")
					iseatable = true;
			}
		}
		/* if actor's energy value is enough to breed
		 * then Fox acts breed if there are vacant place around
		 * the place to breed is randomly determined
		 */
		if (energy > ((Animal)actor).getBreedLimit()) {
			if (ismovable) {
				do {
					nextdir = Util.randomDir();
					nextLoc = new Location(world.getLocation(actor), nextdir);
					if (world.isValidLocation(nextLoc)) {
						j = world.getThing(nextLoc);
					}
				} while (!(j == null && world.isValidLocation(nextLoc)));
				return new BreedCommand(nextdir);
			}
			else return null;
		} 
		/*
		 * if actor's energy is not enough to breed
		 * Fox act eat function if there are rabbits around and choose a rabbit randomly
		 * But if rabbit are not existed around, 
		 * then fox randomly move if they can move,
		 *  which means there are null objects around actor 
		 * 
		 */
		else {
			if (iseatable) {
				do{
					nextdir = Util.randomDir();
					nextLoc = new Location(world.getLocation(actor), nextdir);
					if (world.isValidLocation(nextLoc)) {
						j = world.getThing(nextLoc);
					}
				} while (j==null||!((j.getClass().getName() == "faceduck.actors.RabbitImpl") && world.isValidLocation(nextLoc)));
				return new EatCommand(nextdir);
			}
			else if(ismovable){
				do {
					nextdir = Util.randomDir();
					nextLoc = new Location(world.getLocation(actor), nextdir);
					if(world.isValidLocation(nextLoc)){
					j = world.getThing(nextLoc);
					}
				} while (!(j == null && world.isValidLocation(nextLoc)));
				return new MoveCommand(nextdir);
			}
		}
		return null;
	}
}
