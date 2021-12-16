package fase3;

import java.util.ArrayList;

public class HitzenLista implements HitzenInterfazea{

	private ArrayList<Hitza> lista;

	// Eraikitzailea

	public HitzenLista() {

		this.setLista(new ArrayList<Hitza>());
	}

	// Get/Seterrak

	public ArrayList<Hitza> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Hitza> lista) {
		this.lista = lista;
	}

	/**
	 * Hitz bat gehitzen dio listari
	 * 
	 * @param hitza: gehitzen den hitza
	 */
	public void hitzaGehitu(Hitza hitza) {

		if (this.hitzaBilatu(hitza.getDatua()) == null)

			this.lista.add(hitza);

	}

	/**
	 * Emandako stringa bilatzen du hitzen listan eta dagokion hitza itzultzen du
	 * 
	 * @param s: bilatu nahi den hitzaren testua (stringa)
	 * @return s stringari dagokion hitza (listan badago), null bestela
	 */
	public Hitza hitzaBilatu(String s) {		

		// Listako elementu kopurua 0 denerako erroreak sahiesteko
		if (this.lista.size() > 0 && s.length()>=4 && s.length()<=10) {
			// Bilaketa bitarra egiteko
			int ezker, eskuin, erdiko, konparaketa;
			ezker = 0;
			eskuin = this.lista.size() - 1;
			erdiko = (ezker + eskuin) / 2;
			konparaketa = s.compareTo(this.lista.get(erdiko).getDatua());
			while (ezker < eskuin & konparaketa != 0) {
				if (konparaketa < 0)
					eskuin = erdiko - 1;
				else
					ezker = erdiko + 1;
				erdiko = (ezker + eskuin) / 2;
				konparaketa = s.compareTo(this.lista.get(erdiko).getDatua());
			}

			if (konparaketa == 0)
				return this.lista.get(erdiko);

			else
				return null;

		}
		return null;
	}

}
