package projeto.de.ed;

import projeto.de.ed.DataStructures.DoubleLinkedUnorderedList;
import projeto.de.ed.Enums.Team;

/**
 * <h3>
 * <strong>Classe Jogador</strong>
 * </h3>
 *
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class Jogador {
    private int energyAmount;
    private String name;
    private Team team;
    private int level;
    private int numOfPortalsConquered;
    private Locals actualPlace;
    private DoubleLinkedUnorderedList<SecretRoute> secretRoutes;
    private Double experiencePoints;

    /**
     * Construtor parametrizado
     * 
     * @param name              Nome do jogador
     * @param team              Equipa do jogador  
     * @param actualPlace       Localização atual do jogador
     */
    public Jogador(String name, Team team, Locals actualPlace) {
        this.energyAmount = 0;
        this.team = team;
        this.level = 1;
        this.name = name;
        this.numOfPortalsConquered = 0;
        this.actualPlace = actualPlace;
        this.experiencePoints = 0.0;
    }
    
    /**
     * Construtor parametrizado
     * 
     * @param name Nome do jogador
     */
    public Jogador(String nome) {
        this.energyAmount = 0;
        this.team = null;
        this.level = 1;
        this.name = nome;
        this.numOfPortalsConquered = 0;
        this.actualPlace = null;
        this.experiencePoints = 0.0;
    }

    /**
     * Construtor parametrizado
     * 
     * @param energyAmount      Energia do jogador
     * @param name              Nome do jogador
     * @param team              Equipa do jogador
     * @param experiencePoints Pontos de experiência do jogador
     * @param level            Nível do jogador
     */
    public Jogador(int energyAmount, String name, Team team, Double experiencePoints, int level) {
        this.energyAmount = energyAmount;
        this.name = name;
        this.team = team;
        this.experiencePoints = experiencePoints;
        this.level = level;
        this.numOfPortalsConquered = 0;
        this.actualPlace = null;
    }

    /**
     * Getter da energia do jogador
     */
    public int getEnergyAmount() {
        return energyAmount;
    }

    /**
     * Setter da energia do jogador
     * 
     * @param energyAmount
     */
    public void setEnergyAmount(int energyAmount) {
        this.energyAmount = energyAmount;
    }

    /**
     * Método que permite ao jogador recarregar a energia
     * 
     * @param energyAmount
     */
    public void rechargeEnergy(int energyAmount) {
        this.energyAmount += energyAmount;
    }

    /**
     * Getter da equipa do jogador
     * 
     * @return team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Setter da equipa do jogador
     * 
     * @param team
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Getter do nível do jogador
     * 
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Setter do nível do jogador
     * 
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Getter do nome do jogador
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter do nome do jogador
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter do número de portais conquistados pelo jogador
     * 
     * @return numOfPortalsConquered
     */
    public int getNumOfPortalsConquered() {
        return numOfPortalsConquered;
    }

    /**
     * Setter do número de portais conquistados pelo jogador
     * 
     * @param numOfPortalsConquered
     */
    public void setNumOfPortalsConquered(int numOfPortalsConquered) {
        this.numOfPortalsConquered = numOfPortalsConquered;
    }

    /**
     * Getter do local atual do jogador
     * 
     * @return actualPlace
     */
    public Locals getActualPlace() {
        return actualPlace;
    }

    /**
     * Setter do local atual do jogador
     * 
     * @param actualPlace
     */
    public void setActualPlace(Locals actualPlace) {
        this.actualPlace = actualPlace;
    }

    /**
     * Getter das rotas secretas do jogador
     * 
     * @return secretRoutes
     */
    public DoubleLinkedUnorderedList<SecretRoute> getSecretRoutes() {
        return secretRoutes;
    }

    /**
     * Setter das rotas secretas do jogador
     * 
     * @param secretRoutes
     */
    public void setSecretRoutes(DoubleLinkedUnorderedList<SecretRoute> secretRoutes) {
        this.secretRoutes = secretRoutes;
    }
    
    /**
     * Getter dos pontos de experiência do jogador
     * 
     * @param experiencePoints Pontos de experiência do jogador
     */
    public Double getExperiencePoints() {
        return experiencePoints;
    }

    /**
     * Setter dos pontos de experiência do jogador
     * 
     * @param experiencePoints Pontos de experiência do jogador
     */
    public void setExperiencePoints(Double experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

}
