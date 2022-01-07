package fase3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Internet {

	private static Internet internetInstance = new Internet();;

	private WebenLista webak;

	// Eraikitzailea

	private Internet() {

		this.webak = new WebenLista();
	}

	// Get/Seterrak

	public WebenLista getWebak() {
		return webak;
	}

	public void setWebak(WebenLista webak) {
		this.webak = webak;
	}

	public static Internet getInternetInstance() {

		return internetInstance;
	}

	/**
	 * Pasatako fitxategian dauden webak kargatzen ditu
	 * 
	 * @param fitxIzena: webak dauzkan fitxategiaren izena
	 * @throws FileNotFoundException
	 */
	private void webakKargatu(String fitxIzena) throws FileNotFoundException {

		// Fitxategi izenak hutsunerik ez duela ziurtatzeko
		fitxIzena.trim();

		Scanner sc = new Scanner(new File(fitxIzena));
		String lerroa;
		String[] osagaiak;

		while (sc.hasNextLine()) {
			lerroa = sc.nextLine();
			lerroa.trim();
			osagaiak = lerroa.split("\\s+");
			Web w = new Web(Integer.parseInt(osagaiak[1]), osagaiak[0]);

			this.webak.webaErantsi(w);

		}

		sc.close();

	}

	/**
	 * Pasatako fitxategian dauden estekak kargatzen ditu
	 * 
	 * @param fitxIzena: estekak dauzkan fitxategiaren izena
	 * @throws FileNotFoundException
	 */
	private void estekakKargatu(String fitxIzena) throws FileNotFoundException {

		// Fitxategi izenak hutsunerik ez duela ziurtatzeko
		fitxIzena.trim();

		Scanner sc = new Scanner(new File(fitxIzena));
		String lerroa;
		String[] osagaiak;

		while (sc.hasNextLine()) {
			lerroa = sc.nextLine();
			lerroa.trim();
			osagaiak = lerroa.split("\\s+");
			this.webak.estekaErantsi(Integer.parseInt(osagaiak[0]), Integer.parseInt(osagaiak[1]));

		}

		sc.close();

	}

	/**
	 * Klasea hasieratzen du: horretarako web-orriak eta estekak kargatzen ditu
	 * 
	 * @param webenFitxIzena: webak dauzkan fitxategiaren izena
	 * @param estekenFitxIzena: estekak dauzkan fitxategiaren izena
	 * @throws FileNotFoundException
	 */
	public void hasieratu(String webenFitxIzena, String estekenFitxIzena) throws FileNotFoundException {

		webakKargatu(webenFitxIzena);
		estekakKargatu(estekenFitxIzena);

	}

	/**
	 * Hitz bati dagokion stringa emanda, pantailan inprimatzen ditu gako-hitz hori
	 * duten webak
	 * 
	 * @param s: hitzari dagokion stringa
	 */
	public void webBilatzailea(String s) {

		Hitza h = Hiztegia.getHiztegiaInstance().hitzaBilatu(s);

		if (h == null) {

			System.out.println("Hitz gakoa ez dago hiztegian");
			System.out.println("");
		}

		else if (h.getWebOrrienLista().getWebenLista().isEmpty()) {

			System.out.println("Ez dago web-orririk gako hitz horrekin");
			System.out.println("");
		}

		else {
			int i = 0;

			for (Web w : h.getWebOrrienLista().getWebenLista()) {

				System.out.println("\t" + i + " - " + w.getDomeinua());

				i++;

			}

			System.out.println("");

		}

	}

	/**
	 * Bi URL emanda, jatorri-URLtik helburu-URLra esteken bide bat badagoen
	 * adierazten du.
	 * 
	 * @param url1: Jatorri-URLa
	 * @param url2: Helburu-URLa
	 * @return: true bide bat badago, false bestela
	 */
	public boolean konektatutaDaude(String url1, String url2) {
		// Bi url-ak existitzen direla konprobatu elkar konektaturik dauden konprobatzen
		// hasi aurretik
		Web jatorria = this.webak.bilatuWebakUrlBidez(url1);

		if (jatorria == null)

			return false;

		Web helburua = this.webak.bilatuWebakUrlBidez(url2);

		if (helburua == null)

			return false;

		// Bisitatu beharreko Webak ordenean gordetzeko
		Queue<Web> itxaroteIlara = new LinkedList<Web>();
		itxaroteIlara.add(jatorria);

		// Bisitatutako Webak gordetzeko
		HashSet<Web> bisitatuak = new HashSet<Web>();
		// markatu bisitatu gisa
		bisitatuak.add(jatorria);

		while (!itxaroteIlara.isEmpty()) {

			Web erpina = itxaroteIlara.poll();

			if (erpina.equals(helburua))

				return true;

			for (Web w : erpina.getEstekenLista().getWebenLista()) {

				if (!bisitatuak.contains(w)) {

					// markatu bisitatu gisa
					bisitatuak.add(w);
					itxaroteIlara.add(w);
				}
			}
		}

		return false;

	}

	/**
	 * Bi URL emanda, jatorri-URLtik helburu-URLra dagoen biderik motzena (esteka
	 * gutxien duena) inprimatzen du, bide hori existitzen bada.
	 * 
	 * @param url1: Jatorri-URLa
	 * @param url2: Helburu-URLa
	 */
	public void bideaInprimatu(String url1, String url2) {

		// Bi url badaudela frogatzeko bilaketa egin
		Web jatorria = this.webak.bilatuWebakUrlBidez(url1);
		Web helburua = this.webak.bilatuWebakUrlBidez(url2);

		// Bi url-ak badauden kasuan
		if (jatorria != null && helburua != null) {

			// Korritzeko falta diren Weben lista
			Queue<Web> itxaroteIlara = new LinkedList<Web>();
			itxaroteIlara.add(jatorria);

			// Bisitatutako Webak gordetzeko HashTaula
			HashSet<Web> bisitatuak = new HashSet<Web>();
			// markatu bisitatu gisa
			bisitatuak.add(jatorria);

			// Bisitatutako web bakoitzaren aurrekoa gordetzen duen HashMapa
			// Gakoa webaren domeinua, datua webaren aurreko webaren domeinua
			HashMap<String, String> aurrekoa = new HashMap<String, String>();
			aurrekoa.put(jatorria.getDomeinua(), null);

			// Inprimatuko den bidea gordetzeko aldagaia
			String bidea = "";

			boolean aurkitua = false;

			while (!itxaroteIlara.isEmpty()) {

				Web erpina = itxaroteIlara.poll();

				if (erpina.equals(helburua)) {
					// Helburu webaren domeinua eta bere aurreko webaren domeinua gorde
					aurkitua = true;
					// Bidean helburu weba gorde
					bidea = helburua.getDomeinua();
					break;
				}

				else {
					for (Web w : erpina.getEstekenLista().getWebenLista()) {

						if (!bisitatuak.contains(w)) {
							// Helburu webaren domeinua eta bere aurreko webaren domeinua gorde
							aurrekoa.put(w.getDomeinua(), erpina.getDomeinua());
							// markatu bisitatu gisa
							bisitatuak.add(w);
							itxaroteIlara.add(w);
						}
					}
				}
			}

			// Bidea dagoen kasuan
			if (aurkitua) {

				String unekoa = aurrekoa.get(helburua.getDomeinua());
				// HashTaula erabili bidean atzera egiteko eta inprimatu behar diren elementuak
				// bidea String-ean gordetzeko
				while (unekoa != null) {

					bidea = unekoa + ", " + bidea;
					unekoa = aurrekoa.get(unekoa);

				}
				// Emaitza inprimatu
				bidea = "<" + bidea + " >\n";
				System.out.print(bidea);

			}

		}

	}

}
