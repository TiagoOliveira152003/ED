/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.de.ed.DataStructures;


import java.util.Iterator;

import projeto.de.ed.Interfaces.LinkedListADT;
import projeto.de.ed.exceptions.*;

/**
 *
 * @author User
 */
abstract class LinkedList<T> implements LinkedListADT<T> {
    protected LinearNode<T> head;
    protected LinearNode<T> tail;
    protected int count;
    private int modCount;
    
    public LinkedList() {
        this.head = this.tail = null;
        this.count = 0;
        this.modCount = 0;
    }

    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("empty list");
        }

        T value = this.head.getData();

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
        
        T value = this.tail.getData();
        
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
        LinearNode temp = new LinearNode();
        temp = this.head;
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty list");
        }
        
        while(temp.getNext() != null) {
            if (temp == element) {
                value = (T) temp.getData();
                temp.getPrevious().setNext(temp.getNext());
                temp.getNext().setPrevious(temp.getPrevious());
            } else {
                System.out.println("There is no element found");
                return null;
            }
        }
        count--;
        modCount++;
        return value;
    }

    @Override
    public T first() throws EmptyCollectionException {
        return this.head.getData();
    }

    @Override
    public T last() throws EmptyCollectionException {
        return this.tail.getData();
    }

    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        LinearNode temp = new LinearNode();
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty list");
        }
        while (temp.getNext() != null) {
            if (temp.getData() == target) {
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
        private LinearNode current;
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
                data = (E) current.getData();
                return data;
            }
            return null;
        }
        
    }

    @Override
    public Iterator<T> iterator() {
        return new customIterator();
    }

    public LinearNode<T> getHead() {
        return head;
    }

    public LinearNode<T> getTail() {
        return tail;
    }

    public int getCount() {
        return count;
    }

    public int getModCount() {
        return modCount;
    }

    public void setHead(LinearNode<T> head) {
        this.head = head;
    }

    public void setTail(LinearNode<T> tail) {
        this.tail = tail;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setModCount(int modCount) {
        this.modCount = modCount;
    }
}
