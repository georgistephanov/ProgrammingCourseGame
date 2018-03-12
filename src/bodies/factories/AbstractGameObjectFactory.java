package bodies.factories;

import city.cs.engine.World;

/**
 * Abstract implementation of the {@code GameObjectFactory} which provides implementations of commonly used methods
 * in concrete factories.
 *
 * @param <T>  the type of the game object which is to be created
 */
abstract class AbstractGameObjectFactory<T> implements GameObjectFactory<T> {

	final World world;

	AbstractGameObjectFactory(World world) {
		this.world = world;
	}

	@Override
	public abstract T create(String line);

	/**
	 * Returns the number of strings in the current line of the file separated by a tab character (\t).
	 *
	 * @param line  line of a text file which specifies level elements
	 * @return  the number of strings separated by a tab character
	 */
	int stringsInLine(String line) {
		return line.split("\t").length;
	}

	/**
	 * Converts and returns the strings which represent a number as an array of floats.
	 *
	 * @param line  line of a text file which specifies level elements
	 * @return  array of floats consisting of the numbers of that line
	 */
	float [] getParametersFromFileLine(String line) {
		String [] stringParameters = line.split("\t");
		float [] parameters = new float[stringParameters.length - 1];

		try {
			// Start from 1 to avoid the object which is specified to be created
			for (int i = 1; i < stringParameters.length; i++) {
				parameters[i - 1] = Float.valueOf(stringParameters[i]);
			}
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			System.out.println("Incorrect parameter in the level file.");
		}

		return parameters;
	}
}
