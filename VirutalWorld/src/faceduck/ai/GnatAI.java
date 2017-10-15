package faceduck.ai;

import faceduck.commands.MoveCommand;
import faceduck.skeleton.interfaces.AI;
import faceduck.skeleton.interfaces.Actor;
import faceduck.skeleton.interfaces.Command;
import faceduck.skeleton.interfaces.World;
import faceduck.skeleton.util.*;

/**
 * The AI for a Gnat. This AI will pick a random direction and then return a
 * command which moves in that direction.
 *
 * This class serves as a simple example for how other AIs should be
 * implemented.
 */
public class GnatAI extends AbstractAI implements AI {

	/*
	 * Your AI implementation must provide a public default constructor so that
	 * the it can be initialized outside of the package.
	 */
	public GnatAI() {
	}

	/*
	 * GnatAI is dumb. It disregards its surroundings and simply tells the Gnat
	 * to move in a random direction.
	 */
	@Override
	public Command act(World world, Actor actor) {
		Boolean isactionable = false;
		Object j =this;
		Direction nextdir;
		Location nextLoc;
		/*
		 * search around object
		 * if there are null value, which means actors can execute move 
		 * ismovable will be true
		 */
		for (Direction i : Direction.values()) {
			if (world.isValidLocation(new Location(world.getLocation(actor), i))) {
				if (world.getThing(new Location(world.getLocation(actor), i)) == null) {
					isactionable = true;
					break;
				}
			}
		}
		/* 
		 * if is actionable is true,
		 * there are null place to move it
		 * So, Ai makes decision which place go to move, 
		 * and return move commands
		 * 
		 * else, gnat stay silence
		 */
		if (isactionable) {
			do {
				nextdir = Util.randomDir();
				nextLoc = new Location(world.getLocation(actor), nextdir);
				if(world.isValidLocation(nextLoc)){
				j = world.getThing(nextLoc);
				}
			} while (!(j == null && world.isValidLocation(nextLoc)));
			return new MoveCommand(nextdir);
		} else
			return null;
	}
}
