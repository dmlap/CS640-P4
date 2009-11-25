package edu.bu;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author jdavis
 *
 * Class that handles the logic of the player
 */
public class Player {	
	private int depth = 3;
	private Evaluator evaluator;

	/**
	 * @param depth
	 * @param evaluator
	 */
	public Player(int depth, Evaluator evaluator) {
		this.depth = depth;
		this.evaluator = evaluator;
	}

	/**
	 * Main method for the player to make a move
	 * 
	 * @param currentstate
	 * 			The current state of the board
	 * @return
	 * 			The new state of the board with the selected move
	 */
	public AtroposState makeMove(AtroposState currentstate) {
		AtroposCircle bestCircle = null;
		int bestAlpha = Integer.MIN_VALUE;
		
		// loop through all playable next circles
	    Colors[] colors = Colors.values();
		for (Iterator<AtroposCircle> circleIterator = currentstate.playableCircles(); circleIterator.hasNext(); ) {
			AtroposCircle circle = (AtroposCircle) circleIterator.next();
			
			for (int i = 1; i < colors.length; i++){
                int alpha =  0;
                int Mini  = -1;
				AtroposCircle circlecopy = circle.clone();	
				circlecopy.color(i); 							// color the circle
				AtroposState nextState = currentstate.clone(); 	// copy the current state
				nextState.makePlay(circlecopy); 				// make move on copy 
				
				if (currentstate.lastPlay == null)              // check if the level is a 
                    Mini = 0;                                   // maximizing level or a
                else
                    Mini = 1;                                   // minimizing level.
                
                alpha = alphabeta(nextState, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, Mini);
				
				if (alpha > bestAlpha) {
					bestAlpha = alpha;
					bestCircle = circlecopy;
				}
			}
		}
		
		// Make the play
		currentstate.makePlay(bestCircle);
		
		// Return the board with the new move
		return currentstate;
	}
	
	/**
	 * The main method for calculating the alpha beta values for the current move
	 *  
	 * @param sm
	 * 			The StateMove object holding the current state and the proposed move
	 * @param depth
	 * 			How deep the alpha beta should go down the tree
	 * @param alpha
	 * 			The current alpha value
	 * @param beta
	 * 			The current beta value
	 * @return
	 * 			The value of the proposed move
	 */
	private int alphabeta(AtroposState state, int depth, int alpha, int beta, int Mini){
        ArrayList<AtroposState> childStates = new ArrayList<AtroposState>();
		
        // If we got a losing condition or the depth is reached, return
	    if(state.isFinished() || depth == 0)
			return evaluator.evaluateMove(state);
	    
	    // loop through all playable next circles
	    Colors[] colors = Colors.values();
		for (Iterator<AtroposCircle> circleIterator = state.playableCircles(); circleIterator.hasNext(); ) {
			AtroposCircle circle = (AtroposCircle) circleIterator.next();
			
			for(int i = 1; i < colors.length; i++){
				AtroposCircle circlecopy = circle.clone();	
				circlecopy.color(i); 						// color the circle
				AtroposState nextState = state.clone(); 	// copy the current state
				nextState.makePlay(circlecopy); 			// make move on copy 
				childStates.add(nextState);
			}
		}
		
		if (Mini == 0) {
            // loop through all child states and call alphabeta
		    for(AtroposState child : childStates) {	
                alpha = Math.max(alpha, -alphabeta(child, depth-1, -beta, -alpha, Mini));
			
			    if (beta <= alpha)
				    return alpha;
		    }
		
		    return alpha;
        }
        else {
            // loop through all child states and call alphabeta
		    for(AtroposState child : childStates) {	
			    beta = Math.max(alpha, -alphabeta(child, depth-1, -beta, -alpha, Mini));
			
			    if (alpha >= beta)
				    return beta;
		    }
		
		    return beta;
        }

	}
}
