package jorge.rolmap.controller.util.requests.tasks;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
public class TaskPost {

    @NotNull
    @NotEmpty
    String title;
    @NotNull
    @NotEmpty
    String description;
    @NotNull
    boolean isDone;

    public TaskPost() {
    }

    public TaskPost(@NotNull @NotEmpty String title, @NotNull @NotEmpty String description, @NotNull boolean isDone) {
        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }
}
