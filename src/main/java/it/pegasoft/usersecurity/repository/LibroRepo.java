package it.pegasoft.usersecurity.repository;

import it.pegasoft.usersecurity.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepo extends JpaRepository<Libro, Long> {

    void deleteLibroById(Long id);

    Optional<Libro> findLibroById(Long id);

}
