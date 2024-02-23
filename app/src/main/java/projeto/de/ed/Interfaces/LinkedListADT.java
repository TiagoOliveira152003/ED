/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projeto.de.ed.Interfaces;

import java.util.Iterator;

import projeto.de.ed.exceptions.EmptyCollectionException;

/**
 *
 * @author User
 */
public interface LinkedListADT<T> {
    public T removeFirst() throws EmptyCollectionException;

    public T removeLast() throws EmptyCollectionException;

    public T remove(T element) throws EmptyCollectionException;

    public T first() throws EmptyCollectionException;

    public T last() throws EmptyCollectionException;

    public boolean contains(T target) throws EmptyCollectionException;

    public boolean isEmpty();

    public int size();

    public Iterator<T> iterator();

    @Override
    public String toString();
}
