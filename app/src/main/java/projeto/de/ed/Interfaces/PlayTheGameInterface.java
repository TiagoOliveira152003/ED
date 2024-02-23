package projeto.de.ed.Interfaces;

import projeto.de.ed.Jogador;
import projeto.de.ed.Locals;
import projeto.de.ed.DataStructures.DoubleLinkedUnorderedList;
import projeto.de.ed.DataStructures.Network;
import projeto.de.ed.Management.LocalsManagement;
import projeto.de.ed.Management.LocalsRoutesManagement;

/**
 * <h3>
 * <strong>Interface PlayTheGame</strong>
 * </h3>
 * 
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public interface PlayTheGameInterface {
        public DoubleLinkedUnorderedList<Locals> possibleMovements(LocalsRoutesManagement lrm, LocalsManagement lm,
                        Jogador jogador);

        public void moveToLocal(LocalsRoutesManagement lrm, LocalsManagement lm,
                        Jogador jogador, int id);

        public int[][] ListAdjMatrix(Network<Locals> mapa, LocalsManagement lm, LocalsRoutesManagement lrm,
                        Jogador jogador);

        public void exportAdjMatrixToTXT(LocalsManagement lm, LocalsRoutesManagement lrm,
                        Jogador jogador);
}
