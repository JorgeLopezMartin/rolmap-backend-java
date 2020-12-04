package jorge.rolmap.controller.util.requests.places;

import jorge.rolmap.util.constants.PlaceType;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
public class PlacePost {

    @NotEmpty String name;
    @NotEmpty String description;
    String mapUrl;
    @NotNull PlaceType placeType;
    @NotNull Integer squareId;

    public PlacePost() {
    }

    public PlacePost(@NotEmpty String name, @NotEmpty String description, String mapUrl, @NotNull PlaceType placeType, @NotNull Integer squareId) {
        this.name = name;
        this.description = description;
        this.mapUrl = mapUrl;
        this.placeType = placeType;
        this.squareId = squareId;
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

    public PlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }

    public Integer getSquareId() {
        return squareId;
    }

    public void setSquareId(Integer squareId) {
        this.squareId = squareId;
    }
}
