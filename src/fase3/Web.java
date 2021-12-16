package fase3;

public class Web {

	private int id;

	private String domeinua;

	private WebenLista estekenLista;

	// Eraikitzailea

	public Web(int id, String domeinua) {

		this.id = id;

		this.setDomeinua(domeinua);

		this.setEstekenLista(new WebenLista());
	}

	// Get/Seterrak

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDomeinua() {
		return domeinua;
	}

	public void setDomeinua(String domeinua) {
		this.domeinua = domeinua;
	}

	public WebenLista getEstekenLista() {
		return estekenLista;
	}

	public void setEstekenLista(WebenLista estekenLista) {
		this.estekenLista = estekenLista;
	}

	@Override
	public boolean equals(Object obj) {
		Web w = (Web) obj;
		if (this.domeinua.equals(w.domeinua))
			return true;
		return false;
	}

}
