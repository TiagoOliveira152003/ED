package projeto.de.ed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import projeto.de.ed.DataStructures.DoubleNode;
import projeto.de.ed.Enums.Team;
import projeto.de.ed.Management.LocalsManagement;
import projeto.de.ed.Management.LocalsRoutesManagement;
import projeto.de.ed.Management.PlayersManagement;
import projeto.de.ed.Management.TeamManagement;
import projeto.de.ed.exceptions.ElementNotFoundException;

/**
 * <h3>
 * <strong>Classe Teste Gestão de Equipas</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */ 
public class TeamManagementTest {
    private PlayersManagement pm;
    private LocalsManagement lm;
    private LocalsRoutesManagement lrm;

    @BeforeEach
    public void setUp() {
        pm = new PlayersManagement();
        lm = new LocalsManagement();
        lrm = new LocalsRoutesManagement();
    }

    @Test
    @DisplayName("Teste Adicionar Jogador a Equipa")
    public void testAddTeam() throws ElementNotFoundException {
        TeamManagement tm = new TeamManagement(pm);
        int i = 0;
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        lm.addConnector(2, 30.0, 40.0, 5, 50);
        lm.addPortal(3, 10.0, 20.0, "Portal 2", 100);
        lm.addConnector(4, 30.0, 40.0, 5, 50);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Locals[] locais = new Locals[lm.getLocals().getCount() * 2];
        while (i != lm.getLocals().getCount()) {
            locais[i] = current.getElement();
            i++;
            current = current.getNext();
        }   
        pm.addPlayer("Dave", Team.Sparks);
        tm.addPlayerToTeams();
        SecretRoute rotaSec = new SecretRoute(locais[0], locais[2],Team.Sparks);
        lrm.addSecretRoute(lm, rotaSec);
        tm.addSparksRotasSecretas(rotaSec);
        assertEquals(1, tm.SparksSecretRoutes().getCount());
    }
}
