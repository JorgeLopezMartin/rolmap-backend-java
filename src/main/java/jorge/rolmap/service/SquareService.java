package jorge.rolmap.service;

import java.math.BigDecimal;
import java.util.List;
import jorge.rolmap.model.Square;
import jorge.rolmap.util.constants.Environment;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;

public interface SquareService {

    List<Square> getSquares();
    Square findSquare(Integer integer) throws InstanceNotFoundException;
    Square createSquare(BigDecimal latitude, BigDecimal longitude, Environment environment) throws InvalidArgumentException;
    Square updateSquare(Integer id, BigDecimal latitude, BigDecimal longitude, Environment environment) throws InstanceNotFoundException, InvalidArgumentException;
    void removeSquare(Integer id) throws InstanceNotFoundException;

}
