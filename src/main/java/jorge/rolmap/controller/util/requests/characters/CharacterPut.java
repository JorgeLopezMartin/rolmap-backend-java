package jorge.rolmap.controller.util.requests.characters;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
public class CharacterPut {

    @NotEmpty String name;
    @NotEmpty String description;
    String portrait;

    public CharacterPut() {
    }

    public CharacterPut(@NotEmpty String name, @NotEmpty String description, String portrait) {
        this.name = name;
        this.description = description;
        this.portrait = portrait;
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

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
