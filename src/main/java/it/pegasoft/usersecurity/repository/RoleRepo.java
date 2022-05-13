package it.pegasoft.usersecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.pegasoft.usersecurity.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
    
    Role findByName(String name);
}
