import java.util.*;
import java.io.*;

public class WordfitHelper {
    private ArrayList<Word> words = new ArrayList<>();

    public WordfitHelper(){
        loadFromFile("files/aug26.txt");
        // TODO: allow user to select input file (GUI feature?)
    }

    public void loadFromFile(String filepath){
        try
        {
            File file=new File(filepath);
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                // TODO: check for invalid input
                if (!line.isEmpty() && line.charAt(0) != '#') {
                    words.add(new Word(line, line.length()));
                }
            }
            fr.close();
            search();
        }
        catch(IOException e)
        {
            System.out.println("Error: invalid input.");
            e.printStackTrace();
        }
    }

    public void search() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) { // Loop until exit command received.
            System.out.println("\nEnter character followed by index. (# to exit)");

            String input = reader.readLine();
            if (input.equals("#")) {
                break;
            }

            String[] values = input.split(" ");
            char character = values[0].charAt(0); // FIXME: is this dodgy?
            int natIndex = Integer.parseInt(values[1]);

            if (natIndex > 0 && natIndex < 8) { // Boundary checking.
                findWords(character, natIndex - 1);
            } else {
                System.out.println("Invalid input. Try again.");
                // TODO: differentiate between invalid characters and indices
            }
        }
        System.out.println("\nThank you for using Wordfit Helper.");
    }

    public void findWords(char character, int index){
        // TODO: add length headers e.g. "4 letter words:"
        boolean wordFound = false;
        for (Word word : words) {
            if (word.length > index && word.charAt(index) == character) {
                wordFound = true;
                System.out.println(word.toString());
            }
        }

        if (!wordFound) {
            System.out.println("No matches found.");
        }
    }

    public static void main ( String [] arguments )
    {
        System.out.println("Wordfit Helper");
        WordfitHelper wfh = new WordfitHelper();
    }
}