package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.User;
import com.svalero.happDeporte.exception.UserNotFoundException;
import com.svalero.happDeporte.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** 3) Para implementar la interface de cada service
 * @Service: Para que spring boot sepa que es la capa del service y donde está la lógica
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * @Autowired: Para autoconectar con la BBDD
     * le pasamos el repository (DAO) y el nombre que asociamos asi la tendra acceso a todos los métodos del repositorio
     */
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper; //Mapear entre listas

    @Override
    public User addUser(User user) {
        return userRepository.save(user); //conectamos con la BBDD mediante el repositorio
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    //TODO revisar la excepción
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
