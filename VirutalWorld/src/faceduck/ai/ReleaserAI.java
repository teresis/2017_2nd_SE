package faceduck.ai;

import faceduck.commands.BreedCommand;
import faceduck.commands.MoveCommand;
import faceduck.skeleton.interfaces.AI;
import faceduck.skeleton.interfaces.Actor;
import faceduck.skeleton.interfaces.Command;
import faceduck.skeleton.interfaces.World;
import faceduck.skeleton.util.Direction;
import faceduck.skeleton.util.Location;
import faceduck.skeleton.util.Util;
import faceduck.actors.Releaser;

import faceduck.skeleton.world.WorldImpl;
/**
 * The AI for a Releaser. This AI will count both the number of foxes
 * and the number of rabbits
 * If one of them is under 3,
 * the releaser released one of that thing.
 * 
 */
public class ReleaserAI extends AbstractAI implements AI {

	public ReleaserAI() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Command act(World world, Actor actor) {
		// TODO Auto-generated method stub
		int rabbit_num = 0;
		int fox_num = 0;
		Boolean isneededbreed = false;
		Boolean foxORrabbit = false;
		Boolean ismovable = false;
		Location actor_loc = world.getLocation(actor);
		Direction nextdir;
		Location nextLoc;
		Object j = this;
		/*
		 * search around object
		 * if there are null value, which means actors can execute move 
		 * is movable will be true
		 */
		for (Direction i : Direction.values()) {
			if (world.isValidLocation(new Location(actor_loc, i))) {
				Object target = world.getThing(new Location(actor_loc, i));
				if (target == null) {
					ismovable = true;
					break;
				} 
			}
		}
		/*
		 *Search all objects in the world
		 *and count both the number of fox and the number of rabbit 
		 */
		int height =world.getHeight();
		int width = world.getWidth();
		Object[][] O = ((WorldImpl) world).getlocToObj();
		for(int a=0;a<width;a++){
			for(int b=0;b<height;b++){
				if(O[a][b]==null)
					continue;
				else if(O[a][b].getClass().getName()=="faceduck.actors.RabbitImpl")
					rabbit_num++;
				else if(O[a][b].getClass().getName()=="faceduck.actors.FoxImpl")
					fox_num++;
			}
		}
		/*
		 * if one of them is under 3,
		 * releaser need to release.
		 * and foxORrabbit is can be changed
		 */
		if(rabbit_num<3){
			isneededbreed =true;
			foxORrabbit =false;
		}
		else if(fox_num<3){
			isneededbreed =true;
			foxORrabbit =true;
		}
		/*
		 * if releaser need to release
		 * and if there are vacant place to release,
		 * AI return release command
		 */
		if (isneededbreed) {
			if (ismovable) {
				do {
					nextdir = Util.randomDir();
					nextLoc = new Location(world.getLocation(actor), nextdir);
					if (world.isValidLocation(nextLoc)) {
						j = world.getThing(nextLoc);
					}
				} while (!(j == null && world.isValidLocation(nextLoc)));
					((Releaser)actor).setwhichrelease(foxORrabbit);
				
				return new BreedCommand(nextdir);
			} else
				return null;
		}
		/*
		 * if releaser don't need to release
		 * then releaser move if it can,
		 * but there are no vacant place to move,
		 * stay silence
		 */
		else {
			if (ismovable) {
				do {
					nextdir = Util.randomDir();
					nextLoc = new Location(world.getLocation(actor), nextdir);
					if (world.isValidLocation(nextLoc)) {
						j = world.getThing(nextLoc);
					}
				} while (!(j == null && world.isValidLocation(nextLoc)));
				return new MoveCommand(nextdir);
			}
		}
		return null;
	}

}
