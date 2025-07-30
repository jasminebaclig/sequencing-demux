public class SeqNode {
    private SeqNode next, previous;
    private String index;
    private String consensusSequence;
    private int count;

    public SeqNode(String index) {
        next = null;
        previous = null;
        this.index = index;
        consensusSequence = "";
        count = 1;
    }

    public SeqNode getNext() {
        return next;
    }

    public void setNext(SeqNode node) {
        next = node;
    }

    public SeqNode getPrevious() {
        return previous;
    }

    public void setPrevious(SeqNode node) {
        previous = node;
    }

    public String getIndex() {
        return index;
    }

    public void addIndex(String newIndex) {
        index = index + "\t" + newIndex;
    }

    public String getConsensusSequence() {
        return consensusSequence;
    }

    public void setConsensusSequence(String sequence) {
        consensusSequence = sequence;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addCount(int newCount) {
        count += newCount;
    }

    public void incrementCount() {
        count++;
    }

    public String toString() {
        return index + "," + count + "\n";
    }
}