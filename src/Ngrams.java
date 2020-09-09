import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ngrams predictive probabilities for text
 */
public class Ngrams {
	/**
	 * The constructor would be a good place to compute and store the Ngrams probabilities.
	 * Take uncompressed input as a text string, and store a List of Maps. The n-th such
	 * Map has keys that are prefixes of length n. Each value is itself a Map, from
	 * characters to floats (this is the probability of the char, given the prefix).
	 */
	List<Map<String, Map<Character, Float>>> ngram;  /* nb. suggestion only - you don't have to use
                                                     this particular data structure */


	public Ngrams(String input) {
		// TODO fill this in.

		for(int i = 0; i<5; i++) {

			Map<String, Map<Character, Float>> probs = new HashMap<>();
			//Map<Character,Float> probs = new HashMap<>();
			Map<String, Map<Character, Integer>> prefixes_c = this.ngramProbsCalc(input, i);

			for (String prefix : prefixes_c.keySet()) { //Go through every prefix

				Map<Character, Integer> inner_count_map = prefixes_c.get(prefix);
				int sum = 0; //Initialise sum for every prefix

				for (Character c : inner_count_map.keySet()) { //Going through inner map
					sum += inner_count_map.get(c);
				}

				for (Character c : inner_count_map.keySet()) {
					Float prob = (float) prefixes_c.get(prefix).get(c) / sum;
					Map<Character, Float> inner_float_map = new HashMap<>();
					inner_float_map.put(c, prob);
					probs.put(prefix,inner_float_map);
				}

			}
			ngram.add(probs);
		}

	}


	/**
	 * Take a string, and look up the probability of each character in it, under the Ngrams model.
	 * Returns a List of Floats (which are the probabilities).
	 */
	public void findCharProbs(String input) {
		// TODO fill this in.
		char[] input_chars = input.toCharArray();

		for (int i = 0; i < ngram.size(); i++) {
			Map<String, Map<Character, Float>> current_outermap = ngram.get(i);

			for (Map<Character, Float> current_innermap : current_outermap.values()) {
				for (Character c : input_chars) {
					if (current_innermap.containsKey(c)) {
						System.out.println("Ngram input length:" + i + "prob of character: " + c + " = " + current_innermap.get(c) + "\n");
					}

				}
			}

		}

	}

	/**
	 * Take a list of probabilites (floats), and return the sum of the logs (base 2) in the list.
	 */
	public float calcTotalLogProb(List<Float> charProbs) {
		// TODO fill this in.
		float f = 0;
		return f;
	}

	public Map<String, Map<Character, Integer>> ngramProbsCalc(String input, int prefix_L) {

		Map<String, Map<Character, Integer>> prefixes_c = new HashMap<>();

		for (int i = 0; i < input.length()-1; i++) {
			String prefix = input.substring(i, i + prefix_L);
			Character c = input.charAt(i + prefix_L + 1);


			if (prefixes_c.containsKey(prefix) && prefixes_c.get(prefix).get(c) != null) {
				//System.out.println("jeff if");
				for (Character key : prefixes_c.get(prefix).keySet()) {

					if (key.equals(c)) {
						int count = prefixes_c.get(prefix).get(c) + 1;
						prefixes_c.get(prefix).put(c, count);
					}
				}
			} else {
				System.out.println("jeff else");
				Map<Character, Integer> new_p = new HashMap<>();
				new_p.put(c, 1);
				prefixes_c.put(prefix,new_p);
				//System.out.println(prefixes_c.size());

			}
		}
		return prefixes_c;

	}
}
