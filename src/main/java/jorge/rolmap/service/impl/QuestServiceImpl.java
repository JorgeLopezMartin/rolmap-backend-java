package jorge.rolmap.service.impl;

import jorge.rolmap.model.Quest;
import jorge.rolmap.repository.QuestRepository;
import jorge.rolmap.service.QuestService;
import jorge.rolmap.util.exception.InstanceNotFoundException;
import jorge.rolmap.util.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestServiceImpl implements QuestService {

    @Autowired
    QuestRepository questRepository;

    private void validateQuestParams(String name, String description) throws InvalidArgumentException {
        if (name == null || description.isEmpty()) {
            throw new InvalidArgumentException("Invalid quest name.");
        }
        if (description == null || description.isEmpty()) {
            throw new InvalidArgumentException("Invalid quest description.");
        }
    }

    @Override
    public List<Quest> getQuests() {
        return (List<Quest>) questRepository.findAll();
    }

    @Override
    public Quest createQuest(String name, String description) throws InvalidArgumentException {
        this.validateQuestParams(name, description);
        Quest quest = new Quest();
        quest.setName(name);
        quest.setDescription(description);
        return quest;
    }

    @Override
    public Quest updateQuest(Integer id, String name, String description) throws InvalidArgumentException, InstanceNotFoundException {
        Optional<Quest> foundQuest = questRepository.findById(id);
        if (foundQuest.isPresent()) {
            Quest quest = foundQuest.get();
            quest.setName(name);
            quest.setDescription(description);
            questRepository.save(quest);
            return quest;
        } else {
            throw new InstanceNotFoundException("Quest " + id);
        }
    }

    @Override
    public void deleteQuest(Integer id) throws InstanceNotFoundException {
        Optional<Quest> foundQuest = questRepository.findById(id);
        if(foundQuest.isPresent()) {
            questRepository.delete(foundQuest.get());
        } else {
            throw new InstanceNotFoundException("Quest " + id);
        }
    }
}

