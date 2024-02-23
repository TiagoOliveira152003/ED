package projeto.de.ed.Management;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import projeto.de.ed.Locals;
import projeto.de.ed.Route;
import projeto.de.ed.SecretRoute;
import projeto.de.ed.DataStructures.*;
import projeto.de.ed.Enums.Team;
import projeto.de.ed.Interfaces.LocalsRoutesManagementInterface;
import projeto.de.ed.exceptions.ElementNotFoundException;
import projeto.de.ed.exceptions.EmptyCollectionException;

/**
 * <h3>
 * <strong>Classe Gestão de Rotas</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class LocalsRoutesManagement implements LocalsRoutesManagementInterface {
    Network<Locals> rotas = new Network<>();
    TeamManagement tm = new TeamManagement();
    DoubleLinkedUnorderedList<SecretRoute> secretRoutes = new DoubleLinkedUnorderedList<>();
    DoubleLinkedUnorderedList<Route> routes = new DoubleLinkedUnorderedList<>();

    /**
     * Calcula o peso de uma rota entre dois locais
     * 
     * @param start  O vertice de partida
     * @param target O vertice de chegada
     * @return double
     */
    public double calculateWeight(Locals start, Locals target) {
        return Math
                .sqrt((target.getLongitude() - start.getLongitude()) * (target.getLongitude() - start.getLongitude()) +
                        (target.getLatitude() - start.getLatitude()) * (target.getLatitude() - start.getLatitude()));
    }

    /**
     * Adiciona uma rota de um local para outro
     * 
     * @param lm   Gestão de locais
     * @param rota Uma rota
     * @throws ElementNotFoundException
     */
    public void addRouteOneWay(LocalsManagement lm, Route rota)
            throws ElementNotFoundException {
        Locals start = null;
        Locals target = null;

        int i = 0;
        DoubleLinkedUnorderedList<Locals> listaDeLocais = lm.getLocals();
        projeto.de.ed.DataStructures.DoubleNode<Locals> current = listaDeLocais.getHead();
        while (i < listaDeLocais.getCount()) {
            if (current.getElement().getID() == rota.getStart()) {
                start = current.getElement();
            }
            if (current.getElement().getID() == rota.getTarget()) {
                target = current.getElement();
            }
            current = current.getNext();
            i++;
        }
        if (start != null && target != null) {
            if (rotas.getVertexIndex(start) == -1) {
                rotas.addVertex(start);
            }

            if (rotas.getVertexIndex(target) == -1) {
                rotas.addVertex(target);
            }

            if (lm.getLocals().getCount() == 0) {
                System.out.println("Não existem locais para que seja criada uma rota");
            } else if (rotas.getEdgeWeight(start, target) != Double.POSITIVE_INFINITY) {
                System.out.println("Esta rota já existe!");
            } else if (rotas.getVertexIndex(start) != -1
                    && rotas.getVertexIndex(target) != -1) {
                rotas.addEdge(start, target, calculateWeight(start, target));

                System.out.println("- Rota criada -\n");
            }
        }
    }

    /**
     * Adiciona uma rota de um local para outro e vice-versa
     * 
     * @param lm   Gestão de locais
     * @param rota Uma rota
     * @throws ElementNotFoundException
     */
    public void addRouteBothWays(LocalsManagement lm, Route rota)
            throws ElementNotFoundException {
        Locals start = null;
        Locals target = null;

        int i = 0;
        DoubleLinkedUnorderedList<Locals> listaDeLocais = lm.getLocals();
        projeto.de.ed.DataStructures.DoubleNode<Locals> current = listaDeLocais.getHead();
        while (i < listaDeLocais.getCount()) {
            if (current.getElement().getID() == rota.getStart()) {
                start = current.getElement();
            } else if (current.getElement().getID() == rota.getTarget()) {
                target = current.getElement();
            }
            current = current.getNext();
            i++;
        }
        if (start != null && target != null) {
            if (rotas.getVertexIndex(start) == -1) {
                rotas.addVertex(start);
            }

            if (rotas.getVertexIndex(target) == -1) {
                rotas.addVertex(target);
            }

            if (lm.getLocals().getCount() == 0) {
                System.out.println("Não existem locais para que seja criada uma rota");
            } else if (rotas.getEdgeWeight(start, target) != Double.POSITIVE_INFINITY) {
                System.out.println("Esta rota já existe!");
            }
            if (rotas.getVertexIndex(start) != -1
                    && rotas.getVertexIndex(target) != -1
                    && rotas.getEdgeWeight(start, target) == Double.POSITIVE_INFINITY) {
                rotas.addEdge(start, target, calculateWeight(start, target));
                System.out.println("- Rota início -> fim criada -\n");

            }
            if (rotas.getVertexIndex(start) != -1
                    && rotas.getVertexIndex(target) != -1
                    && rotas.getEdgeWeight(target, start) == Double.POSITIVE_INFINITY) {
                rotas.addEdge(target, start, calculateWeight(target, start));
                System.out.println("- Rota fim -> início criada -\n");

            }
        }
    }

    /**
     * Adiciona uma rota secreta de um local para outro
     * 
     * @param lm   Gestão de locais
     * @param rota Uma rota secreta
     * @throws ElementNotFoundException
     */
    public void addSecretRoute(LocalsManagement lm, SecretRoute rota)
            throws ElementNotFoundException {
        Locals start = null;
        Locals target = null;

        int i = 0;
        DoubleLinkedUnorderedList<Locals> listaDeLocais = lm.getLocals();
        projeto.de.ed.DataStructures.DoubleNode<Locals> current = listaDeLocais.getHead();
        while (i < listaDeLocais.getCount()) {
            if (current.getElement().getID() == rota.getStart()) {
                start = current.getElement();
            } else if (current.getElement().getID() == rota.getTarget()) {
                target = current.getElement();
            }
            current = current.getNext();
            i++;
        }
        if (start != null && target != null) {
            if (rotas.getVertexIndex(start) == -1) {
                rotas.addVertex(start);
            }

            if (rotas.getVertexIndex(target) == -1) {
                rotas.addVertex(target);
            }

            if (lm.getLocals().getCount() == 0) {
                System.out.println("Não existem locais para que seja criada uma rota");
            } else if (rotas.getEdgeWeight(start, target) != Double.POSITIVE_INFINITY) {
                System.out.println("Esta rota já existe!");
            } else if (rotas.getVertexIndex(start) != -1
                    && rotas.getVertexIndex(target) != -1) {
                rotas.addEdge(start, target, calculateWeight(start, target));
                rotas.addEdge(target, start, calculateWeight(target, start));

            }
        }
    }

    /**
     * Remove uma rota de um local para outro
     * 
     * @param lm     Gestão de locais
     * @param start  Vertice de partida
     * @param target Vertice de chegada
     * @throws ElementNotFoundException
     */
    public void removeOneWay(LocalsManagement lm, Locals start, Locals target)
            throws ElementNotFoundException {
        if (start != null && target != null) {
            if (lm.getLocals().getCount() == 0 || rotas.isEmpty()) {
                System.out.println("Não existem locais para que seja removida uma rota");
            } else if (rotas.getEdgeWeight(start, target) == Double.POSITIVE_INFINITY) {
                System.out.println("Esta rota não existe!");
            } else {
                rotas.removeEdge(start, target);
                System.out.println("- Rota removida -\n");
            }
        }
    }

    /**
     * Remove uma rota de um local para outro e vice-versa
     * 
     * @param lm     Gestão de locais
     * @param start  Vertice de partida
     * @param target Vertice de chegada
     * @throws ElementNotFoundException
     */
    public void removeBothRoutes(LocalsManagement lm, Locals start, Locals target) throws ElementNotFoundException {
        if (start != null && target != null) {
            if (lm.getLocals().getCount() == 0 || rotas.isEmpty()) {
                System.out.println("Não existem locais para que seja removida uma rota");
            } else if (rotas.getEdgeWeight(start, target) == Double.POSITIVE_INFINITY) {
                System.out.println("Esta rota não existe!");
            } else {
                rotas.removeEdge(start, target);
                rotas.removeEdge(target, start);
                System.out.println("- Rotas removidas -\n");

            }
        }
    }

    /**
     * Getter de uma rede de rotas
     * 
     * @return Uma rede de rotas
     */
    public Network<Locals> getRotas() {
        return rotas;
    }

    /**
     * Setter de uma rede de rotas
     * 
     * @param rotas Uma rede de rotas
     */
    public void setRotas(Network<Locals> rotas) {
        this.rotas = rotas;
    }

    /**
     * Verifica se existe uma rota entre dois locais
     * 
     * @param map    Uma rede de rotas
     * @param start  Vertice de partida
     * @param target Vertice de chegada
     * @return true se existir uma rota entre os dois locais, false caso contrário
     */
    public boolean hasRoute(Network<Locals> map, Locals start, Locals target) {
        if (map.getEdgeWeight(start, target) == Double.POSITIVE_INFINITY) {
            return false;
        }
        if (map.getEdgeWeight(start, target) == 0) {
            return false;
        }
        return true;
    }

    /**
     * Getter de uma lista de rotas
     * 
     * @return Um lista de rotas
     */
    public DoubleLinkedUnorderedList<Route> getRoutesInList()
            throws ElementNotFoundException, EmptyCollectionException {
        DoubleLinkedUnorderedList<Route> temp = new DoubleLinkedUnorderedList<Route>();
        for (int i = 0; i < rotas.getCount(); i++) {
            for (int j = 0; j < rotas.getCount(); j++) {
                if (rotas.getAdjMatrixConnectionCost()[i][j] != Double.POSITIVE_INFINITY) {
                    Route tempRota = new Route(rotas.getVertexAt(i), rotas.getVertexAt(j));
                    tempRota.setDistance(rotas.getAdjMatrixConnectionCost()[i][j]);
                    temp.addToRear(tempRota);
                }
            }
        }
        return temp;
    }

    /**
     * Getter de uma lista de rotas secretas
     * 
     * @return Um lista de rotas secretas
     */
    public DoubleLinkedUnorderedList<SecretRoute> getSecretRoutesInList()
            throws ElementNotFoundException, EmptyCollectionException {
        DoubleLinkedUnorderedList<SecretRoute> temp = new DoubleLinkedUnorderedList<SecretRoute>();
        DoubleNode<SecretRoute> current = temp.getHead();
        for (int i = 0; i < rotas.getCount(); i++) {
            for (int j = 0; j < rotas.getCount(); j++) {
                if (rotas.getAdjMatrixConnectionCost()[i][j] != Double.POSITIVE_INFINITY) {

                    SecretRoute tempRota = new SecretRoute(rotas.getVertexAt(i), rotas.getVertexAt(j),
                            current.getElement().getTeam());
                    tempRota.setDistance(rotas.getAdjMatrixConnectionCost()[i][j]);
                    temp.addToRear(tempRota);
                }
                current = current.getNext();
            }
        }
        return temp;
    }

    /**
     * Importa rotas de um ficheiro JSON
     * 
     * @param String Nome do ficheiro
     * @return Um rota
     */
    public Route importRoutesFromJson(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        Route route = new Route();
        SecretRoute secretRoute = new SecretRoute();
        try {
            JsonNode rootNode = objectMapper.readTree(new File(filename));
            JsonNode routesNode = rootNode.get("routes");

            if (routesNode.isArray()) {
                for (JsonNode routeNode : routesNode) {
                    int fromId = routeNode.get("from").asInt();
                    int toId = routeNode.get("to").asInt();
                    double distance = routeNode.get("distance").asDouble();
                    route.setStart(fromId);
                    route.setTarget(toId);
                    route.setDistance(distance);
                    if (routeNode.get("team") != null) {
                        String Equipa = routeNode.get("team").asText();
                        if (Equipa.equals("Sparks")) {
                            secretRoute.setTeam(Team.Sparks);
                        } else {
                            secretRoute.setTeam(Team.Giants);
                        }
                    }
                }
                System.out.println("- Rotas importados -\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return route;
    }

    /**
     * Exporta o caminho mais curto entre dois locais para um ficheiro JSON
     * 
     * @param filename    O nome do ficheiro para onde o caminho mais curto será
     *                    exportado
     * @param localInicio O local de início do caminho
     * @param localFinal  O local de fim do caminho
     * @param lrm         O objecto que contém a rede de rotas
     * 
     * @throws ElementNotFoundException se um dos locais não existir
     * @throws EmptyCollectionException se a rede de rotas estiver vazia
     */
    public void exportShortestPath(String filename, Locals localInicio, Locals localFinal, LocalsRoutesManagement lrm)
            throws ElementNotFoundException, EmptyCollectionException {
        Iterator<Locals> locals = lrm.getRotas().iteratorShortestPath(localInicio, localFinal);
        Iterator<Locals> locals1 = lrm.getRotas().iteratorShortestPath(localInicio, localFinal);

        DoubleLinkedUnorderedList<Locals> locais = new DoubleLinkedUnorderedList<Locals>();
        int i = 0;
        locais.addToRear(localInicio);
        while (locals.hasNext()) {
            if (locals.next() != null) {
                locais.addToRear(locals.next());
            }
        }
        double pathWeight = 0.0;
        DoubleNode<Locals> current = locais.getHead();
        while (i < locais.getCount()) {
            if (current != null && current.getNext() != null) {
                Route tempRota = new Route(current.getElement(), current.getNext().getElement());
                pathWeight += lrm.getRotas().getAdjMatrixConnectionCost()[lrm.getRotas().getVertexIndex(
                        current.getElement())][lrm.getRotas().getVertexIndex(current.getNext().getElement())];
            }
            i++;
            current = current.getNext();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode shortestPathNode = objectMapper.createArrayNode();

        ObjectNode pathNode = objectMapper.createObjectNode();
        pathNode.put("Distância", pathWeight);
        while (locals.hasNext()) {
            pathNode.put("Visita", locals1.next().getID());
            shortestPathNode.add(pathNode);
        }
        shortestPathNode.add(pathNode);
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.set("ShortestPath", shortestPathNode);

        try {
            objectMapper.writeValue(new File(filename), rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exporta as rotas para um ficheiro JSON
     * 
     * @param filename O nome do ficheiro para onde as rotas serão exportadas
     * @param rota     A lista de rotas a exportar
     */
    public ArrayNode exportRoutesToJson(String filename, DoubleLinkedUnorderedList<Route> rota) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode routesArrayNode = objectMapper.createArrayNode();

        DoubleNode<Route> current = rota.getHead();
        for (int i = 0; i < rota.getCount(); i++) {
            Route tempRota = current.getElement();
            ObjectNode routeNode = objectMapper.createObjectNode();
            routeNode.put("from", tempRota.getStart());
            routeNode.put("to", tempRota.getTarget());
            routeNode.put("distance", tempRota.getDistance());
            routesArrayNode.add(routeNode);
            current = current.getNext();
        }

        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.set("routes", routesArrayNode);

        try {
            objectMapper.writeValue(new File(filename), rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return routesArrayNode;
    }

    /**
     * Exporta as rotas secretas para um ficheiro JSON
     * 
     * @param filename O nome do ficheiro para onde as rotas serão exportadas
     * @param rota     A lista de rotas a exportar
     */
    public void exportSecretRoutesToJson(String filename, DoubleLinkedUnorderedList<SecretRoute> rotaSecreta) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode secretRoutesArrayNode = objectMapper.createArrayNode();

        DoubleNode<SecretRoute> current = rotaSecreta.getHead();
        for (int i = 0; i < rotaSecreta.getCount(); i++) {
            SecretRoute tempSecretRota = current.getElement();
            if (current.getElement().getTeam() == Team.Sparks) {
                ObjectNode secretRouteNode = objectMapper.createObjectNode();
                secretRouteNode.put("from", tempSecretRota.getStart());
                secretRouteNode.put("to", tempSecretRota.getTarget());
                secretRouteNode.put("distance", tempSecretRota.getDistance());
                secretRouteNode.put("team", "Sparks");
                secretRoutesArrayNode.add(secretRouteNode);
            } else {
                ObjectNode secretRouteNode = objectMapper.createObjectNode();
                secretRouteNode.put("from", tempSecretRota.getStart());
                secretRouteNode.put("to", tempSecretRota.getTarget());
                secretRouteNode.put("distance", tempSecretRota.getDistance());
                secretRouteNode.put("team", "Giants");
                secretRoutesArrayNode.add(secretRouteNode);
            }
            current = current.getNext();
        }

        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.set("secretRoutes", secretRoutesArrayNode);

        try {
            objectMapper.writeValue(new File(filename), rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
