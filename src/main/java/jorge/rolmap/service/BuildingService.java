package jorge.rolmap.service;

import java.util.List;
import jorge.rolmap.model.Building;
import jorge.rolmap.util.constants.BuildingType;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;

public interface BuildingService {

    List<Building> getBuildings();
    Building createBuilding(String name, String description, String mapUrl, BuildingType buildingType, Integer placeId) throws InstanceNotFoundException, InvalidArgumentException;
    Building updateBuilding(Integer id, String name, String description, String mapUrl, BuildingType buildingType) throws InvalidArgumentException, InstanceNotFoundException;
    void removeBuilding(Integer id) throws InstanceNotFoundException;

}
