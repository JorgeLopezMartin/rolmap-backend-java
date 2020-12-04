package jorge.rolmap.controller.util.requests.squares;

import jorge.rolmap.util.constants.Environment;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Component
public class SquarePost {

    @NotNull BigDecimal latitude;
    @NotNull BigDecimal longitude;
    @NotNull Environment environment;

    public SquarePost(){
    }

    public SquarePost(@NotNull BigDecimal latitude, @NotNull BigDecimal longitude, @NotNull Environment environment) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.environment = environment;
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

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
