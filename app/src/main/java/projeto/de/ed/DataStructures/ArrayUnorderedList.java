/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.de.ed.DataStructures;

import projeto.de.ed.Interfaces.*;
import projeto.de.ed.exceptions.EmptyCollectionException;

/**
 *
 * @author tiago
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedLinkedListADT<T> {

    public ArrayUnorderedList() {
    }

    public ArrayUnorderedList(int in_cap) {
        super(in_cap);
    }

    public void addToFront(T element) {
        for (int i = this.getRear(); i > 0; i--) {
            this.getList()[i] = this.getList()[i - 1];
        }
        this.getList()[0] = element;
        this.setRear(this.getRear() + 1);
        this.setModCount(this.getModCount() + 1);
    }

    public void addToRear(T element) {
        this.getList()[this.getRear()] = element;
        this.setRear(this.getRear() + 1);
        this.setModCount(this.getModCount() + 1);
    }

    @Override
    public void addAfter(T element, T target) {
        int x = 0;
        while (x < this.getRear() && this.getList()[x] != target) {
            x++;
        }
        for (int i = this.getRear(); i > x; i--) {
            this.getList()[i] = this.getList()[i - 1];
        }
        this.getList()[x] = element;
        this.setRear(this.getRear() + 1);
        this.setModCount(this.getModCount() + 1);
    }

    @Override
    public T remove(T element) throws EmptyCollectionException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void shuffle() {
        // TODO Auto-generated method stub
        
    }

}
