package jorge.rolmap.model;

import jorge.rolmap.util.constants.Environment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Square {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Environment environmentType;

    public Square() {
    }

    public Square(Integer id, BigDecimal latitude, BigDecimal longitude, Environment environmentType) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.environmentType = environmentType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Environment getEnvironmentType() {
        return environmentType;
    }

    public void setEnvironmentType(Environment environmentType) {
        this.environmentType = environmentType;
    }
}
