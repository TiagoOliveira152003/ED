package projeto.de.ed.Management;

import projeto.de.ed.Jogador;
import projeto.de.ed.SecretRoute;
import projeto.de.ed.DataStructures.DoubleLinkedUnorderedList;
import projeto.de.ed.DataStructures.DoubleNode;
import projeto.de.ed.Enums.Team;

/**
 * <h3>
 * <strong>Classe Gestão de Equipas</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class TeamManagement {
    PlayersManagement pm;
    DoubleLinkedUnorderedList<Jogador> Sparks;
    DoubleLinkedUnorderedList<Jogador> Giants;
    DoubleLinkedUnorderedList<SecretRoute> GiantsRotasSecretas;
    DoubleLinkedUnorderedList<SecretRoute> SparksRotasSecretas;

    /**
     * Construtor por omissão
     */
    public TeamManagement() {
    }

    /**
     * Construtor parametrizado
     * 
     * @param pm - PlayersManagement
     */
    public TeamManagement(PlayersManagement pm) {
        this.pm = pm;
        Sparks = new DoubleLinkedUnorderedList<>();
        Giants = new DoubleLinkedUnorderedList<>();
        GiantsRotasSecretas = new DoubleLinkedUnorderedList<>();
        SparksRotasSecretas = new DoubleLinkedUnorderedList<>();
    }

    /**
     * Método que adiciona um jogador a uma equipa
     */
    public void addPlayerToTeams() {
        int i = 0;
        DoubleNode<Jogador> current = pm.getPlayers().getHead();
        while (i < pm.getNumOfPlayers()) {
            if (current.getElement().getTeam() == Team.Giants) {
                Giants.addToRear(current.getElement());
            } else {
                Sparks.addToRear(current.getElement());
            }
            i++;
            current = current.getNext();
        }
    }

    /**
     * Método que adiciona uma rota secreta à equipa Sparks
     * 
     * @param secretRoute Rota Secreta
     */
    public void addSparksRotasSecretas(SecretRoute secretRoute) {
        SparksRotasSecretas.addToRear(secretRoute);
    }

    /**
     * Método que devolve as rotas secretas da equipa Sparks
     * 
     * @return rotasDaEquipa - DoubleLinkedUnorderedList<SecretRoute>
     */
    public DoubleLinkedUnorderedList<SecretRoute> SparksSecretRoutes() {
        DoubleLinkedUnorderedList<SecretRoute> rotasDaEquipa = SparksRotasSecretas;
        return rotasDaEquipa;
    }

    /**
     * Método que adiciona uma rota secreta à equipa Giants
     * 
     * @param secretRoute Rota Secreta
     */
    public void addGiantsSecretRoutes(SecretRoute secretRoute) {
        GiantsRotasSecretas.addToRear(secretRoute);
    }

    /**
     * Método que devolve as rotas secretas da equipa Giants
     * 
     * @return rotasDaEquipa - DoubleLinkedUnorderedList<SecretRoute>
     */
    public DoubleLinkedUnorderedList<SecretRoute> GiantsSecretRoutes() {
        DoubleLinkedUnorderedList<SecretRoute> rotasDaEquipa = GiantsRotasSecretas;
        return rotasDaEquipa;
    }
}
