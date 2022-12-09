package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.Clothes;
import com.svalero.happDeporte.exception.ClothesNotFoundException;

import java.util.List;

/** 2) Capa donde va a estar la lógica, tendremos una interface por cada clase Java del domain
 * con los métodos aquí estará el grueso de la aplicación
 */
public interface ClothesService {

    Clothes addClothes(Clothes clothes);
    void deleteClothes(long id) throws ClothesNotFoundException;
    Clothes modifyClothes(long id, Clothes clothes) throws ClothesNotFoundException;
    List<Clothes> findAll();
    Clothes findById(long id) throws ClothesNotFoundException;
}
