package zadanie1;

public class Figury {

	enum JedenParam {
		KOLO("Kolo"),
		PIECIOKAT("Pieciokat"),
		SZESCIOKAT("Szesciokat"),
		KWADRAT("Kwadrat");

		public double obliczPole (double a) {
			switch (nazwa) {
				case "Kolo":
					return Math.PI * a * a;
				case "Kwadrat":
					return a * a;
				case "Szesciokat":
					return (3 * Math.sqrt(3) / 2) * a * a;
				case "Pieciokat":
					return (Math.sqrt(25 + 10 * Math.sqrt(5)) / 4) * a * a;
				default:
					return 0;
			}
		}

		public double obliczObwod(double a) {
			switch (nazwa) {
				case "Kolo":
					return 2 * Math.PI * a ;
				case "Kwadrat":
					return 4 * a;
				case "Szesciokat":
					return 6 * a;
				case "Pieciokat":
					return 5 * a;
				default:
					return 0;
			}
		}

		public final String nazwa;

		JedenParam(String nazwa) {
			this.nazwa = nazwa;
		}
	}

	enum DwaParamy {
		PROSTOKAT("Prostokat"),
		ROMB("Romb");

		public double obliczPole (double a, double b) {
			switch (nazwa) {
				case "Prostokat":
					return a * b;
				case "Romb":
					return a * a * Math.sin(Math.toRadians(b));
				default:
					return 0;
			}
		}

		public double obliczObwod(double a, double b) {
			switch (nazwa) {
				case "Prostokat":
					return 2 * a + 2 * b;
				case "Romb":
					return 4 * a;
				default:
					return 0;
			}
		}

		public final String nazwa;

		DwaParamy(String nazwa) {
			this.nazwa = nazwa;
		}

	}
}
