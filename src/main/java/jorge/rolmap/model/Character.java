package jorge.rolmap.model;


import javax.persistence.*;

@Entity(name = "rolcharacter")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String portrait;

    public Character(Integer id, String name, String description, String portrait) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.portrait = portrait;
    }

    public Character() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
