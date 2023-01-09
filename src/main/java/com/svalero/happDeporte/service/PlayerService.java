package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.Player;
import com.svalero.happDeporte.domain.User;
import com.svalero.happDeporte.exception.PlayerNotFoundException;
import com.svalero.happDeporte.exception.UserNotFoundException;

import java.util.List;

/** 2) Capa donde va a estar la lógica, tendremos una interface por cada clase Java del domain
 * con los métodos aquí estará el grueso de la aplicación
 */
public interface PlayerService {

    Player addPlayer(Player player, long userId) throws UserNotFoundException;
    void deletePlayer(long id) throws PlayerNotFoundException;
    Player modifyPlayer(long id, Player player) throws PlayerNotFoundException;
    List<Player> findAll();
    Player findById(long id) throws PlayerNotFoundException;

    List<Player> findByDorsalAndActive(String dorsal, boolean active);

//    List<Player> findByUser(User user); // Lista para buscar los Players por user
//    List<Player> findByUser(User user, boolean active); //Lista para buscar jugadores por usuario y activos
}
