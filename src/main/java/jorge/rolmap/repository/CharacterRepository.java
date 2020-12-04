package jorge.rolmap.repository;

import jorge.rolmap.model.Character;
import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<Character, Integer> {

}
