/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {

	private int[] M; // partial match table
	public KMP(String pattern, String text) {
	// TODO maybe fill this in.
		char[] S = pattern.toCharArray(); //Pattern into char
		 M = new int[pattern.length()]; // partial match table
		int m = pattern.length();

		M[0] = -1;
		M[1] = 0;

		int j = 0;
		int pos = 2;

		while(pos<m){
			if(S[pos-1] == S[j]){
				M[pos] = j+1;
				pos++;
				j++;
			}else if(j>0){
				j = M[j];
			}else{
				M[pos] = 0;
				pos++;
			}
		}
	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String pattern, String text) {
		// TODO fill this in.
		int k = 0; // index in T
		int i = 0; // index in S
		int n = text.length();
		int m = pattern.length();


		char[] S = pattern.toCharArray(); //Pattern into char
		char[] T = text.toCharArray(); // Text into char


		while(k+i <n){
			if(S[i] == T[k+i]){
				i = i+1;
				if(i == m){
					return k;
				}
			}else if(M[i] == -1){
				k = k+i+1;
				i = 0;
			}else{
				k = k+i - M[i];
				i = M[i];
			}
		}
		return -1;
	}



}
