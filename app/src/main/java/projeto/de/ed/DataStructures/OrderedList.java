/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.de.ed.DataStructures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import projeto.de.ed.Interfaces.OrderedListADT;

/**
 *
 * @author tiago
 * @param <T>
 */
public class OrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    private final int DEFAULT_CAPACITY = 100;
    private int count;
    private T[] array;

    //-----------------------------------------------------------------
    //  Creates an empty queue using the default capacity.
    //-----------------------------------------------------------------
    public OrderedList() {
        count = 0;
        array = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    //-----------------------------------------------------------------
    //  Creates an empty queue using the specified capacity.
    //-----------------------------------------------------------------
    public OrderedList(int initialCapacity) {
        count = 0;
        array = ((T[]) (new Object[initialCapacity]));
    }

    @Override
    public void add(T element) {

        Comparable<T> temp = (Comparable<T>) element;

        int scan = 0;
        while (scan < count && temp.compareTo(array[scan]) > 0) {
            scan++;
        }

        for (int scan2 = count; scan2 > scan; scan2--) {
            array[scan2] = array[scan2 - 1];
        }

        array[scan] = element;
        count++;
    }

    private class CustomIterator<E> implements Iterator<E> {

        int current;

        public CustomIterator() {
            this.current = 0;
        }

        // returns false if next element does not exist
        public boolean hasNext() {
            if (current + 1 < count && array[current] != null) {
                return true;
            }
            return false;
        }

        // return current data and update pointer
        public E next() throws NoSuchElementException {
            if (hasNext() == false) {
                throw new NoSuchElementException("Não há próximo elemento");
            }
            current++;
            return (E) array[current];
        }

        // implement if needed
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        CustomIterator<T> m = new CustomIterator<>();
        try {
            System.out.println(m.hasNext());
            System.out.println(m.current);
            System.out.println(m.next());
            System.out.println(m.next());
            System.out.println(m.current);
            System.out.println(m.next());
            System.out.println(m.current);
            System.out.println(m.next());
        } catch (NoSuchElementException exp) {
            System.out.println(exp.getMessage());
        }
        return new CustomIterator<>();

    }
}
