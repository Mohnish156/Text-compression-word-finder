import java.util.ArrayList;

/**
 * A new instance of LempelZiv is created for every run.
 */
public class LempelZiv {
	private ArrayList<Tuple> T = new ArrayList<>();


	/**
	 * Take uncompressed input as a text string, compress it, and return it as a
	 * text string.
	 */
	public String compress(String input) {
		// TODO fill this in.
		int input_length = input.length();

		StringBuilder output = new StringBuilder();

		output.append("[" + 0 + "|" + 0 + "|" + input.charAt(0) + "]");

		int cursor = 1;
		int Window_Size = 100;
		int length;
		int prevMatch;
		int match;
		int count=0;
		while (cursor < input_length) {
			count++;
			if(count%10000==0){
				System.out.println(count);
			}
			length = 0;
			prevMatch = 0;
			StringBuilder prefix = new StringBuilder();
			String tail = input.substring(Math.max(cursor - Window_Size, 0), cursor);
			while (true) {
				if (cursor + length == input_length) {
					match = -1;
					length = 0;
				} else {
					prefix.append(input.charAt(cursor + length));
					match = Search(tail, prefix.toString());
				}
				if (match == -1) {
					Tuple t = new Tuple(prevMatch,length,input.charAt(cursor+length));
					T.add(t);
					output.append(t.toString());
					cursor += (length + 1);
					break;
				} else {
					length++;
					prevMatch = match;
				}
			}
		}
		return output.toString();
	}

	public int Search(String tail, String test) {
		int value = tail.lastIndexOf(test);
		if (value == -1) return value;
		else return tail.length() - value;
	}

	/**
	 * Take compressed input as a text string, decompress it, and return it as a
	 * text string.
	 */
	public String decompress(String compressed) {
		// TODO fill this in.

		StringBuilder decompressed = new StringBuilder();
		int size = compressed.length();
		//Scanner s = new Scanner(compressed);
		String offset;
		String length;
		char letter = '\0';
		String combined;
		int cursor = 0;
		while (cursor < size) {
			length = "";
			offset = "";
			combined = "";

			expected('[', compressed.charAt(cursor));
			cursor++;
			while (compressed.charAt(cursor) != '|') {
				offset += compressed.charAt(cursor);
				cursor++;
			}
			expected('|', compressed.charAt(cursor));
			cursor++;
			while (compressed.charAt(cursor) != '|') {
				length += compressed.charAt(cursor);
				cursor++;
			}
			expected('|', compressed.charAt(cursor));
			cursor++;
			letter = compressed.charAt(cursor);
			cursor++;

			expected(']', compressed.charAt(cursor));
			cursor++;
			int seq = Integer.parseInt(length);
			int f = Integer.parseInt(offset);
			for (int i = 0; i < seq; i++) {
				combined += decompressed.charAt(decompressed.length() - f + i);
			}
			decompressed.append(combined);
			decompressed.append(letter);
		}
		return decompressed.toString();
	}

	/**
	 * support method for decompress to tidy up code
	 * throws error if file is unformatted.
	 */
	public void expected(char value,char next){
		try {
			if(value!=next) {
				throw new Exception("file does not follow format, expected " + value +" but was "+next);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't want to. It is called on every run and its return
	 * value is displayed on-screen. You can use this to print out any relevant
	 * information from your compression.
	 */
	public String getInformation() {
		return "";
	}
}
