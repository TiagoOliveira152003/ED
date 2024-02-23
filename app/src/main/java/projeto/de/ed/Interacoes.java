package projeto.de.ed;

import java.time.LocalDateTime;

/**
 * <h3>
 * <strong>Classe Interações</strong>
 * </h3>
 *
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class Interacoes {
    private Jogador player;
    private Connector connector;
    private LocalDateTime time;


    /**
     * Construtor parametrizado
     * 
     * @param player
     * @param connector
     * @param time
     */
    public Interacoes(Jogador player, Connector connector, LocalDateTime time) {
        this.player = player;
        this.connector = connector;
        this.time = time;
    }

    /**
     * Getter do jogador
     * 
     * @return player
     */
    public Jogador getPlayer() {
        return player;
    }

    /**
     * Setter do jogador
     * 
     * @param player
     */
    public void setPlayer(Jogador player) {
        this.player = player;
    }

    /**
     * Getter do Connector
     * 
     * @return connector
     */
    public Connector getConnector() {
        return connector;
    }

    /**
     * Setter do Connector
     * 
     * @param connector
     */
    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    /**
     * Getter do tempo
     * 
     * @return time
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Setter do tempo
     * 
     * @param time
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
