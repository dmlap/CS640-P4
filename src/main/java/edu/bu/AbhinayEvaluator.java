package edu.bu;
import java.lang.Math;

/**
 * @author Abhinay
 *
 */
public class AbhinayEvaluator implements Evaluator {

	/* (non-Javadoc)
	 * @see edu.bu.Evaluator#evaluateMove(edu.bu.AtroposState)
	 */
	@Override
	public int evaluateMove(AtroposState currentstate) {
		int value = 0;
        ScoreEvaluator sceval    = new ScoreEvaluator();
        NeighborsGenerator nbgen = new NeighborsGenerator();
		
		// If this is a losing move, immediately return -100
		if (currentstate.isFinished())
			return -100;
		else {
            // Get last play
            int height       = currentstate.lastPlay.height();
            int leftDistance = currentstate.lastPlay.leftDistance();

            // This ordering is importent to check for Triads
            int[] neighbors = nbgen.generate(height, leftDistance, currentstate);

            value = sceval.evaluate(neighbors, height, leftDistance, currentstate);
            
            // If the move is a winning move, assign value 100.
            if(value == 0)
                value = 100;
           
           
		} 
		
		return value;
	}

}
