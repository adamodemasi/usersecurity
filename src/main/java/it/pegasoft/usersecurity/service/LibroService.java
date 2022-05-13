package it.pegasoft.usersecurity.service;

import it.pegasoft.usersecurity.exception.LibroNotFoundException;
import it.pegasoft.usersecurity.model.Libro;
import it.pegasoft.usersecurity.repository.LibroRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class LibroService {
    private LibroRepo libroRepo;

    @Autowired
    public LibroService(LibroRepo libroRepo){
        this.libroRepo=libroRepo;
    }

    public Libro saveOrUpdateLibro(Libro libro) {
        log.info("Saving new book {} to db", libro.getTitolo());
        return libroRepo.save(libro);
    }

    public List<Libro> findAllLibro(){
        return libroRepo.findAll();
    }

    public Libro findLibro(Long id) throws LibroNotFoundException {
        return libroRepo.findLibroById(id)
                .orElseThrow(() -> new LibroNotFoundException("Libro con id '" + id + "' non trovato"));
    }

    public void deleteLibro(Long id){
        libroRepo.deleteLibroById(id);
    }

}
