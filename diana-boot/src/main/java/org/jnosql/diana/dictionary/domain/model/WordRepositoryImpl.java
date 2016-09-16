package org.jnosql.diana.dictionary.domain.model;

import org.jnosql.diana.api.key.BucketManager;
import org.jnosql.diana.dictionary.application.exceptions.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WordRepositoryImpl implements WordRepository {

    @Autowired
    private BucketManager bucketManager;

    @Override
    public void save(Word word) {
        bucketManager.put(word.getEntity());
    }

    @Override
    public Word findByKey(String key) {
        return Word.of(key, bucketManager.get(key)
                .orElseThrow(NoDataFoundException::new)
                .get()
                .toString());
    }
}
