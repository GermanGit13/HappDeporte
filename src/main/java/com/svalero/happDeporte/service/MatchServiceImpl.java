package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.Match;
import com.svalero.happDeporte.domain.Player;
import com.svalero.happDeporte.domain.Team;
import com.svalero.happDeporte.domain.User;
import com.svalero.happDeporte.exception.MatchNotFoundException;
import com.svalero.happDeporte.exception.TeamNotFoundException;
import com.svalero.happDeporte.exception.UserNotFoundException;
import com.svalero.happDeporte.repository.MatchRepository;
import com.svalero.happDeporte.repository.TeamRepository;
import com.svalero.happDeporte.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** 3) Para implementar la interface de cada service
 * @Service: Para que spring boot sepa que es la capa del service y donde está la lógica
 */
@Service
public class MatchServiceImpl implements MatchService{

    /**
     * @Autowired: Para autoconectar con la BBDD
     * le pasamos el repository (DAO) y el nombre que asociamos asi la tendra acceso a todos los métodos del repositorio
     */
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Match addMatch(Match match, long teamId) throws TeamNotFoundException {
        Match newMatch = match; //Creamos un objeto Player
        Team team = teamRepository.findById(teamId) //Para buscar el usuario si existe
                //User user = UserRepository.findById(userId) //Para buscar el usuario que existe en la relacion cuando nos viene por objeto y no por URL
                .orElseThrow(TeamNotFoundException::new);
        newMatch.setTeamInMatch(team); //El bus nuevo esta relacionado con la linea x

        return matchRepository.save(newMatch); //conectamos con la BBDD mediante el repositorio
    }

    @Override
    public void deteteMatch(long id) throws MatchNotFoundException {
        Match match = matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);
        matchRepository.delete(match);
    }

    @Override
    public Match modifyMatch(long id, Match newMatch) throws MatchNotFoundException {
        Match existingMatch = matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);
        newMatch.setId(id);

        modelMapper.map(newMatch, existingMatch);
        return matchRepository.save(existingMatch);
    }

    @Override
    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public Match findById(long id) throws MatchNotFoundException {
        return matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);
    }
}
