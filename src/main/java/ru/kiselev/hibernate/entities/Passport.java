package ru.kiselev.hibernate.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Oleg Kiselev
 */
@Entity
@Table(name = "passport")
public class Passport {

    @Id
    @SequenceGenerator(name = "ADDRESSSEQ", sequenceName = "addressseq", allocationSize = 5)
    @GeneratedValue(generator = "ADDRESSSEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(mappedBy = "passport", optional = false)
    private Person person;

    @Column(name = "number", unique = true)
    private int passportNumber;

    public Passport() {
    }

    public Passport(Person person, int passportNumber) {
        this.person = person;
        this.passportNumber = passportNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return Objects.equals(id, passport.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" + id + ", " + passportNumber + "}";
    }
}
