package jorge.rolmap.controller;

import com.google.gson.Gson;
import jorge.rolmap.controller.util.ControllerUtils;
import jorge.rolmap.controller.util.requests.tasks.TaskPost;
import jorge.rolmap.model.Task;
import jorge.rolmap.service.TaskService;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public ResponseEntity<String> getTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(taskService.getTasks()));
    }

    @PostMapping("")
    public ResponseEntity<String> postTask(@RequestBody @Valid TaskPost taskPost, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.validationErrorHandler(bindingResult);
        }
        try {
            Task task = taskService.createTask(taskPost.getTitle(), taskPost.getDescription(), taskPost.getIsDone());
            return ResponseEntity.status(HttpStatus.CREATED).body(new Gson().toJson(task));
        } catch (InvalidArgumentException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<String> markTask(@PathVariable("taskId") Integer id, @RequestBody @Valid TaskPost taskIsDonePut, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.validationErrorHandler(bindingResult);
        }
        try {
            Task task = taskService.markAsDone(id, taskIsDonePut.getIsDone());
            return ResponseEntity.ok(new Gson().toJson(task));
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") Integer id) {
        try{
            taskService.removeTask(id);
            return ResponseEntity.ok(null);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<String>(ControllerUtils.parseError(e), HttpStatus.NOT_FOUND);
        }

    }

    private ResponseEntity<String> validationErrorHandler(BindingResult bindingResult) {
        return new ResponseEntity<String>(ControllerUtils.parseError(new InvalidArgumentException(bindingResult.getFieldError().getField())), HttpStatus.BAD_REQUEST);
    }

}
