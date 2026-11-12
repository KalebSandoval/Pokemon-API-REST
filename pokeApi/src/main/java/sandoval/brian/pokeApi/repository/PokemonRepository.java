package sandoval.brian.pokeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sandoval.brian.pokeApi.model.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

}
