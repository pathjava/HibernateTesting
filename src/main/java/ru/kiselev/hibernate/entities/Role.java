package ru.kiselev.hibernate.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Oleg Kiselev
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @SequenceGenerator(name = "ROLESEQ", sequenceName = "roleseq", allocationSize = 5)
    @GeneratedValue(generator = "ROLESEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "role")
    private String nameRole;

    @ManyToMany(mappedBy = "roles")
    private Set<Person> persons = new HashSet<>();

    public Role() {
    }

    public Role(String nameRole, Set<Person> persons) {
        this.nameRole = nameRole;
        this.persons = persons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(nameRole, role.nameRole) && Objects.equals(persons, role.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameRole, persons);
    }

    @Override
    public String toString() {
        return "{" + id + ", " + nameRole + "}";
    }
}
