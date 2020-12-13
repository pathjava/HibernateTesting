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
    @SequenceGenerator(name = "CARSEQ", sequenceName = "carseq", allocationSize = 5)
    @GeneratedValue(generator = "CARSEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "model", nullable = false)
    private String model;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    public Car() {
    }

    public Car(Long id, String model, Person person) {
        this.id = id;
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
        return Objects.equals(id, car.id) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }

    @Override
    public String toString() {
        return "{" + id + ", " + model + "}";
    }
}
