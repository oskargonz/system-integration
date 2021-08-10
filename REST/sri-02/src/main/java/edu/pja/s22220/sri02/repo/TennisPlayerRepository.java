package edu.pja.s22220.sri02.repo;

import edu.pja.s22220.sri02.model.TennisPlayer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//Interfejs dziedziczy po CrudRepository z biblioteki Spring Data JPA. CrudRepository<typ encji (która ma być obsługiwana przez repozytorium), typ klucza>
public interface TennisPlayerRepository extends CrudRepository<TennisPlayer, Long> {
    //Nadpisanie metody findAll(), żeby zwracała listę obiektów zamiast Iterable
    List<TennisPlayer> findAll();
}
