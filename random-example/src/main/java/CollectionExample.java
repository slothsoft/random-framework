
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.types.CollectionRandomField;
import de.slothsoft.random.types.WordRandomField;

/**
 * This example shows how {@link Collection}s are filled. Sadly they can't be filled
 * automatically, yet.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class CollectionExample {

	public static void main(String[] args) {
		final RandomFactory<Pojo> factory = new RandomFactory<>(Pojo::new);
		factory.addRandomField("strings", new CollectionRandomField(ArrayList::new, new WordRandomField()));

		for (final Pojo pojo : factory.create(10)) {
			System.out.println(pojo);
		}
	}

	public static class Pojo {

		private List<String> strings;

		public List<String> getStrings() {
			return this.strings;
		}

		public void setStrings(List<String> strings) {
			this.strings = strings;
		}

		@Override
		public String toString() {
			return "strings: " + this.strings;
		}
	}

}
