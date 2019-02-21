package de.slothsoft.random;

/**
 * Class for logging and debugging
 * 
 * @author Steffi
 */
public class RandomFactoryLogger {

	// TODO: why not use an existing logger?
	
	public static boolean LOG = false;

	/**
	 * Log something...
	 */

	public static void log(String string) {
		if (LOG) {
			System.out.println(string);
		}
	}

	/**
	 * Log some info...
	 */

	public static void logInfo(String string) {
		if (LOG) {
			System.out.println("[Info] " + string);
		}
	}

	/**
	 * Log some warning...
	 */

	public static void logWarning(String string) {
		if (LOG) {
			System.out.println("[Warning] " + string);
		}
	}

	/**
	 * Log some error...
	 */

	public static void logError(String string) {
		if (LOG) {
			System.err.println("[Error] " + string);
		}
	}
}
