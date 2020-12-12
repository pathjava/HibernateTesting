package ru.kiselev.hibernate.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author Oleg Kiselev
 */
@Entity
@Table(name = "country")
public class Country {

    @Id
    @SequenceGenerator(name = "COUNTRYSEQ", sequenceName = "countryseq", allocationSize = 5, initialValue = 1)
    @GeneratedValue(generator = "COUNTRYSEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String countryName;

    @OneToMany(mappedBy = "country"/*, fetch = FetchType.EAGER*/)
    private Set<Person> citizens;

    public Country() {
    }

    public Country(String countryName, Set<Person> citizens) {
        this.countryName = countryName;
        this.citizens = citizens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Set<Person> getCitizens() {
        return citizens;
    }

    public void setCitizens(Set<Person> citizen) {
        this.citizens = citizen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" + id + ", " + countryName + "}";
    }
}
