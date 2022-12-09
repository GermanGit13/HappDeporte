package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.Clothes;
import com.svalero.happDeporte.exception.ClothesNotFoundException;
import com.svalero.happDeporte.repository.ClothesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** 3) Para implementar la interface de cada service
 * @Service: Para que spring boot sepa que es la capa del service y donde está la lógica
 */
@Service
public class ClothesServiceImpl implements ClothesService{

    /**
     * @Autowired: Para autoconectar con la BBDD
     * le pasamos el repository (DAO) y el nombre que asociamos asi la tendra acceso a todos los métodos del repositorio
     */
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Clothes addClothes(Clothes clothes) {
        return clothesRepository.save(clothes);
    }

    @Override
    public void deleteClothes(long id) throws ClothesNotFoundException {
        Clothes clothes = clothesRepository.findById(id)
                .orElseThrow(ClothesNotFoundException::new);
        clothesRepository.delete(clothes);
    }

    @Override
    public Clothes modifyClothes(long id, Clothes newclothes) throws ClothesNotFoundException {
        Clothes modifiedClothes = clothesRepository.findById(id)
                .orElseThrow(ClothesNotFoundException::new);
        newclothes.setId(id);
        modelMapper.map(newclothes, modifiedClothes);
        return clothesRepository.save(modifiedClothes);
    }

    @Override
    public List<Clothes> findAll() {
        return clothesRepository.findAll();
    }

    @Override
    public Clothes findById(long id) throws ClothesNotFoundException {
        return clothesRepository.findById(id)
                .orElseThrow(ClothesNotFoundException::new);
    }
}
