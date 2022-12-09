package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.Match;
import com.svalero.happDeporte.exception.MatchNotFoundException;

import java.util.List;

public interface MatchService {

    /** 2) Capa donde va a estar la lógica, tendremos una interface por cada clase Java del domain
     * con los métodos aquí estará el grueso de la aplicación
     */

    Match addMatch(Match match);
    void deteteMatch(long id) throws MatchNotFoundException;
    Match modifyMatch(long id, Match match) throws MatchNotFoundException;
    List<Match> findAll();
    Match findById(long id) throws MatchNotFoundException;
}
