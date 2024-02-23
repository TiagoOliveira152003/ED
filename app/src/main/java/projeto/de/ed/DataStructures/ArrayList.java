/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.de.ed.DataStructures;

import projeto.de.ed.Interfaces.ArrayListADT;
import projeto.de.ed.exceptions.EmptyCollectionException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author tiago
 */
abstract class ArrayList<T> implements ArrayListADT<T> {
    private T[] list;
    private int rear, front;
    private final int DEF_CAP = 100;
    private int modCount;

    public ArrayList() {
        this.list = (T[]) (new Object[DEF_CAP]);
        this.rear = 0;
        this.front = 0;
        this.modCount = 0;
    }

    public ArrayList(int in_cap) {
        this.list = (T[]) (new Object[in_cap]);
        this.rear = 0;
        this.front = 0;
        this.modCount = 0;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public T[] getList() {
        return list;
    }

    public void setList(T[] list) {
        this.list = list;
    }

    public int getModCount() {
        return modCount;
    }

    public void setModCount(int modCount) {
        this.modCount = modCount;
    }

    public T removeFirst() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("erro");
        }
        T value = this.list[front];
        this.list[front] = null;
        for (int i = 0; i < this.rear; i++) {
            this.list[i] = this.list[i + 1];
        }
        this.rear--;
        this.modCount++;
        return value;
    }

    public T removeLast() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("erro");
        }
        T value = this.list[rear];
        this.list[rear] = null;
        this.rear--;
        this.modCount++;
        return value;
    }

    public T remove(T element) throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("erro");
        }
        T value = null;
        for (int i = 0; i < this.rear; i++) {
            if (list[i] == element) {
                value = list[i];
                list[i] = null;
                for (int j = i; j < this.rear; j++) {
                    list[j] = list[j + 1];
                }
                rear--;
            }
        }
        this.modCount++;
        return value;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("erro");
        }
        return this.list[front];
    }

    @Override
    public T last() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("erro");
        }
        return this.list[rear];
    }

    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("erro");
        }
        for (int i = 0; i < this.rear; i++) {
            if (list[i] == target) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if (rear == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.rear;
    }

    private class customArrayIterator<E> implements Iterator<E> {
        private final int count;
        private int current;
        private final T[] items;

        public customArrayIterator(T[] collection, int size) {
            items = collection;
            count = size;
            current = 0;
        }

        public boolean hasNext() {
            return (current < count);
        }

        public E next() {
            if (!hasNext())
                try {
                    throw new NoSuchElementException("No next element");
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }

            current++;
            return (E) items[current - 1];

        }
    }

    @Override
    public Iterator<T> iterator() {
        return new customArrayIterator(this.list, this.rear);
    }

}
