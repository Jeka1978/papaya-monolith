package com.papaya.infra.rrmistarter.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Evgeny Borisov
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person implements Serializable {
    private String name;
    private int age;

    public Person withNameUpper() {
        return new Person(name.toUpperCase(), age);
    }
}
