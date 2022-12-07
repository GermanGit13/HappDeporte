package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.User;
import com.svalero.happDeporte.exception.UserNotFoundException;

import java.util.List;

/** 2) Capa donde va a estar la lógica, tendremos una interface por cada clase Java del domain
 * con los métodos aquí estará el grueso de la aplicación
 */
public interface UserService {

    User addUser(User user);
    List<User> findAll();

    //TODO revisar la excepcion
    User findByUsername(String username);
    User findById(long id) throws UserNotFoundException;
}
