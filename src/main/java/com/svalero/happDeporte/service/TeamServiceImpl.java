package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.Player;
import com.svalero.happDeporte.domain.Team;
import com.svalero.happDeporte.domain.User;
import com.svalero.happDeporte.exception.TeamNotFoundException;
import com.svalero.happDeporte.exception.UserNotFoundException;
import com.svalero.happDeporte.repository.TeamRepository;
import com.svalero.happDeporte.repository.UserRepository;
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
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Team addTeam(Team team, long userId) throws UserNotFoundException {
        Team newTeam = team; //Creamos un objeto Team
        User user = userRepository.findById(userId) //Para buscar el usuario si existe
                //User user = UserRepository.findById(userId) //Para buscar el usuario que existe en la relacion cuando nos viene por objeto y no por URL
                .orElseThrow(UserNotFoundException::new);
        newTeam.setUserInTeam(user); //El bus nuevo esta relacionado con la linea x

        return teamRepository.save(newTeam); //conectamos con la BBDD mediante el repositorio
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

    @Override
    public List<Team> findByCategory(String category) {
        return teamRepository.findByCategory(category);
    }

    @Override
    public List<Team> findByCategoryAndCompetition(String category, String competition) {
        return teamRepository.findByCategoryAndCompetition(category, competition);
    }

    @Override
    public List<Team> findByCategoryAndCompetitionAndActive(String category, String competition, boolean active) {
        return teamRepository.findByCategoryAndCompetitionAndActive(category, competition, active);
    }
}
