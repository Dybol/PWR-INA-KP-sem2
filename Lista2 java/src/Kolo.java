public class Kolo extends Figura{

	private final double r;

	public Kolo(double r) {
		this.r = r;
	}

	@Override
	public double pole() {
		return Math.PI * r * r;
	}

	@Override
	public double obwod() {
		return 2 * Math.PI * r;
	}

	public double getR() {
		return r;
	}

	@Override
	public String toString() {
		return "Kolo";
	}
}
