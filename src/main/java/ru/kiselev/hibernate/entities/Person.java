package ru.kiselev.hibernate.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Oleg Kiselev
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
    @SequenceGenerator(name = "PERSONSEQ", sequenceName = "personseq", allocationSize = 5, initialValue = 1)
    @GeneratedValue(generator = "PERSONSEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private Passport passport;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person",
            fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "persons_roles",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "birthday")
    private Date dateOfBirth;

    @Column(name = "zdt")
    private ZonedDateTime testTime;

    public Person() {
    }

    public Person(String firstName, String lastName, int age, Passport passport, Country country, List<Car> cars, Date dateOfBirth, ZonedDateTime testTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.passport = passport;
        this.country = country;
        this.cars = cars;
        this.dateOfBirth = dateOfBirth;
        this.testTime = testTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ZonedDateTime getTestTime() {
        return testTime;
    }

    public void setTestTime(ZonedDateTime testTime) {
        this.testTime = testTime;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id) && firstName.equals(person.firstName) && lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        String allCars = null;
        if (cars != null && !cars.isEmpty()){
            allCars = cars.stream().map(car -> car + " ").collect(Collectors.joining());
        }
        String allRoles = null;
        if (roles != null && !roles.isEmpty()){
            allRoles = roles.stream().map(role -> role + " ").collect(Collectors.joining());
        }
        return "Person " + id + ", " + firstName + " " + lastName + ", " + age + ", Roles: " + allRoles + ", Passport: "
                + passport + ", Country: " + country + ", Cars: " + allCars + ", " + dateOfBirth + ", " + testTime;
    }
}
