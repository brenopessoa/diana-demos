package org.jnosql.diana.dictionary.application;

import org.jnosql.diana.dictionary.domain.model.Word;

public interface WordService {
    void save(Word word);

    Word findByKey(String key);
}
