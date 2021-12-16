package fase3;

import java.util.ArrayList;

public class Hitza {

	private String datua;

	private WebenLista webOrrienLista;

	// Eraikitzailea

	public Hitza(String datua) {

		this.setDatua(datua);

		this.setWebOrrienLista(new WebenLista());

	}

	// Get/Seterrak

	public String getDatua() {
		return datua;
	}

	public void setDatua(String datua) {
		this.datua = datua;
	}

	public WebenLista getWebOrrienLista() {
		return webOrrienLista;
	}

	public void setWebOrrienLista(WebenLista webenLista) {
		this.webOrrienLista = webenLista;
	}

	@Override
	public boolean equals(Object obj) {
		Hitza h = (Hitza) obj;
		if (this.datua.equals(h.datua))
			return true;
		return false;
	}

}
