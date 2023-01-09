package com.svalero.happDeporte.repository;

import com.svalero.happDeporte.domain.User;
import com.svalero.happDeporte.exception.UserNotFoundException;
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
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Query Methods: Métodos de las sentencias SQL con el nombre ya resuelve la consulta
     */

    List<User> findAll();
//    User findById(long id) throws UserNotFoundException;
    List<User> findAllByCoach(boolean coach);
    List<User> findByRol(String rol);
    User findByUsername(String username);

    List<User> findByRolAndAndCoach(String rol, boolean coach);

}
