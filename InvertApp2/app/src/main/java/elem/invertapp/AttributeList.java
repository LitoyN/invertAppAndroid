package elem.invertapp;

import java.util.Arrays;

/**
 *
 * @author elliot
 */
public class AttributeList {
    
    private class Node {

        private int[] startArr;
        private int[] data;
        private Node next;

        public Node(int[] data, Node next) {
            this.data = Arrays.copyOf(data, data.length);
            this.next = next;
        }

        public Node(int[] data) {
            this(data, null);
        }

        public Node() {
            this(null, null);
        }
    }
    
    public int[] startArr = new int[1];
    private Node head;
    private Node tail;
    private int size;
    
    public AttributeList() {
        head = new Node(startArr);
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
    
    public int[] get(int index) {

        return getNodeBefore(index).next.data;
    }

    public void add(int[] data) {
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
