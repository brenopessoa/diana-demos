package org.jnosql.diana.dictionary.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.jnosql.diana.api.key.KeyValueEntity;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonAutoDetect(fieldVisibility = ANY)
@JsonInclude(value = NON_EMPTY)
public final class Word implements Serializable {
    private static final long serialVersionUID = 7809701549404804553L;

    private String name;
    private String meaning;

    Word() {
    }

    private Word(String name, String meaning) {
        this.name = name;
        this.meaning = meaning;
    }

    public static Word of(String name, String meaning) {
        return new Word(name, meaning);
    }

    @JsonIgnore
    public KeyValueEntity getEntity() {
        return KeyValueEntity.of(name, meaning);
    }

    @Override
    public String toString() {
        return "Word{" +
                "name='" + name + '\'' +
                ", meaning='" + meaning + '\'' +
                '}';
    }
}
