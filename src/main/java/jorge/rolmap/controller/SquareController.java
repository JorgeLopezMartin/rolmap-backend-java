package jorge.rolmap.controller;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jorge.rolmap.controller.util.ControllerUtils;
import jorge.rolmap.controller.util.requests.squares.SquarePost;
import jorge.rolmap.model.Square;
import jorge.rolmap.service.SquareService;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for square related content
 */
@RestController
@RequestMapping("/square")
public class SquareController {

    @Autowired
    private SquareService squareService;

    /**
     * Finds all squares if the user has permissions needed
     * @return square list
     */
    @GetMapping("")
    @ApiOperation(value="Find all squares", notes = "Square table reading permission is needed.", response = Square[].class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Squares retrieved successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot access square data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> getSquares() {
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(squareService.getSquares()));
    }

    /**
     * Creates a new square if the user has permissions needed
     * @param squarePost: object including new square info
     * @param bindingResult: allows to check if there was a validation error
     * @return new square info json with 201 CREATED status code or error with specific error code
     */
    @PostMapping("")
    @ApiOperation(value="Create a new square", notes = "Square table writing permission is needed.", response = Square.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Square created successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot create square data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> postSquare(@RequestBody @Valid SquarePost squarePost, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.validationErrorHandler(bindingResult);
        }
        try {
            Square square = squareService.createSquare(squarePost.getLatitude(), squarePost.getLongitude(), squarePost.getEnvironment());
            return ResponseEntity.status(HttpStatus.CREATED).body(new Gson().toJson(square));
        } catch (InvalidArgumentException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates a square if the user has permissions needed
     * @param squarePost: object including new square info
     * @param bindingResult: allows to check if there was a validation error
     * @return new square info json with 200 OK status code or error with specific error code
     */
    @PutMapping("/{squareId}")
    @ApiOperation(value="Update a square", notes = "Square table writing permission is needed.", response = Square.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Square created successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot create square data"),
            @ApiResponse(code = 404, message = "Square data not found"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> putSquare(@PathVariable("squareId") Integer id, @RequestBody @Valid SquarePost squarePost, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.validationErrorHandler(bindingResult);
        }
        try {
            Square square = squareService.updateSquare(id, squarePost.getLatitude(), squarePost.getLongitude(), squarePost.getEnvironment());
            return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(square));
        } catch (InvalidArgumentException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.BAD_REQUEST);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a square if the user has permissions needed
     * @param id: Identifier of the square to remove
     * @return result code
     */
    @DeleteMapping("/{squareId}")
    @ApiOperation(value="Remove a square", notes = "Square table deleting permission is needed.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Square deleted successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot create square data"),
            @ApiResponse(code = 404, message = "Square data not found"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> deleteSquare(@PathVariable("squareId") Integer id) {
        try {
            squareService.removeSquare(id);
            return ResponseEntity.ok(null);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<String> validationErrorHandler(BindingResult bindingResult) {
        return new ResponseEntity<String>(ControllerUtils.parseError(new InvalidArgumentException(bindingResult.getFieldError().getField())), HttpStatus.BAD_REQUEST);
    }

}
