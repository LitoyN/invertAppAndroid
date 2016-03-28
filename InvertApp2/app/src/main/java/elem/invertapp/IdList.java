package elem.invertapp;

/**
 *
 * @author elliot
 */
public class IdList {
    
    private class Node {

        private String data;
        private Integer imageFile;
        private Node next;

        public Node(String data, Integer imageFile, Node next) {
            this.data = data;
            this.imageFile = imageFile;
            this.next = next;
        }

        public Node(String data, Integer imageFile) {
            this(data, imageFile, null);
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

    public Integer getImage(int index){
        return getNodeBefore(index).next.imageFile;
    }

    public void add(String data, Integer imageFile) {
        tail.next = new Node(data, imageFile);
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
