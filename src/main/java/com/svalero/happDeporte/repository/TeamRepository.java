package com.svalero.happDeporte.repository;

import com.svalero.happDeporte.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** 1) Son los métodos que conectan con la BBDD
 * @Repository para decirle que es un DAO y que extiende de CrudRepository
 * interface: hacemos interface con la anotación específica
 * así solo con escribir el nombre de los métodos, sprinboot sabe que métodos son
 * extends CrudRepository<TipoDato, ClaveId>
 */
@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    /**
     * Query Methods: Métodos de las sentencias SQL con el nombre ya resuelve la consulta
     */

}
