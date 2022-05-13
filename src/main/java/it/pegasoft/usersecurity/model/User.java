package it.pegasoft.usersecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> role = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id= " + id +
                ", name= '" + name + '/' +
                ", username= '" + username + '/' +
                ", password= '" + password + '/' +
                " " + role.toString() +
                '}';
    }
}
