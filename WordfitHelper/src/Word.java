public class Word {
    private final String word;
    public final int length;

    public Word(String word, int length){
        this.word = word;
        this.length = length;
    }

    public char charAt(int index) {
        // TODO: add bound checking.
        return word.charAt(index);
    }
}