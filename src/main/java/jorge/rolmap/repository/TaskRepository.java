package jorge.rolmap.repository;

import jorge.rolmap.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {

    @Query("select t from Task t order by t.isDone asc, t.id asc")
    Iterable<Task> findAllTasks();

}
