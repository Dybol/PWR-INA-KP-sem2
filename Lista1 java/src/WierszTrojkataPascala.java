public class WierszTrojkataPascala {

	private int[] wiersze;

	public WierszTrojkataPascala(int n) {
		this.wiersze = new int[n+1];

		obliczWiersz(n);
	}

	private void obliczWiersz(int n) {
		int licznik = 0;
		while(licznik < wiersze.length) {
			wiersze[licznik] = (int) (silnia(n) / (silnia(licznik) * silnia(n-licznik)));

			licznik++;
		}
	}

	public void wypiszWiersz() {
		for(int i: wiersze)
			System.out.println(i);
	}

//	private int silnia(int n) {
//		if(n == 0)
//			return 1;
//		return silnia(n-1) * n;
//	}

	private long dwumian(int n, int k) {
		return silnia(n) / (silnia(k) * silnia(n-k));
	}

	private long silnia(int n) {
		long s = 1;
		for(int i = 1; i <=n; i++)
			s*=i;
		return s;
	}

	public int wspolczynnik(int m) {
		return wiersze[m];
	}
}
