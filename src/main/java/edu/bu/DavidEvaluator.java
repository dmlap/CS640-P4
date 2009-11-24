/**
 * 
 */
package edu.bu;

import java.util.EnumMap;
import java.util.HashMap;

/**
 * @author dml
 *
 */
public class DavidEvaluator implements Evaluator {

	/* (non-Javadoc)
	 * @see edu.bu.Evaluator#evaluateMove(edu.bu.AtroposState)
	 */
	@Override
	public int evaluateMove(AtroposState currentstate) {
		if (currentstate.isFinished()) {
			return Integer.MIN_VALUE;
		}
		EnumMap<Colors, Colors> pairs = new EnumMap<Colors, Colors>(
				Colors.class);
		int top = Math.min(currentstate.lastPlay.height(), currentstate.circles.length - 3);
		int left = Math.min(currentstate.lastPlay.leftDistance(), currentstate.circles.length - 3);
		for(int h = 0; h < 2; ++h) {
			for (int l = 0; l < 2; ++l) {
				pairs.put(Colors.values()[currentstate.circles[top + h][left + l].getColor()], Colors.Uncolored);
			}
		}
		return 0;
	}

}
