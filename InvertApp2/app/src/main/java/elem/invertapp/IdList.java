package elem.invertapp;

/**
 *
 * @author elliot
 */
public class IdList {
    
    private class Node {

        private String data;
        private Integer imageFull;
        private Integer imageTn;
        private Node next;

        public Node(String data, Integer imageFull, Integer imageTn, Node next) {
            this.data = data;
            this.imageFull = imageFull;
            this.imageTn = imageTn;
            this.next = next;
        }

        public Node(String data, Integer imageFull, Integer imageTn) {
            this(data, imageFull, imageTn, null);
        }

        public Node() {
            this(null, null, null);
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

    public Integer getImageFull(int index){
        return getNodeBefore(index).next.imageFull;
    }

    public Integer getImageTn(int index){
        return getNodeBefore(index).next.imageTn;
    }

    public void add(String data, Integer imageFull, Integer imageTn) {
        tail.next = new Node(data, imageFull, imageTn);
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
