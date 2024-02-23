/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.de.ed.DataStructures;

import java.util.Arrays;
import java.util.Collections;

import projeto.de.ed.Interfaces.UnorderedLinkedListADT;

public class DoubleLinkedUnorderedList<T> extends DoubleLinkedList<T> implements UnorderedLinkedListADT<T> {

    public DoubleLinkedUnorderedList() {
    }

    @Override
    public void addToFront(T element) {
        DoubleNode<T> node = new DoubleNode(element);
        if (super.count == 0) {
            super.head = super.tail = node;
            super.count++;
            super.modCount++;
        } else {
            node.setNext(super.head);
            super.head.setPrevious(node);
            node.setPrevious(null);
            super.head = node;
            super.count++;
            super.modCount++;
        }
    }

    @Override
    public void addToRear(T element) {
        DoubleNode<T> node = new DoubleNode(element);
        if (super.count == 0) {
            super.head = super.tail = node;
            super.count++;
            super.modCount++;
        } else {
            node.setNext(null);
            super.tail.setNext(node);
            node.setPrevious(super.tail);
            super.tail = node;
            super.count++;
            super.modCount++;
        }
    }

    @Override
    public void addAfter(T element, T target) {
        DoubleNode<T> temp = super.head;
        if (super.count == 0) {
            throw new NullPointerException("Lista vazia");
        } else {
            while (temp != tail && temp.getElement() != target) {
                temp = temp.getNext();
            }
            DoubleNode<T> node = new DoubleNode(element);
            node.setNext(temp.getNext());
            node.setPrevious(temp);
            temp.getNext().setPrevious(node);
            temp.setNext(node);
        }
    }

    public void shuffle() {
        Object[] elements = new Object[super.count];
        int i = 0;
        for (DoubleNode<T> current = super.head; current != null; current = current.getNext()) {
            elements[i++] = current.getElement();
        }

        Collections.shuffle(Arrays.asList(elements));

        DoubleNode<T> current = super.head;
        i = 0;
        while (current != null) {
            current.setElement((T) elements[i++]);
            current = current.getNext();
        }
    }
}

