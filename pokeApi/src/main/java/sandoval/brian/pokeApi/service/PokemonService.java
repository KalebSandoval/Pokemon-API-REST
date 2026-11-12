package sandoval.brian.pokeApi.service;

import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandoval.brian.pokeApi.model.Pokemon;
import sandoval.brian.pokeApi.repository.PokemonRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PokemonService {
    @Autowired
    private PokemonRepository repository;

    public List<Pokemon> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Pokemon> obtenerPorId(Long id) {
        if (id != null) {
            return repository.findById(id);
        }
        return Optional.empty();
    }

    public Pokemon crear(Pokemon pokemon) {
        if (pokemon != null) {
            return repository.save(pokemon);
        }
        return null;
    }

    public Pokemon actualizarCompleto(Long id, Pokemon pokemon) {
        Pokemon encontrado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokemon no encontrado con id " + id));

        encontrado.setNombre(pokemon.getNombre());
        encontrado.setTipo(pokemon.getTipo());
        encontrado.setNivel(pokemon.getNivel());
        encontrado.setPortadaUrl(pokemon.getPortadaUrl());

        return repository.save(encontrado);
    }


    public Pokemon actualizarParcial(Long id, Pokemon datos) {
        Pokemon encontrado = repository.findById(id).orElseThrow(() -> new RuntimeException("Pokemon no encontrado con id " + id));

        if (datos.getNombre() != null) {
            encontrado.setNombre(datos.getNombre());
        }
        if (datos.getTipo() != null) {
            encontrado.setTipo(datos.getTipo());
        }
        if (datos.getNivel() != null) {
            encontrado.setNivel(datos.getNivel());
        }
        if (datos.getAtaque() != null) {
            encontrado.setAtaque(datos.getAtaque());
        }
        if (datos.getDefensa() != null) {
            encontrado.setDefensa(datos.getDefensa());
        }
        if (datos.getDescripcion() != null) {
            encontrado.setDescripcion(datos.getDescripcion());
        }
        if (datos.getPortadaUrl() != null) {
            encontrado.setPortadaUrl(datos.getPortadaUrl());
        }

        return repository.save(encontrado);
    }

    public boolean eliminar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
