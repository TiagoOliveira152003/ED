package projeto.de.ed;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import projeto.de.ed.DataStructures.DoubleNode;
import projeto.de.ed.Enums.PortalsState;
import projeto.de.ed.Enums.Team;

/**
 * <h3>
 * <strong>Configuração de jogo</strong>
 * </h3>
 *
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class GameConfiguration {
    private int experiencePerLevel = 100;
    private int experienceIncreaseFactor = 2;
    private int energyIncreasePercent = 10;

    /**
     * Getter da experiência por nível
     */
    public int getExperiencePerLevel() {
        return experiencePerLevel;
    }

    /**
     * Setter da experiência por nível
     * 
     * @param experiencePerLevel Número de experiência ganha por subida de nível
     */
    public void setExperiencePerLevel(int experiencePerLevel) {
        this.experiencePerLevel = experiencePerLevel;
    }

    /**
     * Getter do factor de aumento de experiência
     */
    public int getExperienceIncreaseFactor() {
        return experienceIncreaseFactor;
    }

    /**
     * Setter do factor de aumento de experiência
     * 
     * @param experienceIncreaseFactor Factor de aumento de experiência
     */
    public void setExperienceIncreaseFactor(int experienceIncreaseFactor) {
        this.experienceIncreaseFactor = experienceIncreaseFactor;
    }

    /**
     * Getter do percentagem de aumento de energia
     */
    public int getEnergyIncreasePercent() {
        return energyIncreasePercent;
    }

    /**
     * Setter do percentagem de aumento de energia
     * 
     * @param energyIncreasePercent Percentagem de aumento de energia
     */
    public void setEnergyIncreasePercent(int energyIncreasePercent) {
        this.energyIncreasePercent = energyIncreasePercent;
    }

    /**
     * Conquistar um portal
     * 
     * @param player Jogador
     * @param portal Portal a conquistar
     */
    public void conquerPortal(Jogador player, Portal portal) {
        // Portal é neutro
        if (portal.getState() == PortalsState.Neutro) {
            int requiredEnergy = portal.getMaxEnergyAmount() / 4; // 25% da energia máxima do portal
            if (player.getEnergyAmount() >= requiredEnergy) {
                portal.setEnergyAmount(requiredEnergy);
                portal.setPlayer(player);
                if (player.getTeam() == Team.Giants) {
                    portal.setState(PortalsState.Giants);
                } else {
                    portal.setState(PortalsState.Sparks);
                }
                player.setEnergyAmount(player.getEnergyAmount() - requiredEnergy);
                gainExperience(player, 50);
                System.out.println("Estado: Neutro -> " + player.getTeam().toString());
            }
            // Portal pertence à equipa do jogador
        }
        if (portal.getState().toString().equals(player.getTeam().toString())) {
            int requiredEnergy = portal.getMaxEnergyAmount() - portal.getEnergyAmount();
            // Se o jogador tiver energia suficiente para encher o portal
            if (player.getEnergyAmount() >= requiredEnergy) {
                portal.setEnergyAmount(portal.getMaxEnergyAmount());
                player.setEnergyAmount(player.getEnergyAmount() - requiredEnergy);
                gainExperience(player, 50);
                System.out.println("Portal " + portal.getName() + " encheu e pertence à equipa"
                        + player.getTeam().toString() + "!");
                // Se o jogador não tiver energia suficiente para encher o portal
            } else {
                portal.setEnergyAmount(portal.getEnergyAmount() + player.getEnergyAmount());
                player.setEnergyAmount(0);
                gainExperience(player, 15);
                System.out.println("Portal " + portal.getName() + " ficou com" + portal.getEnergyAmount()
                        + "de energia e pertence à equipa" + player.getTeam().toString() + "!");
            }
            // Portal pertence à equipa adversária
        } else {
            int requiredEnergy = portal.getEnergyAmount();
            if (player.getEnergyAmount() >= requiredEnergy) {
                player.setEnergyAmount(player.getEnergyAmount() - requiredEnergy);
                int requiredMaxEnergy = portal.getMaxEnergyAmount() / 4;
                // Se o jogador tiver energia suficiente para conquistar o portal
                if (player.getEnergyAmount() >= requiredMaxEnergy) {
                    portal.setEnergyAmount(requiredMaxEnergy);
                    portal.setPlayer(player);
                    player.setEnergyAmount(player.getEnergyAmount() - requiredEnergy);
                    if (player.getTeam() == Team.Giants) {
                        portal.setState(PortalsState.Giants);
                    } else {
                        portal.setState(PortalsState.Sparks);
                    }
                    gainExperience(player, 100);
                    System.out.println("Equipa " + player.getTeam().toString() + " conquistou o portal "
                            + portal.getName() + " à equipa adversária!");
                    // Se o jogador só tiver energia suficiente para neutralizar o portal
                } else {
                    portal.setEnergyAmount(player.getEnergyAmount());
                    player.setEnergyAmount(0);
                    portal.setState(PortalsState.Neutro);
                    gainExperience(player, 30);
                    System.out.println("Equipa " + player.getTeam().toString() + " neutralizou o portal "
                            + portal.getName() + " da equipa adversária!");
                }
            } else {
                // Se o jogador não tiver energia suficiente para neutralizar o portal
                portal.setEnergyAmount(portal.getEnergyAmount() - player.getEnergyAmount());
                player.setEnergyAmount(0);
                gainExperience(player, 10);
                System.out.println("Equipa " + player.getTeam().toString() + " não conseguiu conquistar o portal "
                        + portal.getName() + " da equipa adversária, mas este ficou com" + portal.getEnergyAmount()
                        + " de energia !");
            }
        }
    }

    /**
     * Interagir com um conector
     * 
     * @param player Jogador
     * @param connector Conector a interagir
     */
    public void interact(Jogador player, Connector connector) {
        DoubleNode<Interacoes> tempInter = connector.getLastInteraction().getHead();
        Interacoes inter = new Interacoes(player, connector, LocalDateTime.now());
        int i = 0;
        if (tempInter != null) {
            while (i < connector.getLastInteraction().getCount()) {
                if (tempInter.getElement().getPlayer() == player) {
                    break;
                }
                tempInter = tempInter.getNext();
                i++;
            }
        }
        if (connector.getLastInteraction() != null && tempInter != null
                && tempInter.getElement().getTime().plusMinutes(connector.getCooldown()).isAfter(LocalDateTime.now())) {
            System.out.println("Sorry, this connector is on cooldown. Please try again later.");
            return;
        }

        Jogador otherPlayer = null;
        if (connector.getLastInteraction() != null && connector.getLastInteraction().getCount() > 0) {
            otherPlayer = connector.getLastInteraction().getHead().getElement().getPlayer();
        }

        if (otherPlayer != null && otherPlayer.getEnergyAmount() >= player.getEnergyAmount()) {
            System.out.println(
                    "Sorry, there is already a player in this connector with greater or equal energy. You cannot enter.");
            connector.addInteraction(inter);
            return;
        }

        double random = Math.random();
        if (random <= 0.25) {
            int energyLost = player.getEnergyAmount() / 2;
            player.setEnergyAmount(player.getEnergyAmount() - energyLost);
            System.out.printf("Oh no! You stepped on a mine and lost " + energyLost + " energy points. You now have "
                    + player.getEnergyAmount() + " energy points.%n");
            connector.addInteraction(inter);
            gainExperience(player, 50);
        } else {
            player.rechargeEnergy(connector.getEnergyAmount());
            System.out.println("You have successfully entered the connector and gained " + connector.getEnergyAmount()
                    + " energy points. You now have " + player.getEnergyAmount() + " energy points.");
            connector.addInteraction(inter);
            gainExperience(player, 50);
        }
    }

    /**
     * Método atualiza a experiência do jogador e verifica se a experiência do jogador é suficiente para subir de nível.
     * 
     * @param player
     * @param experienceGained Experiência ganha por interação ou conquista de um portal.
     */
    private void gainExperience(Jogador player, double experienceGained) {
        // Este método atualiza a experiência do jogador e verifica se a experiência do
    // jogador é suficiente para subir de nível.
        Double exp = player.getExperiencePoints();
        exp += experienceGained;
        player.setExperiencePoints(exp);
        int experienceNeeded = calculateExperienceNeededForLevelUp(player);
        while (player.getExperiencePoints() >= experienceNeeded) {
            levelUp(player);
            exp -= experienceNeeded;
            player.setExperiencePoints(exp);
            experienceNeeded = calculateExperienceNeededForLevelUp(player);
        }
    }

    /**
     * Método que calcula a experiência necessária para subir de nível que aumenta exponencialmente.
     * 
     * @param player
     * @return int
     */
    private int calculateExperienceNeededForLevelUp(Jogador player) {
        return (int) Math.pow(experienceIncreaseFactor, player.getLevel()) * experiencePerLevel;
    }

    /**
     * Método que atualiza o nível do jogador e a energia do jogador.
     * 
     * @param player
     */
    private void levelUp(Jogador player) {
        player.setLevel(player.getLevel() + 1);
        int energyIncrease = (int) Math.round(player.getEnergyAmount() * energyIncreasePercent / 100.0);
        player.setEnergyAmount(player.getEnergyAmount() + energyIncrease);
        System.out.println(player.getName() + " leveled up to level " + player.getLevel() + " and now has "
                + player.getEnergyAmount() + " energy");
    }

    /**
     * Método que importa as configurações do jogo de um ficheiro JSON.
     * 
     * @param filename
     */
    public void importGameConfigsToJson(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(new File(filename));
            JsonNode configsNode = rootNode.get("Config");

            if (configsNode.isArray()) {
                for (JsonNode configNode : configsNode) {
                    int experiencePerLevel = configNode.get("experiencePerLevel").asInt();
                    int experienceIncreaseFactor = configNode.get("experienceIncreaseFactor").asInt();
                    int energyIncreasePercent = configNode.get("energyIncreasePercent").asInt();
                    ;
                    setEnergyIncreasePercent(energyIncreasePercent);
                    setExperienceIncreaseFactor(experienceIncreaseFactor);
                    setExperiencePerLevel(experiencePerLevel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que exporta as configurações do jogo para um ficheiro JSON.
     * 
     * @param filename
     * @return ObjectNode
     */
    public ObjectNode exportGameConfigToJson(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode configNode = objectMapper.createObjectNode();
        configNode.put("experiencePerLevel", getExperiencePerLevel());
        configNode.put("experienceIncreaseFactor", getExperienceIncreaseFactor());
        configNode.put("energyIncreasePercent", getEnergyIncreasePercent());

        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.set("config", configNode);

        try {
            objectMapper.writeValue(new File(filename), rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootNode;
    }
}
