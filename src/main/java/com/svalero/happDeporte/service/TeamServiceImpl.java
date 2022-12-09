package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.Team;
import com.svalero.happDeporte.exception.TeamNotFoundException;
import com.svalero.happDeporte.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.svalero.happDeporte.Util.Constants.QUOTA;

/** 3) Para implementar la interface de cada service
 * @Service: Para que spring boot sepa que es la capa del service y donde está la lógica
 */
@Service
public class TeamServiceImpl implements TeamService {

    /**
     * @Autowired: Para autoconectar con la BBDD
     * le pasamos el repository (DAO) y el nombre que asociamos asi la tendra acceso a todos los métodos del repositorio
     */
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Team addTeam(Team team) {
        team.setCuota(QUOTA);
        teamRepository.save(team);

        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(long id) throws TeamNotFoundException {
        Team team = teamRepository.findById(id)
                .orElseThrow(TeamNotFoundException::new);
        teamRepository.delete(team);
    }

    @Override
    public Team modifyTeam(long id, Team newTeam) throws TeamNotFoundException {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(TeamNotFoundException::new);
        newTeam.setId(id);
        modelMapper.map(newTeam, existingTeam);

        return teamRepository.save(existingTeam);
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team findById(long id) throws TeamNotFoundException {
        return teamRepository.findById(id)
                .orElseThrow(TeamNotFoundException::new);
    }
}