package jorge.rolmap.service.impl;

import jorge.rolmap.repository.CharacterRepository;
import jorge.rolmap.service.CharacterService;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jorge.rolmap.model.Character;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    private void validateCharacterParams(String name, String description, String portrait) throws InvalidArgumentException {
        if (name == null || name.isEmpty()) {
            throw new InvalidArgumentException("Invalid character name.");
        }
        if (description == null || description.isEmpty()) {
            throw new InvalidArgumentException("Invalid character description.");
        }
    }

    @Override
    public List<Character> getCharacters() {
        return (List<Character>) characterRepository.findAll();
    }

    @Override
    public Character createCharacter(String name, String description, String portrait) throws InvalidArgumentException {
        this.validateCharacterParams(name, description, portrait);
        Character character = new Character();
        character.setName(name);
        character.setDescription(description);
        character.setPortrait(portrait);
        characterRepository.save(character);
        return character;
    }

    @Override
    public Character updateCharacter(Integer id, String name, String description, String portrait) throws InvalidArgumentException, InstanceNotFoundException {
        Optional<Character> foundCharacter = characterRepository.findById(id);
        if (foundCharacter.isPresent()) {
            Character character = foundCharacter.get();
            character.setName(name);
            character.setDescription(description);
            character.setPortrait(portrait);
            characterRepository.save(character);
            return character;
        } else {
            throw new InstanceNotFoundException("Character " + id);
        }
    }

    @Override
    public void removeCharacter(Integer id) throws InstanceNotFoundException {
        Optional<Character> foundCharacter = characterRepository.findById(id);
        if (foundCharacter.isPresent()) {
            characterRepository.delete(foundCharacter.get());
        } else {
            throw new InstanceNotFoundException("Character " + id);
        }
    }
}
