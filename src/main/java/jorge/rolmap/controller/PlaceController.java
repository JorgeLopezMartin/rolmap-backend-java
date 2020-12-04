package jorge.rolmap.controller;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jorge.rolmap.controller.util.ControllerUtils;
import jorge.rolmap.controller.util.requests.places.PlacePost;
import jorge.rolmap.controller.util.requests.places.PlacePut;
import jorge.rolmap.model.Place;
import jorge.rolmap.service.PlaceService;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for place related content
 */
@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    /**
     * Finds all places if the user has permissions needed
     * @return place list with status code
     */
    @GetMapping("")
    @ApiOperation(value="Find all place", notes = "Place reading permission is needed.", response = Place[].class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "PLace retrieved successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot access square data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> getPlaces() {
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(placeService.getPlaces()));
    }

    /**
     * Creares a new place if the user has permission needed
     * @param placePost: object including new place info
     * @param bindingResult: allows to check if there was a validation error
     * @return created place with status code
     */
    @PostMapping("")
    @ApiOperation(value="Create a new place", notes = "Place writing permission is needed.", response = Place.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Place created successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot create place data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> postPlace(@RequestBody @Valid PlacePost placePost, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.validationErrorHandler(bindingResult);
        }
        try {
            Place place = placeService.createPlace(placePost.getName(), placePost.getDescription(), placePost.getMapUrl(), placePost.getPlaceType(), placePost.getSquareId());
            return ResponseEntity.status(HttpStatus.CREATED).body(new Gson().toJson(place));
        } catch (InvalidArgumentException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.BAD_REQUEST);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates a place's info if the user has permissions needed
     * @param id: place identifier
     * @param placePut: object including place info
     * @param bindingResult: allows to check if there was a validation error
     * @return updated place with status code
     */
    @PutMapping("/{placeId}")
    public ResponseEntity<String> updatePlace(@PathVariable("placeId") Integer id, @RequestBody @Valid PlacePut placePut, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || id == null) {
            return this.validationErrorHandler(bindingResult);
        }
        try {
            Place place = placeService.updatePlace(id, placePut.getName(), placePut.getDescripcion(), placePut.getMapUrl(), placePut.getPlaceType());
            return ResponseEntity.status(HttpStatus.CREATED).body(new Gson().toJson(place));
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.NOT_FOUND);
        } catch (InvalidArgumentException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a character if the user has permissions needed
     * @param id: identifier of character to delete
     * @return status code
     */
    @DeleteMapping("/{placeId}")
    @ApiOperation(value="Remove a place", notes = "Place deleting permission is needed.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Place deleted successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot delete place data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> deletePlace(@PathVariable("placeId") Integer id) {
        try {
            placeService.deletePlace(id);
            return ResponseEntity.ok(null);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<String> validationErrorHandler(BindingResult bindingResult) {
        return new ResponseEntity<String>(ControllerUtils.parseError(new InvalidArgumentException(bindingResult.getFieldError().getField())), HttpStatus.BAD_REQUEST);
    }

}
