package it.pegasoft.usersecurity.api;

import it.pegasoft.usersecurity.model.Libro;
import it.pegasoft.usersecurity.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/libreria")
public class LibroController {
    private final LibroService libroService;

    @GetMapping("/all")
    public ResponseEntity<List<Libro>> getAllLibri(){
        List<Libro> libri = libroService.findAllLibro();
        return new ResponseEntity<>(libri, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable("id") Long id){
        Libro libro = libroService.findLibro(id);
        return new ResponseEntity<>(libro,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Libro> addLibro(@RequestBody Libro libro){
        Libro newLibro = libroService.saveOrUpdateLibro(libro);
        return new ResponseEntity<>(newLibro, HttpStatus.CREATED);
    }

    @Transactional
    @PatchMapping("/update")
    public ResponseEntity<?> updateLibro(@RequestBody Libro libro){
        Libro newLibro = libroService.saveOrUpdateLibro(libro);
        return new ResponseEntity<>(newLibro, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLibro(@PathVariable("id") Long id){
        libroService.deleteLibro(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}