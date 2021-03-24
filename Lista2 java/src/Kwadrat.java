public class Kwadrat extends Czworokat{

	public Kwadrat(double a) throws BlednaWartoscKataException {
		super(a, a,a,a,90);
	}

	@Override
	public double pole() {
		return getA()*getA();
	}

	@Override
	public double obwod() {
		return super.obwod();
	}

	@Override
	public String toString() {
		return "Kwadrat";
	}
}
