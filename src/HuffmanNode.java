public class HuffmanNode implements Comparable<HuffmanNode> {
    public int frequency;
    public char data;
    HuffmanNode left, right;


    public HuffmanNode(char d, int freq, HuffmanNode left, HuffmanNode right){
        this.data = d;
        this.frequency = freq;
        this.left = left;
        this.right = right;
    }

    public int compareTo(HuffmanNode node){
        return frequency - node.frequency;
    }

}
