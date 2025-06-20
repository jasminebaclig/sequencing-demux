import java.io.*;

public class IndexComparator_NoArray {
    public static void main(String[] args) {
        File inputFile = new File("index_list_no_array_updated_R1.csv");
        SeqLinkedList indexList = parseFile(inputFile);
        compareIndeces(indexList);
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

    private static void compareIndeces(SeqLinkedList indexList) {
        int currPos = 0;
        int matchingPos = currPos + 1;
        SeqNode currNode = indexList.getHead();
        SeqNode matchingNode;
        String currIndex = "";
        String matchingIndex = "";
        String[] currIndexArray;
        String[] matchingIndexArray;
        boolean startNewFile;

        while(currNode != null) {
            matchingNode = currNode.getNext();
            currIndex = currNode.getIndex();
            currIndexArray = currIndex.split("[+]");
            startNewFile = true;

            System.out.println(currIndex);

            while(matchingNode != null) {
                matchingIndex = matchingNode.getIndex();
                matchingIndexArray = matchingIndex.split("[+]");

                System.out.println("\tmathcing to " + matchingIndex);

                if(isMatch(currIndexArray[0], matchingIndexArray[0]) && isMatch(currIndexArray[1], matchingIndexArray[1])) {
                    currNode.addIndex(matchingIndex);
                    currNode.addCount(matchingNode.getCount());
                    matchingNode = matchingNode.getNext();
                    indexList.remove(matchingPos);
                } else {
                    updateIndexList(matchingNode.toString(), startNewFile);
                    startNewFile = false;
                    matchingPos++;
                    matchingNode = matchingNode.getNext();
                }
            }

            outputResults(currNode.toString());
            currPos++;
            matchingPos = currPos + 1;
            currNode = currNode.getNext();
        }

        updateIndexList("done", true);
    }

    private static boolean isMatch(String currIndex, String matchingIndex) {
        final int MAX_DIFF = 1;
        int currDiff = 0;
        int minLength = currIndex.length();
        boolean isMatch = true;

        if(minLength > matchingIndex.length()) {
            minLength = matchingIndex.length();
        }

        for(int i = 0; i < minLength; i++) {
            if(currIndex.charAt(i) != matchingIndex.charAt(i)) {
                currDiff++;
            }

            if(currDiff > MAX_DIFF) {
                isMatch = false;
                break;
            }
        }

        return isMatch;
    }

    private static void outputResults(String output) {
        try {
            File file = new File("index_list_no_array_combined_R1.csv");

            if(!file.exists()) {
                file.createNewFile();
            }

            FileWriter outputFile = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(outputFile);
            bw.write(output);
            bw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateIndexList(String nonMatch, boolean startNewFile) {
        try {
            File tempFile = new File("temp_no_array_R1.csv");
            File file = new File("index_list_no_array_updated_R1.csv");

            if(!tempFile.exists()) {
                tempFile.createNewFile();
            } else if(startNewFile) {
                if(file.exists()) {
                    file.delete();
                }

                tempFile.renameTo(file);
                tempFile.delete();
                tempFile.createNewFile();
            }

            FileWriter outputFile = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(outputFile);
            bw.write(nonMatch);
            bw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
