package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.Player;
import com.svalero.happDeporte.exception.PlayerNotFoundException;

import java.util.List;

/** 2) Capa donde va a estar la lógica, tendremos una interface por cada clase Java del domain
 * con los métodos aquí estará el grueso de la aplicación
 */
public interface PlayerService {

    Player addPlayer(Player player);
    void deletePlayer(long id) throws PlayerNotFoundException;
    Player modifyPlayer(long id, Player player) throws PlayerNotFoundException;
    List<Player> findAll();
    Player findById(long id) throws PlayerNotFoundException;

//    List<Player> findByUserInPlayerAndSexAndActive(long UserInPlayer, char Sex, boolean Active);
}
