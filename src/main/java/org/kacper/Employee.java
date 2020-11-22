package org.kacper;

public class Employee {
    private final int id;
    private final String name;
    private final String surname;
    private final String pesel;

    public Employee(int id, String name, String surname, String pesel) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }
}
