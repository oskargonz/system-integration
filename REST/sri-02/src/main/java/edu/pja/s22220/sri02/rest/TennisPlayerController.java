package edu.pja.s22220.sri02.rest;

import edu.pja.s22220.sri02.dto.TennisPlayerDto;
import edu.pja.s22220.sri02.model.TennisPlayer;
import edu.pja.s22220.sri02.repo.TennisPlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Adnotacja Springa - metody będą wywoływane przy pomocy odpowiednich żądań HTTP do aplikacji
@RestController
//Wskazanie głównego adresu URL, pod którym metody będą dostępne
@RequestMapping("/api/tennisplayers")
public class TennisPlayerController {
    private TennisPlayerRepository tennisPlayerRepository;
    private ModelMapper modelMapper;

    public TennisPlayerController(TennisPlayerRepository tennisPlayerRepository, ModelMapper modelMapper) {
        this.tennisPlayerRepository = tennisPlayerRepository;
        this.modelMapper = modelMapper;
    }

    //Metody konwertujące pomiędzy entity i dto
    private TennisPlayerDto convertToDto(TennisPlayer e) {
        return modelMapper.map(e, TennisPlayerDto.class);
    }
    private TennisPlayer convertToEntity(TennisPlayerDto dto) {
        return modelMapper.map(dto, TennisPlayer.class);
    }

    //Wskazanie na żądanie GET - to uruchomi metodę
    @GetMapping
    public ResponseEntity<Collection<TennisPlayerDto>> getTennisPlayers() {
        //Pobranie listy wszystkich obiektów z bazy danych za pomocą repo
        List<TennisPlayer> allTennisPlayers = tennisPlayerRepository.findAll();
        //Konwersja pobranej listy encji na listę obiektów DTO
        List<TennisPlayerDto> result = allTennisPlayers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Użycie nawiasów klamrowych oznacza, że ta część adresu będzie wykorzystana jako parametr metody, opisanej w jej sygnaturze adnotacją @PathVariable.
    @GetMapping("/{tpId}")
    public ResponseEntity<TennisPlayerDto>
    getTennisPlayerById(@PathVariable Long tpId) {
        //Sprawdzenie czy w bazie danych jest obiekt o wskazanym ID. Jeśli jest to zwraca obiekt DTO
        Optional<TennisPlayer> tp =
                tennisPlayerRepository.findById(tpId);
        if(tp.isPresent()) {
            TennisPlayerDto tennisPlayerDto = convertToDto(tp.get());
            return new ResponseEntity<>(tennisPlayerDto,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,
                    HttpStatus.NOT_FOUND);
        }
    }

    //Dane przekazane w ciele żądania są mapowane na obiekt z użyciem adnotacji @RequestBody
    @PostMapping
    public ResponseEntity saveNewTennisPlayer(@RequestBody TennisPlayerDto tp) {
        TennisPlayer entity = convertToEntity(tp);
        tennisPlayerRepository.save(entity);
        HttpHeaders headers = new HttpHeaders();
        //Adres URL nowego obiektu jest tworzony na podstawie adresu bieżącego żądania z dopisanym id
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        headers.add("Location", location.toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{tpId}")
    public ResponseEntity updateTennisPlayer(@PathVariable Long tpId, @RequestBody TennisPlayerDto employeeDto) {
        Optional<TennisPlayer> currentTp =
                tennisPlayerRepository.findById(tpId);
        if(currentTp.isPresent()) {
            employeeDto.setId(tpId);
            TennisPlayer entity = convertToEntity(employeeDto);
            tennisPlayerRepository.save(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{tpId}")
    public ResponseEntity deleteTennisPlayer(@PathVariable Long tpId)
    {
        tennisPlayerRepository.deleteById(tpId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
