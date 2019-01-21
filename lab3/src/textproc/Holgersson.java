package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {

		Set<String> exceptionWords = new HashSet<String>();
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		long t0 = System.nanoTime();
		
		while (scan.hasNext()) {
			exceptionWords.add(scan.next());
		}
		scan.close();

		TextProcessor p = new SingleWordCounter("nils");
		TextProcessor q = new SingleWordCounter("norge");
		TextProcessor r = new MultiWordCounter(REGIONS);
		TextProcessor t = new GeneralWordCounter(exceptionWords);
		List<TextProcessor> list = new ArrayList<TextProcessor>();

		//list.add(p);
		//list.add(q);
		//list.add(r);
		list.add(t);
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			for (TextProcessor n : list) {
				n.process(word);
				
			}
		}

		for (TextProcessor k : list) {
			k.report();
			s.close();
		}
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1-t0) / 1000000.0 + " ms");
	}
}
