public class Szesciokat extends Figura{

	private final double bok;

	public Szesciokat(double bok) {
		this.bok = bok;
	}
	@Override
	public double pole() {
		return (3*Math.sqrt(3)/2) * bok * bok;
	}

	@Override
	public double obwod() {
		return 6 * bok;
	}

	public double getBok() {
		return bok;
	}

	@Override
	public String toString() {
		return "Szesciokat";
	}
}
