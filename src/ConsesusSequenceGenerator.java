import java.io.*;

public class ConsesusSequenceGenerator {
    public static void main(String[] args) {
        File inputFile = new File("index_list_combined_2.csv");
        SeqLinkedList indexList = parseFile(inputFile);
    }

    private static SeqLinkedList parseFile(File inputFile) {
        SeqLinkedList indexList = new SeqLinkedList();

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String currLine = "";
            String[] currEntry;

            while((currLine = br.readLine()) != null) {
                currEntry = currLine.split(",");
                indexList.add(currEntry[0], Integer.parseInt(currEntry[1]));
            }

            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return indexList;
    }

    private static 
}