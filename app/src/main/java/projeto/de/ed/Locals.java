package projeto.de.ed;

import projeto.de.ed.Enums.LocalsType;

/**
 * <h3>
 * <strong>Classe Locais</strong>
 * </h3>
 *
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public abstract class Locals {
    private int ID;
    private double longitude;
    private double latitude;
    private int energyAmount;
    private LocalsType type;


    /**
     * Construtor parametrizado
     * 
     * @param id ID do local
     * @param longitude Longitude do local
     * @param latitude Latitude do local
     * @param energyAmount Energia do local
     * @param type Tipo do local
     */
    public Locals(int id, double longitude, double latitude, int energyAmount, LocalsType type) {
        this.ID = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.energyAmount = energyAmount;
        this.type = type;
    }
    
    /**
     * Construtor vazio
     */
    public Locals() {
    }

    /**
     * Getter do ID
     * 
     * @return ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Setter do ID
     * 
     * @param iD
     */
    public void setID(int iD) {
        ID = iD;
    }

    /**
     * Getter da longitude
     * 
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Setter da longitude
     * 
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Getter da latitude
     * 
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Setter da latitude 
     * 
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Getter da energia do local
     * 
     * @return energyAmount
     */
    public int getEnergyAmount() {
        return energyAmount;
    }

    /**
     * Setter da energia de local
     * 
     * @param energyAmount
     */
    public void setEnergyAmount(int energyAmount) {
        this.energyAmount = energyAmount;
    }

    /**
     * Getter do tipo de local (Connector ou Portal)
     * 
     * @return type
     */
    public LocalsType getType() {
        return type;
    }

    /**
     * Setter do tipo de local(Connector ou Portal)
     *  
     * @param type
     */
    public void setType(LocalsType type) {
        this.type = type;
    }

}
