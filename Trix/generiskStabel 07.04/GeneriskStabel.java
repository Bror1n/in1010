public class GeneriskStabel<E> {
    Node første;
    int length = 0;

    protected class Node{
        Node neste;
        E element;
        
        Node(E element){
            this.element = element;
        }
        
        protected void settNeste(Node neste){
            this.neste = neste;
        }
        
        protected E hentElement(){
            return this.element;
        }
    }

    public void leggPaa(E element){
        Node ny = new Node(element);

        if (this.første == null){
            this.første = ny;
        }
        else {
            ny.neste = første;
            this.første = ny;
        }
        this.length++;
    }

    public E taAv(){
        if (første == null){
            return null;
        }
        this.length--;
        E mid = første.hentElement();
        første = første.neste;
        return mid;
    }
    
    public Boolean erTom(){
        return length < 1;
    }
    
}
