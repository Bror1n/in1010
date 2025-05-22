import java.util.Iterator;

public class EnkeltLenket<E> implements Iterable<E>{
    Node first;

    protected class Node {
        private E content;
        public Node next = null;

        public Node(E content) {
            this.content = content;
        }

        public void setNext(Node node){
            this.next = node;
        }

        public E getContent(){
            return this.content;
        }
    }

    public void leggTil(E content){
        Node newNode = new Node(content);
        if (first == null){
            first = newNode;
            return;
        }
        findLastR(this.first).setNext(newNode);
    }

    public Node findLastR(Node node){
        if (node.next == null){
            return node;
        }
        return findLastR(node.next);
    }

    public E taUt(){
        Node mellompeker = first;
        first = first.next;
        return mellompeker.getContent();
    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node current = first;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public E next() {
            E output = current.getContent();
            current = current.next;
            return output; 
        }
    }


}
