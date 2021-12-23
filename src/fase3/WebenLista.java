package fase3;

import java.util.ArrayList;
import java.util.Hashtable;

public class WebenLista {

	// private ArrayList<Web> webenLista;
	private Hashtable<Integer, Web> webenLista;
	// Eraikitzailea

	public WebenLista() {

		this.setWebenLista(new Hashtable<>());

	}

	// Get/Seterrak

	public Hashtable<Integer, Web> getWebenLista() {
		return webenLista;
	}

	public void setWebenLista(Hashtable<Integer, Web> webenLista) {
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
		this.webenLista.put(web.getId(), web);
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

		jatorri.getEstekenLista().webenLista.put(helburu.getId(), helburu);

	}

	/**
	 * URL bat emanda, listan URL hori duen weba itzultzen du
	 * 
	 * @param url: bilatzen den URLa
	 * @return: URL hori duen web-a (listan badago), bestela null.
	 */
	public Web bilatuWebakUrlBidez(String url) {

		for (Web w : this.webenLista.values()) {

			if (w.getDomeinua().equals(url))

				return w;

		}

		return null;

	}

}
