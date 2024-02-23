package projeto.de.ed;

import projeto.de.ed.Enums.LocalsType;
import projeto.de.ed.Enums.PortalsState;
import projeto.de.ed.Enums.Team;

/**
 * <h3>
 * <strong>Classe Portal</strong>
 * </h3>
 *
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class Portal extends Locals {
    private PortalsState state;
    private Jogador player;
    private Team equipa;
    private String name;
    private int maxEnergyAmount;

    /**
     * Construtor parametrizado
     * 
     * @param id ID do portal
     * @param longitude Longitude do portal
     * @param latitude Latitude do portal
     * @param energyAmount Energia do portal
     * @param type Tipo 
     * @param state Estado do portal
     * @param player Jogador que controla o portal
     * @param name Nome do portal
     * @param maxEnergyAmount Energia máxima do portal
     */
    public Portal(int id, double longitude, double latitude, int energyAmount, LocalsType type, PortalsState state,
            Jogador player, String name, int maxEnergyAmount) {
        super(id, longitude, latitude, energyAmount, type);
        this.state = state;
        this.player = player;
        this.name = name;
        if (player != null) {
            this.equipa = this.player.getTeam();
        } else {
            this.equipa = null;
        }
        this.maxEnergyAmount = maxEnergyAmount;
    }

    /**
     * Construtor parametrizado
     * 
     * @param player Jogador que controla o portal
     */
    public Portal(Jogador player) {
        super();
        this.state = PortalsState.Neutro;
        this.player = player;
        this.name = null;
        this.equipa = null;
        this.maxEnergyAmount = 0;
    }

    /**
     * Getter do estado do portal
     * 
     * @return state
     */
    public PortalsState getState() {
        return state;
    }

    /**
     * Setter do estado do portal
     * 
     * @param state
     */
    public void setState(PortalsState state) {
        this.state = state;
    }

    /**
     * Getter do jogador que controla o portal
     * 
     * @return player
     */
    public Jogador getPlayer() {
        return player;
    }

    /**
     * Setter do jogador que controla o portal
     * 
     * @param player
     */
    public void setPlayer(Jogador player) {
        this.player = player;
    }

    /**
     * Getter do nome do portal
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter do nome do portal
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter da energia máxima do portal
     * 
     * @return maxEnergyAmount
     */
    public int getMaxEnergyAmount() {
        return maxEnergyAmount;
    }

    /**
     * Setter da energia máxima do portal
     * 
     * @param maxEnergyAmount
     */
    public void setMaxEnergyAmount(int maxEnergyAmount) {
        this.maxEnergyAmount = maxEnergyAmount;
    }

    /**
     * Getter da equipa que controla portal
     * 
     * @return equipa
     */
    public Team getEquipa() {
        return equipa;
    }

    /**
     * Setter da equipa que controla portal
     * 
     * @param equipa
     */
    public void setEquipa(Team equipa) {
        this.equipa = equipa;
    }
}
