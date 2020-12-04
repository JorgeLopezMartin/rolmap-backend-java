package jorge.rolmap.service;

import jorge.rolmap.model.Quest;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;

import java.util.List;

public interface QuestService {

    List<Quest> getQuests();
    Quest createQuest(String name, String description) throws InvalidArgumentException;
    Quest updateQuest(Integer id, String name, String description) throws InvalidArgumentException, InstanceNotFoundException;
    void deleteQuest(Integer id) throws InstanceNotFoundException;

}
