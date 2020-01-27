
import java.util.Arrays;

import de.slothsoft.random.RandomFactory;

/**
 * This example shows how arrays are filled.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class ArrayExample {

	public static void main(String[] args) {
		final RandomFactory<Pojo> factory = RandomFactory.forClass(Pojo.class);
		for (final Pojo pojo : factory.create(10)) {
			System.out.println(pojo);
		}
	}

	public static class Pojo {

		private int[] intArray;

		public int[] getIntArray() {
			return this.intArray;
		}

		public void setIntArray(int[] intArray) {
			this.intArray = intArray;
		}

		@Override
		public String toString() {
			return "ints: " + Arrays.toString(this.intArray);
		}
	}

}
