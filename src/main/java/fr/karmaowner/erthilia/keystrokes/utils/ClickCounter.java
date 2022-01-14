package fr.karmaowner.erthilia.keystrokes.utils;

import java.util.LinkedList;
import java.util.Queue;

public class ClickCounter {
	
	  private final Queue<Long> clicks = new LinkedList<>();

	    /**
	     * Register a new click.
	     */
	    public void onClick() {
	        clicks.add(System.currentTimeMillis() + 1000L);
	    }

	    /**
	     * Get the amount of clicks registered in the past second.
	     *
	     * @return The clicks per second
	     */
	    public int getCps() {
	        long time = System.currentTimeMillis();

	        while (!clicks.isEmpty() && clicks.peek() < time) {
	            clicks.remove();
	        }

	        return clicks.size();
	    }
}
