package edu.bu;

/**
 * @author Abhinay
 *
 */
public class NeighborsGenerator {
	// Generates the colors of the neighbors and returns an integer
    // array with the colors.
	public int[] generate(int height, int leftDistance, AtroposState currentstate) {
		int leftUpColor    = currentstate.circles[height + 1][leftDistance - 1].getColor();
		int leftColor      = currentstate.circles[height][leftDistance - 1].getColor();
    	int leftDownColor  = currentstate.circles[height - 1][leftDistance].getColor();
		int rightDownColor = currentstate.circles[height - 1][leftDistance + 1].getColor();
		int rightColor     = currentstate.circles[height][leftDistance + 1].getColor();
		int rightUpColor   = currentstate.circles[height + 1][leftDistance].getColor();
	
        // This ordering is importent to check for Triads
        int[] neighbors = {leftColor, leftUpColor, rightUpColor, rightColor, rightDownColor, leftDownColor};                
        return neighbors;
        }
}
