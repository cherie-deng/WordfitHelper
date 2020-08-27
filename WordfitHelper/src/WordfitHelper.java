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
            File file = new File(filepath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null)
            {
                if (!line.isEmpty() && line.charAt(0) != '#') {
                    words.add(new Word(line));
                }
            }
            fr.close();
            search();
            // TODO: add search for combination of characters anywhere in word. E.g. "ee" returns bee, tee, deed etc.
        }
        catch(IOException e)
        {
            System.out.println("Error: invalid input file.");
            e.printStackTrace();
        }
    }

    public void search() throws IOException{ // IOException for readLine().
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) { // Loop until exit command received.
            System.out.println("\nEnter character followed by index. (# to exit)");

            String input = reader.readLine();
            if (input.equals("#")) { // Exit.
                break;
            }

            String[] values = input.split(" ");
            try {
                char character = values[0].charAt(0); // FIXME: shouldn't allow double letters or numbers as a character
                int natIndex = Integer.parseInt(values[1]);
                if (natIndex > 0 && natIndex < 8) { // Boundary checking.
                    findWords(character, natIndex - 1);
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                // TODO: differentiate between invalid characters and indices?
            }
        }
        System.out.println("\nThank you for using Wordfit Helper.");
    }

    public void findWords(char character, int index){
        Map<Integer, ArrayList<Word>> matches = newLengthMap(index);
        for (Word word : words) {
            if (word.length > index && word.charAt(index) == character) {
                matches.get(word.length).add(word);
            }
        }

        for (Map.Entry<Integer, ArrayList<Word>> entry : matches.entrySet()) {
            ArrayList<Word> words = entry.getValue();
            if (words.size() > 0) {
                System.out.println("\n" + entry.getKey() + " letter words:");
                for (Word word : words) {
                    System.out.println(word.toString());
                }
            }
        }

        if (!wordFound(matches)) {
            System.out.println("No matches found.");
        }
    }

    public Map<Integer, ArrayList<Word>> newLengthMap(int index){
        Map<Integer, ArrayList<Word>> lengthMap = new HashMap<>();
        // TODO: remove limit of 7 letter words
        for (int i = index; i <= 7; i++) {
            lengthMap.put(i, new ArrayList<>());
        }
        return lengthMap;
    }

    public boolean wordFound(Map<Integer, ArrayList<Word>> lengthMap){
        for (ArrayList<Word> list : lengthMap.values()) {
            if (list.size() > 0) {
                return true;
            }
        }
        return false;
    }

    public static void main ( String [] arguments )
    {
        System.out.println("Wordfit Helper");
        WordfitHelper wfh = new WordfitHelper();
    }
}