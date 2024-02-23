package projeto.de.ed.Management;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import projeto.de.ed.Jogador;
import projeto.de.ed.DataStructures.*;
import projeto.de.ed.Enums.Team;
import projeto.de.ed.Interfaces.PlayersManagementInterface;
import projeto.de.ed.exceptions.ElementNotFoundException;
import projeto.de.ed.exceptions.EmptyCollectionException;

/**
 * <h3>
 * <strong>Classe Gestão de Jogadores</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class PlayersManagement implements PlayersManagementInterface {
    private DoubleLinkedUnorderedList<Jogador> players;
    private int numOfPlayers;

    /**
     * Getter de jogadores
     * 
     * @return Um DoubleLinkedUnorderedList com os jogadores
     */
    public DoubleLinkedUnorderedList<Jogador> getPlayers() {
        return players;
    }

    /**
     * Setter de jogadores
     * 
     * @param players Um DoubleLinkedUnorderedList com os jogadores
     */
    public void setPlayers(DoubleLinkedUnorderedList<Jogador> players) {
        this.players = players;
    }

    /**
     * Getter do número de jogadores
     * 
     * @return Um inteiro com o número de jogadores
     */
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    /**
     * Setter do número de jogadores
     * 
     * @param numOfPlayers Um inteiro com o número de jogadores
     */
    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    /**
     * Construtor da classe PlayersManagement
     */
    public PlayersManagement() {
        this.players = new DoubleLinkedUnorderedList<Jogador>();
        this.numOfPlayers = 0;
    }

    /**
     * Método que adiciona um jogador à lista de jogadores
     * 
     * @param name Nome do jogador
     * @param team Equipa do jogador
     */
    public void addPlayer(String name, Team team) {
        int i = 0;
        boolean existance = false;
        DoubleNode<Jogador> current = players.getHead();

        while (i < players.getCount()) {
            if (current.getElement().getName().equals(name)) {
                existance = true;
                break;
            } else {
                current = current.getNext();
            }
            i++;
        }
        if (existance == true) {
            System.out.println("Nome já se encontra em uso.");
        } else {
            Jogador player = new Jogador(name, team, null);
            players.addToRear(player);
            System.out.println("- Player adicionado -\n");
            numOfPlayers++;
        }
    }

    /**
     * Método que atualiza um jogador
     * 
     * @param name Nome do jogador
     * @param newName Novo nome do jogador
     */
    public void updatePlayer(String name, String newName) {
        int i = 0;
        DoubleNode<Jogador> current = players.getHead();
        while (i < players.getCount()) {
            if (current.getElement().getName().equals(name)) {
                current.getElement().setName(newName);
                System.out.println("- Player editado -\n");
                return;
            }
            current = current.getNext();
            i++;
        }
        System.out.println("Nenhum jogador encontrado!");
    }

    /**
     * Método que remove um jogador
     * 
     * @param name Nome do jogador
     */
    public void removePlayer(String name) {
        int i = 0;
        boolean existance = false;
        DoubleNode<Jogador> current = players.getHead();

        while (i < players.getCount()) {
            if (current.getElement().getName().equals(name)) {
                existance = true;
                break;
            } else {
                current = current.getNext();
            }
            i++;
        }
        if (existance == true) {
            try {
                players.remove(current.getElement());
                System.out.println("- Player removido -\n");
                numOfPlayers--;
            } catch (EmptyCollectionException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Não há nenhum jogador com esse nome");
        }
    }

    /**
     * Método que altera a equipa de um jogador
     * 
     * @param name Nome do jogador
     */
    public void ChangeTeam(String name) {
        int i = 0;
        boolean existance = false;
        DoubleNode<Jogador> current = players.getHead();

        while (i < players.getCount()) {
            if (current.getElement().getName().equals(name)) {
                existance = true;
                break;
            } else {
                current = current.getNext();
            }
            i++;
        }
        if (existance == true) {
            if (current.getElement().getTeam() == Team.Giants) {
                current.getElement().setTeam(Team.Sparks);
                System.out.println("- Player associado a nova equipa -");
            } else {
                current.getElement().setTeam(Team.Giants);
                System.out.println("- Player associado a nova equipa -");
            }
        } else {
            System.out.println("Não há nenhum jogador com esse nome");
        }
    }

    /**
     * Método que lista todos os jogadores
     */
    public void listarjogadores() {
        DoubleNode<Jogador> current = players.getHead();
        int total = 0;
        int i = 0;
        while (i < this.numOfPlayers) {
            System.out.println(current.getElement().getName());
            System.out.println("--------------------");
            current = current.getNext();
            i++;
            total++;
        }
        if (total == 0) {
            System.out.println("Não há jogadores");
        }
    }

    /**
     * Método que lista todos os jogadores de uma equipa
     * 
     * @param team Equipa
     */
    public void listPlayersByTeam(Team team) {
        DoubleNode<Jogador> current = players.getHead();
        int i = 0;

        while (i < this.numOfPlayers) {
            if (current.getElement().getTeam() == team) {
                System.out.println(current.getElement().getName());
                System.out.println("--------------------");
            }
            current = current.getNext();
            i++;
        }
    }

    /**
     * Método que lista todos os jogadores por nivel
     */
    public void listPlayersByLevel() {
        Jogador[] playerArray = new Jogador[numOfPlayers];
        DoubleNode<Jogador> current = players.getHead();
        for (int i = 0; i < numOfPlayers; i++) {
            playerArray[i] = current.getElement();
            current = current.getNext();
        }
        for (int i = 0; i < numOfPlayers - 1; i++) {
            for (int j = 0; j < numOfPlayers - i - 1; j++) {
                if (playerArray[j].getLevel() > playerArray[j + 1].getLevel()) {
                    Jogador temp = playerArray[j];
                    playerArray[j] = playerArray[j + 1];
                    playerArray[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println(playerArray[i].getName());
            System.out.println("Level: " + playerArray[i].getLevel());
            System.out.println("--------------------");
        }
    }

    /**
     * Método que lista todos os jogadores por numero de portais conquistados
     */
    public void listPlayersByNumOfPortalsConquered() {
        DoubleNode<Jogador> current;
        int i, j;
        Jogador temp;
        for (i = 0; i < numOfPlayers - 1; i++) {
            current = players.getHead();
            for (j = 0; j < numOfPlayers - i - 1; j++) {
                if (current.getElement().getNumOfPortalsConquered() > current.getNext().getElement()
                        .getNumOfPortalsConquered()) {
                    temp = current.getElement();
                    current.setElement(current.getNext().getElement());
                    current.getNext().setElement(temp);
                }
                current = current.getNext();
            }
        }
        current = players.getHead();
        i = 0;

        while (i < numOfPlayers) {
            System.out.println(current.getElement().getName());
            System.out.println("PortalsConquered: " + current.getElement().getNumOfPortalsConquered());
            System.out.println("--------------------");
            current = current.getNext();
            i++;
        }
    }

    /**
     * Método que adiciona um jogador à classe de importar
     * 
     * @param name        Nome do jogador
     * @param team        Equipa do jogador
     * @param level       Nivel do jogador
     * @param exp         Experiencia do jogador
     * @param energyAmount Energia do jogador
     */
    private void addPlayerImport(String name, Team team, int level, Double exp, int energyAmount) {
        int i = 0;
        boolean existance = false;
        DoubleNode<Jogador> current = players.getHead();

        while (i < players.getCount()) {
            if (current.getElement().getName().equals(name)) {
                existance = true;
                break;
            } else {
                current = current.getNext();
            }
            i++;
        }
        if (existance == true) {
            System.out.println("Nome já se encontra em uso.");
        } else {
            Jogador player = new Jogador(energyAmount, name, team, exp, level);
            players.addToRear(player);
            System.out.println("- Player adicionado -\n");
            numOfPlayers++;
        }
    }

    /**
     * Método que importa os jogadores de um ficheiro json
     * 
     * @param filename Nome do ficheiro
     * @throws ElementNotFoundException
     * @throws EmptyCollectionException
     */
    public void importPlayersFromJson(String filename)
            throws ElementNotFoundException, EmptyCollectionException {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(new File(filename));
            JsonNode playersNode = rootNode.get("players");

            if (playersNode.isArray()) {
                for (JsonNode playerNode : playersNode) {
                    String name = playerNode.get("name").asText();
                    String teamName = playerNode.get("team").asText();
                    Team team = Team.valueOf(teamName);
                    int level = playerNode.get("level").asInt();
                    int currentEnergy = playerNode.get("currentEnergy").asInt();
                    double experiencePoints = playerNode.get("experiencePoints").asDouble();
                    Jogador player = new Jogador(currentEnergy, name, team, experiencePoints, level);
                    player.setActualPlace(null);
                    player.setLevel(level);
                    player.setEnergyAmount(currentEnergy);
                    player.setExperiencePoints(experiencePoints);
                    addPlayerImport(player.getName(), player.getTeam(), player.getLevel(), player.getExperiencePoints(),
                            player.getEnergyAmount());
                }
                System.out.println("- Players importados -\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que exporta os jogadores para um ficheiro json
     * 
     * @param filename Nome do ficheiro
     * @return ArrayNode
     */
    public ArrayNode exportPlayersToJson(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode playerArrayNode = objectMapper.createArrayNode();

        DoubleNode<Jogador> current = players.getHead();
        for (int i = 0; i < numOfPlayers; i++) {
            ObjectNode playerNode = objectMapper.createObjectNode();
            playerNode.put("name", current.getElement().getName());
            playerNode.put("team", current.getElement().getTeam().toString());
            playerNode.put("level", current.getElement().getLevel());
            playerNode.put("currentEnergy", current.getElement().getEnergyAmount());
            playerArrayNode.add(playerNode);
            current = current.getNext();
        }
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.set("players", playerArrayNode);

        try {
            objectMapper.writeValue(new File(filename), rootNode);
            System.out.println("- Players exportados -\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerArrayNode;
    }

}
