package jorge.rolmap.model;

import jorge.rolmap.util.constants.PlaceType;

import javax.persistence.*;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String mapUrl;
    private PlaceType placeType;

    @ManyToOne
    @JoinColumn(name="square_id", nullable = false)
    private Square square;

    public Place() {
    }

    public Place(Integer id, String name, String description, String mapUrl, PlaceType placeType, Square square) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mapUrl = mapUrl;
        this.placeType = placeType;
        this.square = square;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public PlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }
}
