package com.svalero.happDeporte.controller;

import com.svalero.happDeporte.domain.Clothes;
import com.svalero.happDeporte.exception.ClothesNotFoundException;
import com.svalero.happDeporte.exception.ErrorMessage;
import com.svalero.happDeporte.exception.PlayerNotFoundException;
import com.svalero.happDeporte.service.ClothesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.svalero.happDeporte.Util.Literal.LITERAL_BEGIN_ADD;
import static com.svalero.happDeporte.Util.Literal.LITERAL_END_ADD;

/** 4) Las clases que expongan la lógica de la Aplicación al exterior
 * parecido a los jsp antiguos, capa visible
 * @RestController: para que spring boot sepa que es la capa que se ve al exterior
 */
@RestController
public class ClothesController {

    /**
     * Llamamos al ClothesService el cual llama al ClothesRepository y a su vez este llama a la BBDD
     */
    @Autowired
    private ClothesService clothesService;
    private final Logger logger = LoggerFactory.getLogger(ClothesController.class);

    /**
     * ResponseEntity<Clothes>: Devolvemos el objeto y un 201
     * @PostMapping("/clothes"): Método para dar de alta en la BBDD clothes
     * @RequestBody: Los datos van en el cuerpo de la llamada como codificados
     * @Valid Para decir que valide los campos a la hora de añadir un nuevo objeto,  los campos los definidos en el domain de que forma no pueden ser introducidos o dejados en blanco por ejemplo en la BBDD
     */
    @PostMapping("/clothes")
    @Validated
    public ResponseEntity<Clothes> addClothes(@Valid @RequestBody Clothes clothes) {
        logger.debug(LITERAL_BEGIN_ADD + "Clothes");
        Clothes newClothes = clothesService.addClothes(clothes);
        logger.debug(LITERAL_END_ADD + "Clothes");
        return new ResponseEntity<>(newClothes, HttpStatus.CREATED);
    }

    /**
     * @ExceptionHandler(ClothesNotFoundException.class): manejador de excepciones, recoge la que le pasamos por parametro en este caso ClothesNotFoundException.class
     * ResponseEntity<?>: Con el interrogante porque no sabe que nos devolver
     * @return
     */
    @ExceptionHandler(ClothesNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleClothesNotFoundException(ClothesNotFoundException cnfe) {
        logger.error(cnfe.getMessage(), cnfe); //Mandamos la traza de la exception al log, con su mensaje y su traza
        //cnfe.printStackTrace(); //Traza por consola del error
        cnfe.printStackTrace(); //Para la trazabilidad de la exception
        ErrorMessage errorMessage = new ErrorMessage(404, cnfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND); // le pasamos el error y el 404 de not found
    }

    /** Capturamos la excepcion para las validaciones y así devolvemos un 400 Bad Request alguien llama a la API de forma incorrecta
     *@ExceptionHandler(MethodArgumentNotValidException.class) Para capturar la excepcion de las validaciones que hacemos al dar de alta un bus
     * le pasamos un mesnaje personalizado de ErrorMessage
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        logger.error(manve.getMessage(), manve); //Mandamos la traza de la exception al log, con su mensaje y su traza
        manve.printStackTrace(); //Para la trazabilidad de la exception
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
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST); // le pasamos el error y el 400 de not found
    }

    /**
     * Lo usamos para contralar las excepciones en general para pillar los errors 500
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        logger.error(exception.getMessage(), exception); //Mandamos la traza de la exception al log, con su mensaje y su traza
        //exception.printStackTrace(); //Para la trazabilidad de la exception
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error"); //asi no damos pistas de como está programa como si pasaba usando e.getMessage()
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR); // le pasamos el error y el 500 error en el servidor no controlado, no sé que ha pasado jajaja
    }
}
