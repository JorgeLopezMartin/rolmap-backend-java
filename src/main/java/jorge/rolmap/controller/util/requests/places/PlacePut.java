package jorge.rolmap.controller.util.requests.places;

import jorge.rolmap.util.constants.PlaceType;
import org.springframework.stereotype.Component;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
public class PlacePut {

    @NotEmpty String name;
    @NotEmpty String descripcion;
    @NotEmpty String mapUrl;
    @NotNull PlaceType placeType;
    @NotNull Integer squareId;

    public PlacePut() {
    }

    public PlacePut(@NotEmpty String name, @NotEmpty String descripcion, @NotEmpty String mapUrl, @NotNull PlaceType placeType, @NotNull Integer squareId) {
        this.name = name;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
