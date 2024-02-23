/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.de.ed.DataStructures;

import java.util.Iterator;

import projeto.de.ed.Interfaces.ListADT;
import projeto.de.ed.exceptions.EmptyCollectionException;
import projeto.de.ed.exceptions.NoSuchElementException;

public class DoubleLinkedList<T> implements ListADT<T> {
    protected DoubleNode<T> head;
    protected DoubleNode<T> tail;
    protected int count;
    protected int modCount;

    public DoubleLinkedList() {
        this.head = this.tail = null;
        this.count = 0;
        this.modCount = 0;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("empty list");
        }

        T value = this.head.getElement();

        if (count == 1) {
            this.head = this.tail = null;

        } else {

            this.head = this.head.getNext();
            this.head.setPrevious(null);
        }
        this.count--;
        modCount++;

        return value;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty list");
        }

        T value = this.tail.getElement();

        if (count == 1) {
            this.head = this.tail = null;
        } else {
            this.tail = this.tail.getPrevious();
            this.tail.setNext(null);
        }
        this.count--;
        modCount++;

        return value;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException {
        T value = null;
        boolean found = false;
        DoubleNode current = this.head;

        if (isEmpty()) {
            throw new EmptyCollectionException("Empty list");
        }

        while (current != null && !found) {
            if (element.equals(current.getElement())) {
                found = true;
            } else {
                current = current.getNext();
            }
        }

        if (!found) {
            throw new NullPointerException("Elemento não existe");
        } else {
            if (size() == 1) {
                this.head = this.tail = null;
            } else {
                if (current.equals(this.head)) {
                    this.head = current.getNext();
                } else {
                    if (current.equals(this.tail)) {
                        this.tail = current.getPrevious();
                        this.tail.setNext(null);
                    } else {
                        current.getPrevious().setNext(current.getNext());
                        current.getNext().setPrevious(current.getPrevious());
                    }
                }
            }
        }
        this.count--;
        this.modCount++;
        return (T) current.getElement();
    }

    @Override
    public T first() throws EmptyCollectionException {
        return this.head.getElement();
    }

    @Override
    public T last() throws EmptyCollectionException {
        return this.tail.getElement();
    }

    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        DoubleNode temp = new DoubleNode();
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty list");
        }
        while (temp.getNext() != null) {
            if (temp.getElement() == target) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if (this.count == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.count;
    }

    private class customIterator<E> implements Iterator<E> {
        private DoubleNode current;
        private int expectedModCount;

        public customIterator() {
            this.current = null;
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            if (modCount != expectedModCount) {
                try {
                    throw new NoSuchElementException("mod count diff from expected");
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
            }
            return (current != null);
        }

        @Override
        public E next() {
            E data = null;
            if (modCount != expectedModCount) {
                try {
                    throw new NoSuchElementException("mod count diff from expected");
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
            }
            if (hasNext()) {
                data = (E) current.getElement();
                current = current.getNext();
                return data;
            }
            return null;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new customIterator();
    }

    public DoubleNode<T> getHead() {
        return head;
    }

    public DoubleNode<T> getTail() {
        return tail;
    }

    public int getCount() {
        return count;
    }

    public int getModCount() {
        return modCount;
    }

    public void setHead(DoubleNode<T> head) {
        this.head = head;
    }

    public void setTail(DoubleNode<T> tail) {
        this.tail = tail;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setModCount(int modCount) {
        this.modCount = modCount;
    }
}
