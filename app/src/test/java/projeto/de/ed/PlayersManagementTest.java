package projeto.de.ed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import projeto.de.ed.Enums.Team;
import projeto.de.ed.Management.PlayersManagement;
import projeto.de.ed.DataStructures.DoubleNode;

/**
 * <h3>
 * <strong>Classe Teste Gestão de Jogadores</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class PlayersManagementTest {
    private PlayersManagement pm;

    @BeforeEach
    public void setUp() {
        pm = new PlayersManagement();
    }

    @Test
    @DisplayName("Teste Adicionar Jogador")
    public void testAddPlayer() {
        pm.addPlayer("Dave", Team.Sparks);
        DoubleNode<Jogador> current = pm.getPlayers().getHead();

        Jogador playerTest = current.getElement();
        assertTrue(current.getElement() instanceof Jogador);
        assertEquals("Dave", playerTest.getName());
        assertEquals(Team.Sparks, playerTest.getTeam());
        assertEquals(1, pm.getNumOfPlayers());
    }

    @Test
    @DisplayName("Teste Atualizar Jogador")
    public void testUpdatePlayer() {
        pm.addPlayer("Dave", Team.Sparks);
        pm.updatePlayer("Dave", "Dave 2");
        DoubleNode<Jogador> current = pm.getPlayers().getHead();

        Jogador playerTest = current.getElement();
        assertTrue(current.getElement() instanceof Jogador);
        assertEquals("Dave 2", playerTest.getName());
    }

    @Test
    @DisplayName("Teste Alterar Equipa")
    public void testChangeTeam() {
        pm.addPlayer("Dave", Team.Sparks);
        pm.ChangeTeam("Dave");
        DoubleNode<Jogador> current = pm.getPlayers().getHead();

        Jogador playerTest = current.getElement();
        assertTrue(current.getElement() instanceof Jogador);
        assertEquals(Team.Giants, playerTest.getTeam());
    }

    @Test
    @DisplayName("Teste Remover Jogador")
    public void testRemovePlayer() {
        pm.removePlayer("Dave");
        assertEquals(0, pm.getNumOfPlayers());
    }
}
