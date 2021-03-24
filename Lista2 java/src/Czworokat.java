public abstract class Czworokat extends Figura{

	private final double a;
	private final double b;
	private final double c;
	private final double d;
	private final double kat;
	public Czworokat(double a, double b, double c, double d, double kat) throws BlednaWartoscKataException {
		//super(kat, a,b,c,d);

		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.kat = kat;
		if(kat > 180 || kat < 0) {
			throw new BlednaWartoscKataException("Wprowadzono nieprawidlowa wartosc kata!");
		}
	}

	@Override
	public abstract double pole();

	@Override
	public double obwod() {
		return a+b+c+d;
	}

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public double getC() {
		return c;
	}

	public double getD() {
		return d;
	}

	public double getKat() {
		return kat;
	}
}
