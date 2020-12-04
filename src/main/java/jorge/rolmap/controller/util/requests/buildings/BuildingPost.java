package jorge.rolmap.controller.util.requests.buildings;

import jorge.rolmap.util.constants.BuildingType;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
public class BuildingPost {

    @NotEmpty String name;
    @NotEmpty String description;
    String mapUrl;
    @NotNull BuildingType buildingType;
    @NotNull Integer placeId;

    public BuildingPost() {
    }

    public BuildingPost(@NotEmpty String name, @NotEmpty String description, String mapUrl, @NotNull BuildingType buildingType, @NotNull Integer placeId) {
        this.name = name;
        this.description = description;
        this.mapUrl = mapUrl;
        this.buildingType = buildingType;
        this.placeId = placeId;
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

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }
}
