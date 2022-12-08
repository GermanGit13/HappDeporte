package com.svalero.happDeporte.controller;

import com.svalero.happDeporte.domain.Player;
import com.svalero.happDeporte.domain.User;
import com.svalero.happDeporte.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.svalero.happDeporte.Util.Literal.*;

/** 4) Las clases que expongan la lógica de la Aplicación al exterior
 * parecido a los jsp antiguos, capa visible
 * @RestController: para que spring boot sepa que es la capa que se ve al exterior
 */
@RestController
public class PlayerController {

    /**
     * Llamamos al UserService el cual llama al UserRepository y a su vez este llama a la BBDD
     */
    @Autowired
    private PlayerService playerService;
    private final Logger logger = LoggerFactory.getLogger(PlayerController.class); //Creamos el objeto capaz de pintar las trazas en el log y lo asociamos a la clase que queremos controlar

    /**
     * ResponseEntity<Player>: Devolvemos el objeto y un 201
     * @PostMapping("/players"): Método para dar de alta en la BBDD players
     * @RequestBody: Los datos van en el cuerpo de la llamada como codificados
     * @Valid Para decir que valide los campos a la hora de añadir un nuevo objeto,  los campos los definidos en el domain de que forma no pueden ser introducidos o dejados en blanco por ejemplo en la BBDD
     */
    @PostMapping("/players")
    public ResponseEntity<Player> addPlayer(@Valid @RequestBody Player player) {
        logger.debug(LITERAL_BEGIN_ADD); //Indicamos que el método ha sido llamado y lo registramos en el log
        Player newPlayer = playerService.addPlayer(player);
        logger.debug(LITERAL_END_ADD); //Indicamos que el método ha sido llamado y lo registramos en el log
        //return ResponseEntity.status(200).body(newUser); Opcion a mano le pasamos el código y los datos del Objeto creado
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED); //Tambien podemos usar la opción rápida
    }
}
