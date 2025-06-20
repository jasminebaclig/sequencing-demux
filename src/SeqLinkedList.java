public class SeqLinkedList {
    private SeqNode head, tail;
    private int count;

    public SeqLinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    public SeqNode getHead() {
        return head;
    }

    public void setHead(SeqNode node) {
        head = node;
    }

    public SeqNode getTail() {
        return tail;
    }

    public void setTail(SeqNode node) {
        tail = node;
    }

    public int getCount() {
        return count;
    }

    public SeqNode getNode(String index) {
        SeqNode current = head;
        boolean found = false;

        while(current != null && !found) {
            if(current.getIndex().equals(index)) {
                found = true;
            } else {
                current = current.getNext();
            }
        }

        return current;
    }

    public SeqNode getNode(int index) {
        if(index < 0 || index >= count) {
            return null;
        }

        SeqNode current = head;

        for(int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current;
    }

    public void add(String index) {
        SeqNode newNode = new SeqNode(index);

        if(count != 0) {
            tail.setNext(newNode);
        } else {
            head = newNode;
        }

        newNode.setPrevious(tail);
        tail = newNode;
        count++;
    }

    public void add(String index, int indexCount) {
        SeqNode newNode = new SeqNode(index);
        newNode.setCount(indexCount);

        if(count != 0) {
            tail.setNext(newNode);
        } else {
            head = newNode;
        }

        newNode.setPrevious(tail);
        tail = newNode;
        count++;
    }

    public void remove(int index) {
        if(index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0) {
            SeqNode next = head.getNext();
            head.setNext(null);

            if(count == 1) {
                tail = null;
            } else {
                next.setPrevious(null);
            }

            head = next;
        } else {
            SeqNode previous = head;

            for(int i = 0; i < index - 1; i++) {
                previous = previous.getNext();
            }

            SeqNode current = previous.getNext();
            SeqNode next = current.getNext();
            previous.setNext(next);
            current.setNext(null);
            current.setPrevious(null);

            if(index == count - 1) {
                tail = previous;
            } else {
                next.setPrevious(previous);
            }
        }

        count--;
    }

    public String toString() {
        SeqNode current = head;
        String str = "";

        while(current != null) {
            str += current.getIndex() + "," + current.getCount() + "\n";
            current = current.getNext();
        }

        return str;
    }
}