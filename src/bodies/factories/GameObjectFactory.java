package bodies.factories;

import java.security.InvalidParameterException;

/**
 * Contract for creating game objects from a given line of a file (located in data/levels/level?/).
 * Game objects include the following:
 * <ul>
 *     <li>Platforms</li>
 *     <li>Walls</li>
 *     <li>Enemies</li>
 *     <li>Collectibles</li>
 * </ul>
 *
 * @param <T>  the type of the game object which is to be created
 */
interface GameObjectFactory<T> {

	/**
	 * Creates a game object from a line of one of the text files associated with level creation.
	 *
	 * <p>
	 * The line contains several strings divided by a tab character (\t).
	 * The first string of the line contains a letter which specifies the type of the object to be created.
	 * The other strings of the line contain floating point decimal numbers representing several of the following:
	 * <ul>
	 *     <li>{@code Platform}/{@code Wall}: [width|height], [x], [y]</li>
	 *     <li>{@code Enemy}: [walking direction (0-left, 1-right)], [x], [y]</li>
	 *     <li>{@code Collectible}: [x], [y]</li>
	 * </ul>
	 * (platforms) [width|height], [x], [y]
	 * (enemies) [walking direction (0-left, 1-right)], [x], [y]
	 * (collectibles) [x], [y]
	 * </p>
	 *
	 * @param line  line of a text file which specifies level elements
	 * @return  the object created
	 * @throws InvalidParameterException  if the line is in incorrect format and an object cannot be created
	 */
	T create(String line) throws InvalidParameterException;
}
