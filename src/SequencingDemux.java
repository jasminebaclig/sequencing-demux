import java.io.*;

public class SequencingDemux {
    public static void main(String[] args) {
        File inputFile = new File("test.fastq");
        parseFile(inputFile);
    }

    private static void parseFile(File inputFile) {
        String currLine = "";
        String[] lineArray;
        String currIndex = "";
        SeqLinkedList indexList = new SeqLinkedList();
        SeqNode matchingNode = null;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));

            while((currLine = br.readLine()) != null) {
                if(currLine.startsWith("@")) {
                    lineArray = currLine.split(":");
                    currIndex = lineArray[9];

                    System.out.print(currIndex);

                    if((matchingNode = indexList.getNode(currIndex)) == null) {
                        System.out.println(" new");
                        indexList.add(currIndex);
                    } else {
                        System.out.println(" found");
                        matchingNode.incrementCount();
                    }
                }
            }

            outputResults(indexList.toString());
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void outputResults(String output) {
        try {
            FileWriter outputFile = new FileWriter("index_list.csv");
            BufferedWriter bw = new BufferedWriter(outputFile);
            bw.write(output);
            bw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}