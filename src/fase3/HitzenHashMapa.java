package fase3;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

public class HitzenHashMapa<K,V> implements HitzenInterfazea {

	HashMap<String, Hitza> hashHiztegia = new HashMap<String,Hitza>();
	
	@Override
	public void hitzaGehitu(Hitza hitza) {
		this.hashHiztegia.put(hitza.getDatua(), hitza);
	}

	@Override
	public Hitza hitzaBilatu(String hitza) {
		return (Hitza) this.hashHiztegia.get(hitza);
	}
	
}