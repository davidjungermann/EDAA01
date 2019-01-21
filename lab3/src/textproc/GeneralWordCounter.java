package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {
	Set<String> exepWords;
	Map<String, Integer> wordCount;

	public GeneralWordCounter(Set<String> exepWords) {
		this.exepWords = exepWords;
		this.wordCount = new HashMap<String, Integer>();

	}

	public void process(String w) {
		if (!exepWords.contains(w)) {
			if (!wordCount.containsKey(w)) {
				wordCount.put(w, 1);
			} else {
				wordCount.put(w, wordCount.get(w) + 1);
			}
		}

	}

	@Override
	public void report() {
//		for (String key : wordCount.keySet()) {
//			if (wordCount.get(key) >= 200) {
//				System.out.println(key + ": " + wordCount.get(key));
//			}
//		}

		Set<Map.Entry<String, Integer>> wordSet = wordCount.entrySet(); // "counts" i instruktionerna refererar till den
																		// använda hashmapen?
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());

		for (int i = 0; i <=  4; i++) {
			System.out.println(wordList.get(i));
		}

	}
	
	public Set<Entry<String, Integer>> getWords(){
		return wordCount.entrySet();
		
	}
}
