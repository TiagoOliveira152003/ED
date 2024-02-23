package projeto.de.ed;

import projeto.de.ed.Enums.Team;

/**
 * <h3>
 * <strong>Classe Rota Secreta</strong>
 * </h3>
 *
 * @author Adão Araújo
 * @author Tiago Oliveira
 */
public class SecretRoute extends Route {
    private Team team;

    /**
     * Construtor parametrizado
     * 
     * @param start Local de partida
     * @param target Local de chegada
     * @param team  Equipa que controla a rota
     */
    public SecretRoute(Locals start, Locals target, Team team) {
        super(start, target);
        this.team = team;
    }

    /**
     * Construtor vazio
     */
    public SecretRoute() {
    }
    
    /**
     * Getter da equipa que controla a rota
     * 
     * @return team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Setter da equipa que controla a rota
     * 
     * @param team
     */
    public void setTeam(Team team) {
        this.team = team;
    }

}
