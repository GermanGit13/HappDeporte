package com.svalero.happDeporte.controller;

import com.svalero.happDeporte.domain.User;
import com.svalero.happDeporte.exception.ErrorMessage;
import com.svalero.happDeporte.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 4) Las clases que expongan la lógica de la Aplicación al exterior
 * parecido a los jsp antiguos, capa visible
 * @RestController: para que spring boot sepa que es la capa que se ve al exterior
 */
@RestController
public class UserController {

    /**
     * Llamamos al BusService el cual llama al BusRepository y a su vez este llama a la BBDD
     */
    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class); //Creamos el objeto capaz de pintar las trazas en el log y lo asociamos a la clase que queremos controlar

    /**
     * ResponseEntity<User>: Devolvemos el objeto y un 201
     * @PostMapping("/user"): Método para dar de alta en la BBDD user
     * @RequestBody: Los datos van en el cuerpo de la llamada como codificados
     * @Valid Para decir que valide los campos a la hora de añadir un nuevo objeto,  los campos los definidos en el domain de que forma no pueden ser introducidos o dejados en blanco por ejemplo en la BBDD
     */
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        //return ResponseEntity.status(200).body(newUser); Opcion a mano le pasamos el código y los datos del Objeto creado
        return new ResponseEntity<>(newUser, HttpStatus.CREATED); //Tambien podemos usar la opción rápida
    }

    /**
     * ResponseEntity: Para devolver una respuesta con los datos y el código de estado de forma explícita
     * ResponseEntity.ok: Devuelve un 200 ok con los datos buscados
     * @GetMapping("/users"): URL donde se devolverán los datos
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUser() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }


    /** Capturamos la excepcion para las validaciones y así devolvemos un 400 Bad Request alguien llama a la API de forma incorrecta
     *@ExceptionHandler(MethodArgumentNotValidException.class) Para capturar la excepcion de las validaciones que hacemos al dar de alta un bus
     * le pasamos un mesnaje personalizado de ErrorMessage
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        logger.error(manve.getMessage(), manve); //Mandamos la traza de la exception al log, con su mensaje y su traza
        //manve.printStackTrace(); //Para la trazabilidad de la exception
        /**
         * Código que extrae que campos no han pasado la validación
         */
        Map<String, String> errors = new HashMap<>(); //Montamos un Map de errores
        manve.getBindingResult().getAllErrors().forEach(error -> { //para la exception manve recorremos todos los campos
            String fieldName = ((FieldError) error).getField(); //Extraemos con getField el nombre del campo que no ha pasado la validación
            String message = error.getDefaultMessage(); // y el mensaje asociado
            errors.put(fieldName, message);
        });
        /**
         * FIN Código que extrae que campos no han pasado la validación
         */

        ErrorMessage errorMessage = new ErrorMessage(400, "Bad Request", errors); //Podemos pasarle código y mensaje o añadir los códigos de error del Map sacamos los campos que han fallado
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST); // le pasamos el error y el 404 de not found
    }

    /**
     * Lo usamos para contralar las excepciones ne general para pillar los errors 500
     * @param exception
     * @return
     * La dejamos comentada porque el bar request que ofrece Spring Boot de momento es mejor hasta tener la parte de los logs
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        logger.error(exception.getMessage(), exception); //Mandamos la traza de la exception al log, con su mensaje y su traza
        //exception.printStackTrace(); //Para la trazabilidad de la exception
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error"); //asi no damos pistas de como está programa como si pasaba usando e.getMessage()
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR); // le pasamos el error y el 500 error en el servidor no controlado, no sé que ha pasado jajaja
    }

}
