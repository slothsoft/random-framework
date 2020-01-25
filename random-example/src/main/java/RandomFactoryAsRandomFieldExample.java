
import de.slothsoft.random.RandomFactory;
import de.slothsoft.random.RandomField;
import de.slothsoft.random.types.CharacterRandomField;

/**
 * This example shows how to set up a {@link RandomFactory} as a {@link RandomField}. When
 * run it displays the cutest little cars on the console.
 *
 * @author Stef Schulz
 * @since 2.1.0
 */

public class RandomFactoryAsRandomFieldExample {

	public static void main(String[] args) {
		final RandomFactory<Passenger> driverFactory = RandomFactory.forClass(Passenger.class);
		driverFactory.addRandomField("initial", new CharacterRandomField().capitalProbability(1));

		final RandomFactory<Passenger> passengerFactory = RandomFactory.forClass(Passenger.class);
		passengerFactory.addRandomField("initial",
				new CharacterRandomField().capitalProbability(1).nullProbability(0.5));

		final RandomFactory<Car> factory = RandomFactory.forClass(Car.class);
		factory.addRandomField("driver", driverFactory);
		factory.addRandomField("shotgun", passengerFactory);
		factory.addRandomField("backseatLeft", passengerFactory);
		factory.addRandomField("backseatRight", passengerFactory);

		for (final Car person : factory.create(5)) {
			System.out.println(person);
		}
	}

	public static class Car {

		private Passenger driver;
		private Passenger shotgun;
		private Passenger backseatLeft;
		private Passenger backseatRight;

		@Override
		public String toString() {
			return "+-o---+\n( " + toString(this.driver) + ' ' + toString(this.shotgun) + " )\n|     |\n( "
					+ toString(this.backseatLeft) + ' ' + toString(this.backseatRight) + " )\n+-----+\n";
		}

		private static char toString(Passenger passenger) {
			return passenger == null ? ' ' : (passenger.initial == null ? ' ' : passenger.initial.charValue());
		}

		public Passenger getDriver() {
			return this.driver;
		}

		public void setDriver(Passenger driver) {
			this.driver = driver;
		}

		public Passenger getShotgun() {
			return this.shotgun;
		}

		public void setShotgun(Passenger shotgun) {
			this.shotgun = shotgun;
		}

		public Passenger getBackseatLeft() {
			return this.backseatLeft;
		}

		public void setBackseatLeft(Passenger backseatLeft) {
			this.backseatLeft = backseatLeft;
		}

		public Passenger getBackseatRight() {
			return this.backseatRight;
		}

		public void setBackseatRight(Passenger backseatRight) {
			this.backseatRight = backseatRight;
		}

	}

	public static class Passenger {

		Character initial;

		public Character getInitial() {
			return this.initial;
		}

		public void setInitial(Character initial) {
			this.initial = initial;
		}

	}

}
