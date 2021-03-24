public class Romb extends Czworokat{

	public Romb(double a, double kat) throws BlednaWartoscKataException {
		super(a,a,a,a,kat);
	}

	@Override
	public double pole() {
		return Math.pow(getA(),2) * Math.sin(Math.toRadians(getKat()));
	}

	@Override
	public double obwod() {
		return super.obwod();
	}

	@Override
	public String toString() {
		return "Romb";
	}
}
