package fase3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hiztegia {

	private static Hiztegia hiztegiaInstance = new Hiztegia();

	private HitzenInterfazea hitzak;

	// Eraikitzailea

	private Hiztegia() {

	}

	// Get/Seterrak

	public static Hiztegia getHiztegiaInstance() {

		return hiztegiaInstance;
	}

	public HitzenInterfazea getHitzak() {
		return hitzak;
	}

	/**
	 * Hiztegia hasieratzen du parametro gisa pasatzen zaion hiztegiarekin
	 * 
	 * @param hiztegia
	 */
	public void setHiztegia(HitzenInterfazea hiztegia) {

		this.hitzak = hiztegia;

	}

	/**
	 * Hiztegia kargatzen du emandako fitxategitik
	 * 
	 * @param fitxIzena: hiztegia daukan fitxategiaren izena
	 * @throws FileNotFoundException
	 */
	private void hitzakKargatu(String fitxIzena) throws FileNotFoundException {

		// Fitxategi izenak hutsunerik ez duela ziurtatzeko
		fitxIzena.trim();

		Scanner sc = new Scanner(new File(fitxIzena));
		String lerroa;
		String[] osagaiak;

		while (sc.hasNextLine()) {
			lerroa = sc.nextLine();
			lerroa.trim();
			osagaiak = lerroa.split("\\s+");
			Hitza h = new Hitza(osagaiak[0]);
			this.hitzak.hitzaGehitu(h);

		}

		sc.close();

	}

	/**
	 * Hiztegiko hitz bakoitzari erreferentziatzen dituen web-orriak esleitzen
	 * zaizkio Aurre: Internet eta hiztegia kargatuta daude jadanik
	 */
	private void hitzenWebakKonputatu() {

		for (Web web : Internet.getInternetInstance().getWebak().getWebenLista()) {

			String domeinua = web.getDomeinua();

			for (int i = 4; i <= 10; i++) {

				for (int j = 0; j <= domeinua.length() - i; j++) {

					// Hitzen bilaketa bitarra compareTo erabiliz
					String bilatzeko = domeinua.substring(j, j + i);

					Hitza bilaketa = this.hitzaBilatu(bilatzeko);
					if (bilaketa != null) {
						// System.out.println("Domeinua: "+domeinua+" Gakoa: "+bilatzeko+" eta bilaketa:
						// "+bilaketa.getDatua());
		
						// Hitzaren webenlistan, domeinua jada ez dagoela konprobatu
						if (!bilaketa.getWebOrrienLista().getWebenLista().contains(web)) {

							bilaketa.getWebOrrienLista().getWebenLista().add(web);
							// System.out.println(bilaketa.getDatua() + "\t" + web.getDomeinua());
						}

					}

				}

			}

		}

	}

	/**
	 * Hiztegia kargatzen du emandako fitxategitik, eta hitz bakoitzaren webak
	 * konputatzen ditu (hitz bakoitzari erreferentziatzen dituen web-orriak
	 * esleitzen zaizkio)
	 * 
	 * @param fitxIzena: hiztegia daukan fitxategiaren izena AURRE: Internet eta
	 *        hiztegia kargatuta daude dagoeneko
	 * @throws FileNotFoundException
	 */
	public void hasieratu(String fitxIzena) throws FileNotFoundException {

		hitzakKargatu(fitxIzena);
		System.out.println("Hitzak kargatuta");
		hitzenWebakKonputatu();
		System.out.println("Hitzen webak konputatuta");

		// Hiztegi klasea BZBHitzak klasearekin hasieratu bada bahetu metodoari deia egin
		if (this.getHitzak().getClass().toString().equals("class fase3.BZBHitzak"))
			// Casting aplikatu
			((BZBHitzak) this.getHitzak()).bahetuHitzGakoak();

	}

	/**
	 * Emandako stringa bilatzen du hiztegian eta dagokion hitza itzultzen du
	 * 
	 * @param s: bilatu nahi den hitzaren testua (stringa)
	 * @return s stringari dagokion hitza (hiztegian badago), null bestela
	 */
	public Hitza hitzaBilatu(String s) {

		return this.hitzak.hitzaBilatu(s);

	}

}
