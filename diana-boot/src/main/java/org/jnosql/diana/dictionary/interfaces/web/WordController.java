package org.jnosql.diana.dictionary.interfaces.web;

import org.jnosql.diana.dictionary.application.WordService;
import org.jnosql.diana.dictionary.domain.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/v1/words")
public class WordController {

    @Autowired
    private WordService service;

    @RequestMapping(method = POST)
    public ResponseEntity<Void> save(@RequestBody Word word) {
        service.save(word);
        return ResponseEntity.status(CREATED).build();
    }

    @RequestMapping(value = "/{hazelcast}", method = GET)
    public ResponseEntity<Word> findByKey(@PathVariable("hazelcast") String key) {
        return ResponseEntity.ok()
                .body(service.findByKey(key));
    }
}
