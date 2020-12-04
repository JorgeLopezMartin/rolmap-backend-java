package jorge.rolmap.model;

import jorge.rolmap.util.constants.BuildingType;

import javax.persistence.*;

@Entity
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String mapUrl;
    private BuildingType buildingType;

    @ManyToOne
    @JoinColumn(name="place_id", nullable = false)
    private Place place;

    public Building() {
    }

    public Building(Integer id, String name, String description, String mapUrl, BuildingType buildingType, Place place) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mapUrl = mapUrl;
        this.buildingType = buildingType;
        this.place = place;
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

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
