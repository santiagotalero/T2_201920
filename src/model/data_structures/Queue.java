package model.data_structures
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 *  Implementación tomada de:
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> primero;    // comienzo del queue
    private Node<Item> ultimo;     // fin del queue
    private int n;               // numero de elementos en el queue

    // helper linked list class

    private static class Node<Item> 
    {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Inicializa un queue vacío
     */
    public Queue() {
        primero = null;
        ultimo  = null;
        n = 0;
    }

    /**
     * Retorna true si el queue esta vacío
     *
     * @return {@code true} si el queue está vació; o sino {@code false} 
     */
    public boolean isEmpty() {
        return primero == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return primero.item;
    }

    /**
     * Adds the item to this queue.
     *
     * @param  item the item to add
     */
    public void enqueue(Item item) {
        Node<Item> oldultimo = ultimo;
        ultimo = new Node<Item>();
        ultimo.item = item;
        ultimo.next = null;
        if (isEmpty()) primero = ultimo;
        else           oldultimo.next = ultimo;
        n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = primero.item;
        primero = primero.next;
        n--;
        if (isEmpty()) ultimo = null;   // to avoid loitering
        return item;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
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
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator(primero);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> primero) {
            current = primero;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }


    /**
     * Unit tests the {@code Queue} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
