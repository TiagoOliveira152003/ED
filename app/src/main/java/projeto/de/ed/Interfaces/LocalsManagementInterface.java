package projeto.de.ed.Interfaces;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * <h3>
 * <strong>Interface  Gestão de Locais</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public interface LocalsManagementInterface {
    public void addPortal(int id, double longitude, double latitude, String name, int maxEnergy);

    public void addConnector(int id, double longitude, double latitude, int cooldown, int energyAmount);

    public void updatePortal(int iD, String newName, double newLongitude, double newLatitude, int newMaxEnergyAmount);

    public void updateConnector(int iD, double newLongitude, double newLatitude, int newCooldown,
            int newMaxEnergyAmount);

    public void removePortal(int iD);

    public void removeConnector(int iD);

    public void ListPortals();

    public void ListPortalsByMaxEnergy();

    public void ListConnectors();

    public void listConnectorsByCooldown();

    public void importLocalsFromJson(String filename);

    public ObjectNode exportLocalsToJson(String filename);
}
