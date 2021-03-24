public class KlasaGlowna {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Wprowadz argumenty!");
			return;
		}


		String figury = args[0];
		String[] figury_z_osobna = figury.split("");
		//o - okrag
		//c - czworokat
		//p - pieciokat
		//s - szesciokat
		Figura[] wszystkie_figury = new Figura[figury_z_osobna.length];
		int argsCounter = 1;
		int fig_counter = 0;
		try {
			for (String s : figury_z_osobna) {
				if (s.equals("o")) {
					wszystkie_figury[fig_counter] = new Kolo(Double.parseDouble(args[argsCounter]));
					argsCounter++;
					fig_counter++;
				} else if (s.equals("p")) {
					wszystkie_figury[fig_counter] = new Pieciokat(Double.parseDouble(args[argsCounter]));
					argsCounter++;
					fig_counter++;
				} else if (s.equals("s")) {
					wszystkie_figury[fig_counter] = new Szesciokat(Double.parseDouble(args[argsCounter]));
					argsCounter++;
					fig_counter++;
				} else if (s.equals("c")) {
					double a = Double.parseDouble(args[argsCounter]);
					double b = Double.parseDouble(args[argsCounter + 1]);
					double c = Double.parseDouble(args[argsCounter + 2]);
					double d = Double.parseDouble(args[argsCounter + 3]);
					double kat = Double.parseDouble(args[argsCounter + 4]);
					if (a == b && b == c && c == d)
						if (kat == 90)
							wszystkie_figury[fig_counter] = new Kwadrat(a);
						else
							wszystkie_figury[fig_counter] = new Romb(a, kat);

					else if (((a == b && c == d) && kat == 90))
						wszystkie_figury[fig_counter] = new Prostokat(a, c);
					else if ((a == c && b == d || (a == d && b == c)) && kat == 90)
						wszystkie_figury[fig_counter] = new Prostokat(a, b);
					else {
						System.out.println("Wprowadz poprawne dane dla czworokata!");
						return;
					}

					argsCounter += 5;
					fig_counter++;
				} else {
					System.out.println("Wprowadz poprawnie dane!");
					return;
				}
			}
		} catch (NumberFormatException ex) {
			System.out.println("Wprowadz typ double jako argument!");
			return;
		} catch(NullPointerException | ArrayIndexOutOfBoundsException ex) {
			System.out.println("Wprowadz poprawna ilosc elementow!");
			return;
		} catch (BlednaWartoscKataException e) {
			e.printStackTrace();
			return;
		}
		//to znaczy, ze wprowadzilismy za duzo parametrow
		if(argsCounter != args.length) {
			System.out.println("Pewne parametry mogly zostac pominiete");
		}

		for(Figura f: wszystkie_figury) {
			System.out.println("----------" + f.toString() + "----------");
			System.out.println("Obwod: " + f.obwod());
			System.out.println("Pole: " + f.pole());
		}
	}
}
