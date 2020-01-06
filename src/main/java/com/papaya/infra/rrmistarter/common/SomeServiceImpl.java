package com.papaya.infra.rrmistarter.common;

import org.springframework.stereotype.Service;

/**
 * @author Evgeny Borisov
 */
//@Service
public class SomeServiceImpl implements SomeService {
    @Override
    public Person processPerson(Person person) {
        return person.withNameUpper();
    }
}
