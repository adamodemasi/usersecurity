package it.pegasoft.usersecurity.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.pegasoft.usersecurity.model.Libro;
import it.pegasoft.usersecurity.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "userservice")
@RequestMapping("/api/libreria")
public class LibroController {
    private final LibroService libroService;

    static final Logger logger = LoggerFactory.getLogger(LibroController.class);
    @Operation(summary = "Get all the books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the books",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Libro.class)) }),
            @ApiResponse(responseCode = "404", description = "Books not found",
                    content = @Content) })
    @GetMapping("/all")
    public ResponseEntity<List<Libro>> getAllLibri(){
        List<Libro> libri = libroService.findAllLibro();
        return new ResponseEntity<>(libri, HttpStatus.OK);
    }
    @Operation(summary = "Get a book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Libro.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @GetMapping("/find/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable("id") Long id){
        Libro libro = libroService.findLibro(id);
        logger.info("Found book with id " + id);
        return new ResponseEntity<>(libro,HttpStatus.OK);
    }
    @Operation(summary = "Add a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Libro.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied",
                    content = @Content) })
    @PostMapping("/add")
    public ResponseEntity<Libro> addLibro(@RequestBody Libro libro){
        Libro newLibro = libroService.saveOrUpdateLibro(libro);
        return new ResponseEntity<>(newLibro, HttpStatus.CREATED);
    }
    @Operation(summary = "Update a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Libro.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content)})
    @Transactional
    @PatchMapping("/update")
    public ResponseEntity<?> updateLibro(@RequestBody Libro libro){
        Libro newLibro = libroService.saveOrUpdateLibro(libro);
        return new ResponseEntity<>(newLibro, HttpStatus.OK);
    }
    @Operation(summary = "Delete a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Libro.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLibro(@PathVariable("id") Long id){
        libroService.deleteLibro(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}