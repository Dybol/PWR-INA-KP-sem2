public class Prostokat extends Czworokat{

	public Prostokat(double a, double b) throws BlednaWartoscKataException {
		super(a, b, a, b, 90);
	}

	@Override
	public double pole() {
		return getA()*getB();
	}

	@Override
	public double obwod() {
		return super.obwod();
	}

	@Override
	public String toString() {
		return "Prostokat";
	}
}
