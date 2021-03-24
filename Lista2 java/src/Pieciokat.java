public class Pieciokat extends Figura{

	private final double bok;

	public Pieciokat(double bok) {
		this.bok = bok;
	}

	@Override
	public double pole() {
		return (Math.sqrt(25+10*Math.sqrt(5))/4) * bok * bok;
	}

	@Override
	public double obwod() {
		return 5 * bok;
	}

	public double getBok() {
		return bok;
	}

	@Override
	public String toString() {
		return "Pieciokat";
	}
}
