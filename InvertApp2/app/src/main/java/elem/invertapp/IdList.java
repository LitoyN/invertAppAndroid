package elem.invertapp;

/**
 *
 * @author elliot
 */
public class IdList {
    
    private class Node {

        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(String data) {
            this(data, null);
        }

        public Node() {
            this(null, null);
        }
    }
    
    private Node head;
    private Node tail;
    private int size;
    
    public IdList() {
        head = new Node();
        tail = head;
        size = 0;
    }
    
    public int size() {
        return size;
    }
    
    private Node getNodeBefore(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    
    public String get(int index) {

        return getNodeBefore(index).next.data;
    }

    public void add(String data) {
        tail.next = new Node(data);
        tail = tail.next;
        size++;
    }
    
    public void remove(int index) {

        Node previous = getNodeBefore(index);
        previous.next = previous.next.next;
        if (previous.next == null) {
            tail = previous;
        }
        size--;
    }
    
}
