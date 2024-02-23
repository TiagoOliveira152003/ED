package projeto.de.ed;

import projeto.de.ed.DataStructures.DoubleLinkedUnorderedList;
import projeto.de.ed.Enums.LocalsType;
import projeto.de.ed.exceptions.EmptyCollectionException;

/**
 * <h3>
 * <strong>Classe Connector</strong>
 * </h3>
 *
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class Connector extends Locals {
    private int cooldown;
    private DoubleLinkedUnorderedList<Interacoes> lastInteraction;

    /**
     * Construtor parametrizado
     * 
     * @param id
     * @param longitude
     * @param latitude
     * @param energyAmount
     * @param type
     * @param cooldown
     */
    public Connector(int id, double longitude, double latitude, int energyAmount, LocalsType type, int cooldown) {
        super(id, longitude, latitude, energyAmount, type);
        this.cooldown = cooldown;
        this.lastInteraction = new DoubleLinkedUnorderedList<Interacoes>();
    }

    /**
     * Getter do cooldown de um Connector
     */
    public int getCooldown() {
        return cooldown;
    }

    /**
     * Setter do cooldown de um Connector
     * 
     * @param cooldown
     */
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    /**
     * Getter da lista de interações de um Connector
     * 
     * @return lastInteraction
     */
    public DoubleLinkedUnorderedList<Interacoes> getLastInteraction() {
        return lastInteraction;
    }

    /**
     * Setter da lista de interações de um Connector
     * 
     * @param lastInteraction
     */
    public void setLastInteraction(DoubleLinkedUnorderedList<Interacoes> lastInteraction) {
        this.lastInteraction = lastInteraction;
    }

    /**
     * Método que adiciona uma interação a um Connector
     * 
     * @param interacao
     */
    public void addInteraction(Interacoes interacao) {
        lastInteraction.addToFront(interacao);
        System.out.println("- Interação adicionada ao Connector -\n");
    }

    /**
     * Método que remove uma interação de um Connector
     * 
     * @param playerName
     * @throws EmptyCollectionException
     */
    public void removerInteraction(String playerName) throws EmptyCollectionException {
        projeto.de.ed.DataStructures.DoubleNode<Interacoes> tempInt = lastInteraction.getHead();
        boolean exists = false;
        while (tempInt != null && exists != true) {
            if (tempInt.getElement().getPlayer().getName() == playerName) {

                exists = true;
                lastInteraction.remove(tempInt.getElement());
            }
            tempInt = tempInt.getNext();
        }
    }
}
