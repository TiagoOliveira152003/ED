/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.de.ed.DataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import projeto.de.ed.Interfaces.UnorderedLinkedListADT;

/**
 *
 * @author tiago
 */
public class DoubleUnorderedList<T> implements UnorderedLinkedListADT<T> {

    protected DoubleNode<T> front, rear;
    protected int count;

    //-----------------------------------------------------------------
    //  Creates an empty list using the default capacity.
    //-----------------------------------------------------------------
    public DoubleUnorderedList() {
        rear = null;
        front = null;
        count = 0;
    }

    //-----------------------------------------------------------------
    //  Removes and returns the last element in the list.
    //-----------------------------------------------------------------
    public T removeLast() {
        T result;

        result = rear.getElement();
        rear = rear.getPrevious();

        if (rear == null) {
            front = null;
        } else {
            rear.setNext(null);
        }

        count--;

        return result;
    }

    //-----------------------------------------------------------------
    //  Removes and returns the first element in the list.
    //-----------------------------------------------------------------
    public T removeFirst() {

        T result = front.getElement();
        front = front.getNext();

        if (front == null) {
            rear = null;
        } else {
            front.setPrevious(null);
        }

        count--;

        return result;
    }

    //-----------------------------------------------------------------
    //  Removes and returns the specified element.
    //-----------------------------------------------------------------
    public T remove(T element) {
        T result;
        DoubleNode<T> nodeptr = find(element);

        result = nodeptr.getElement();

        // check to see if front or rear
        if (nodeptr == front) {
            result = this.removeFirst();
        } else if (nodeptr == rear) {
            result = this.removeLast();
        } else {
            nodeptr.getNext().setPrevious(nodeptr.getPrevious());
            nodeptr.getPrevious().setNext(nodeptr.getNext());
            count--;
        }

        return result;
    }

    //-----------------------------------------------------------------
    //  Returns a reference to the element at the front of the list.
    //  The element is not removed from the list.  Throws an
    //  EmptyCollectionException if the list is empty.  
    //-----------------------------------------------------------------
    public T first() {
        return front.getElement();
    }

    //-----------------------------------------------------------------
    //  Returns a reference to the element at the rear of the list.
    //  The element is not removed from the list.  Throws an
    //  EmptyCollectionException if the list is empty.  
    //-----------------------------------------------------------------
    public T last() {
        return rear.getElement();
    }

    //-----------------------------------------------------------------
    //  Returns true if this list contains the specified element.
    //-----------------------------------------------------------------
    public boolean contains(T target) {
        return (find(target) != null);
    }

    //-----------------------------------------------------------------
    //  Returns a reference to the specified element, or
    //  null if it is not found.
    //-----------------------------------------------------------------
    private DoubleNode<T> find(T target) {
        boolean found = false;
        DoubleNode<T> traverse = front;
        DoubleNode<T> result = null;

        if (!isEmpty()) {
            while (!found && traverse != null) {
                if (target.equals(traverse.getElement())) {
                    found = true;
                } else {
                    traverse = traverse.getNext();
                }
            }
        }

        if (found) {
            result = traverse;
        }

        return result;
    }

    //-----------------------------------------------------------------
    //  Returns true if this list is empty and false otherwise. 
    //-----------------------------------------------------------------
    public boolean isEmpty() {
        return (count == 0);
    }

    //-----------------------------------------------------------------
    //  Returns the number of elements currently in this list.
    //-----------------------------------------------------------------
    public int size() {
        return count;
    }

    @Override
    public void addToFront(T element) {
        DoubleNode<T> new_Node = new DoubleNode<T>(element);

        new_Node.setNext(this.front);
        new_Node.setPrevious(null);

        if (front != null) {
            front.setPrevious(new_Node);
        }
        front = new_Node;
        if (rear == null) {
            rear = front;
        }
        count++;
    }

    @Override
    public void addToRear(T element) {
        DoubleNode<T> new_Node = new DoubleNode<T>(element);

        new_Node.setPrevious(this.rear);
        new_Node.setNext(null);

        if (rear != null) {
            rear.setNext(new_Node);
        }
        rear = new_Node;
        if (front == null) {
            front = rear;
        }
        count++;
    }

    @Override
    public void addAfter(T element, T target) {
        if (front == null) {
            DoubleNode<T> n = new DoubleNode<T>(element);
            n.setNext(n);
            n.setPrevious(n);
            front = n;
        } else {
            DoubleNode<T> current = front;
            while (current.getElement() != target) {
                current = current.getNext();
            }
            //current points to node that will follow new node.
            DoubleNode<T> temp = current.getNext();
            DoubleNode<T> n2 = new DoubleNode<T>(element);
            current.setNext(n2);
            temp.setPrevious(n2);
            n2.setNext(temp);
            n2.setPrevious(current);
            //update first if necessary.
        }
        count++;
    }

    private class DoubleIterator<E> implements Iterator<E> {

        private int count;  // the number of elements in the collection
        private DoubleNode<E> current;  // the current position

        //-----------------------------------------------------------------
        //  Sets up this iterator using the specified items.
        //-----------------------------------------------------------------
        public DoubleIterator(DoubleNode<E> list, int size) {
            current = list;
            count = size;
        }

        //-----------------------------------------------------------------
        //  Returns true if this iterator has at least one more element
        //  to deliver in the iteraion.
        //-----------------------------------------------------------------
        public boolean hasNext() {
            return (current.getNext() != null);
        }

        //-----------------------------------------------------------------
        //  Returns the next element in the iteration. If there are no
        //  more elements in this itertion, a NoSuchElementException is
        //  thrown.
        //-----------------------------------------------------------------
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            current = current.getNext();
            E result = current.getElement();
            return result;
        }

        //-----------------------------------------------------------------
        //  The remove operation is not supported.
        //-----------------------------------------------------------------
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    }

//-----------------------------------------------------------------
//  Returns an iterator for the elements currently in this list.
//-----------------------------------------------------------------
    public Iterator<T> iterator() {
        DoubleIterator<T> m = new DoubleIterator<T>(this.front, count);
        System.out.println(m.next());
        return new DoubleIterator<T>(front, count);
    }

    //-----------------------------------------------------------------
    //  Returns a string representation of this list. 
    //-----------------------------------------------------------------
    public String toString() {
        String result = "";
        DoubleNode<T> traverse = front;

        while (traverse != null) {
            result = result + (traverse.getElement()).toString() + "\n";
            traverse = traverse.getNext();
        }
        return result;
    }

    @Override
    public void shuffle() {
        // TODO Auto-generated method stub
        
    }

}
