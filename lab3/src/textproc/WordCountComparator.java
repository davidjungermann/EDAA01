package textproc;

import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {

		int compValue = arg1.getValue().compareTo(arg0.getValue()); // värdet sorteras i sjunkande ordning
		int compWord = arg0.getKey().compareTo(arg1.getKey()); // bokstavsordning är sjunkande ordning, byter därför plats på parametrar

		if (compValue == 0) {
			return compWord;
		}

		return compValue;

	}

}
