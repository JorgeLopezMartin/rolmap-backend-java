package jorge.rolmap.service;

import jorge.rolmap.model.Task;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();
    Task createTask(String title, String description, Boolean isDone) throws InvalidArgumentException;
    Task markAsDone(Integer id, Boolean isDone) throws InstanceNotFoundException;
    void removeTask(Integer id) throws InstanceNotFoundException;

}
