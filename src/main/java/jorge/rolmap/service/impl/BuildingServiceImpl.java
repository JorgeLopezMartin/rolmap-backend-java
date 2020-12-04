package jorge.rolmap.service.impl;

import jorge.rolmap.model.Building;
import jorge.rolmap.repository.BuildingRepository;
import jorge.rolmap.service.BuildingService;
import jorge.rolmap.util.constants.BuildingType;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    BuildingRepository buildingRepository;

    private void validateBuildingParams(String name, String description, String mapUrl, BuildingType buildingType) throws InvalidArgumentException {
        if (name == null || name.isEmpty()) {
            throw new InvalidArgumentException("Invalid building name.");
        }
        if (description == null || description.isEmpty()) {
            throw new InvalidArgumentException("Invalid description.");
        }
        if (mapUrl == null || mapUrl.isEmpty()) {
            throw new InvalidArgumentException("Invalid map url.");
        }
        if (buildingType == null) {
            throw new InvalidArgumentException("Invalid building type.");
        }
    }

    @Override
    public List<Building> getBuildings() {
        return (List<Building>) buildingRepository.findAll();
    }

    @Override
    public Building createBuilding(String name, String description, String mapUrl, BuildingType buildingType) throws InvalidArgumentException {
        this.validateBuildingParams(name, description, mapUrl, buildingType);
        Building building = new Building();
        building.setName(name);
        building.setDescription(description);
        building.setMapUrl(mapUrl);
        building.setBuildingType(buildingType);
        buildingRepository.save(building);
        return building;
    }

    @Override
    public Building updateBuilding(Integer id, String name, String description, String mapUrl, BuildingType buildingType) throws InvalidArgumentException, InstanceNotFoundException {
        Optional<Building> foundBuilding = buildingRepository.findById(id);
        if (foundBuilding.isPresent()) {
            this.validateBuildingParams(name, description, mapUrl, buildingType);
            Building building = foundBuilding.get();
            building.setName(name);
            building.setDescription(description);
            building.setMapUrl(mapUrl);
            building.setBuildingType(buildingType);
            buildingRepository.save(building);
            return building;
        } else {
            throw new InstanceNotFoundException("Building " + id);
        }
    }

    @Override
    public void removeBuilding(Integer id) throws InstanceNotFoundException {
        Optional<Building> foundBuilding = buildingRepository.findById(id);
        if (foundBuilding.isPresent()) {
            buildingRepository.delete(foundBuilding.get());
        } else {
            throw new InstanceNotFoundException("Building " + id);
        }
    }
}
