package projeto.de.ed.Interfaces;

import projeto.de.ed.exceptions.ElementNotFoundException;

public interface UnorderedLinkedListADT<T> extends ListADT<T> {
    public void addToFront(T element);

    public void addToRear(T element);

    public void addAfter(T element, T target) throws ElementNotFoundException;

    public void shuffle();
}
