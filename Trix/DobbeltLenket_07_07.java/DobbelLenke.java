public class DobbelLenke<E> {
    Node startNode = null;

    public void settInn(E element){
        Node node = new Node(element);
        if (startNode == null){
            startNode = node;
        }
        else {
            Node peker = startNode;
            while (peker.neste != null){
                peker = peker.neste;
            }
            peker.neste = node;
            //peker.settNeste(node);
            node.settForrige(peker);
            
        }
    }

    public void test(){
        Node peker = startNode;
        if (peker != null){
            while (peker.neste != null){
                System.out.println(peker.hentData());
                peker = peker.neste;
            }
        }
    }

    protected class Node<E> {
        E element = null;
        Node neste = null;
        Node forrige = null;

        protected Node(E element) {
            this.element = element;
        }

        void settNeste(Node node) {
            this.neste = node;
        }
        void settForrige(Node node) {
            this.forrige = node;
        }
        E hentData(){
            return this.element;
        }
    }
}
