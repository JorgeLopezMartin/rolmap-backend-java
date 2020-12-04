package jorge.rolmap.service;

import jorge.rolmap.model.Place;
import jorge.rolmap.util.constants.PlaceType;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;

import java.util.List;

public interface PlaceService {

    List<Place> getPlaces();
    Place createPlace(String name, String description, String mapUrl, PlaceType placeType, Integer squareId) throws InvalidArgumentException, InstanceNotFoundException;
    Place updatePlace (Integer id, String name, String description, String mapUrl, PlaceType placeType) throws InstanceNotFoundException, InvalidArgumentException;
    void deletePlace (Integer id) throws InstanceNotFoundException;

}
