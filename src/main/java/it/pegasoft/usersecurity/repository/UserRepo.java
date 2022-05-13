package it.pegasoft.usersecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.pegasoft.usersecurity.model.User;

public interface UserRepo extends JpaRepository<User, Long>{
    
    User findByUsername(String username);

    void deleteUserById(Long id);
}
