package jorge.rolmap.service.impl;

import jorge.rolmap.model.Place;
import jorge.rolmap.model.Square;
import jorge.rolmap.repository.PlaceRepository;
import jorge.rolmap.service.PlaceService;
import jorge.rolmap.service.SquareService;
import jorge.rolmap.util.constants.PlaceType;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    SquareService squareService;

    private void validatePlaceParams(String name, String description, String mapUrl, PlaceType placeType) throws InvalidArgumentException {
        if (name == null || name.isEmpty()) {
            throw new InvalidArgumentException("Invalid place name.");
        }
        if (description == null || description.isEmpty()) {
            throw new InvalidArgumentException("Invalid place description.");
        }
        if (placeType == null) {
            throw new InvalidArgumentException("Invalid place type.");
        }
    }

    @Override
    public List<Place> getPlaces() {
        return (List<Place>) placeRepository.findAll();
    }

    @Override
    public Place createPlace(String name, String description, String mapUrl, PlaceType placeType, Integer squareId) throws InvalidArgumentException, InstanceNotFoundException {
        this.validatePlaceParams(name, description, mapUrl, placeType);
        Place place = new Place();
        place.setName(name);
        place.setDescription(description);
        place.setMapUrl(mapUrl);
        place.setPlaceType(placeType);
        Square square = squareService.findSquare(squareId);
        place.setSquare(square);
        placeRepository.save(place);
        return place;
    }

    @Override
    public Place updatePlace(Integer id, String name, String description, String mapUrl, PlaceType placeType) throws InstanceNotFoundException, InvalidArgumentException {
        Optional<Place> foundPlace = placeRepository.findById(id);
        if (foundPlace.isPresent()) {
            Place place = foundPlace.get();
            place.setName(name);
            place.setDescription(description);
            place.setMapUrl(mapUrl);
            place.setPlaceType(placeType);
            return place;
        } else {
            throw new InstanceNotFoundException("Place " + id);
        }
    }

    @Override
    public void deletePlace(Integer id) throws InstanceNotFoundException {
        Optional<Place> foundPlace = placeRepository.findById(id);
        if (foundPlace.isPresent()) {
            placeRepository.delete(foundPlace.get());
        } else {
            throw new InstanceNotFoundException("Place " + id);
        }
    }

}
