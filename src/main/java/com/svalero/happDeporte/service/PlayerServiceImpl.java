package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.Player;
import com.svalero.happDeporte.exception.PlayerNotFoundException;
import com.svalero.happDeporte.repository.PlayerRepository;
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
    private ModelMapper modelMapper; //Mapear entre listas

    @Override
    public Player addPlayer(Player player) {
        return playerRepository.save(player); //conectamos con la BBDD mediante el repositorio
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
        newPlayer.setId(id);

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
}
