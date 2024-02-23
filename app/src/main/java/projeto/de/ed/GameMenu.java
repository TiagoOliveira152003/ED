package projeto.de.ed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import projeto.de.ed.DataStructures.DoubleLinkedUnorderedList;
import projeto.de.ed.DataStructures.DoubleNode;
import projeto.de.ed.Enums.LocalsType;
import projeto.de.ed.Management.*;
import projeto.de.ed.exceptions.*;

/**
 * <h3>
 * <strong>Classe Menu de Jogo</strong>
 * </h3>
 *
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class GameMenu {
    GameConfiguration gc = new GameConfiguration();
    LocalsManagement lm = new LocalsManagement();
    PlayersManagement pm = new PlayersManagement();
    TeamManagement tm = new TeamManagement(pm);
    LocalsRoutesManagement lrm = new LocalsRoutesManagement();
    PlayThegame ptg = new PlayThegame(lrm.getRotas());
    int opcaoPlayerMenu;
    int opcaoMenu;
    int opcaoInGame;
    int opcaoMover;

    /**
     * Construtor parametrizado
     * 
     * @param lm - LocalsManagement
     * @param pm - PlayersManagement
     * @param tm - TeamManagement
     * @param lrm - LocalsRoutesManagement
     * @param ptg - PlayThegame
     */
    public GameMenu(LocalsManagement lm, PlayersManagement pm, TeamManagement tm, LocalsRoutesManagement lrm,
            PlayThegame ptg) {
        this.lm = lm;
        this.pm = pm;
        this.tm = tm;
        this.lrm = lrm;
        this.ptg = ptg;
    }
    
    /**
     * Getter do LocalsManagement
     */
    public LocalsManagement getLm() {
        return lm;
    }

    /**
     * Setter do LocalsManagement
     * 
     * @param lm - LocalsManagement
     */
    public void setLm(LocalsManagement lm) {
        this.lm = lm;
    }
    
    /**
     * Getter do PlayersManagement
     */
    public PlayersManagement getPm() {
        return pm;
    }

    /**
     * Setter do PlayersManagement
     * 
     * @param pm - PlayersManagement
     */
    public void setPm(PlayersManagement pm) {
        this.pm = pm;
    }

    /**
     * Getter do TeamManagement
     */
    public TeamManagement getTm() {
        return tm;
    }

    /**
     * Setter do TeamManagement
     * 
     * @param tm - TeamManagement
     */
    public void setTm(TeamManagement tm) {
        this.tm = tm;
    }

    /**
     * Getter do LocalsRoutesManagement
     */
    public LocalsRoutesManagement getLrm() {
        return lrm;
    }

    /**
     * Setter do LocalsRoutesManagement
     * 
     * @param lrm - LocalsRoutesManagement
     */
    public void setLrm(LocalsRoutesManagement lrm) {
        this.lrm = lrm;
    }

    /**
     * Getter do PlayThegame
     */
    public PlayThegame getPtg() {
        return ptg;
    }

    /**
     * Setter do PlayThegame
     * 
     * @param ptg - PlayThegame
     */
    public void setPtg(PlayThegame ptg) {
        this.ptg = ptg;
    }

    /**
     * Menu principal do jogo
     */
    public void mainMenu()
            throws NumberFormatException, IOException, ElementNotFoundException, EmptyCollectionException {
        System.out.println("! Bem vindo ao jogo !");
        Jogador actualPlayer = playerSelection();
        DoubleNode<Locals> localInicial = lm.getLocals().getHead();
        actualPlayer.setActualPlace(localInicial.getElement());

        do {
            if (actualPlayer.getActualPlace() instanceof Connector) {
                System.out.println(
                        "O jogador encontra-se no " + actualPlayer.getActualPlace().getType() + " com o id "
                                + actualPlayer.getActualPlace().getID());
                System.out.println("----------");
                System.out.println("O jogador pode:");
                System.out.println("1 - Carregar Energia");
                System.out.println("2 - Deslocar-se para outro local");
                System.out.println("------------------------");
                System.out.println("3 - Exportar ficheiro .txt com matriz de adjacências");
                System.out.println("------------------------");
                System.out.println("\n0 - Sair para o menu inicial");
                System.out.println("------------------------");
                do {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    this.opcaoInGame = Integer.parseInt(br.readLine());
                    if (this.opcaoInGame < 0 || this.opcaoInGame > 3) {
                        System.out.println("\n!! Escolha uma opcao válida !!\n");
                    }
                } while (this.opcaoInGame < 0 && this.opcaoInGame > 3);
                switch (opcaoInGame) {
                    case 1:
                        Connector tempCon = (Connector) actualPlayer.getActualPlace();
                        gc.interact(actualPlayer, tempCon);
                        int a = 0;
                        DoubleNode<Interacoes> intl = tempCon.getLastInteraction().getHead();
                        while (intl != null) {
                            System.out.println(intl.getElement().getPlayer().getName());
                            System.out.println(intl.getElement().getConnector().getCooldown());
                            System.out.println(intl.getElement().getTime());
                            intl = intl.getNext();
                        }
                        break;
                    case 2:
                        DoubleLinkedUnorderedList<Locals> locaisPossiveis = ptg.possibleMovements(lrm, lm,
                                actualPlayer);
                        int i = 0;
                        int max = 0;
                        DoubleNode<Locals> actualPlace = locaisPossiveis.getHead();
                        while (i < locaisPossiveis.getCount()) {
                            if (max < actualPlace.getElement().getID()) {
                                max = actualPlace.getElement().getID();
                            }
                            if (actualPlace.getElement().getType() == LocalsType.Connector) {
                                System.out.println("ID: " + actualPlace.getElement().getID() + " Tipo: Connector");
                            } else {
                                System.out.println("ID: " + actualPlace.getElement().getID() + " Tipo: Portal");
                            }

                            i++;
                            actualPlace = actualPlace.getNext();
                        }
                        if (locaisPossiveis.getCount() != 0) {
                            System.out.println("Insira um ID para onde se pretende deslocar");
                            do {
                                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                                this.opcaoMover = Integer.parseInt(br.readLine());
                                if (this.opcaoMover < 0
                                        || this.opcaoMover > max) {
                                    System.out.println("\n!! Escolha uma opcao válida !!\n");
                                }
                            } while (this.opcaoMover < 0
                                    || this.opcaoMover > max);

                            ptg.moveToLocal(lrm, lm, actualPlayer, opcaoMover);
                        } else {
                            System.out.println("Sem movimentos possíveis");
                        }
                        break;
                    case 3:
                        ptg.exportAdjMatrixToTXT(lm, lrm, actualPlayer);
                        break;
                }
            } else if (actualPlayer.getActualPlace() instanceof Portal) {

                System.out.println(
                        "O jogador encontra-se no " + actualPlayer.getActualPlace().getType() + " com o id "
                                + actualPlayer.getActualPlace().getID());
                System.out.println("----------");
                System.out.println("O jogador pode:");
                System.out.println("1 - Interagir com o Portal");
                System.out.println("2 - Deslocar-se para outro local");
                System.out.println("------------------------");
                System.out.println("3 - Exportar ficheiro .txt com matriz de adjacências");
                System.out.println("------------------------");
                System.out.println("\n0 - Sair para o menu inicial");
                System.out.println("------------------------");
                do {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    this.opcaoInGame = Integer.parseInt(br.readLine());
                    if (this.opcaoInGame < 0 || this.opcaoInGame > 3) {
                        System.out.println("\n!! Escolha uma opcao válida !!\n");
                    }
                } while (this.opcaoInGame < 0 && this.opcaoInGame > 3);
                switch (opcaoInGame) {
                    case 1:
                        Portal tempPor = (Portal) actualPlayer.getActualPlace();
                        gc.conquerPortal(actualPlayer, tempPor);
                        break;
                    case 2:
                        DoubleLinkedUnorderedList<Locals> locaisPossiveis = ptg.possibleMovements(lrm, lm,
                                actualPlayer);
                        int i = 0;
                        int max = 0;
                        DoubleNode<Locals> actualPlace = locaisPossiveis.getHead();
                        while (i < locaisPossiveis.getCount()) {
                            if (max < actualPlace.getElement().getID()) {
                                max = actualPlace.getElement().getID();
                            }
                            if (actualPlace.getElement().getType() == LocalsType.Connector) {
                                System.out.println("ID: " + actualPlace.getElement().getID() + " Tipo: Connector");
                            } else {
                                System.out.println("ID: " + actualPlace.getElement().getID() + " Tipo: Portal");
                            }
                            i++;
                            actualPlace = actualPlace.getNext();
                        }
                        if (locaisPossiveis.getCount() != 0) {
                            System.out.println("Insira um ID para onde se pretende deslocar");
                            do {
                                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                                this.opcaoMover = Integer.parseInt(br.readLine());
                                if (this.opcaoMover < 0
                                        || this.opcaoMover > max) {
                                    System.out.println("\n!! Escolha uma opcao válida !!\n");
                                }
                            } while (this.opcaoMover < 0
                                    || this.opcaoMover > max);

                            ptg.moveToLocal(lrm, lm, actualPlayer, opcaoMover);
                        } else {
                            System.out.println("Sem movimentos possíveis");
                        }
                        break;
                    case 3:
                        ptg.exportAdjMatrixToTXT(lm, lrm, actualPlayer);
                        break;
                }
            }
        } while (opcaoInGame != 0);
    }

    /**
     * Método que permite selecionar um jogador para interagir
     * 
     * @return Jogador selecionado
     * @throws NumberFormatException
     * @throws IOException
     */
    public Jogador playerSelection() throws NumberFormatException, IOException {
        Jogador[] players = new Jogador[pm.getPlayers().getCount() * 2];
        Jogador selectedPlayer;
        System.out.println("Escolha um jogador:");
        int i = 0;
        DoubleNode<Jogador> current = pm.getPlayers().getHead();
        while (i < pm.getNumOfPlayers()) {
            players[i] = current.getElement();
            System.out.println(i + ". " + current.getElement().getName());
            i++;
            current = current.getNext();
        }
        do {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            this.opcaoPlayerMenu = Integer.parseInt(br.readLine());
            if (this.opcaoPlayerMenu < 0 || this.opcaoPlayerMenu > pm.getNumOfPlayers() - 1) {
                System.out.println("\n!! Escolha uma opcao válida !!\n");
            }
        } while (this.opcaoPlayerMenu < 0 && this.opcaoPlayerMenu > pm.getNumOfPlayers());
        selectedPlayer = players[opcaoPlayerMenu];
        return selectedPlayer;
    }
}
