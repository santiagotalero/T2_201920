import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *  Implementación tomada de:
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Stack<Item> implements Iterable<Item> {
    private Node<Item> primero;     // parte superior del stack 
    private int n;                // tamaño del stack

    // helper linked list class
    private static class Node<Item> 
    {
        private Item item;
        private Node<Item> siguiente;
    }

    /**
     * Inicializa un stack vacío
     */
    public Stack() 
    {
        primero = null;
        n = 0;
    }

    /**
     * Retorna true si el stack está vacío
     *
     * @return true si el stack está vació; o sino false.
     */
    public boolean isEmpty() 
    {
        return primero == null;
    }

    /**
     * Retorna el numero de elementos en el stack
     *
     * @return el numero de elementos en el stack
     */
    public int size() {
        return n;
    }

    /**
     * Agrega un item al stack
     *
     * @param  item el item para agregar
     */
    public void push(Item item) {
        Node<Item> oldprimero = primero;
        primero = new Node<Item>();
        primero.item = item;
        primero.next = oldprimero;
        n++;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = primero.item;        // save item to return
        primero = primero.next;            // delete primero node
        n--;
        return item;                   // return the saved item
    }


    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return primero.item;
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of items in this stack in LIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
       

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order
     */
    public Iterator<Item> iterator() {
        return new ListIterator(primero);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> primero) {
            current = primero;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }


    /**
     * Unit tests the {@code Stack} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }
}
