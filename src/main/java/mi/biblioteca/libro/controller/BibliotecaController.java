package mi.biblioteca.libro.controller;

import mi.biblioteca.libro.model.Libro;
import mi.biblioteca.libro.model.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BibliotecaController {
    public BibliotecaController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    private LibroRepository libroRepository;

    @PostMapping("/crearLibro")
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @PostMapping("/crearLibros")
    public List<Libro> crearLibros(@RequestBody List<Libro> libros){
        return libroRepository.saveAll(libros);
    }

    @GetMapping("/listarLibros")
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @GetMapping("/buscarLibroPorId/{id}")
    public Libro libroPorId(@PathVariable int id) {
        return libroRepository.findById(id).orElse(null);
    }

    @GetMapping("/buscarLibrosPorAutor/{nombre}")
    public List<Libro> buscarLibrosPorAutor(@PathVariable String nombre){
        return libroRepository.findByAutor(nombre);
    }

    @DeleteMapping("/eliminarLibro/{id}")
    public String eliminarLibro(@PathVariable int id) {
        libroRepository.deleteById(id);
        return "Libro eliminado con el id "+id;
    }

    @PutMapping("/actualizarLibro")
    public Libro actualizarLibro(@RequestBody Libro libro) {
        Libro libroExistente = libroRepository.findById(libro.getId()).orElse(null);
        libroExistente.setNombre(libro.getNombre());
        libroExistente.setAutor(libro.getAutor());
        libroExistente.setPrecio(libro.getPrecio());
        return libroRepository.save(libroExistente);
    }
}
