package sandoval.brian.pokeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sandoval.brian.pokeApi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
