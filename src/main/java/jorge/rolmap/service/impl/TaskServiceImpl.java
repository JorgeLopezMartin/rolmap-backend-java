package jorge.rolmap.service.impl;

import jorge.rolmap.model.Task;
import jorge.rolmap.repository.TaskRepository;
import jorge.rolmap.service.TaskService;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    private void validateParams(String title, String description) throws InvalidArgumentException {
        if (title == null || title.equals("")) {
            throw new InvalidArgumentException("title");
        }
        if (description == null || description.equals("")) {
            throw new InvalidArgumentException("description");
        }
    }

    public Task createTask(String title, String description, Boolean isDone) throws InvalidArgumentException {
        this.validateParams(title, description);
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDone(isDone);
        taskRepository.save(task);
        return task;
    }

    public List<Task> getTasks() {
        return (List<Task>) taskRepository.findAllTasks();
    }

    public Task markAsDone(Integer id, Boolean isDone) throws InstanceNotFoundException {
        Optional<Task> foundTask = taskRepository.findById(id);
        if (foundTask.isPresent()) {
            Task task = foundTask.get();
            task.setDone(isDone);
            Task result = taskRepository.save(task);
            return result;
        } else {
            throw new InstanceNotFoundException("Task " + id);
        }
    }

    public void removeTask(Integer id) throws InstanceNotFoundException {
        Optional<Task> foundTask = taskRepository.findById(id);
        if (foundTask.isPresent()) {
            taskRepository.delete(foundTask.get());
        } else {
            throw new InstanceNotFoundException("Task " + id);
        }
    }

}
