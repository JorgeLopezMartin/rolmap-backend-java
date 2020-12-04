package jorge.rolmap.controller;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jorge.rolmap.controller.util.ControllerUtils;
import jorge.rolmap.controller.util.requests.quests.QuestPost;
import jorge.rolmap.controller.util.requests.quests.QuestPut;
import jorge.rolmap.model.Quest;
import jorge.rolmap.service.QuestService;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for quest related content
 */
@RestController
@RequestMapping("/quest")
public class QuestController {

    @Autowired
    private QuestService questService;

    /**
     * Finds all quests if the user has permissions needed
     * @return quest list
     */
    @GetMapping("")
    @ApiOperation(value="Find all quests", notes = "Quest reading permission is needed.", response = Quest[].class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Quests retrieved successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot access quest data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> getQuests() {
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(questService.getQuests()));
    }

    /**
     * Creates a new quest if the user has permissions needed
     * @param questPost: object including new quest info
     * @param bindingResult: allows to check if there was a validation error
     * @return new quest info json with 201 CREATED status code or error with specific error code
     */
    @PostMapping("")
    @ApiOperation(value="Create a new quest", notes = "Quest writing permission is needed.", response = Quest.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Quest created successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot create quest data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> postQuests(@RequestBody @Valid QuestPost questPost, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return this.validationErrorHandler(bindingResult);
        }
        try {
            Quest quest = questService.createQuest(questPost.getName(), questPost.getDescription());
            return ResponseEntity.status(HttpStatus.CREATED).body(new Gson().toJson(quest));
        } catch (InvalidArgumentException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{questId}")
    @ApiOperation(value="Update a quest", notes = "Quest updating permission is needed.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Quest updated successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot update quest data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> updateQuest(@PathVariable("questId") Integer id, @RequestBody @Valid QuestPut questPut, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || id == null) {
            return this.validationErrorHandler(bindingResult);
        }
        try {
            Quest quest = questService.updateQuest(id, questPut.getName(), questPut.getDescription());
            return ResponseEntity.status(HttpStatus.CREATED).body(new Gson().toJson(quest));
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.NOT_FOUND);
        } catch (InvalidArgumentException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{questId}")
    @ApiOperation(value="Remove a quest", notes = "Quest deleting permission is needed.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Quest deleted successfully"),
            @ApiResponse(code = 401, message = "Authentication required"),
            @ApiResponse(code = 403, message = "Forbidden user, cannot create quest data"),
            @ApiResponse(code = 500, message = "Internal server error occurred while retrieving data")
    })
    public ResponseEntity<String> deleteQuest(@PathVariable("questId") Integer id) {
        try {
            questService.deleteQuest(id);
            return ResponseEntity.ok(null);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.NOT_FOUND);
        }
    }


    private ResponseEntity<String> validationErrorHandler(BindingResult bindingResult) {
        return new ResponseEntity<String>(ControllerUtils.parseError(new InvalidArgumentException(bindingResult.getFieldError().getField())), HttpStatus.BAD_REQUEST);
    }

}
