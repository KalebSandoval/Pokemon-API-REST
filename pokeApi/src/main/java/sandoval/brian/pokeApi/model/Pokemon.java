package sandoval.brian.pokeApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pokemones")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String nombre;

    @Column(length = 1000, nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Integer nivel;


    @Column(length = 1000, nullable = false)
    private String ataque;

    @Column(length = 1000, nullable = false)
    private String defensa;

    @Column(length = 1000, nullable = false)
    private String descripcion;

    @Column(name = "portada_url")
    private String portadaUrl;

}
