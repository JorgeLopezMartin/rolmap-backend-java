package jorge.rolmap.controller;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jorge.rolmap.controller.util.ControllerUtils;
import jorge.rolmap.controller.util.requests.buildings.BuildingPost;
import jorge.rolmap.controller.util.requests.buildings.BuildingPut;
import jorge.rolmap.model.Building;
import jorge.rolmap.service.BuildingService;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for building related content
 */
@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    /**
     * Finds all buildings if the user has permissions needed
     * @return square list with status code
     */
    @GetMapping(value= "", produces = "application/json")
    @ApiOperation(value="Find all buildings", notes = "Building reading permission is needed.", response = Building[].class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Buildings retrieved successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot access square data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> getBuilding() {
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(buildingService.getBuildings()));
    }

    /**
     * Creates a new building if the user has permissions needed
     * @param buildingPost: object including new building info
     * @param bindingResult: allows to check if there was a validation error
     * @return created building with status code
     */
    @PostMapping("")
    @ApiOperation(value="Create a new building", notes = "Building table writing permission is needed.", response = Building.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Building created successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot create" +
                    " square data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> postBuilding(@RequestBody @Valid BuildingPost buildingPost, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return this.validationErrorHandler(bindingResult);
        }
        try {
            Building building = buildingService.createBuilding(buildingPost.getName(), buildingPost.getDescription(), buildingPost.getMapUrl(), buildingPost.getBuildingType(), buildingPost.getPlaceId());
            return ResponseEntity.status(HttpStatus.CREATED).body(new Gson().toJson(building));
        } catch(InstanceNotFoundException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.NOT_FOUND);
        } catch (InvalidArgumentException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates a building's info if the user has permissions needed
     * @param buildingPut: object including building info
     * @param bindingResult: allows to check if there was a validation error
     * @return updated building with status code
     */
    @PutMapping("/{buildingId}")
    @ApiOperation(value="Update a building", notes = "Building updating permission is needed.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Building updated successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot update building data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> updateBuilding(@PathVariable("buildingId") Integer id, @RequestBody @Valid BuildingPut buildingPut, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || id == null) {
            return this.validationErrorHandler(bindingResult);
        }
        try {
            Building building = buildingService.updateBuilding(id, buildingPut.getName(), buildingPut.getDescription(), buildingPut.getMapUrl(), buildingPut.getBuildingType());
            return ResponseEntity.status(HttpStatus.CREATED).body(new Gson().toJson(building));
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.NOT_FOUND);
        } catch (InvalidArgumentException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a building if the user has permissions needed
     * @param id: identifier of the building to delete
     * @return status code
     */
    @DeleteMapping("/{buildingId}")
    @ApiOperation(value="Remove a building", notes = "Building deleting permission is needed.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Building deleted successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot create square data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> deleteBuilding(@PathVariable("buildingId") Integer id) {
        try {
            buildingService.removeBuilding(id);
            return ResponseEntity.ok(null);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<String> validationErrorHandler(BindingResult bindingResult) {
        return new ResponseEntity<String>(ControllerUtils.parseError(new InvalidArgumentException(bindingResult.getFieldError().getField())), HttpStatus.BAD_REQUEST);
    }

}
