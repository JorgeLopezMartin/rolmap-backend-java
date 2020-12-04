package jorge.rolmap.service;

import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;

import jorge.rolmap.model.Character;
import java.util.List;


public interface CharacterService {

    List<Character> getCharacters();
    Character createCharacter(String name, String description, String portrait) throws InvalidArgumentException;
    Character updateCharacter(Integer id, String name, String description, String portrait) throws InvalidArgumentException, InstanceNotFoundException;
    void removeCharacter(Integer id) throws InstanceNotFoundException;

}
