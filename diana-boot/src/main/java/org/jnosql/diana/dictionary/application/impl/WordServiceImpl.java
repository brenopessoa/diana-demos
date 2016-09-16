package org.jnosql.diana.dictionary.application.impl;

import org.jnosql.diana.dictionary.application.WordService;
import org.jnosql.diana.dictionary.domain.model.Word;
import org.jnosql.diana.dictionary.domain.model.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordRepository repository;

    @Override
    public void save(Word word) {
        repository.save(word);
    }

    @Override
    public Word findByKey(String key) {
        return repository.findByKey(key);
    }
}
