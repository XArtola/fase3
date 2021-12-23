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

		Web jatorria = this.webak.bilatuWebakUrlBidez(url1);

		if (jatorria == null)

			return false;

		Web helburua = this.webak.bilatuWebakUrlBidez(url2);

		if (helburua == null)

			return false;

		Queue<Web> itxaroteIlara = new LinkedList<Web>();
		itxaroteIlara.add(jatorria);

		// LinkedList<KaleErpina> bisitatuak = new LinkedList<KaleErpina>();
		HashSet<Web> bisitatuak = new HashSet<Web>();

		bisitatuak.add(jatorria); // markatu bisitatu gisa

		HashMap<String, String> aurrekoa = new HashMap<String, String>();
		aurrekoa.put(jatorria.getDomeinua(), null);

		LinkedList<String> bidea = new LinkedList<String>();

		while (!itxaroteIlara.isEmpty()) {

			Web erpina = itxaroteIlara.poll();

			for (Web w : erpina.getEstekenLista().getWebenLista()) {
				// aurrekoa.put(w.kaleIzena, erpina.kaleIzena);

				if (w.equals(helburua)) {
					aurrekoa.put(w.getDomeinua(), erpina.getDomeinua());

					bidea.add(helburua.getDomeinua());
					break;
				}

				else if (!bisitatuak.contains(w)) {
					aurrekoa.put(w.getDomeinua(), erpina.getDomeinua());

					bisitatuak.add(w); // markatu bisitatu gisa
					itxaroteIlara.add(w);
				}
			}
		}

		if (bidea.isEmpty())

			return false;

		else

			return true;

	}

	/**
	 * Bi URL emanda, jatorri-URLtik helburu-URLra dagoen biderik motzena (esteka
	 * gutxien duena) inprimatzen du, bide hori existitzen bada.
	 * 
	 * @param url1: Jatorri-URLa
	 * @param url2: Helburu-URLa
	 */
	public void bideaInprimatu(String url1, String url2) {

		Web jatorria = this.webak.bilatuWebakUrlBidez(url1);

		Web helburua = this.webak.bilatuWebakUrlBidez(url2);

		if (jatorria != null && helburua != null) {

			Queue<Web> itxaroteIlara = new LinkedList<Web>();
			itxaroteIlara.add(jatorria);

			// LinkedList<KaleErpina> bisitatuak = new LinkedList<KaleErpina>();
			HashSet<Web> bisitatuak = new HashSet<Web>();

			bisitatuak.add(jatorria); // markatu bisitatu gisa

			HashMap<String, String> aurrekoa = new HashMap<String, String>();
			aurrekoa.put(jatorria.getDomeinua(), null);

			// LinkedList<String> bidea = new LinkedList<String>();
			String bidea = "";
			while (!itxaroteIlara.isEmpty()) {

				Web erpina = itxaroteIlara.poll();

				for (Web w : erpina.getEstekenLista().getWebenLista()) {
					// aurrekoa.put(w.kaleIzena, erpina.kaleIzena);

					if (w.equals(helburua)) {
						aurrekoa.put(w.getDomeinua(), erpina.getDomeinua());

						// bidea.add(helburua.getDomeinua());
						bidea = helburua.getDomeinua();
						break;
					}

					else if (!bisitatuak.contains(w)) {
						aurrekoa.put(w.getDomeinua(), erpina.getDomeinua());

						bisitatuak.add(w); // markatu bisitatu gisa
						itxaroteIlara.add(w);
					}
				}
			}
			// System.out.print(aurrekoa);

			// if (bidea.isEmpty()) {
			if (bidea != "") {
				String unekoa = aurrekoa.get(helburua.getDomeinua());
				while (unekoa != null) {
					// System.out.print(unekoa + " ");
					if (unekoa != null)
						// bidea.addFirst(unekoa);
						bidea = unekoa + ", " + bidea;
					// System.out.print(unekoa + " ");
					unekoa = aurrekoa.get(unekoa);

				}
				
				System.out.print(bidea);

			/*	for (String url : bidea) {

					System.out.print(url + ", ");

				}*/

			}

		}

	}

}
