package projeto.de.ed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import projeto.de.ed.DataStructures.DoubleNode;
import projeto.de.ed.Enums.PortalsState;
import projeto.de.ed.Enums.Team;
import projeto.de.ed.Management.LocalsManagement;

/**
 * <h3>
 * <strong>Classe Teste Gestão de Configurações</strong>
 * </h3>
 *
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class GameConfigurationTest {
    private GameConfiguration gc;
    private LocalsManagement lm;

    @BeforeEach
    public void setUp() {
        gc = new GameConfiguration();
        lm = new LocalsManagement();

    }

    @Test
    @DisplayName("Teste de conquista de portal por parte de um jogador no qual o portal está neutro")
    public void testConquerPortal() {
        Jogador jogador = new Jogador("Alice");
        jogador.setTeam(Team.Giants);
        jogador.setEnergyAmount(200);
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Portal portal = (Portal) current.getElement();
        gc.conquerPortal(jogador, portal);
        assertEquals(PortalsState.Giants, portal.getState());
    }

    @Test
    @DisplayName("Teste de conquista de portal por parte de um jogador no qual o portal pertence a outra equipa")
    public void testConquerPortal2() {
        Jogador jogador = new Jogador("Alice");
        jogador.setTeam(Team.Giants);
        jogador.setEnergyAmount(400);
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Portal portal = (Portal) current.getElement();
        portal.setEnergyAmount(100);
        portal.setState(PortalsState.Sparks);
        gc.conquerPortal(jogador, portal);
        assertEquals(PortalsState.Giants, portal.getState());
    }

    @Test
    @DisplayName("Teste de conquista de portal por parte de um jogador no qual o portal pertence à outra equipa e o jogador não tem energia suficiente para o conquistar")
    public void testConquerPortal3() {
        Jogador jogador = new Jogador("Alice");
        jogador.setTeam(Team.Giants);
        jogador.setEnergyAmount(100);
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Portal portal = (Portal) current.getElement();
        portal.setEnergyAmount(100);
        portal.setState(PortalsState.Sparks);
        gc.conquerPortal(jogador, portal);
        assertEquals(PortalsState.Neutro, portal.getState());
    }

    @Test
    @DisplayName("Teste de conquista de portal por parte de um jogador no qual o portal pertence à outra equipa e o jogador tem energia suficiente para o conquistar")
    public void testConquerPortal4() {
        Jogador jogador = new Jogador("Alice");
        jogador.setTeam(Team.Giants);
        jogador.setEnergyAmount(115);
        lm.addPortal(1, 10.0, 20.0, "Portal 1", 100);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Portal portal = (Portal) current.getElement();
        portal.setEnergyAmount(100);
        portal.setState(PortalsState.Sparks);
        gc.conquerPortal(jogador, portal);
        assertEquals(15, portal.getEnergyAmount());
    }

    @Test
    @DisplayName("Teste de interação com um connector por parte de um jogador")
    public void testInteract1() {
        Jogador jogador = new Jogador("Alice");
        jogador.setTeam(Team.Giants);
        jogador.setEnergyAmount(100);
        lm.addConnector(1, 22, 35, 5, 100);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Connector connector = (Connector) current.getElement();
        gc.interact(jogador, connector);
        assertEquals(200, jogador.getEnergyAmount());
    }

    @Test
    @DisplayName("Teste de interação com um connector por parte de um jogador e verificação da data da última interação e se entrou em cooldown")
    public void testInteract2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Jogador jogador = new Jogador("Alice");
        jogador.setTeam(Team.Giants);
        jogador.setEnergyAmount(100);
        lm.addConnector(1, 22, 35, 5, 100);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Connector connector = (Connector) current.getElement();
        gc.interact(jogador, connector);
        assertEquals(200, jogador.getEnergyAmount());
        gc.interact(jogador, connector);
        assertEquals(LocalDateTime.now().format(formatter), connector.getLastInteraction().getHead().getElement().getTime().format(formatter));
    }

    @Test
    @DisplayName("Teste par verificar se o jogador consegue carregar com um player no connector com mais energia que o jogador")
    public void testInteract3() {
        Jogador jogador = new Jogador("Alice");
        Jogador jogador2 = new Jogador("Bob");
        jogador.setTeam(Team.Giants);
        jogador2.setTeam(Team.Sparks);
        jogador.setEnergyAmount(100);
        jogador2.setEnergyAmount(50);
        lm.addConnector(1, 22, 35, 5, 100);
        DoubleNode<Locals> current = lm.getLocals().getHead();
        Connector connector = (Connector) current.getElement();
        gc.interact(jogador, connector);
        assertEquals(200, jogador.getEnergyAmount());
        gc.interact(jogador2, connector);
        assertEquals(50, jogador2.getEnergyAmount());
    }  
}
