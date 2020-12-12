package ru.kiselev.hibernate.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Oleg Kiselev
 */
@Entity
@Table(name = "car")
public class Car {

    @Id
    @SequenceGenerator(name = "CARSEQ", sequenceName = "carseq", allocationSize = 5, initialValue = 1)
    @GeneratedValue(generator = "CARSEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String model;

    @ManyToOne/*(fetch = FetchType.LAZY)*/
    @JoinColumn(name = "person_id")
    private Person person;

    public Car() {
    }

    public Car(String model, Person person) {
        this.model = model;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" + id + ", " + model + "}";
    }
}
