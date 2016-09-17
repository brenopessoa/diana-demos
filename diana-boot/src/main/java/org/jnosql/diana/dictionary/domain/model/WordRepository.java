package org.jnosql.diana.dictionary.domain.model;

public interface WordRepository {
    void save(Word word);

    Word findByKey(String key);
}
