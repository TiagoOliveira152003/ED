package projeto.de.ed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import projeto.de.ed.DataStructures.DoubleNode;
import projeto.de.ed.DataStructures.Network;
import projeto.de.ed.Enums.Team;
import projeto.de.ed.Management.LocalsManagement;
import projeto.de.ed.Management.LocalsRoutesManagement;
import projeto.de.ed.Management.PlayThegame;
import projeto.de.ed.exceptions.ElementNotFoundException;

/**
 * <h3>
 * <strong>Classe Teste PlayTheGame</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class PlayTheGameTest {
    Network<Locals> mapa;
    LocalsManagement lm;
    LocalsRoutesManagement lrm;

    @BeforeEach
    public void setUp() {
        mapa = new Network<>();
        lm = new LocalsManagement();
        lrm = new LocalsRoutesManagement();
    }

    @Test 
    @DisplayName("Teste Método PossibleMovements")
    public void testPossibleMovements() throws ElementNotFoundException {
        int i = 0;
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        lm.addConnector(2, 30.0, 40.0, 5, 50);
        lm.addPortal(3, 10.0, 20.0, "Portal 2", 100);
        lm.addConnector(4, 30.0, 40.0, 5, 50);
        DoubleNode<Locals> current = lm.getLocals().getHead().getNext();
        while (i != lm.getLocals().getCount()) {
            if(current != null) {
            Route rota = new Route(lm.getLocals().getHead().getElement(), current.getElement());
            lrm.addRouteOneWay(lm, rota);
            i++;
            current = current.getNext();
            }else {
            break;
            }
        }   
        
        int cont = 0;

        for(int k = 0; k < lm.getLocals().getCount(); k++) {
            for(int j = 0; j < lm.getLocals().getCount(); j++) {
                if(lrm.getRotas().getAdjMatrixConnectionCost()[k][j] != Double.POSITIVE_INFINITY){
                    cont += 1;
                }
            }
        }

        assertEquals(3, cont , "Não foi encontrado o local correto");
    }

    @Test
    @DisplayName("Teste Método MoveToLocal")
    public void testMoveToLocal() throws ElementNotFoundException {        
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
        Jogador jogador = new Jogador("Dave", Team.Giants, locais[1]);
        lrm.addRouteOneWay(lm, route);
        PlayThegame game = new PlayThegame(mapa);
        game.moveToLocal(lrm, lm, jogador, 2);
        assertEquals(2, jogador.getActualPlace().getID(), "O jogador não foi movido para o local correto");
    }
}
