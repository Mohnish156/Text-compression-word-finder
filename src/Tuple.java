public class Tuple {
    public int offset;
    public int length;
    public char symbol;


    @Override
    public String toString() {
        return "[" + offset + "|" + length + "|" + symbol + "]";
    }

    public Tuple(int offset, int length, char symbol) {
        this.offset = offset;
        this.length = length;
        this.symbol = symbol;
    }
}
