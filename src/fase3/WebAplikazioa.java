package fase3;

import java.io.IOException;
import java.util.Scanner;

public class WebAplikazioa {

	public static void main(String[] args) throws IOException {

		Internet i = Internet.getInternetInstance();
		Hiztegia h = Hiztegia.getHiztegiaInstance();

		i.hasieratu("fitxategiak/index", "fitxategiak/pld-arc");

		System.out.println("Webak Kargatuta");

		HitzenHashMapa<String, Hitza> hitzenHashMapa = new HitzenHashMapa<>();
		h.setHiztegia(hitzenHashMapa);
		h.hasieratu("fitxategiak/wordsshuffle.txt");
		System.out.println("Hiztegia kargatuta\n");

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
		String URL1;
		String URL2;
		while (aukera != 0) {
			System.out.println("Zer egin nahi duzu?");
			System.out.println("0. Irten");
			System.out.println("1. Web-orriak bilatu gako-hitzen bidez");
			System.out.println("2. Web-orriak konektatuak dauden konrpobatu");
			System.out.println("3. Web-orrien arteko biderik motzena inprimatu");
			String irakurritakoa = sc.nextLine();
			String pattern = "[0123]";
			while (!irakurritakoa.matches(pattern)) {

				System.out.println("Zer egin nahi duzu?");
				System.out.println("0. Irten");
				System.out.println("1. Web-orriak bilatu gako-hitzen bidez");
				System.out.println("2. Web-orriak konektatuak dauden konrpobatu");
				System.out.println("3. Web-orrien arteko biderik motzena inprimatu");
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

			case 2:

				System.out.println("Sartu lehen URLa:");
				URL1 = sc.nextLine();
				URL1.trim();

				System.out.println("Sartu bigarren URLa:");
				URL2 = sc.nextLine();
				URL2.trim();

				if (i.konektatutaDaude(URL1, URL2)) {

					System.out.println(URL1 + " URLtik " + URL2 + " URLra bidea dago");

				} else {

					System.out.println(URL1 + " URLtik " + URL2 + " URLra ez dago biderik");

				}

				break;

			case 3:

				System.out.println("Sartu lehen URLa:");
				URL1 = sc.nextLine();
				URL1.trim();

				System.out.println("Sartu bigarren URLa:");
				URL2 = sc.nextLine();
				URL2.trim();

				if (i.konektatutaDaude(URL1, URL2)) {
					System.out.println(URL1 + " URLtik " + URL2 + " URLra bidea, honakoa da");
					i.bideaInprimatu(URL1, URL2);

				} else {

					System.out.println(URL1 + " URLtik " + URL2 + " URLra ez dago biderik");

				}

				break;
			default:
				break;
			}
		}
		sc.close();

	}

}