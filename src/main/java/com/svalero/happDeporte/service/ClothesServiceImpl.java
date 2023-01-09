package com.svalero.happDeporte.service;

import com.svalero.happDeporte.domain.Clothes;
import com.svalero.happDeporte.domain.Player;
import com.svalero.happDeporte.exception.ClothesNotFoundException;
import com.svalero.happDeporte.exception.PlayerNotFoundException;
import com.svalero.happDeporte.repository.ClothesRepository;
import com.svalero.happDeporte.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.svalero.happDeporte.Util.Constants.PRICE_EQUIPMENT;
import static com.svalero.happDeporte.Util.Constants.PRICE_SWEATSHIRT;

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
    private PlayerRepository playerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Clothes addClothes(Clothes clothes, long playerId) throws PlayerNotFoundException {

        clothes.setPriceEquipment(PRICE_EQUIPMENT);
        clothes.setPriceSweatshirt(PRICE_SWEATSHIRT);
        Player player = playerRepository.findById(playerId) //Para buscar el jugador si existe
                .orElseThrow(PlayerNotFoundException::new);
        clothes.setPlayerInClothes(player); //El bus nuevo esta relacionado con la linea x

        return clothesRepository.save(clothes); //conectamos con la BBDD mediante el repositorio
    }

    @Override
    public Clothes addClothes(Clothes clothes) {
        clothes.setPriceEquipment(PRICE_EQUIPMENT);
        clothes.setPriceSweatshirt(PRICE_SWEATSHIRT);

        return clothesRepository.save(clothes);
    }

//    @Override
//    public Clothes addClothes(Clothes clothes) throws UserNotFoundException, PlayerNotFoundException {
////        User user = userRepository.findById(clothes.getUserInClothes())
////                .orElseThrow(UserNotFoundException::new);
//
//
//        Player player = playerRepository.findById(clothes.getPlayerInClothes())
//                        .orElseThrow(PlayerNotFoundException::new);
////
////        clothes.setUserInClothes(user);
//        clothes.setPlayerInClothes(player);
//        clothes.setPriceEquipment(PRICE_EQUIPMENT);
//        clothes.setPriceSweatshirt(PRICE_SWEATSHIRT);
//        return clothesRepository.save(clothes);
//    }

//    @Override
//    public Clothes addClothes(Clothes clothes) {
//        clothes.setPriceEquipment(PRICE_EQUIPMENT);
//        clothes.setPriceSweatshirt(PRICE_SWEATSHIRT);
//        return clothesRepository.save(clothes);
//    }

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
