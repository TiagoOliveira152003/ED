package projeto.de.ed;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import projeto.de.ed.Management.LocalsManagement;
import projeto.de.ed.DataStructures.DoubleNode;

/**
 * <h3>
 * <strong>Classe Teste Gestão de Locais</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class LocalsManagementTest {
    private LocalsManagement lm;

    @BeforeEach
    public void setUp() {
        lm = new LocalsManagement();
    }

    @Test
    @DisplayName("Teste Adicionar Portal")
    public void testAddPortal() {
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Portal portalTest = (Portal) current.getElement();
        assertTrue(current.getElement() instanceof Portal);
        assertEquals(1, portalTest.getID());
        assertEquals(10.0, portalTest.getLongitude(), 0.0);
        assertEquals(20.0, portalTest.getLatitude(), 0.0);
        assertEquals("Portal 1", portalTest.getName());
        assertEquals(100, portalTest.getMaxEnergyAmount());

     }


    @Test
    @DisplayName("Teste Adicionar Conector")
    public void testAddConnector() {
        lm.addConnector(2, 30.0, 40.0, 5, 50);
        DoubleNode<Locals> current = lm.getLocals().getHead();

        Connector connectorTest = (Connector) current.getElement();
        assertTrue(current.getElement() instanceof Connector);
        assertEquals(2, connectorTest.getID());
        assertEquals(30.0, connectorTest.getLongitude(), 0.0);
        assertEquals(40.0, connectorTest.getLatitude(), 0.0);
        assertEquals(5, connectorTest.getCooldown());
        assertEquals(50, connectorTest.getEnergyAmount());
    }

    @Test
    @DisplayName("Teste Atualizar Portal")
    public void testUpdatePortal() {
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        lm.updatePortal(1, "Portal 2", 30.0, 40.0, 200);
        DoubleNode<Locals> current = lm.getLocals().getHead();

        Portal portalTest = (Portal) current.getElement();
        assertTrue(current.getElement() instanceof Portal);
        assertEquals(1, portalTest.getID());
        assertEquals(30.0, portalTest.getLongitude(), 0.0);
        assertEquals(40.0, portalTest.getLatitude(), 0.0);
        assertEquals("Portal 2", portalTest.getName());
        assertEquals(200, portalTest.getMaxEnergyAmount());
    }

    @Test
    @DisplayName("Teste Atualizar Conector")
    public void testUpdateConnector() {
        lm.addConnector(2, 30.0, 40.0, 5, 50);
        lm.updateConnector(2, 50.0, 60.0, 10, 100);
        DoubleNode<Locals> current = lm.getLocals().getHead();

        Connector connectorTest = (Connector) current.getElement();
        assertTrue(current.getElement() instanceof Connector);
        assertEquals(2, connectorTest.getID());
        assertEquals(50.0, connectorTest.getLongitude(), 0.0);
        assertEquals(60.0, connectorTest.getLatitude(), 0.0);
        assertEquals(10, connectorTest.getCooldown());
        assertEquals(100, connectorTest.getEnergyAmount());
    }

    @Test
    @DisplayName("Teste Remover Portal")
    public void testRemovePortal() {
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        lm.removePortal(1);
        assertTrue(lm.getLocals().isEmpty());
    }

    @Test
    @DisplayName("Teste Remover Conector")
    public void testRemoveConnector() {
    lm.addConnector(1, 10.0, 20.0, 5, 100);
    lm.removeConnector(1);
    assertTrue(lm.getLocals().isEmpty());
    }
}