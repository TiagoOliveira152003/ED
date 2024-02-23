package projeto.de.ed.Management;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import projeto.de.ed.Connector;
import projeto.de.ed.Jogador;
import projeto.de.ed.Locals;
import projeto.de.ed.Portal;
import projeto.de.ed.DataStructures.DoubleLinkedUnorderedList;
import projeto.de.ed.DataStructures.DoubleNode;
import projeto.de.ed.Enums.LocalsType;
import projeto.de.ed.Enums.PortalsState;
import projeto.de.ed.Interfaces.LocalsManagementInterface;
import projeto.de.ed.exceptions.EmptyCollectionException;

/**
 * <h3>
 * <strong>Classe de Gestão de Locais</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class LocalsManagement implements LocalsManagementInterface {
    private DoubleLinkedUnorderedList<Locals> locals = new DoubleLinkedUnorderedList<Locals>();

    /**
     * Adiciona um portal ao jogo
     * 
     * @param id          ID do portal
     * @param longitude   Longitude do portal
     * @param latitude    Latitude do portal
     * @param name        Nome do portal
     * @param maxEnergy   Energia máxima do portal
     */
    public void addPortal(int id, double longitude, double latitude, String name, int maxEnergy) {
        boolean exists = false;
        int i = 0;
        DoubleNode<Locals> current = locals.getHead();
        while (i < locals.getCount()) {
            if (current.getElement().getID() == id) {
                exists = true;
            }
            i++;
            current = current.getNext();
        }
        if (exists == false) {
            Portal portal = new Portal(id, longitude, latitude, 0, LocalsType.Portal, PortalsState.Neutro, null, name,
                    maxEnergy);
            locals.addToRear(portal);
            System.out.println("- Portal adicionado -\n");
        } else {
            System.out.println("ID inválido ou em uso!");
        }
    }

    /**
     * Adiciona um connector ao jogo
     * 
     * @param id          ID do connector
     * @param longitude   Longitude do connector
     * @param latitude    Latitude do connector
     * @param cooldown    Cooldown do connector
     * @param energyAmount Energia máxima do connector
     */
    public void addConnector(int id, double longitude, double latitude, int cooldown, int energyAmount) {
        boolean exists = false;
        int i = 0;
        DoubleNode<Locals> current = locals.getHead();
        while (i < locals.getCount()) {
            if (current.getElement().getID() == id) {
                exists = true;
            }
            i++;
            current = current.getNext();
        }
        if (exists == false) {
            Connector connector = new Connector(id, longitude, latitude, energyAmount, LocalsType.Connector, cooldown);
            locals.addToRear(connector);
            System.out.println("- Connector adicionado -\n");

        } else {
            System.out.println("ID inválido ou em uso!");
        }
    }

    /**
     * Atualiza um portal do jogo
     * 
     * @param iD                  ID do portal
     * @param newName             Novo nome do portal
     * @param newLongitude        Nova longitude do portal
     * @param newLatitude         Nova latitude do portal
     * @param newMaxEnergyAmount  Nova energia máxima do portal
     */
    public void updatePortal(int iD, String newName, double newLongitude, double newLatitude, int newMaxEnergyAmount) {
        int i = 0;
        DoubleNode<Locals> current = locals.getHead();
        while (i < locals.getCount()) {
            if (current.getElement().getID() == iD && current.getElement() instanceof Portal) {
                Portal portalUpd = (Portal) current.getElement();
                portalUpd.setName(newName);
                ;
                portalUpd.setLongitude(newLongitude);
                portalUpd.setLatitude(newLatitude);
                portalUpd.setMaxEnergyAmount(newMaxEnergyAmount);
                System.out.println("- Portal editado -\n");
                return;
            }
            current = current.getNext();
            i++;
        }
        System.out.println("Nenhum portal encontrado");
    }

    /**
     * Atualiza um connector do jogo
     * 
     * @param iD                  ID do connector
     * @param newLongitude        Nova longitude do connector
     * @param newLatitude         Nova latitude do connector
     * @param newCooldown         Novo cooldown do connector
     * @param newMaxEnergyAmount  Nova energia máxima do connector
     */
    public void updateConnector(int iD, double newLongitude, double newLatitude, int newCooldown,
            int newMaxEnergyAmount) {
        int i = 0;
        DoubleNode<Locals> current = locals.getHead();
        while (i < locals.getCount()) {
            if (current.getElement().getID() == iD && current.getElement() instanceof Connector) {
                Connector connectorUpd = (Connector) current.getElement();
                connectorUpd.setLongitude(newLongitude);
                connectorUpd.setLatitude(newLatitude);
                connectorUpd.setCooldown(newCooldown);
                connectorUpd.setEnergyAmount(newMaxEnergyAmount);
                System.out.println("- Connector editado -\n");
                return;
            }
            current = current.getNext();
            i++;
        }
        System.out.println("Nenhum connector encontrado");
    }

    /**
     * Remove um portal do jogo
     * 
     * @param iD ID do portal
     */
    public void removePortal(int iD) {
        int i = 0;
        boolean existance = false;
        DoubleNode<Locals> current = locals.getHead();

        while (i < locals.getCount()) {
            if (current.getElement().getID() == iD && current.getElement() instanceof Portal) {
                existance = true;
                break;
            } else {
                current = current.getNext();
            }
            i++;
        }
        if (existance == true) {
            Portal portalUpd = (Portal) current.getElement();
            try {
                locals.remove(portalUpd);
                System.out.println("- Portal removido -\n");
            } catch (EmptyCollectionException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Não há nenhum Portal com esse ID");
        }
    }

    /**
     * Remove um connector do jogo
     * 
     * @param iD ID do connector
     */
    public void removeConnector(int iD) {
        int i = 0;
        boolean existance = false;
        DoubleNode<Locals> current = locals.getHead();

        while (i < locals.getCount()) {
            if (current.getElement().getID() == iD && current.getElement() instanceof Connector) {
                existance = true;
                break;
            } else {
                current = current.getNext();
            }
            i++;
        }
        if (existance == true) {
            Connector connectorUpd = (Connector) current.getElement();
            try {
                locals.remove(connectorUpd);
                System.out.println("- Connector removido -\n");
            } catch (EmptyCollectionException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Não há nenhum Connector com esse ID");
        }
    }

    /**
     * Lista todos os portais do jogo
     */
    public void ListPortals() {
        int i = 0;
        int total = 0;
        DoubleNode<Locals> current = locals.getHead();

        while (i < locals.getCount()) {
            if (current.getElement() instanceof Portal) {
                Portal portalUpd = (Portal) current.getElement();

                System.out.println("ID: " + portalUpd.getID());
                System.out.println("Nome: " + portalUpd.getName());
                System.out.println("Latitude: " + portalUpd.getLatitude());
                System.out.println("Longitude: " + portalUpd.getLongitude());
                System.out.println("Energia Atual: " + portalUpd.getEnergyAmount());
                System.out.println("Energia Máxima: " + portalUpd.getMaxEnergyAmount());
                if (portalUpd.getPlayer() != null) {
                    System.out.println(portalUpd.getPlayer().getName());
                    System.out.println("------------------------");
                } else {
                    System.out.println("O portal não está no domínio de nenhum jogador!");
                    System.out.println("------------------------");
                }
                total++;
            }
            current = current.getNext();
            i++;
        }
        if (total == 0) {
            System.out.println("------------------------");
            System.out.println("Sem portais para listar!");
            System.out.println("------------------------");
        } else {
            System.out.println("------------------------");
            System.out.println("- Portais Listados -\n");
            System.out.println("------------------------");
        }
    }

    /**
     * Lista todos os portais do jogo por energia maxima
     */
    public void ListPortalsByMaxEnergy() {
        int i = 0;
        int total = 0;
        boolean swapped;
        DoubleNode<Locals> current = locals.getHead();

        do {
            swapped = false;
            current = locals.getHead();

            while (current.getNext() != null) {
                if (current.getElement() instanceof Portal && current.getNext().getElement() instanceof Portal) {
                    Portal portal1 = (Portal) current.getElement();
                    Portal portal2 = (Portal) current.getNext().getElement();

                    if (portal1.getMaxEnergyAmount() < portal2.getMaxEnergyAmount()) {
                        Locals temp = current.getElement();
                        current.setElement(current.getNext().getElement());
                        current.getNext().setElement(temp);

                        swapped = true;
                    }
                }

                current = current.getNext();
            }
        } while (swapped);

        current = locals.getHead();

        while (i < locals.getCount()) {
            if (current.getElement() instanceof Portal) {
                Portal portalUpd = (Portal) current.getElement();

                System.out.println("ID: " + portalUpd.getID());
                System.out.println("Nome: " + portalUpd.getName());
                System.out.println("Energia Máxima: " + portalUpd.getMaxEnergyAmount());
                System.out.println("-----------------------------");
                total++;
            }
            current = current.getNext();
            i++;
        }

        if (total == 0) {
            System.out.println("Sem portais para listar!");
        }
    }

    /**
     * Lista todos os connectors do jogo
     */
    public void ListConnectors() {
        int total = 0;
        int i = 0;
        DoubleNode<Locals> current = locals.getHead();

        while (i < locals.getCount()) {
            if (current.getElement() instanceof Connector) {
                Connector connectorUpd = (Connector) current.getElement();

                System.out.println("ID: " + connectorUpd.getID());
                System.out.println("Cooldown: " + connectorUpd.getCooldown());
                System.out.println("Energia: " + connectorUpd.getEnergyAmount());
                total++;
            }
            i++;
            current = current.getNext();
        }
        if (total == 0) {
            System.out.println("------------------------");
            System.out.println("Sem connectors para listar!");
            System.out.println("------------------------");
        } else {
            System.out.println("------------------------");
            System.out.println("- Connectors Listados -\n");
            System.out.println("------------------------");
        }
    }

    /**
     * Lista todos os connectors do jogo por cooldown
     */
    public void listConnectorsByCooldown() {
        int i = 0;
        int total = 0;
        boolean swapped;
        DoubleNode<Locals> current = locals.getHead();

        do {
            swapped = false;
            current = locals.getHead();

            while (current.getNext() != null) {
                if (current.getElement() instanceof Connector && current.getNext().getElement() instanceof Connector) {
                    Connector connector1 = (Connector) current.getElement();
                    Connector connector2 = (Connector) current.getNext().getElement();

                    if (connector1.getCooldown() > connector2.getCooldown()) {
                        Locals temp = current.getElement();
                        current.setElement(current.getNext().getElement());
                        current.getNext().setElement(temp);

                        swapped = true;
                    }
                }

                current = current.getNext();
            }
        } while (swapped);

        current = locals.getHead();

        while (i < locals.getCount()) {
            if (current.getElement() instanceof Connector) {
                Connector connector = (Connector) current.getElement();

                System.out.println("ID: " + connector.getID());
                System.out.println("Cooldown: " + connector.getCooldown());
                System.out.println("Energia: " + connector.getEnergyAmount());
                System.out.println("-----------------------------");
                total++;
            }

            current = current.getNext();
            i++;
        }

        if (total == 0) {
            System.out.println("Sem connectors para listar!");
        }
    }

    /**
     * Importa os locais do jogo a partir de um ficheiro JSON
     * 
     * @param filename Nome do ficheiro JSON
     */
    public void importLocalsFromJson(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(new File(filename));
            JsonNode localsNode = rootNode.get("locals");
            if (localsNode.isArray()) {
                for (JsonNode localNode : localsNode) {
                    String type = localNode.get("type").asText();
                    if (type.equals("Portal")) {
                        int id = localNode.get("id").asInt();
                        String name = localNode.get("name").asText();
                        double latitude = localNode.get("coordinates").get("latitude").asDouble();
                        double longitude = localNode.get("coordinates").get("longitude").asDouble();
                        int energy = localNode.get("gameSettings").get("energy").asInt();
                        int maxEnergy = localNode.get("gameSettings").get("maxEnergy").asInt();
                        String player = localNode.get("gameSettings").get("ownership").get("player").asText();
                        Jogador jogador = new Jogador("");
                        Portal portal = new Portal(jogador);
                        portal.setName(name);
                        portal.setLongitude(longitude);
                        portal.setLatitude(latitude);
                        portal.setMaxEnergyAmount(maxEnergy);
                        portal.setEnergyAmount(energy);
                        portal.setID(id);
                        portal.getPlayer().setName(player);
                        locals.addToRear(portal);
                    } else if (type.equals("Connector")) {
                        int id2 = localNode.get("id").asInt();
                        double latitude2 = localNode.get("coordinates").get("latitude").asDouble();
                        double longitude2 = localNode.get("coordinates").get("longitude").asDouble();
                        int energy2 = localNode.get("gameSettings").get("energy").asInt();
                        int cooldown = localNode.get("gameSettings").get("cooldown").asInt();
                        Connector connector = new Connector(id2, longitude2, latitude2, energy2, LocalsType.Connector,
                                cooldown);
                        connector.setID(id2);
                        locals.addToRear(connector);
                    }
                }
            }
            System.out.println("- Locals importados -\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exporta os locais do jogo para um ficheiro JSON
     * 
     * @param filename Nome do ficheiro JSON
     * @return ObjectNode
     */
    public ObjectNode exportLocalsToJson(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode localsArrayNode = objectMapper.createArrayNode();

        DoubleNode<Locals> current = locals.getHead();
        for (int i = 0; i < locals.getCount(); i++) {
            if (current.getElement() instanceof Portal) {
                Portal tempPortal = (Portal) current.getElement();
                ObjectNode localsNode = objectMapper.createObjectNode();
                localsNode.put("type", "Portal");
                localsNode.put("id", tempPortal.getID());
                localsNode.put("name", (tempPortal.getName()));
                ObjectNode coordinatesNode = objectMapper.createObjectNode();
                coordinatesNode.put("latitude", tempPortal.getLatitude());
                coordinatesNode.put("longitude", tempPortal.getLongitude());
                localsNode.set("coordinates", coordinatesNode);
                ObjectNode gameSettingsNode = objectMapper.createObjectNode();
                gameSettingsNode.put("energy", tempPortal.getEnergyAmount());
                gameSettingsNode.put("maxEnergy", (tempPortal.getMaxEnergyAmount()));
                ObjectNode ownershipNode = objectMapper.createObjectNode();
                if (tempPortal.getPlayer() != null) {
                    ownershipNode.put("player", (tempPortal.getPlayer().getName()));
                } else {
                    ownershipNode.put("player", "null");
                }
                gameSettingsNode.set("ownership", ownershipNode);
                localsNode.set("gameSettings", gameSettingsNode);
                localsArrayNode.add(localsNode);
                current = current.getNext();
            } else if (current.getElement() instanceof Connector) {
                Connector tempConnector = (Connector) current.getElement();
                ObjectNode localsNode = objectMapper.createObjectNode();
                localsNode.put("type", "Connector");
                localsNode.put("id", tempConnector.getID());
                ObjectNode coordinatesNode = objectMapper.createObjectNode();
                coordinatesNode.put("latitude", tempConnector.getLatitude());
                coordinatesNode.put("longitude", tempConnector.getLongitude());
                localsNode.set("coordinates", coordinatesNode);
                ObjectNode gameSettingsNode = objectMapper.createObjectNode();
                gameSettingsNode.put("energy", tempConnector.getEnergyAmount());
                gameSettingsNode.put("cooldown", (tempConnector.getCooldown()));
                localsNode.set("gameSettings", gameSettingsNode);
                localsArrayNode.add(localsNode);
                current = current.getNext();
            }
        }

        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.set("locals", localsArrayNode);

        try {
            objectMapper.writeValue(new File(filename), rootNode);
            System.out.println("- Locals exportados -\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootNode;
    }

    /**
     * Getter de uma lista de locais
     * 
     * @return DoubleLinkedUnorderedList<Locals> Lista de locais
     */
    public DoubleLinkedUnorderedList<Locals> getLocals() {
        return locals;
    }

    /**
     * Setter de uma lista de locais
     * 
     * @param locals Lista de locais
     */
    public void setLocals(DoubleLinkedUnorderedList<Locals> locals) {
        this.locals = locals;
    }

    /**
     * Getter de um Portal
     * 
     * @param i Posição do local
     * @return Portal null
     */
    public Portal getPortal(int i) {
        return null;
    }

}
