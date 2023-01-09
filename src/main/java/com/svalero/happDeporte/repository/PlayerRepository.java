package com.svalero.happDeporte.repository;

import com.svalero.happDeporte.domain.Player;
import com.svalero.happDeporte.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 1) Son los métodos que conectan con la BBDD
 * @Repository para decirle que es un DAO y que extiende de CrudRepository
 * interface: hacemos interface con la anotación específica
 * así solo con escribir el nombre de los métodos, sprinboot sabe que métodos son
 * extends CrudRepository<TipoDato, ClaveId>
 */
@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    /**
     * Query Methods: Métodos de las sentencias SQL con el nombre ya resuelve la consulta
     */

    List<Player> findAll();
    List<Player> findByUserInPlayer(User user); //Para poder recibir el objeto User
//    List<Player> findByUserInPlayerAndActive(User user, boolean active);
    List<Player> findByUserInPlayerAndSexAndActive(long userInPlayer, char sex, boolean active);

//    List<Player> findByUserInPlayer();
//    List<Player> findByDni();
}
