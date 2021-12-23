package fase3;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

public class HitzenHashMapa<K,V> implements HitzenInterfazea {

	Hashtable<K, V> hashHiztegia = new Hashtable<>();
	
	@Override
	public void hitzaGehitu(Hitza hitza) {
		// TODO Auto-generated method stub
		this.hashHiztegia.put((K)hitza.getDatua(), (V)hitza);
	}

	@Override
	public Hitza hitzaBilatu(String hitza) {
		return (Hitza) this.hashHiztegia.get((K)hitza);
	}
	
}