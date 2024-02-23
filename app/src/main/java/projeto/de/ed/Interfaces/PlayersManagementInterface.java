package projeto.de.ed.Interfaces;

import com.fasterxml.jackson.databind.node.ArrayNode;

import projeto.de.ed.Enums.Team;
import projeto.de.ed.exceptions.ElementNotFoundException;
import projeto.de.ed.exceptions.EmptyCollectionException;

/**
 * <h3>
 * <strong>Interface  Gestão de Jogadores</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public interface PlayersManagementInterface {
    public void addPlayer(String name, Team team);

    public void updatePlayer(String name, String newName);

    public void removePlayer(String name);

    public void ChangeTeam(String name);

    public void listarjogadores();

    public void listPlayersByTeam(Team team);

    public void listPlayersByLevel();

    public void listPlayersByNumOfPortalsConquered();

    public void importPlayersFromJson(String filename)
            throws ElementNotFoundException, EmptyCollectionException;

    public ArrayNode exportPlayersToJson(String filename);

}
