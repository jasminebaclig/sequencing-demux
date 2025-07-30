/**
 * Unfinished code.
 */
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

    private static void makeConsensusSequence(SeqLinkedList indexList) {
        SeqNode currNode = indexList.getHead();
        String[] currIndexArray;
        int currCount = 0;
        String currConsensus = "";

        while(currNode != null) {
            currIndexArray = currNode.getIndex().split("\t");

            for(int i = 0; i < currIndexArray[0].length(); i++) {
                for(int j = 0; j < currIndexArray.length; j++) {
                }
            }
        }
    }
}