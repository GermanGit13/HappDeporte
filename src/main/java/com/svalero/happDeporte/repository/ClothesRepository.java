package com.svalero.happDeporte.repository;

import com.svalero.happDeporte.domain.Clothes;
import jdk.dynalink.linker.LinkerServices;
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
public interface ClothesRepository extends CrudRepository<Clothes, Long> {

    /**
     * Query Methods: Métodos de las sentencias SQL con el nombre ya resuelve la consulta
     */
    List<Clothes> findAll();
}
