package jorge.rolmap.controller.util.requests.tasks;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
public class TaskIsDonePut {

    @NotNull
    boolean isDone;

    public TaskIsDonePut() {
    }

    public TaskIsDonePut(@NotNull boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }
}
