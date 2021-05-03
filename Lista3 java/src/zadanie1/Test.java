package zadanie1;

public class Test {

	public static void main(String[] args) {
		String test = "a";

		try {
			int i = Integer.parseInt(test);
		} catch(Exception ex) {
			System.out.println("zlapano wyjatek");
		}

		System.out.println("dziala dalej....");
	}

}
