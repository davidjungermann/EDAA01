package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {

	Map<String, Integer> m = new HashMap<String, Integer>();

	public MultiWordCounter(String[] word) {
		for (int i = 0; i < word.length; i++) {
			m.put(word[i], 0);
		}
	}

	@Override
	public void process(String w) {

		for (String key : m.keySet()) { // ta bort, använd containsKey! 
			if (key.equals(w)) {
				int value = m.get(key);
				value++;
				m.put(key, value);
			}
		}
	}

	@Override
	public void report() {

		for (String key : m.keySet()) {
			System.out.println(key + ":" + " " + m.get(key));
		}
	}
}
