package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.Player;
import com.svalero.happDeporte.domain.User;
import com.svalero.happDeporte.exception.PlayerNotFoundException;
import com.svalero.happDeporte.exception.UserNotFoundException;
import com.svalero.happDeporte.repository.PlayerRepository;
import com.svalero.happDeporte.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** 3) Para implementar la interface de cada service
 * @Service: Para que spring boot sepa que es la capa del service y donde está la lógica
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    /**
     * @Autowired: Para autoconectar con la BBDD
     * le pasamos el repository (DAO) y el nombre que asociamos asi la tendra acceso a todos los métodos del repositorio
     */
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private UserRepository userRepository; //Para poder hacerme con el id de un User y asociarlo al player
    @Autowired
    private ModelMapper modelMapper; //Mapear entre listas

    @Override
    public Player addPlayer(Player player, long userId) throws UserNotFoundException {
        Player newPlayer = player; //Creamos un objeto Player
        User user = userRepository.findById(userId) //Para buscar el usuario si existe
                //User user = UserRepository.findById(userId) //Para buscar el usuario que existe en la relacion cuando nos viene por objeto y no por URL
                .orElseThrow(UserNotFoundException::new);
        newPlayer.setUserInPlayer(user); //El bus nuevo esta relacionado con la linea x

        return playerRepository.save(newPlayer); //conectamos con la BBDD mediante el repositorio
    }

    @Override
    public void deletePlayer(long id) throws PlayerNotFoundException {
        Player player = playerRepository.findById(id) //recogemos el user en concreto si existe, sino saltara la excepción
                .orElseThrow(PlayerNotFoundException::new);
        playerRepository.delete(player); //Si existe y llega aquí lo borramos
    }

    @Override
    public Player modifyPlayer(long id, Player newPlayer) throws PlayerNotFoundException {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(PlayerNotFoundException::new);
        newPlayer.setId(id); // Para añadirle el id, sino no viene en el cuerpo

        modelMapper.map(newPlayer, existingPlayer);
        return playerRepository.save(existingPlayer);
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(long id) throws PlayerNotFoundException {
        return playerRepository.findById(id)
                .orElseThrow(PlayerNotFoundException::new);
    }

    @Override
    public List<Player> findByDorsalAndActive(String dorsal, boolean active) {
        return playerRepository.findByDorsalAndActive(dorsal, active);
    }

    /**
     * Para buscar jugadores por usuario
     * @param user
     * @return
     */
//    @Override
//    public List<Player> findByUser(User user) {
//        return playerRepository.findByUserInPlayer(user);
//    }
//
//    @Override
//    public List<Player> findByUser(User user, boolean active) {
//        return playerRepository.findByUserInPlayerAndActive(user, active);
//    }
}
