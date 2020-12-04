package jorge.rolmap.service.impl;

import jorge.rolmap.model.Square;
import jorge.rolmap.repository.SquareRepository;
import jorge.rolmap.service.SquareService;
import jorge.rolmap.util.constants.Environment;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

@Service
public class SquareServiceImpl implements SquareService {

    @Autowired
    SquareRepository squareRepository;

    private void validateSquareParams(BigDecimal latitude, BigDecimal longitude) throws InvalidArgumentException {
        if (latitude == null || longitude == null) {
            throw new InvalidArgumentException("Invalid square coordinates.");
        }
    }

    @Override
    public List<Square> getSquares() {
        return (List<Square>) squareRepository.findAll();
    }

    @Override
    public Square findSquare(Integer id) throws InstanceNotFoundException {
        Optional<Square> foundSquare = squareRepository.findById(id);
        if (foundSquare.isPresent()) {
            return foundSquare.get();
        } else {
            throw new InstanceNotFoundException("Square " + id);
        }
    }

    @Override
    public Square createSquare(BigDecimal latitude, BigDecimal longitude, Environment environment) throws InvalidArgumentException {
        this.validateSquareParams(latitude, longitude);
        Square square = new Square();
        square.setLatitude(latitude);
        square.setLongitude(longitude);
        square.setEnvironmentType(environment);
        squareRepository.save(square);
        return square;
    }

    @Override
    public Square updateSquare(Integer id, BigDecimal latitude, BigDecimal longitude, Environment environment) throws InstanceNotFoundException, InvalidArgumentException {
        Optional<Square> foundSquare = squareRepository.findById(id);
        if (foundSquare.isPresent()) {
            this.validateSquareParams(latitude, longitude);
            Square square = foundSquare.get();
            square.setLatitude(latitude);
            square.setLongitude(longitude);
            square.setEnvironmentType(environment);
            squareRepository.save(square);
            return square;
        } else {
            throw new InstanceNotFoundException("Square " + id);
        }
    }

    @Override
    public void removeSquare(Integer id) throws InstanceNotFoundException {
        Optional<Square> foundSquare = squareRepository.findById(id);
        if (foundSquare.isPresent()) {
            squareRepository.delete(foundSquare.get());
        } else {
            throw new InstanceNotFoundException("Square " + id);
        }
    }

}
