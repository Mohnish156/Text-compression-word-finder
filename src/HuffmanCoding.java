import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {

	private Map<Character,String> charPrefixHashMap = new HashMap<>();
	private HuffmanNode root;
	/**
	 * This would be a good place to compute and store the tree.
	 */
	public HuffmanCoding(String text) {
		// TODO fill this in.

		Map<Character, Integer> freq = new HashMap<>();
		for(char c : text.toCharArray()){
			freq.put(c,freq.getOrDefault(c,0)+1);
		}
		PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
		for(Map.Entry<Character, Integer> entry : freq.entrySet()){
			pq.add(new HuffmanNode(entry.getKey(),entry.getValue(),null,null));
		}

		while(pq.size()!=1){
			HuffmanNode left = pq.poll();
			HuffmanNode right = pq.poll();

			int sum = left.frequency + right.frequency;
			pq.add(new HuffmanNode(('\0'),sum,left,right));
		}
		root = pq.peek();



	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {
		// TODO fill this in.
		Map<Character,Integer> freq = new HashMap<>();
		for(int i = 0; i<text.length();i++){
			if(!freq.containsKey(text.charAt(i))){
				freq.put(text.charAt(i), 0);
			}
			freq.put(text.charAt(i),freq.get(text.charAt(i))+1);
		}

		setPrefixCodes(root, new StringBuilder());

		StringBuilder s = new StringBuilder();
		for(int i = 0; i<text.length();i++){
			char c = text.charAt(i);
			s.append(charPrefixHashMap.get(c));
		}
		//return "";
		return s.toString();
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		// TODO fill this in.
		StringBuilder sb = new StringBuilder();
		HuffmanNode temp = root;

		for(int i = 0; i<encoded.length();i++){
			int j = Integer.parseInt(String.valueOf(encoded.charAt(i)));
			if(j==0){
				temp = temp.left;
				if(temp.left == null && temp.right == null){
					sb.append(temp.data);
					temp = root;
				}
			}
			if(j==1){
				temp = temp.right;
				if(temp.left == null && temp.right == null){
					sb.append(temp.data);
					temp = root;
				}
			}
		}

		return sb.toString();
	}

	private void setPrefixCodes(HuffmanNode node, StringBuilder prefix){
		if(node != null){
			if(node.left == null && node.right==null){
				charPrefixHashMap.put(node.data,prefix.toString());
			}else{
				prefix.append('0');
				setPrefixCodes(node.left,prefix);
				prefix.deleteCharAt(prefix.length()-1);

				prefix.append('1');
				setPrefixCodes(node.right,prefix);
				prefix.deleteCharAt(prefix.length()-1);
			}
		}
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print
	 * out the encoding tree.
	 */
	public String getInformation() {
		StringBuilder sb = new StringBuilder();
		for(Character c : charPrefixHashMap.keySet()){
			sb.append("Character: " + c + "  Symbol: " + charPrefixHashMap.get(c)+'\n');

		}

		return sb.toString();
	}


}
