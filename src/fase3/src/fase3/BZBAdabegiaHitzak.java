package fase3;

import java.util.LinkedList;

public class BZBAdabegiaHitzak {

	private Hitza info;

	private BZBAdabegiaHitzak ezkerra;
	private BZBAdabegiaHitzak eskuina;

	// Eraikitzailea

	public BZBAdabegiaHitzak(String hitza) {
		info = new Hitza(hitza);
		ezkerra = null;
		eskuina = null;
	}

	// Getterrak eta Setterrak

	public Hitza getInfo() {
		return info;
	}

	public void setInfo(Hitza info) {
		this.info = info;
	}

	public BZBAdabegiaHitzak getEzkerra() {
		return ezkerra;
	}

	public void setEzkerra(BZBAdabegiaHitzak ezkerra) {
		this.ezkerra = ezkerra;
	}

	public BZBAdabegiaHitzak getEskuina() {
		return eskuina;
	}

	public void setEskuina(BZBAdabegiaHitzak eskuina) {
		this.eskuina = eskuina;
	}

	/**
	 * Adabegiak ezkerreko umea baduen ala ez itzultzen du.
	 * 
	 * @return true, ezkerreko umea baldin badu; false, bestela.
	 */
	public boolean baduEzkerra() {
		return this.ezkerra != null;
	}

	/**
	 * Adabegiak eskuineko umea baduen ala ez itzultzen du.
	 * 
	 * @return true, eskuineko umea baldin badu; false, bestela.
	 */
	public boolean baduEskuina() {
		return this.eskuina != null;
	}

	/**
	 * Adabegia hostoa den ala ez itzultzen du.
	 * 
	 * @return true, hostoa bada; false, bestela.
	 */
	public boolean hostoaDa() {
		return !this.baduEzkerra() && !this.baduEskuina();
	}

	/**
	 * Hitza gehitzen du
	 * 
	 * @param hitza
	 */
	public void hitzaGehitu(Hitza hitza) {
		// Hitza ezkerreko azpizuhaitzean gehitu behar da
		if (this.info.getDatua().compareTo(hitza.getDatua()) > 0) {
			if (this.baduEzkerra()) {
				this.ezkerra.hitzaGehitu(hitza);
			} else {
				this.ezkerra = new BZBAdabegiaHitzak(hitza.getDatua());
			}
		} else {// Hitza eskuineko azpizuhaitzean gehitu behar da
			if (this.baduEskuina()) {
				this.eskuina.hitzaGehitu(hitza);
			} else {
				this.eskuina = new BZBAdabegiaHitzak(hitza.getDatua());
			}
		}

	}

	/**
	 * Zuhaitzean hitza bilatzen du
	 * 
	 * @param hitza
	 * @return Hitza baldin badago, bestela null
	 */

	public Hitza hitzaBilatu(String hitza) {
		// Adabegia da hitza
		if (this.info.getDatua().equals(hitza)) {
			return this.info;
		}
		// Eskuinetara bilatu
		if (this.info.getDatua().compareTo(hitza) < 0 && this.baduEskuina()) {

			return this.eskuina.hitzaBilatu(hitza);
		}
		// Ezkerretara bilatu
		if (this.info.getDatua().compareTo(hitza) > 0 && this.baduEzkerra()) {

			return this.ezkerra.hitzaBilatu(hitza);
		}
		return null;
	}

	/**
	 * Inongo web-orriren hitz gakoak ez diren zuhaitzeko hitzen lista bat itzultzen
	 * du
	 * 
	 * @return Ezabatu behar diren hitzen lista
	 */
	public LinkedList<Hitza> lortuEzabatzekoHitzak() {

		LinkedList<Hitza> lista = new LinkedList<>();
		LinkedList<Hitza> listaEzker;
		LinkedList<Hitza> listaEskuin;

		// Ezker azpizuhaitza badu bertatik ezabatu behar diren hitzak lortu
		if (this.baduEzkerra())

			listaEzker = this.ezkerra.lortuEzabatzekoHitzak();

		else
			listaEzker = new LinkedList<>();

		// Ezkuin azpizuhaitza badu bertatik ezabatu behar diren hitzak lortu
		if (this.baduEskuina())

			listaEskuin = this.eskuina.lortuEzabatzekoHitzak();

		else
			listaEskuin = new LinkedList<>();

		// Listak batu
		lista.addAll(listaEzker);
		lista.addAll(listaEskuin);

		// Uneko adabegiko informazioa (Hitza) ezabatu behar den konprobatu
		if (this.info.getWebOrrienLista().getWebenLista().isEmpty())

			lista.addLast(this.info);

		return lista;

	}

	/**
	 * Zuhaitzeko elementurik txikiena itzultzen du datua eta adabegia
	 * 
	 * @return
	 */

	public EzabatuMinEmaitza ezabatuMin() {
		EzabatuMinEmaitza emaitza = new EzabatuMinEmaitza();
		if (!this.baduEzkerra()) {// Txikiena unekoa da
			emaitza.setBalioa(this.info.getDatua());
			emaitza.setAdabegia(this.eskuina);
		} else { // Txikiena ezkerreko azpizuhaitzean dago
			EzabatuMinEmaitza emaitzaEzkerra = this.ezkerra.ezabatuMin();
			this.ezkerra = emaitzaEzkerra.getAdabegia();
			emaitza.setBalioa(emaitzaEzkerra.getBalioa());
			emaitza.setAdabegia(this);
		}
		return emaitza;
	}

	/**
	 * Zuhaitzeko hitz bat ezabatzen du
	 *
	 * @param hitza Ezabatu beharreko hitza
	 * @return Ezabatu beharreko hitza fuen adabegia
	 */

	public BZBAdabegiaHitzak ezabatuHitza(Hitza hitza) {
		// Konparaketaren emaitza gorde
		int konparaketa = this.info.getDatua().compareTo(hitza.getDatua());

		if (konparaketa == 0) {// Ezabatu beharreko elementua unekoa da
			if (!this.baduEzkerra())
				return this.eskuina;
			else if (!this.baduEskuina())
				return this.ezkerra;
			else {// Baditu ezker eta eskuin azpizuhaitzak
				EzabatuMinEmaitza min = this.eskuina.ezabatuMin();
				this.eskuina = min.getAdabegia();
				this.info.setDatua(min.getBalioa());
				return this;
			}
		} else if (konparaketa > 0) {// Ezabatu beharreko elementua ezkerretara
			if (this.baduEzkerra())
				this.ezkerra = this.ezkerra.ezabatuHitza(hitza);
			return this;
		} else {// ezabatu beharreko elementua eskuinetara
			if (this.baduEskuina())
				this.eskuina = this.eskuina.ezabatuHitza(hitza);
			return this;
		}

	}

}
