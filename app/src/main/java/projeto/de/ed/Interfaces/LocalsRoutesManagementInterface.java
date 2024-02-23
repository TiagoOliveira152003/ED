package projeto.de.ed.Interfaces;

import com.fasterxml.jackson.databind.node.ArrayNode;

import projeto.de.ed.Locals;
import projeto.de.ed.Route;
import projeto.de.ed.SecretRoute;
import projeto.de.ed.DataStructures.DoubleLinkedUnorderedList;
import projeto.de.ed.DataStructures.Network;
import projeto.de.ed.Management.LocalsManagement;
import projeto.de.ed.exceptions.ElementNotFoundException;
import projeto.de.ed.exceptions.EmptyCollectionException;

/**
 * <h3>
 * <strong>Interface  Gestão de Rotas</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public interface LocalsRoutesManagementInterface {
        public double calculateWeight(Locals start, Locals target);

        public void addRouteOneWay(LocalsManagement lm, Route rota)
                        throws ElementNotFoundException;

        public void addRouteBothWays(LocalsManagement lm, Route rota)
                        throws ElementNotFoundException;

        public void addSecretRoute(LocalsManagement lm, SecretRoute rota)
                        throws ElementNotFoundException;

        public void removeOneWay(LocalsManagement lm, Locals start, Locals target)
                        throws ElementNotFoundException;

        public void removeBothRoutes(LocalsManagement lm, Locals start, Locals target) throws ElementNotFoundException;

        public boolean hasRoute(Network<Locals> map, Locals start, Locals target);

        public DoubleLinkedUnorderedList<Route> getRoutesInList()
                        throws ElementNotFoundException, EmptyCollectionException;

        public DoubleLinkedUnorderedList<SecretRoute> getSecretRoutesInList()
                        throws ElementNotFoundException, EmptyCollectionException;

        public Route importRoutesFromJson(String filename);

        public ArrayNode exportRoutesToJson(String filename, DoubleLinkedUnorderedList<Route> rota);

        public void exportSecretRoutesToJson(String filename, DoubleLinkedUnorderedList<SecretRoute> rotaSecreta);
}
