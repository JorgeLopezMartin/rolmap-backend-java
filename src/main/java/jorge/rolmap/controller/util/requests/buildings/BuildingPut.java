package jorge.rolmap.controller.util.requests.buildings;

import jorge.rolmap.util.constants.BuildingType;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
public class BuildingPut {

    @NotEmpty String name;
    @NotEmpty String description;
    String mapUrl;
    @NotNull BuildingType buildingType;
    @NotNull Integer buildingId;

    public BuildingPut() {
    }

    public BuildingPut(@NotEmpty String name, @NotEmpty String description, String mapUrl, @NotNull BuildingType buildingType, @NotNull Integer buildingId) {
        this.name = name;
        this.description = description;
        this.mapUrl = mapUrl;
        this.buildingType = buildingType;
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }
}
