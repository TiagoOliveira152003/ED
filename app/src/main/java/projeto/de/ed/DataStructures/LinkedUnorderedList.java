/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.de.ed.DataStructures;

import projeto.de.ed.Interfaces.UnorderedLinkedListADT;
import projeto.de.ed.exceptions.ElementNotFoundException;
import projeto.de.ed.exceptions.EmptyCollectionException;

/**
 * @param <T> tipo genérico
 */
public class LinkedUnorderedList<T> extends LinkedList<T> implements UnorderedLinkedListADT<T> {

    /**
     * Adiciona um elemento especifico à front da LinkedUnorderedList
     *
     * @param t elemento a ser adicionado
     */
    @Override
    public void addToFront(T t) {
        LinearNode<T> temp = new LinearNode<>(t);

        if (this.count == 0) {
            this.head = this.tail = temp;
            this.count++;
        } else {
            temp.setNext(this.head);
            this.head = temp;
            this.count++;
        }
    }

    /**
     * Adiciona um elemento especifico à rear da LinkedUnorderedList
     *
     * @param t elemento a ser adicionado
     */
    @Override
    public void addToRear(T t) {
        LinearNode<T> temp = new LinearNode<>(t);

        if (this.count == 0) {
            this.head = this.tail = temp;
            this.count++;
        } else {
            this.tail.setNext(temp);
            this.tail = temp;
            this.count++;
        }

    }

    /**
     * Adiciona um elemento especifico a seguir a um elemento especifico
     *
     * @param t o elemento a ser adiconado
     * @param t1 o elemento t (em cima) vai ser adicionado depois do elemento t1
     */
    @Override
    public void addAfter(T t, T t1) throws ElementNotFoundException {
        LinearNode<T> tmp = this.head;
        LinearNode<T> refNode = null;

        while (true) {
            if (tmp == null) {
                break;
            }

            if (tmp.getData().equals(t1)) {
                refNode = tmp;
                break;
            }

            tmp = tmp.getNext();
        }
        if (refNode != null) {
            LinearNode<T> nd = new LinearNode<>(t);
            nd.setNext(tmp.getNext());

            if (tmp == this.tail) {
                this.tail = nd;
            }

            tmp.setNext(nd);
        } else {
            throw new ElementNotFoundException("Elemento inexistente.");
        }

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

