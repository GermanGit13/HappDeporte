package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.Team;
import com.svalero.happDeporte.exception.TeamNotFoundException;

import java.util.List;

/** 2) Capa donde va a estar la lógica, tendremos una interface por cada clase Java del domain
 * con los métodos aquí estará el grueso de la aplicación
 */
public interface TeamService {

    Team addTeam(Team team);
    void deleteTeam(long id) throws TeamNotFoundException;
    Team modifyTeam(long id, Team newTeam) throws TeamNotFoundException;
    List<Team> findAll();
    Team findById(long id) throws TeamNotFoundException;
}
