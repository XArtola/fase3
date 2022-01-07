package fase3;

import java.util.ArrayList;

public class WebenLista {

	private ArrayList<Web> webenLista;

	// Eraikitzailea

	public WebenLista() {

		this.setWebenLista(new ArrayList<Web>());

	}

	// Get/Seterrak

	public ArrayList<Web> getWebenLista() {
		return webenLista;
	}

	public void setWebenLista(ArrayList<Web> webenLista) {
		this.webenLista = webenLista;
	}

	/**
	 * Web bat gehitzen dio listari
	 * 
	 * @param web: gehitzen den weba
	 * 
	 *        AURRE: web ez dago listan
	 */
	public void webaErantsi(Web web) {
		this.webenLista.add(web);
	}

	/**
	 * Listako web bati esteka bat eransten dio
	 * 
	 * @param idJatorriWeba: Jatorriko webaren id-a
	 * @param idHelburuWeba: Helburuko webaren id-a AURRE: lista id-en arabera
	 *        ordenatuta dago AURRE: idJatorriWeba eta idHelburuWeba id-a duten
	 *        webak listan daude
	 */
	public void estekaErantsi(int idJatorriWeba, int idHelburuWeba) {

		Web jatorri = this.webenLista.get(idJatorriWeba);

		Web helburu = this.webenLista.get(idHelburuWeba);

		jatorri.getEstekenLista().webenLista.add(helburu);

	}

	/**
	 * URL bat emanda, listan URL hori duen weba itzultzen du
	 * 
	 * @param url: bilatzen den URLa
	 * @return: URL hori duen web-a (listan badago), bestela null.
	 */
	public Web bilatuWebakUrlBidez(String url) {

		for (Web w : this.webenLista) {

			if (w.getDomeinua().equals(url))

				return w;

		}

		return null;

	}

}
