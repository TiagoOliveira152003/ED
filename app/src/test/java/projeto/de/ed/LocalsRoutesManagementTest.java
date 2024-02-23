package projeto.de.ed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import projeto.de.ed.DataStructures.DoubleNode;
import projeto.de.ed.Enums.Team;
import projeto.de.ed.Management.LocalsManagement;
import projeto.de.ed.Management.LocalsRoutesManagement;
import projeto.de.ed.exceptions.ElementNotFoundException;
import projeto.de.ed.exceptions.EmptyCollectionException;

/**
 * <h3>
 * <strong>Classe Teste Gestão de Rotas</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class LocalsRoutesManagementTest {
    
    LocalsRoutesManagement lrm;
    LocalsManagement lm;

    @BeforeEach
    public void setUp() throws Exception {
         lrm = new LocalsRoutesManagement();
         lm = new LocalsManagement();
    }

    @Test
    @DisplayName("Teste Adicionar Rota Unidirecional")
    public void testAddRouteOneWay() throws ElementNotFoundException, EmptyCollectionException {
        int i = 0;
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        lm.addConnector(2, 30.0, 40.0, 5, 50);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Locals[] locais = new Locals[lm.getLocals().getCount() * 2];
        while (i != lm.getLocals().getCount()) {
            locais[i] = current.getElement();
            i++;
            current = current.getNext();
        }   
        Route route = new Route(locais[0], locais[1]);
        lrm.addRouteOneWay(lm, route);
        assertEquals(lrm.getRotas().getVertexAt(0), locais[0], "Route not added");
        assertEquals(lrm.getRotas().getVertexAt(1), locais[1], "Route not added");
    }

    @Test
    @DisplayName("Teste Adicionar Rota Bidirecional")
    public void TestAddRouteBothWays() throws ElementNotFoundException, EmptyCollectionException {
        int i = 0;
        lm = new LocalsManagement();
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        lm.addConnector(2, 30.0, 40.0, 5, 50);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Locals[] locais = new Locals[lm.getLocals().getCount() * 2];
        while (i != lm.getLocals().getCount()) {
            locais[i] = current.getElement();
            i++;
            current = current.getNext();
        }   
        Route route = new Route(locais[0], locais[1]);
        lrm.addRouteBothWays(lm, route);
        assertEquals(lrm.getRotas().getVertexAt(0), locais[0], "Route not added");
        assertEquals(lrm.getRotas().getVertexAt(1), locais[1], "Route not added");
    }

    @Test
    @DisplayName("Teste Remover Rota Unidirecional")
    public void testRemoveRouteOneWay() throws ElementNotFoundException, EmptyCollectionException {
        int i = 0;
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        lm.addConnector(2, 30.0, 40.0, 5, 50);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Locals[] locais = new Locals[lm.getLocals().getCount() * 2];
        while (i != lm.getLocals().getCount()) {
            locais[i] = current.getElement();
            i++;
            current = current.getNext();
        }   
        Route route = new Route(locais[0], locais[1]);
        lrm.addRouteOneWay(lm, route);
        lrm.removeOneWay(lm, locais[0], locais[1]);
        assertEquals(lrm.getRotas().getAdjMatrixConnectionCost()[0][1], 0.0 , "Route not removed");
    }

    @Test
    @DisplayName("Teste Remover Rota Bidirecional")
    public void testRemoveRouteBothWays() throws ElementNotFoundException, EmptyCollectionException {
        int i = 0;
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        lm.addConnector(2, 30.0, 40.0, 5, 50);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Locals[] locais = new Locals[lm.getLocals().getCount() * 2];
        while (i != lm.getLocals().getCount()) {
            locais[i] = current.getElement();
            i++;
            current = current.getNext();
        }   
        Route route = new Route(locais[0], locais[1]);
        lrm.addRouteBothWays(lm, route);
        lrm.removeBothRoutes(lm, locais[0], locais[1]);
        assertEquals(lrm.getRotas().getAdjMatrixConnectionCost()[0][1], 0.0 , "Route not removed");
        assertEquals(lrm.getRotas().getAdjMatrixConnectionCost()[1][0], 0.0 , "Route not removed");
    }

    @Test
    @DisplayName("Teste Adicionar Rota Secreta")
    public void tesAddSecretRoute() throws ElementNotFoundException, EmptyCollectionException {
        int i = 0;
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        lm.addConnector(2, 30.0, 40.0, 5, 50);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Locals[] locais = new Locals[lm.getLocals().getCount() * 2];
        while (i != lm.getLocals().getCount()) {
            locais[i] = current.getElement();
            i++;
            current = current.getNext();
        }   
        SecretRoute secretRoute = new SecretRoute(locais[0], locais[1], Team.Giants);
        lrm.addSecretRoute(lm, secretRoute);
        assertEquals(lrm.getRotas().getVertexAt(0), locais[0], "Route not added");
        assertEquals(lrm.getRotas().getVertexAt(1), locais[1], "Route not added");
    }

    @Test
    @DisplayName("Teste Método hasRoute")
    public void testHasRoute() throws ElementNotFoundException, EmptyCollectionException {
        int i = 0;
        lm = new LocalsManagement();
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        lm.addConnector(2, 30.0, 40.0, 5, 50);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Locals[] locais = new Locals[lm.getLocals().getCount() * 2];
        while (i != lm.getLocals().getCount()) {
            locais[i] = current.getElement();
            i++;
            current = current.getNext();
        }   
        Route route = new Route(locais[0], locais[1]);
        lrm.addRouteOneWay(lm, route);
        assertTrue(lrm.hasRoute(lrm.getRotas(), locais[0], locais[1]), "Route not found");
    }
}

