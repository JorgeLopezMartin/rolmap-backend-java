package jorge.rolmap.controller.util.requests.quests;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
public class QuestPut {

    @NotEmpty String name;
    @NotEmpty String description;

    public QuestPut() {
    }

    public QuestPut(@NotEmpty String name, @NotEmpty String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
