package it.pegasoft.usersecurity.service;

import it.pegasoft.usersecurity.exception.LibroNotFoundException;
import it.pegasoft.usersecurity.model.Libro;
import it.pegasoft.usersecurity.repository.LibroRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class LibroService {
    private LibroRepo libroRepo;
    static final Logger logger = LoggerFactory.getLogger(LibroService.class);
    @Autowired
    public LibroService(LibroRepo libroRepo){
        this.libroRepo=libroRepo;
    }

    public Libro saveOrUpdateLibro(Libro libro) {
        logger.info("Saving book {} to db", libro.getTitolo());
        return libroRepo.save(libro);
    }

    public List<Libro> findAllLibro(){
        logger.info("All books displayed");
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
