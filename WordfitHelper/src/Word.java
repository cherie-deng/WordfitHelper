public class Word {
    private final String word;
    public final int length;

    public Word(String word){
        this.word = word;
        this.length = word.length(); // TODO: remove hyphens
    }

    public char charAt(int index) {
        // TODO: add bound checking
        if (index >= word.length()) {
            return '#'; // Random non-letter symbol.
        }
        return word.charAt(index);
    }

    @Override
    public String toString(){
        return word;
    }
}