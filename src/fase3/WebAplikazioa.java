package fase3;

import java.io.IOException;
import java.util.Scanner;

public class WebAplikazioa {

	public static void main(String[] args) throws IOException {

		Internet i = Internet.getInternetInstance();
		Hiztegia h = Hiztegia.getHiztegiaInstance();

		i.hasieratu("fitxategiak/index", "fitxategiak/pld-arc");
	
		BZBHitzak zuhaitza = new BZBHitzak();
		h.setHiztegia(zuhaitza);
		h.hasieratu("fitxategiak/wordsshuffle.txt");

		/////////////////////////////////// PROBAK
		/////////////////////////////////// EGITEKO////////////////////////////////////////////
//		i.hasieratu("fitxategiak/smallindex", "fitxategiak/smallpld-arc");	
//		h.hasieratu("fitxategiak/smallwords.txt");

		/*
		 * for(int j = 0; j <
		 * i.getWebak().getWebenLista().get(0).getEstekenLista().getWebenLista().size();
		 * j++) {
		 * System.out.println(i.getWebak().getWebenLista().get(0).getEstekenLista().
		 * getWebenLista().get(j).getDomeinua());
		 * 
		 * 
		 * }
		 */

		/////////////////////////////////// PROBAK
		/////////////////////////////////// EGITEKO////////////////////////////////////////////

		int aukera = 1;
		Scanner sc = new Scanner(System.in);
		String hitza;
		while (aukera != 0) {
			System.out.println("Zer egin nahi duzu?");
			System.out.println("1. Web-orriak bilatu gako-hitzen bidez");
			System.out.println("0. Irten");
			String irakurritakoa = sc.nextLine();
			String pattern = "[01]";
			while (!irakurritakoa.matches(pattern)) {

				System.out.println("Zer egin nahi duzu?");
				System.out.println("1. Web-orriak bilatu gako-hitzen bidez");
				System.out.println("0. Irten");
				irakurritakoa = sc.nextLine();

			}
			aukera = Integer.parseInt(irakurritakoa);
			switch (aukera) {
			case 1:
				System.out.println("Sartu gako-hitz bat:");
				hitza = sc.nextLine();
				hitza.trim();

				i.webBilatzailea(hitza);

				break;
			default:
				break;
			}
		}
		sc.close();

	}

}