package zadanie1;

import java.util.HashMap;
import java.util.Map;

public class KlasaGlowna {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Wprowadz argumenty!");
			return;
		}

		String figury = args[0];
		String[] figury_z_osobna = figury.split("");

		//o - okrag
		//k - kwadrat
		//P - prostokat
		//p - pieciokat
		//s - szesciokat

		Map<Figury.JedenParam, Map<String, Double>> jedenParamMap = new HashMap<>();

		Map<Figury.DwaParamy, Map<String, Double>> dwaParamyMap = new HashMap<>();
		int argsCounter = 1;

		try {
			for (String s : figury_z_osobna) {
				if (s.equals("o")) {
					Map<String, Double> mapaPolaIObwodu = new HashMap<>();
					mapaPolaIObwodu.put("Obwod", Figury.JedenParam.KOLO.obliczObwod(Double.parseDouble(args[argsCounter])));
					mapaPolaIObwodu.put("Pole", Figury.JedenParam.KOLO.obliczPole(Double.parseDouble(args[argsCounter])));

					jedenParamMap.put(Figury.JedenParam.KOLO, mapaPolaIObwodu);

					argsCounter++;
				} else if (s.equals("p")) {
					Map<String, Double> mapaPolaIObwodu = new HashMap<>();
					mapaPolaIObwodu.put("Obwod", Figury.JedenParam.PIECIOKAT.obliczObwod(Double.parseDouble(args[argsCounter])));
					mapaPolaIObwodu.put("Pole", Figury.JedenParam.PIECIOKAT.obliczPole(Double.parseDouble(args[argsCounter])));

					jedenParamMap.put(Figury.JedenParam.PIECIOKAT, mapaPolaIObwodu);
					argsCounter++;
				} else if (s.equals("s")) {
					Map<String, Double> mapaPolaIObwodu = new HashMap<>();
					mapaPolaIObwodu.put("Obwod", Figury.JedenParam.SZESCIOKAT.obliczObwod(Double.parseDouble(args[argsCounter])));
					mapaPolaIObwodu.put("Pole", Figury.JedenParam.SZESCIOKAT.obliczPole(Double.parseDouble(args[argsCounter])));

					jedenParamMap.put(Figury.JedenParam.SZESCIOKAT, mapaPolaIObwodu);
					argsCounter++;
				} else if (s.equals("k")) {
					Map<String, Double> mapaPolaIObwodu = new HashMap<>();
					mapaPolaIObwodu.put("Obwod", Figury.JedenParam.KWADRAT.obliczObwod(Double.parseDouble(args[argsCounter])));
					mapaPolaIObwodu.put("Pole", Figury.JedenParam.KWADRAT.obliczPole(Double.parseDouble(args[argsCounter])));

					jedenParamMap.put(Figury.JedenParam.KWADRAT, mapaPolaIObwodu);
					argsCounter++;
				}  else if (s.equals("P")) {

					Map<String, Double> mapaPolaIObwodu = new HashMap<>();
					mapaPolaIObwodu.put("Obwod", Figury.DwaParamy.PROSTOKAT.obliczObwod(Double.parseDouble(args[argsCounter]), Double.parseDouble(args[argsCounter+1])));
					mapaPolaIObwodu.put("Pole", Figury.DwaParamy.PROSTOKAT.obliczPole(Double.parseDouble(args[argsCounter]), Double.parseDouble(args[argsCounter+1])));


					dwaParamyMap.put(Figury.DwaParamy.PROSTOKAT, mapaPolaIObwodu);

					argsCounter += 2;
				}
				else if (s.equals("r")) {

					double kat = Double.parseDouble(args[argsCounter+1]);
					if(kat <= 0 || kat >=180)
						throw new BlednaWartoscKataException("Podaj poprawny kat!");
					Map<String, Double> mapaPolaIObwodu = new HashMap<>();
					mapaPolaIObwodu.put("Obwod", Figury.DwaParamy.ROMB.obliczObwod(Double.parseDouble(args[argsCounter]), Double.parseDouble(args[argsCounter+1])));
					mapaPolaIObwodu.put("Pole", Figury.DwaParamy.ROMB.obliczPole(Double.parseDouble(args[argsCounter]), kat));

					dwaParamyMap.put(Figury.DwaParamy.ROMB, mapaPolaIObwodu);

					argsCounter += 2;
				}
				else {
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

		for(Figury.JedenParam fig: jedenParamMap.keySet()) {

			System.out.println("----------" + fig.nazwa + "----------");
			Map<String, Double> mapaPolaIObwodu = jedenParamMap.get(fig);
			System.out.println("Obwod: " + mapaPolaIObwodu.get("Obwod"));
			System.out.println("Pole: " + mapaPolaIObwodu.get("Pole"));
		}

		for(Figury.DwaParamy fig: dwaParamyMap.keySet()) {

			System.out.println("----------" + fig.nazwa + "----------");
			Map<String, Double> mapaPolaIObwodu = dwaParamyMap.get(fig);
			System.out.println("Obwod: " + mapaPolaIObwodu.get("Obwod"));
			System.out.println("Pole: " + mapaPolaIObwodu.get("Pole"));
		}
	}
}
