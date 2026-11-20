package sandoval.brian.pokeApi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sandoval.brian.pokeApi.model.Pokemon;
import sandoval.brian.pokeApi.service.PokemonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokemones")
public class PokemonController {
    @Autowired
    private PokemonService service;

    @GetMapping
    public List<Pokemon> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Pokemon obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public Pokemon crearPokemon(@Valid @RequestBody Pokemon pokemon) {
        return service.crear(pokemon);
    }

    @PutMapping("/{id}")
    public Pokemon actualizarCompleto(@PathVariable Long id, @Valid @RequestBody Pokemon pokemon) {
        return service.actualizarCompleto(id, pokemon);
    }

    @PatchMapping("/{id}")
    public Pokemon actualizarParcial(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        return service.actualizarParcial(id, pokemon);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

}
