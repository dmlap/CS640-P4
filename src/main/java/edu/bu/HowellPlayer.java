package edu.bu;

import java.io.IOException;
import java.io.StringReader;

public class HowellPlayer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		AtroposState state = new AtroposStateReader(new StringReader(args[0])).read();
		
		// Calculate next move
		Player player = new Player(7, new HowellEvaluator());
		AtroposState newstate = player.makeMove(state);
		
		System.out.println(newstate.lastPlay.getColorLocationString());
	}

}
