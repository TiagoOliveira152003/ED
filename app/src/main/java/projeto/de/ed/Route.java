package projeto.de.ed;

/**
 * <h3>
 * <strong>Classe Rota</strong>
 * </h3>
 *
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class Route {
    private int start;
    private int target;
    private double distance;

    /**
     * Construtor parametrizado
     * @param start ID do local de partida
     * @param target ID do local de chegada
     */
    public Route(Locals start, Locals target) {
        this.start = start.getID();
        this.target = target.getID();
        this.distance = 0.0;
    }

    /**
     * Construtor vazio
     */
    public Route() {
    }
    
    /**
     * Getter do ID do local de chegada
     * @return start
     */
    public int getTarget() {
        return target;
    }

    /**
     * Setter do ID do local de chegada
     * @param target
     */
    public void setTarget(int target) {
        this.target = target;
    }

    /**
     * Getter do ID do local de partida
     * @return start
     */
    public int getStart() {
        return start;
    }

    /**
     * Setter do ID do local de partida
     * 
     * @param start
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * Getter da distância entre os dois locais
     * 
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Setter da distância entre os dois locais
     * 
     * @param distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

}
