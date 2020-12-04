package jorge.rolmap.controller;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jorge.rolmap.controller.util.ControllerUtils;
import jorge.rolmap.controller.util.requests.characters.CharacterPost;
import jorge.rolmap.controller.util.requests.characters.CharacterPut;
import jorge.rolmap.service.CharacterService;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jorge.rolmap.model.Character;

import javax.validation.Valid;

/**
 * Controller for character related content
 */
@RestController
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    /**
     * Finds all characters if the user has permissions needed
     * @return character list with status code
     */
    @GetMapping("")
    @ApiOperation(value="Find all characters", notes = "Character reading permission is needed.", response = Character[].class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Character retrieved successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot access square data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> getCharacters() {
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(characterService.getCharacters()));
    }

    /**
     * Creares a new character if the user has permission needed
     * @param characterPost: object including new character info
     * @param bindingResult: allows to check if there was a validation error
     * @return created character with status code
     */
    @PostMapping("")
    @ApiOperation(value="Create a new character", notes = "Character writing permission is needed.", response = Character.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Character created successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot create character data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> postCharacters(@RequestBody @Valid CharacterPost characterPost, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.validationErrorHandler(bindingResult);
        }
        try {
            Character character = characterService.createCharacter(characterPost.getName(), characterPost.getDescription(), characterPost.getPortrait());
            return ResponseEntity.status(HttpStatus.CREATED).body(new Gson().toJson(character));
        } catch (InvalidArgumentException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates a character's info if the user has permissions needed
     * @param id: character identifier
     * @param characterPut: object including character info
     * @param bindingResult: allows to check if there was a validation error
     * @return updated character with status code
     */
    @PutMapping("/{characterId}")
    @ApiOperation(value="Update a character", notes = "Character updating permission is needed.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Character updated successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot update character data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> updateCharacter(@PathVariable("characterId") Integer id, @RequestBody @Valid CharacterPut characterPut, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || id == null) {
            return this.validationErrorHandler(bindingResult);
        }
        try {
            Character character = characterService.updateCharacter(id, characterPut.getName(), characterPut.getDescription(), characterPut.getPortrait());
            return ResponseEntity.status(HttpStatus.CREATED).body(new Gson().toJson(character));
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
    @DeleteMapping("/{characterId}")
    @ApiOperation(value="Remove a character", notes = "Character deleting permission is needed.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Character deleted successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot detele character data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> deteleCharacter(@PathVariable("characterId") Integer id) {
        try {
            characterService.removeCharacter(id);
            return ResponseEntity.ok(null);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<String> validationErrorHandler(BindingResult bindingResult) {
        return new ResponseEntity<String>(ControllerUtils.parseError(new InvalidArgumentException(bindingResult.getFieldError().getField())), HttpStatus.BAD_REQUEST);
    }

}
