package projeto.de.ed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Iterator;

import projeto.de.ed.DataStructures.DoubleNode;
import projeto.de.ed.DataStructures.Network;
import projeto.de.ed.Enums.Team;
import projeto.de.ed.Management.*;
import projeto.de.ed.exceptions.*;

/**
 * <h3>
 * <strong>Classe Menu</strong>
 * </h3>
 *
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class Menu {
    private int opcao1;
    private int opcao2;
    private int opcaoPortalConnector;
    private int opcaoRoutes;
    private int opcaoPlayer;
    private int opcaoGame;

    /**
     * Construtor parametrizado
     * 
     * @param opcao1
     * @param opcao2
     * @param opcaoPortalConnector
     * @param opcaoRoutes
     * @param opcaoPlayer
     * @param opcaoGame
     */
    public Menu() {
        this.opcao1 = 0;
        this.opcao2 = 0;
        this.opcaoPortalConnector = 0;
        this.opcaoRoutes = 0;
        this.opcaoPlayer = 0;
        this.opcaoGame = 0;
    }

    /**
     * Menu principal
     * 
     * @throws IOException
     * @throws ElementNotFoundException
     * @throws EmptyCollectionException
     */
    public void mainMenu() throws IOException, ElementNotFoundException, EmptyCollectionException {
        /*
         * Network<Locals> mapa = new Network<Locals>();
         * LocalsManagement lm = new LocalsManagement();
         * LocalsRoutesManagement lrm = new LocalsRoutesManagement();
         * int i = 0;
         * lm.addPortal(1, 15.0, 25.0, "Portal 1", 100);
         * lm.addConnector(2, 30.0, 40.0, 5, 50);
         * lm.addPortal(3, 177.0, 240.0, "Portal 2", 100);
         * lm.addConnector(4, 5.0, 40.0, 5, 50);
         * DoubleNode<Locals> current = lm.getLocals().getHead();
         * Locals[] locais = new Locals[lm.getLocals().getCount() * 2];
         * while (i != lm.getLocals().getCount()) {
         * locais[i] = current.getElement();
         * i++;
         * current = current.getNext();
         * }
         * Route route = new Route(locais[0], locais[1]);
         * Route route4 = new Route(locais[1], locais[2]);
         * Route route2 = new Route(locais[0], locais[3]);
         * Jogador jogador = new Jogador("Dave", Team.Giants, null);
         * DoubleNode<Locals> localInicial = lm.getLocals().getHead();
         * jogador.setActualPlace(localInicial.getElement());
         * lrm.addRouteOneWay(lm, route);
         * lrm.addRouteOneWay(lm, route2);
         * lrm.addRouteOneWay(lm, route4);
         * System.out.println(lrm.getRotas().getVertexAt(0));
         * System.out.println(lrm.getRotas().getVertexAt(1));
         * System.out.println(lrm.getRotas().getVertexAt(2));
         * System.out.println(lrm.getRotas().getVertexAt(3));
         * System.out.println(lrm.getRotas().getEdgeWeight(lm.getLocals().getHead().
         * getElement(),
         * lm.getLocals().getHead().getNext().getElement()));
         * System.out.println(lrm.getRotas().getEdgeWeight(lm.getLocals().getHead().
         * getElement(),
         * lm.getLocals().getHead().getNext().getNext().getElement()));
         * System.out.println(lrm.getRotas().getEdgeWeight(lm.getLocals().getHead().
         * getElement(),
         * lm.getLocals().getHead().getNext().getNext().getNext().getElement()));
         * PlayThegame ptg = new PlayThegame(mapa);
         * ptg.ListAdjMatrix(mapa, lm, lrm, jogador);
         * GameConfiguration gc = new GameConfiguration();
         * TeamManagement tm = new TeamManagement();
         * PlayersManagement pm = new PlayersManagement();
         * ptg.importProblemDataToJSON("problemData.json", gc, lm, lrm, pm);
         * ptg.exportDataToJson("exportProblem.json", gc, lm, lrm, pm);
         */

        LocalsManagement lm = new LocalsManagement();
        PlayersManagement pm = new PlayersManagement();
        LocalsRoutesManagement lrm = new LocalsRoutesManagement();
        PlayThegame ptg = new PlayThegame(lrm.getRotas());
        TeamManagement tm = new TeamManagement(pm);
        GameConfiguration gc = new GameConfiguration();

        do {
            System.out.println("------------------------");
            System.out.println(" - Main Menu - ");
            System.out.println("\n1 - Jogar");
            System.out.println("\n2 - Configurações de jogo");
            System.out.println("------------------------");
            System.out.println("\n0 - Sair");
            System.out.println("------------------------");
            do {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                this.opcao1 = Integer.parseInt(br.readLine());
                if (this.opcao1 < 0 || this.opcao1 > 2) {
                    System.out.println("\n!! Escolha uma opcao válida !!\n");
                }
            } while (this.opcao1 < 0 && this.opcao1 > 2);

            switch (this.opcao1) {
                case 1:
                    GameMenu gameM = new GameMenu(lm, pm, tm, lrm, ptg);
                    if (gameM.getLm().getLocals().getCount() == 0 || gameM.getLrm().getRotas().getCount() == 0
                            || gameM.getPm().getNumOfPlayers() == 0) {
                        System.out.println("Tem de configurar o jogo primeiro");
                        System.out.println("-----------------------");
                    } else {
                        gameM.mainMenu();
                        System.out.println("- Game Finished -\n");
                    }
                    break;
                case 2:
                    do {
                        System.out.println("------------------------");
                        System.out.println(" - Configuration Menu - ");
                        System.out.println("\n1 - Gestão de Portais e connectors");
                        System.out.println("\n2 - Gestão de Rotas");
                        System.out.println("\n3 - Gestão de Jogadores");
                        System.out.println("\n4 - Gestão de Jogo");
                        System.out.println("------------------------");
                        System.out.println("\n0 - Sair");
                        System.out.println("------------------------");
                        do {
                            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                            this.opcao2 = Integer.parseInt(br.readLine());
                            if (this.opcao2 < 0 || this.opcao2 > 4) {
                                System.out.println("\n!! Escolha uma opcao válida !!\n");
                            }
                        } while (this.opcao2 < 0 && this.opcao2 > 4);
                        switch (this.opcao2) {
                            case 1:
                                do {
                                    System.out.println("------------------------");
                                    System.out.println(" - Locals Menu - ");
                                    System.out.println("\n1 - Adicionar Portal");
                                    System.out.println("\n2 - Remover Portal");
                                    System.out.println("\n3 - Editar Portal");
                                    System.out.println("\n4 - Listar Portais");
                                    System.out.println("\n5 - Listar Portais por energia");
                                    System.out.println("\n6 - Adicionar Connector");
                                    System.out.println("\n7 - Remover Connector");
                                    System.out.println("\n8 - Editar Connector");
                                    System.out.println("\n9 - Listar Connector");
                                    System.out.println("\n10 - Listar Connectors por cooldown");
                                    System.out.println("\n11 - Adicionar interações do Connector");
                                    System.out.println("\n12 - Remover interações do Connector");
                                    System.out.println("\n13 - Importar Locals via JSON");
                                    System.out.println("\n14 - Exportar Locals via JSON");
                                    System.out.println("------------------------");
                                    System.out.println("\n0 - Sair");
                                    System.out.println("------------------------");
                                    do {
                                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                                        this.opcaoPortalConnector = Integer.parseInt(br.readLine());
                                        if (this.opcaoPortalConnector < 0 || this.opcaoPortalConnector > 14) {
                                            System.out.println("\n!! Escolha uma opcao válida !!\n");
                                        }
                                    } while (this.opcaoPortalConnector < 0 && this.opcaoPortalConnector > 14);
                                    System.out.println("------------------------");

                                    switch (opcaoPortalConnector) {
                                        case 1:
                                            int ID = 0;
                                            double longitude = 0;
                                            double latitude = 0;
                                            String name = null;
                                            int maxEnergy = 0;
                                            do {
                                                System.out.println("Insira um ID");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                ID = Integer.parseInt(br.readLine());
                                                if (ID < 0) {
                                                    System.out
                                                            .println("\n!! O ID toma valores entre 0 e infinito !!\n");
                                                }
                                            } while (ID < 0);
                                            do {
                                                System.out.println("Insira uma longitude");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                longitude = Double.parseDouble(br.readLine());
                                                if (longitude < -180 || longitude > 180) {
                                                    System.out.println(
                                                            "\n!! A longitude toma valores entre -180 e 180 !!\n");
                                                }
                                            } while (longitude < -180 || longitude > 180);
                                            do {
                                                System.out.println("Insira uma latitude");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                latitude = Double.parseDouble(br.readLine());
                                                if (latitude < -90 || latitude > 90) {
                                                    System.out.println(
                                                            "\n!! A latitude toma valores entre -90 e 90 !!\n");
                                                }
                                            } while (latitude < -90 || latitude > 90);
                                            do {
                                                System.out.println("Insira um nome");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                name = br.readLine();
                                                if (name == null) {
                                                    System.out.println("\n!! Insira algum nome !!\n");
                                                }
                                            } while (name == null);
                                            do {
                                                System.out.println("Insira a energia máxima");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                maxEnergy = Integer.parseInt(br.readLine());
                                                if (maxEnergy < 30 || maxEnergy > 200) {
                                                    System.out.println(
                                                            "\n!! A energia máxima toma valores entre 30 e 200 !!\n");
                                                }
                                            } while (maxEnergy < 30 || maxEnergy > 200);
                                            lm.addPortal(ID, longitude, latitude, name, maxEnergy);
                                            break;
                                        case 2:
                                            int IDRemove = 0;
                                            if (lm.getLocals().getCount() != 0) {
                                                System.out.println("------------------------");
                                                lm.ListPortals();
                                                System.out.println("------------------------");
                                                lm.ListConnectors();
                                                System.out.println("------------------------");
                                                do {
                                                    System.out.println("Insira um ID para remover");
                                                    BufferedReader br = new BufferedReader(
                                                            new InputStreamReader(System.in));
                                                    IDRemove = Integer.parseInt(br.readLine());
                                                    if (IDRemove < 0) {
                                                        System.out
                                                                .println(
                                                                        "\n!! O ID toma valores entre 0 e infinito !!\n");
                                                    }
                                                } while (IDRemove < 0);
                                                lm.removePortal(IDRemove);
                                            } else {
                                                System.out.println("Não há locais");
                                            }
                                            break;
                                        case 3:
                                            if (lm.getLocals().getCount() != 0) {
                                                int IDUpdate = 0;
                                                double longitudeUpdate = 0;
                                                double latitudeUpdate = 0;
                                                String nameUpdate = null;
                                                int maxEnergyUpdate = 0;
                                                System.out.println("------------------------");
                                                lm.ListPortals();
                                                System.out.println("------------------------");
                                                do {
                                                    System.out.println("Insira o ID que pretende editar");
                                                    BufferedReader br = new BufferedReader(
                                                            new InputStreamReader(System.in));
                                                    IDUpdate = Integer.parseInt(br.readLine());
                                                    if (IDUpdate < 0) {
                                                        System.out
                                                                .println(
                                                                        "\n!! O ID toma valores entre 0 e infinito !!\n");
                                                    }
                                                } while (IDUpdate < 0);
                                                do {
                                                    System.out.println("Insira uma longitude");
                                                    BufferedReader br = new BufferedReader(
                                                            new InputStreamReader(System.in));
                                                    longitudeUpdate = Double.parseDouble(br.readLine());
                                                    if (longitudeUpdate < -180 || longitudeUpdate > 180) {
                                                        System.out.println(
                                                                "\n!! A longitude toma valores entre -180 e 180 !!\n");
                                                    }
                                                } while (longitudeUpdate < -180 || longitudeUpdate > 180);
                                                do {
                                                    System.out.println("Insira uma latitude");
                                                    BufferedReader br = new BufferedReader(
                                                            new InputStreamReader(System.in));
                                                    latitudeUpdate = Double.parseDouble(br.readLine());
                                                    if (latitudeUpdate < -90 || latitudeUpdate > 90) {
                                                        System.out.println(
                                                                "\n!! A latitude toma valores entre -90 e 90 !!\n");
                                                    }
                                                } while (latitudeUpdate < -90 || latitudeUpdate > 90);
                                                do {
                                                    System.out.println("Insira um nome");
                                                    BufferedReader br = new BufferedReader(
                                                            new InputStreamReader(System.in));
                                                    nameUpdate = br.readLine();
                                                    if (nameUpdate == null) {
                                                        System.out.println("\n!! Insira algum nome !!\n");
                                                    }
                                                } while (nameUpdate == null);
                                                do {
                                                    System.out.println("Insira a energia máxima");
                                                    BufferedReader br = new BufferedReader(
                                                            new InputStreamReader(System.in));
                                                    maxEnergyUpdate = Integer.parseInt(br.readLine());
                                                    if (maxEnergyUpdate < 30 || maxEnergyUpdate > 200) {
                                                        System.out.println(
                                                                "\n!! A energia máxima toma valores entre 30 e 200 !!\n");
                                                    }
                                                } while (maxEnergyUpdate < 30 || maxEnergyUpdate > 200);
                                                lm.updatePortal(IDUpdate, nameUpdate, longitudeUpdate, latitudeUpdate,
                                                        maxEnergyUpdate);
                                            } else {
                                                System.out.println("Não há portais");
                                            }
                                            break;
                                        case 4:
                                            System.out.println("------------------------");
                                            lm.ListPortals();
                                            System.out.println("------------------------");
                                            break;
                                        case 5:
                                            System.out.println("------------------------");
                                            lm.ListPortalsByMaxEnergy();
                                            System.out.println("------------------------");
                                            break;
                                        case 6:
                                            int IDCON = 0;
                                            double longitudeCON = 0;
                                            double latitudeCON = 0;
                                            int cooldown = 0;
                                            int amountEnergy = 0;
                                            do {
                                                System.out.println("Insira um ID");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                IDCON = Integer.parseInt(br.readLine());
                                                if (IDCON < 0) {
                                                    System.out
                                                            .println("\n!! O ID toma valores entre 0 e infinito !!\n");
                                                }
                                            } while (IDCON < 0);
                                            do {
                                                System.out.println("Insira uma longitude");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                longitudeCON = Double.parseDouble(br.readLine());
                                                if (longitudeCON < -180 || longitudeCON > 180) {
                                                    System.out.println(
                                                            "\n!! A longitude toma valores entre -180 e 180 !!\n");
                                                }
                                            } while (longitudeCON < -180 || longitudeCON > 180);
                                            do {
                                                System.out.println("Insira uma latitude");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                latitudeCON = Double.parseDouble(br.readLine());
                                                if (latitudeCON < -90 || latitudeCON > 90) {
                                                    System.out.println(
                                                            "\n!! A latitude toma valores entre -90 e 90 !!\n");
                                                }
                                            } while (latitudeCON < -90 || latitudeCON > 90);
                                            do {
                                                System.out.println("Insira um Cooldown");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                cooldown = Integer.parseInt(br.readLine());
                                                if (cooldown < 1 || cooldown > 5) {
                                                    System.out
                                                            .println("\n!! O cooldown toma valores entre 1 e 5 !!\n");
                                                }
                                            } while (cooldown < 1 || cooldown > 5);
                                            do {
                                                System.out.println("Insira a energia do connector");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                amountEnergy = Integer.parseInt(br.readLine());
                                                if (amountEnergy < 30 || amountEnergy > 100) {
                                                    System.out.println(
                                                            "\n!! A energia do connector toma valores entre 30 e 100 !!\n");
                                                }
                                            } while (amountEnergy < 30 || amountEnergy > 100);
                                            lm.addConnector(IDCON, longitudeCON, latitudeCON, cooldown, amountEnergy);
                                            break;
                                        case 7:
                                            int IDRemoveCON = 0;
                                            System.out.println("------------------------");
                                            lm.ListConnectors();
                                            System.out.println("------------------------");
                                            do {
                                                System.out.println("Insira um ID para remover");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                IDRemoveCON = Integer.parseInt(br.readLine());
                                                if (IDRemoveCON < 0) {
                                                    System.out
                                                            .println("\n!! O ID toma valores entre 0 e infinito !!\n");
                                                }
                                            } while (IDRemoveCON < 0);
                                            lm.removeConnector(IDRemoveCON);
                                            break;
                                        case 8:
                                            if (lm.getLocals().getCount() != 0) {
                                                int IDUpdateCON = 0;
                                                double longitudeUpdateCON = 0;
                                                double latitudeUpdateCON = 0;
                                                int cooldownCON = 0;
                                                int maxEnergyUpdateCON = 0;
                                                System.out.println("------------------------");
                                                lm.ListConnectors();
                                                System.out.println("------------------------");
                                                do {
                                                    System.out.println("Insira o ID que pretende editar");
                                                    BufferedReader br = new BufferedReader(
                                                            new InputStreamReader(System.in));
                                                    IDUpdateCON = Integer.parseInt(br.readLine());
                                                    if (IDUpdateCON < 0) {
                                                        System.out
                                                                .println(
                                                                        "\n!! O ID toma valores entre 0 e infinito !!\n");
                                                    }
                                                } while (IDUpdateCON < 0);
                                                do {
                                                    System.out.println("Insira uma longitude");
                                                    BufferedReader br = new BufferedReader(
                                                            new InputStreamReader(System.in));
                                                    longitudeUpdateCON = Double.parseDouble(br.readLine());
                                                    if (longitudeUpdateCON < -180 || longitudeUpdateCON > 180) {
                                                        System.out.println(
                                                                "\n!! A longitude toma valores entre -180 e 180 !!\n");
                                                    }
                                                } while (longitudeUpdateCON < -180 || longitudeUpdateCON > 180);
                                                do {
                                                    System.out.println("Insira uma latitude");
                                                    BufferedReader br = new BufferedReader(
                                                            new InputStreamReader(System.in));
                                                    latitudeUpdateCON = Double.parseDouble(br.readLine());
                                                    if (latitudeUpdateCON < -90 || latitudeUpdateCON > 90) {
                                                        System.out.println(
                                                                "\n!! A latitude toma valores entre -90 e 90 !!\n");
                                                    }
                                                } while (latitudeUpdateCON < -90 || latitudeUpdateCON > 90);
                                                do {
                                                    System.out.println("Insira um Cooldown");
                                                    BufferedReader br = new BufferedReader(
                                                            new InputStreamReader(System.in));
                                                    cooldownCON = Integer.parseInt(br.readLine());
                                                    if (cooldownCON < 1 || cooldownCON > 5) {
                                                        System.out
                                                                .println(
                                                                        "\n!! O cooldown toma valores entre 1 e 5 !!\n");
                                                    }
                                                } while (cooldownCON < 1 || cooldownCON > 5);
                                                do {
                                                    System.out.println("Insira a energia máxima");
                                                    BufferedReader br = new BufferedReader(
                                                            new InputStreamReader(System.in));
                                                    maxEnergyUpdateCON = Integer.parseInt(br.readLine());
                                                    if (maxEnergyUpdateCON < 30 || maxEnergyUpdateCON > 200) {
                                                        System.out.println(
                                                                "\n!! A energia máxima toma valores entre 30 e 200 !!\n");
                                                    }
                                                } while (maxEnergyUpdateCON < 30 || maxEnergyUpdateCON > 200);
                                                lm.updateConnector(IDUpdateCON, longitudeUpdateCON, latitudeUpdateCON,
                                                        cooldownCON, maxEnergyUpdateCON);
                                            } else {
                                                System.out.println("Não há locais para editar");
                                            }
                                            break;
                                        case 9:
                                            System.out.println("------------------------");
                                            lm.ListConnectors();
                                            System.out.println("------------------------");
                                            break;
                                        case 10:
                                            System.out.println("------------------------");
                                            lm.listConnectorsByCooldown();
                                            System.out.println("------------------------");
                                            break;
                                        case 11:
                                            int idConInteraction = 0;
                                            String namePlayerInteraction = null;
                                            if (lm.getLocals().getCount() != 0) {
                                                System.out.println("------------------------");
                                                lm.ListConnectors();
                                                System.out.println("------------------------");
                                                do {
                                                    System.out.println("Insira o ID de um connector");
                                                    BufferedReader br = new BufferedReader(
                                                            new InputStreamReader(System.in));
                                                    idConInteraction = Integer.parseInt(br.readLine());
                                                    if (idConInteraction < 0) {
                                                        System.out
                                                                .println(
                                                                        "\n!! O ID toma valores entre 0 e infinito !!\n");
                                                    }
                                                } while (idConInteraction < 0);
                                                System.out.println("------------------------");
                                                pm.listarjogadores();
                                                System.out.println("------------------------");
                                                do {
                                                    System.out.println("Insira um nome de Jogador");
                                                    BufferedReader br = new BufferedReader(
                                                            new InputStreamReader(System.in));
                                                    namePlayerInteraction = br.readLine();
                                                    if (namePlayerInteraction == null) {
                                                        System.out.println("\n!! Insira algum nome !!\n");
                                                    }
                                                } while (namePlayerInteraction == null);
                                                int iPlayerInteraction = 0;
                                                DoubleNode<Jogador> currPlayerINT = pm.getPlayers().getHead();
                                                while (iPlayerInteraction < pm.getNumOfPlayers()) {
                                                    if (currPlayerINT != null) {
                                                        if (currPlayerINT.getElement()
                                                                .getName() == namePlayerInteraction) {
                                                            break;
                                                        }
                                                        currPlayerINT = currPlayerINT.getNext();
                                                        iPlayerInteraction++;
                                                    } else {
                                                        System.out.println("Não há jogadores com esse nome!");
                                                    }
                                                }
                                                int iInteraction = 0;
                                                DoubleNode<Locals> currentINT = lm.getLocals().getHead();
                                                while (iInteraction < lm.getLocals().getCount()) {
                                                    if (currentINT != null) {
                                                        if (currentINT.getElement().getID() == idConInteraction
                                                                && currentINT.getElement() instanceof Connector) {
                                                            break;
                                                        }
                                                        currentINT = currentINT.getNext();
                                                        iInteraction++;
                                                    } else {
                                                        System.out.println("Não há connectors com esse ID");
                                                    }
                                                }
                                                if (currPlayerINT != null && currentINT != null) {
                                                    Connector tempCONNECTOR = (Connector) currentINT.getElement();
                                                    Interacoes interaction = new Interacoes(currPlayerINT.getElement(),
                                                            tempCONNECTOR,
                                                            LocalDateTime.now());
                                                    tempCONNECTOR.addInteraction(interaction);
                                                } else {
                                                    System.out.println("! Não foi possível adicionar a interação !");
                                                }
                                            } else {
                                                System.out.println("Não há locais para listar");
                                            }
                                            break;
                                        case 12:
                                            System.out.println("- Interações de Connector removidas -\n");
                                            break;
                                        case 13:
                                            lm.importLocalsFromJson("locals.json");
                                            break;
                                        case 14:
                                            lm.exportLocalsToJson("locals2.json");
                                            break;
                                    }
                                } while (opcaoPortalConnector != 0);
                                break;
                            case 2:
                                do {
                                    System.out.println("------------------------");
                                    System.out.println(" - Routes Menu - ");
                                    System.out.println("\n1 - Criar rota num sentido");
                                    System.out.println("\n2 - Criar rota nos dois sentidos");
                                    System.out.println("\n3 - Remover rota num sentido");
                                    System.out.println("\n4 - Remover rota nos dois sentidos");
                                    System.out.println("\n5 - Importar JSON com rotas");
                                    System.out.println("\n6 - Exportar JSON com rotas");
                                    System.out.println("------------------------");
                                    System.out.println("\n0 - Sair");
                                    System.out.println("------------------------");
                                    do {
                                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                                        this.opcaoRoutes = Integer.parseInt(br.readLine());
                                        if (this.opcaoRoutes < 0 || this.opcaoRoutes > 6) {
                                            System.out.println("\n!! Escolha uma opcao válida !!\n");
                                        }
                                    } while (this.opcaoRoutes < 0 && this.opcaoRoutes > 6);
                                    System.out.println("------------------------");

                                    switch (opcaoRoutes) {
                                        case 1:
                                            int opcaoInicioRota = 0;
                                            int opcaoFimRota = 0;
                                            int iRotasADD = 0;
                                            int maxADD = 0;
                                            DoubleNode<Locals> actualPlace = lm.getLocals().getHead();
                                            while (iRotasADD < lm.getLocals().getCount()) {
                                                if (maxADD < actualPlace.getElement().getID()) {
                                                    maxADD = actualPlace.getElement().getID();
                                                }
                                                if (actualPlace.getElement() instanceof Portal) {
                                                    System.out.println("ID: " + actualPlace.getElement().getID()
                                                            + " Local: Portal");
                                                } else if (actualPlace.getElement() instanceof Connector) {
                                                    System.out.println("ID: " + actualPlace.getElement().getID()
                                                            + " Local: Connector");
                                                }

                                                iRotasADD++;
                                                actualPlace = actualPlace.getNext();
                                            }
                                            System.out.println("ID do local inicial");
                                            do {
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                opcaoInicioRota = Integer.parseInt(br.readLine());
                                                if (opcaoInicioRota < 0 || opcaoInicioRota > maxADD) {
                                                    System.out.println("\n!! Escolha uma opcao válida !!\n");
                                                }
                                            } while (opcaoInicioRota < 0 && opcaoInicioRota > maxADD);
                                            System.out.println("ID do local final da rota");
                                            do {
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                opcaoFimRota = Integer.parseInt(br.readLine());
                                                if (opcaoFimRota < 0 || opcaoFimRota > maxADD) {
                                                    System.out.println("\n!! Escolha uma opcao válida !!\n");
                                                }
                                            } while (opcaoFimRota < 0 && opcaoFimRota > maxADD);
                                            Locals localInicio = null;
                                            Locals localFinal = null;
                                            int localsCount = 0;
                                            DoubleNode<Locals> currentPlace = lm.getLocals().getHead();
                                            while (localsCount < lm.getLocals().getCount()) {
                                                if (currentPlace != null) {
                                                    if (currentPlace.getElement().getID() == opcaoInicioRota) {
                                                        localInicio = currentPlace.getElement();
                                                    }
                                                    if (currentPlace.getElement().getID() == opcaoFimRota) {
                                                        localFinal = currentPlace.getElement();
                                                    }
                                                    localsCount++;
                                                    currentPlace = currentPlace.getNext();
                                                }
                                            }
                                            if (localInicio != null && localFinal != null) {
                                                Route rotaADD = new Route(localInicio, localFinal);
                                                lrm.addRouteOneWay(lm, rotaADD);
                                            } else {
                                                System.out.println("Não foi possível criar rota");
                                            }
                                            break;
                                        case 2:
                                            int opcaoInicioRotaAddBoth = 0;
                                            int opcaoFimRotaAddBoth = 0;
                                            int iRotasAddBoth = 0;
                                            int maxAddBoth = 0;
                                            DoubleNode<Locals> actualPlaceAddBoth = lm.getLocals().getHead();
                                            while (iRotasAddBoth < lm.getLocals().getCount()) {
                                                if (maxAddBoth < actualPlaceAddBoth.getElement().getID()) {
                                                    maxAddBoth = actualPlaceAddBoth.getElement().getID();
                                                }
                                                if (actualPlaceAddBoth.getElement() instanceof Portal) {
                                                    System.out.println("ID: " + actualPlaceAddBoth.getElement().getID()
                                                            + " Local: Portal");
                                                } else if (actualPlaceAddBoth.getElement() instanceof Connector) {
                                                    System.out.println("ID: " + actualPlaceAddBoth.getElement().getID()
                                                            + " Local: Connector");
                                                }

                                                iRotasAddBoth++;
                                                actualPlaceAddBoth = actualPlaceAddBoth.getNext();
                                            }
                                            System.out.println("ID do local inicial");
                                            do {
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                opcaoInicioRotaAddBoth = Integer.parseInt(br.readLine());
                                                if (opcaoInicioRotaAddBoth < 0 || opcaoInicioRotaAddBoth > maxAddBoth) {
                                                    System.out.println("\n!! Escolha uma opcao válida !!\n");
                                                }
                                            } while (opcaoInicioRotaAddBoth < 0 && opcaoInicioRotaAddBoth > maxAddBoth);
                                            System.out.println("ID do local final da rota");
                                            do {
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                opcaoFimRotaAddBoth = Integer.parseInt(br.readLine());
                                                if (opcaoFimRotaAddBoth < 0 || opcaoFimRotaAddBoth > maxAddBoth) {
                                                    System.out.println("\n!! Escolha uma opcao válida !!\n");
                                                }
                                            } while (opcaoFimRotaAddBoth < 0 && opcaoFimRotaAddBoth > maxAddBoth);
                                            Locals localInicioAddBoth = null;
                                            Locals localFinalAddBoth = null;
                                            int localsCountAddBoth = 0;
                                            DoubleNode<Locals> currentPlaceAddBoth = lm.getLocals().getHead();
                                            while (localsCountAddBoth < lm.getLocals().getCount()) {
                                                if (currentPlaceAddBoth != null) {
                                                    if (currentPlaceAddBoth.getElement()
                                                            .getID() == opcaoInicioRotaAddBoth) {
                                                        localInicioAddBoth = currentPlaceAddBoth.getElement();
                                                    }
                                                    if (currentPlaceAddBoth.getElement()
                                                            .getID() == opcaoFimRotaAddBoth) {
                                                        localFinalAddBoth = currentPlaceAddBoth.getElement();
                                                    }
                                                    localsCountAddBoth++;
                                                    currentPlaceAddBoth = currentPlaceAddBoth.getNext();
                                                }
                                            }
                                            if (localInicioAddBoth != null && localFinalAddBoth != null) {
                                                Route rotaADDBOTH = new Route(localInicioAddBoth, localFinalAddBoth);
                                                lrm.addRouteBothWays(lm, rotaADDBOTH);
                                            } else {
                                                System.out.println("Não foi possível criar rota");
                                            }
                                            break;
                                        case 3:
                                            int opcaoInicioRotaRemoveOne = 0;
                                            int opcaoFimRotaRemoveOne = 0;
                                            int iRotasRemoveOne = 0;
                                            int maxRemoveOne = 0;
                                            DoubleNode<Locals> actualPlaceRemoveOne = lm.getLocals().getHead();
                                            while (iRotasRemoveOne < lm.getLocals().getCount()) {
                                                if (maxRemoveOne < actualPlaceRemoveOne.getElement().getID()) {
                                                    maxRemoveOne = actualPlaceRemoveOne.getElement().getID();
                                                }
                                                if (actualPlaceRemoveOne.getElement() instanceof Portal) {
                                                    System.out
                                                            .println("ID: " + actualPlaceRemoveOne.getElement().getID()
                                                                    + " Local: Portal");
                                                } else if (actualPlaceRemoveOne.getElement() instanceof Connector) {
                                                    System.out
                                                            .println("ID: " + actualPlaceRemoveOne.getElement().getID()
                                                                    + " Local: Connector");
                                                }

                                                iRotasRemoveOne++;
                                                actualPlaceRemoveOne = actualPlaceRemoveOne.getNext();
                                            }
                                            System.out.println("ID do local inicial");
                                            do {
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                opcaoInicioRotaRemoveOne = Integer.parseInt(br.readLine());
                                                if (opcaoInicioRotaRemoveOne < 0
                                                        || opcaoInicioRotaRemoveOne > maxRemoveOne) {
                                                    System.out.println("\n!! Escolha uma opcao válida !!\n");
                                                }
                                            } while (opcaoInicioRotaRemoveOne < 0
                                                    && opcaoInicioRotaRemoveOne > maxRemoveOne);
                                            System.out.println("ID do local final da rota");
                                            do {
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                opcaoFimRotaRemoveOne = Integer.parseInt(br.readLine());
                                                if (opcaoFimRotaRemoveOne < 0 || opcaoFimRotaRemoveOne > maxRemoveOne) {
                                                    System.out.println("\n!! Escolha uma opcao válida !!\n");
                                                }
                                            } while (opcaoFimRotaRemoveOne < 0 && opcaoFimRotaRemoveOne > maxRemoveOne);
                                            Locals localInicioRemoveOne = null;
                                            Locals localFinalRemoveOne = null;
                                            int localsCountRemoveOne = 0;
                                            DoubleNode<Locals> currentPlaceRemoveOne = lm.getLocals().getHead();
                                            while (localsCountRemoveOne < lm.getLocals().getCount()) {
                                                if (currentPlaceRemoveOne != null) {
                                                    if (currentPlaceRemoveOne.getElement()
                                                            .getID() == opcaoInicioRotaRemoveOne) {
                                                        localInicioRemoveOne = currentPlaceRemoveOne.getElement();
                                                    }
                                                    if (currentPlaceRemoveOne.getElement()
                                                            .getID() == opcaoFimRotaRemoveOne) {
                                                        localFinalRemoveOne = currentPlaceRemoveOne.getElement();
                                                    }
                                                    localsCountRemoveOne++;
                                                    currentPlaceRemoveOne = currentPlaceRemoveOne.getNext();
                                                }
                                            }
                                            if (localInicioRemoveOne != null && localFinalRemoveOne != null) {
                                                lrm.removeOneWay(lm, localInicioRemoveOne, localFinalRemoveOne);
                                            } else {
                                                System.out.println("Não foi possível remover a rota");
                                            }
                                            break;
                                        case 4:
                                            int opcaoInicioRotaRemoveBoth = 0;
                                            int opcaoFimRotaRemoveBoth = 0;
                                            int iRotasRemoveBoth = 0;
                                            int maxRemoveBoth = 0;
                                            DoubleNode<Locals> actualPlaceRemoveBoth = lm.getLocals().getHead();
                                            while (iRotasRemoveBoth < lm.getLocals().getCount()) {
                                                if (maxRemoveBoth < actualPlaceRemoveBoth.getElement().getID()) {
                                                    maxRemoveBoth = actualPlaceRemoveBoth.getElement().getID();
                                                }
                                                if (actualPlaceRemoveBoth.getElement() instanceof Portal) {
                                                    System.out
                                                            .println("ID: " + actualPlaceRemoveBoth.getElement().getID()
                                                                    + " Local: Portal");
                                                } else if (actualPlaceRemoveBoth.getElement() instanceof Connector) {
                                                    System.out
                                                            .println("ID: " + actualPlaceRemoveBoth.getElement().getID()
                                                                    + " Local: Connector");
                                                }
                                                iRotasRemoveBoth++;
                                                actualPlaceRemoveBoth = actualPlaceRemoveBoth.getNext();
                                            }
                                            System.out.println("ID do local inicial");
                                            do {
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                opcaoInicioRotaRemoveBoth = Integer.parseInt(br.readLine());
                                                if (opcaoInicioRotaRemoveBoth < 0
                                                        || opcaoInicioRotaRemoveBoth > maxRemoveBoth) {
                                                    System.out.println("\n!! Escolha uma opcao válida !!\n");
                                                }
                                            } while (opcaoInicioRotaRemoveBoth < 0
                                                    && opcaoInicioRotaRemoveBoth > maxRemoveBoth);
                                            System.out.println("ID do local final da rota");
                                            do {
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                opcaoFimRotaRemoveBoth = Integer.parseInt(br.readLine());
                                                if (opcaoFimRotaRemoveBoth < 0
                                                        || opcaoFimRotaRemoveBoth > maxRemoveBoth) {
                                                    System.out.println("\n!! Escolha uma opcao válida !!\n");
                                                }
                                            } while (opcaoFimRotaRemoveBoth < 0
                                                    && opcaoFimRotaRemoveBoth > maxRemoveBoth);
                                            Locals localInicioRemoveBoth = null;
                                            Locals localFinalRemoveBoth = null;
                                            int localsCountRemoveBoth = 0;
                                            DoubleNode<Locals> currentPlaceRemoveBoth = lm.getLocals().getHead();
                                            while (localsCountRemoveBoth < lm.getLocals().getCount()) {
                                                if (currentPlaceRemoveBoth != null) {
                                                    if (currentPlaceRemoveBoth.getElement()
                                                            .getID() == opcaoInicioRotaRemoveBoth) {
                                                        localInicioRemoveBoth = currentPlaceRemoveBoth.getElement();
                                                    }
                                                    if (currentPlaceRemoveBoth.getElement()
                                                            .getID() == opcaoFimRotaRemoveBoth) {
                                                        localFinalRemoveBoth = currentPlaceRemoveBoth.getElement();
                                                    }
                                                    localsCountRemoveBoth++;
                                                    currentPlaceRemoveBoth = currentPlaceRemoveBoth.getNext();
                                                }
                                            }
                                            if (localInicioRemoveBoth != null && localFinalRemoveBoth != null) {
                                                lrm.removeBothRoutes(lm, localInicioRemoveBoth, localFinalRemoveBoth);
                                            } else {
                                                System.out.println("Não foi possível remover as rotas");
                                            }
                                            break;
                                        case 5:
                                            lrm.importRoutesFromJson("routes.json");
                                            System.out.println("- Rota importada -\n");
                                            break;
                                        case 6:
                                            lrm.exportRoutesToJson("routes1.json", lrm.getRoutesInList());
                                            System.out.println("- Rota exportada -\n");
                                            break;
                                        case 7:
                                            lrm.exportSecretRoutesToJson("routes2.json", lrm.getSecretRoutesInList());
                                            System.out.println("- Rota Secreta exportada -\n");
                                    }
                                } while (opcaoRoutes != 0);
                                break;
                            case 3:
                                do {
                                    System.out.println("------------------------");
                                    System.out.println(" - Players Menu - ");
                                    System.out.println("\n1 - Adicionar");
                                    System.out.println("\n2 - Remover");
                                    System.out.println("\n3 - Editar");
                                    System.out.println("\n4 - Associar a equipa");
                                    System.out.println("\n5 - Listar por equipa");
                                    System.out.println("\n6 - Listar por nível");
                                    System.out.println("\n7 - Listar por número de portais conquistados");
                                    System.out.println("\n8 - Importar JSON");
                                    System.out.println("\n9 - Exportar JSON");
                                    System.out.println("------------------------");
                                    System.out.println("\n0 - Sair");
                                    System.out.println("------------------------");
                                    do {
                                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                                        this.opcaoPlayer = Integer.parseInt(br.readLine());
                                        if (this.opcaoPlayer < 0 || this.opcaoPlayer > 9) {
                                            System.out.println("\n!! Escolha uma opcao válida !!\n");
                                        }
                                    } while (this.opcaoPlayer < 0 && this.opcaoPlayer > 9);
                                    System.out.println("------------------------");

                                    switch (opcaoPlayer) {
                                        case 1:
                                            int numTeam = 0;
                                            String namePlayer = null;
                                            do {
                                                System.out.println("1.Sparks");
                                                System.out.println("2.Giants");
                                                System.out.println("Insira um número para escolher a equipa");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                numTeam = Integer.parseInt(br.readLine());
                                                if (numTeam < 1 || numTeam > 2) {
                                                    System.out
                                                            .println("\n!! O ID toma valores entre 1 e 2 !!\n");
                                                }
                                            } while (numTeam < 1 || numTeam > 2);
                                            do {
                                                System.out.println("Insira um nome");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                namePlayer = br.readLine();
                                                if (namePlayer == null) {
                                                    System.out.println("\n!! Insira algum nome !!\n");
                                                }
                                            } while (namePlayer == null);
                                            if (numTeam == 1) {
                                                pm.addPlayer(namePlayer, Team.Sparks);
                                            } else if (numTeam == 2) {
                                                pm.addPlayer(namePlayer, Team.Giants);
                                            }
                                            break;
                                        case 2:
                                            String namePlayerRemove = null;
                                            pm.listarjogadores();
                                            do {
                                                System.out.println("Insira o nome do jogador que pretende remover");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                namePlayerRemove = br.readLine();
                                                if (namePlayerRemove == null) {
                                                    System.out.println("\n!! Insira algum nome !!\n");
                                                }
                                            } while (namePlayerRemove == null);
                                            pm.removePlayer(namePlayerRemove);
                                            break;
                                        case 3:
                                            String namePlayerToEdit = null;
                                            String newNameEdit = null;
                                            pm.listarjogadores();
                                            do {
                                                System.out.println("Insira o nome do jogador que pretende editar");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                namePlayerToEdit = br.readLine();
                                                if (namePlayerToEdit == null) {
                                                    System.out.println("\n!! Insira algum nome !!\n");
                                                }
                                            } while (namePlayerToEdit == null);
                                            do {
                                                System.out.println("Insira o novo nome do jogador");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                newNameEdit = br.readLine();
                                                if (newNameEdit == null) {
                                                    System.out.println("\n!! Insira algum nome !!\n");
                                                }
                                            } while (newNameEdit == null);
                                            pm.updatePlayer(namePlayerToEdit, newNameEdit);
                                            break;
                                        case 4:
                                            String changeTeamName = null;
                                            pm.listarjogadores();
                                            do {
                                                System.out.println(
                                                        "Insira o nome do jogador que pretende mudar a equipa");
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                changeTeamName = br.readLine();
                                                if (changeTeamName == null) {
                                                    System.out.println("\n!! Insira algum nome !!\n");
                                                }
                                            } while (changeTeamName == null);
                                            pm.ChangeTeam(changeTeamName);
                                            break;
                                        case 5:
                                            System.out.println("------------------------");
                                            System.out.println("Giants");
                                            pm.listPlayersByTeam(Team.Giants);
                                            System.out.println("------------------------");
                                            System.out.println("Sparks");
                                            pm.listPlayersByTeam(Team.Sparks);
                                            System.out.println("------------------------");
                                            break;
                                        case 6:
                                            pm.listPlayersByLevel();
                                            break;
                                        case 7:
                                            pm.listPlayersByNumOfPortalsConquered();
                                            break;
                                        case 8:
                                            pm.importPlayersFromJson("players.json");
                                            break;
                                        case 9:
                                            pm.exportPlayersToJson("players2.json");
                                            break;
                                    }
                                } while (opcaoPlayer != 0);
                                break;
                            case 4:
                                do {
                                    System.out.println("------------------------");
                                    System.out.println(" - Game Menu - ");
                                    System.out
                                            .println("\n1 - Imprimir e Exportar caminho mais curto entre dois pontos");
                                    System.out.println("\n3 - Importar JSON com configurações");
                                    System.out.println("\n4 - Exportar JSON com configurações");
                                    System.out.println("------------------------");
                                    System.out.println("\n0 - Sair");
                                    System.out.println("------------------------");
                                    do {
                                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                                        this.opcaoGame = Integer.parseInt(br.readLine());
                                        if (this.opcaoGame < 0 || this.opcaoGame > 3) {
                                            System.out.println("\n!! Escolha uma opcao válida !!\n");
                                        }
                                    } while (this.opcaoGame < 0 && this.opcaoGame > 3);
                                    System.out.println("------------------------");

                                    switch (opcaoGame) {
                                        case 1:
                                            int opcaoInicioRota = 0;
                                            int opcaoFimRota = 0;
                                            int iRotasADD = 0;
                                            int maxADD = 0;
                                            DoubleNode<Locals> actualPlace = lm.getLocals().getHead();
                                            while (iRotasADD < lm.getLocals().getCount()) {
                                                if (maxADD < actualPlace.getElement().getID()) {
                                                    maxADD = actualPlace.getElement().getID();
                                                }
                                                if (actualPlace.getElement() instanceof Portal) {
                                                    System.out.println("ID: " + actualPlace.getElement().getID()
                                                            + " Local: Portal");
                                                } else if (actualPlace.getElement() instanceof Connector) {
                                                    System.out.println("ID: " + actualPlace.getElement().getID()
                                                            + " Local: Connector");
                                                }

                                                iRotasADD++;
                                                actualPlace = actualPlace.getNext();
                                            }
                                            System.out.println("ID do local inicial");
                                            do {
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                opcaoInicioRota = Integer.parseInt(br.readLine());
                                                if (opcaoInicioRota < 0 || opcaoInicioRota > maxADD) {
                                                    System.out.println("\n!! Escolha uma opcao válida !!\n");
                                                }
                                            } while (opcaoInicioRota < 0 && opcaoInicioRota > maxADD);
                                            System.out.println("ID do local final da rota");
                                            do {
                                                BufferedReader br = new BufferedReader(
                                                        new InputStreamReader(System.in));
                                                opcaoFimRota = Integer.parseInt(br.readLine());
                                                if (opcaoFimRota < 0 || opcaoFimRota > maxADD) {
                                                    System.out.println("\n!! Escolha uma opcao válida !!\n");
                                                }
                                            } while (opcaoFimRota < 0 && opcaoFimRota > maxADD);
                                            Locals localInicio = null;
                                            Locals localFinal = null;
                                            int localsCount = 0;
                                            DoubleNode<Locals> currentPlace = lm.getLocals().getHead();
                                            while (localsCount < lm.getLocals().getCount()) {
                                                if (currentPlace != null) {
                                                    if (currentPlace.getElement().getID() == opcaoInicioRota) {
                                                        localInicio = currentPlace.getElement();
                                                    }
                                                    if (currentPlace.getElement().getID() == opcaoFimRota) {
                                                        localFinal = currentPlace.getElement();
                                                    }
                                                    localsCount++;
                                                    currentPlace = currentPlace.getNext();
                                                }
                                            }
                                            if (localInicio != null && localFinal != null) {
                                                Iterator<Locals> locals = lrm.getRotas()
                                                        .iteratorShortestPath(localInicio, localFinal);

                                                System.out.println("Rota do caminho: ");
                                                while (locals.hasNext()) {
                                                    System.out.println(locals.next().getID());
                                                }
                                            } else {
                                                System.out.println("Impossível");
                                            }

                                            lrm.exportShortestPath("shortestPath.json", localInicio, localFinal, lrm);
                                            System.out.println("- Caminhos exportados -\n");
                                            break;
                                        case 2:
                                            ptg.importProblemDataToJSON("problemData", gc, lm, lrm, pm);
                                            System.out.println("- JSON com caminhos importado -\n");
                                            break;
                                        case 3:
                                            ptg.exportDataToJson("exportProblem", gc, lm, lrm, pm);
                                            System.out.println("- JSON exportado -\n");
                                            break;
                                    }
                                } while (opcaoGame != 0);
                                break;
                        }
                    } while (opcao2 != 0);
            }
        } while (opcao1 != 0);
    }
}
