package projeto.de.ed.Management;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import projeto.de.ed.GameConfiguration;
import projeto.de.ed.Jogador;
import projeto.de.ed.Locals;
import projeto.de.ed.DataStructures.DoubleLinkedUnorderedList;
import projeto.de.ed.DataStructures.DoubleNode;
import projeto.de.ed.DataStructures.Network;
import projeto.de.ed.exceptions.ElementNotFoundException;
import projeto.de.ed.exceptions.EmptyCollectionException;
import projeto.de.ed.Interfaces.PlayTheGameInterface;

/**
 * <h3>
 * <strong>Classe PlayThegame</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class PlayThegame implements PlayTheGameInterface {
    Network<Locals> mapa;

    /**
     * Construtor parametrizado
     * 
     * @param mapa
     */
    public PlayThegame(Network<Locals> mapa) {
        this.mapa = mapa;
    }

    /**
     * Construtor por omissão
     */
    public PlayThegame() {
    }

    /**
     * Método que apresenta ao jogador os movimentos possíveis
     * 
     * @param lrm - LocalsRoutesManagement
     * @param lm - LocalsManagement
     * @param jogador - Jogador
     * @return locaisP - DoubleLinkedUnorderedList<Locals>
     */
    public DoubleLinkedUnorderedList<Locals> possibleMovements(LocalsRoutesManagement lrm, LocalsManagement lm,
            Jogador jogador) {
        DoubleLinkedUnorderedList<Locals> locaisP = new DoubleLinkedUnorderedList<>();
        int i = 0;
        int j = 0;
        DoubleNode<Locals> current = lm.getLocals().getHead();
        if (locaisP.getCount() == 0) {
            while (i < lm.getLocals().getCount()) {
                if (lrm.hasRoute(mapa, jogador.getActualPlace(), current.getElement())) {
                    locaisP.addToRear(current.getElement());
                }
                i++;
                current = current.getNext();
            }
        } else {
            System.out.println("Não há movimentos possíveis!");
        }
        return locaisP;
    }

    /**
     * Método para verifica se o movimento é possível
     * 
     * @param lrm - LocalsRoutesManagement
     * @param lm - LocalsManagement
     * @param jogador - Jogador
     * @return - DoubleLinkedUnorderedList<Locals>
     */
    private DoubleLinkedUnorderedList<Locals> possibleMovements1(LocalsRoutesManagement lrm, LocalsManagement lm,
            Jogador jogador) {
        DoubleLinkedUnorderedList<Locals> locaisP = new DoubleLinkedUnorderedList<>();
        int i = 0;
        DoubleNode<Locals> current = lm.getLocals().getHead();
        if (locaisP.getCount() == 0) {
            while (i < lm.getLocals().getCount()) {
                if (lrm.hasRoute(mapa, jogador.getActualPlace(), current.getElement())) {
                    locaisP.addToRear(current.getElement());
                }
                i++;
                current = current.getNext();
            }
        }
        return locaisP;
    }

    /**
     * Método para mover o jogador para um local
     * 
     * @param lrm - LocalsRoutesManagement
     * @param lm - LocalsManagement
     * @param jogador - Jogador
     * @param id - int
     */
    public void moveToLocal(LocalsRoutesManagement lrm, LocalsManagement lm,
            Jogador jogador, int id) {
        DoubleLinkedUnorderedList<Locals> locaisP = possibleMovements1(lrm, lm, jogador);
        LocalsManagement a = new LocalsManagement();
        a.setLocals(locaisP);
        DoubleNode<Locals> current = a.getLocals().getHead();
        int i = 0;
        boolean done = false;
        while (i < possibleMovements1(lrm, lm, jogador).getCount()) {
            if (current.getElement().getID() == id && current != null) {
                jogador.setActualPlace(current.getElement());
                done = true;
                break;
            }
            i++;
            current = current.getNext();
        }
        if (done == false) {
            System.out.println("O movimento não foi possível executar!");
        }
    }

    /**
     * Método para apresentar a matriz de adjacência
     * 
     * @param lrm - LocalsRoutesManagement
     * @param lm - LocalsManagement
     * @param jogador - Jogador
     * @param id - Network<Locals>
     * @return adjMatrix - int[][]
     */
    public int[][] ListAdjMatrix(Network<Locals> mapa, LocalsManagement lm, LocalsRoutesManagement lrm,
            Jogador jogador) {
        int[][] adjMatrix = new int[lm.getLocals().getCount()][lm.getLocals().getCount()];
        for (int i = 0; i < lm.getLocals().getCount(); i++) {
            for (int j = 0; j < lm.getLocals().getCount(); j++) {
                if (lrm.getRotas().getAdjMatrixConnectionCost()[i][j] != Double.POSITIVE_INFINITY) {
                    adjMatrix[i][j] = 1;
                } else {
                    adjMatrix[i][j] = 0;
                }
            }
        }

        for (int row = 0; row < adjMatrix.length; row++) {
            for (int col = 0; col < adjMatrix.length; col++) {
                System.out.print(adjMatrix[row][col] + " ");
            }
            System.out.println();
        }
        return adjMatrix;
    }

    /**
     * Método para exportar a matriz de adjacência para um ficheiro txt
     * 
     * @param lrm - LocalsRoutesManagement
     * @param lm - LocalsManagement
     * @param jogador - Jogador
     */
    public void exportAdjMatrixToTXT(LocalsManagement lm, LocalsRoutesManagement lrm,
            Jogador jogador) {
        try {
            int[][] adjM = ListAdjMatrix(mapa, lm, lrm, jogador);
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("matrix.txt")));

            int num = 1;

            for (int i = 0; i < adjM.length; i++) {
                for (int j = 0; j < adjM[i].length; j++) {
                    writer.write(adjM[i][j] + ", ");
                    num++;
                }

                writer.newLine();
            }
            writer.flush();
            writer.close();

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    /**
     * Método para importar os dados do problema para um ficheiro JSON
     * 
     * @param filename - String
     * @param gc - GameConfiguration
     * @param lm - LocalsManagement
     * @param lrm - LocalsRoutesManagement
     * @param pm - PlayersManagement
     * @throws ElementNotFoundException
     * @throws EmptyCollectionException
     */
    public void importProblemDataToJSON(String filename, GameConfiguration gc, LocalsManagement lm, LocalsRoutesManagement lrm, PlayersManagement pm) throws ElementNotFoundException, EmptyCollectionException {
        gc.importGameConfigsToJson(filename);
        lm.importLocalsFromJson(filename);
        lrm.importRoutesFromJson(filename);
        pm.importPlayersFromJson(filename);
    }

    /**
     * Método para exportar os dados do problema para um ficheiro JSON
     * 
     * @param filename - String
     * @param gc - GameConfiguration
     * @param lm - LocalsManagement
     * @param lrm - LocalsRoutesManagement
     * @param pm - PlayersManagement
     * @throws ElementNotFoundException
     * @throws EmptyCollectionException
     */
    public void exportDataToJson(String filename, GameConfiguration gc, LocalsManagement lm, LocalsRoutesManagement lrm, PlayersManagement pm) throws ElementNotFoundException, EmptyCollectionException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode data = mapper.createObjectNode();
        
        ObjectNode gameConfigData = gc.exportGameConfigToJson(filename);
        data.set("gameConfiguration", gameConfigData);
        
        ObjectNode localsData = lm.exportLocalsToJson(filename);
        data.set("locals", localsData);
        
        ArrayNode routesData = mapper.createArrayNode();
        routesData.addAll(lrm.exportRoutesToJson(filename, lrm.getRoutesInList()));
        data.set("routes", routesData);
        
        ArrayNode playersData = mapper.createArrayNode();
        playersData.addAll(pm.exportPlayersToJson(filename));
        data.set("players", playersData);
        
        try (FileWriter fileWriter = new FileWriter(filename)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
