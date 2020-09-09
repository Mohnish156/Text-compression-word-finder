public class BruteForce {

    public int search_bruteforce(String pattern, String text){

        char[] S = pattern.toCharArray(); //Pattern into char
        char[] T = text.toCharArray(); // Text into char

        int m = pattern.length();
        int n = text.length();

        boolean found;

        for(int k = 0; k<n-m;k++){
            found = true;
            for(int i = 0; i<m;i++){
                if(S[i] != T[k+i]){
                    found = false;
                    break;
                }
            }if(found){
                return k;
            }
        }
        return -1;
    }



}
