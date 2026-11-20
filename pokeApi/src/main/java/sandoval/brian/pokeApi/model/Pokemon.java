package sandoval.brian.pokeApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pokemones")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligato")
    @Size(min = 2, max = 50, message = "El nombre debe de tener entre 2 y 50 caracteres.")
    @Column(nullable = false, length = 1000)
    private String nombre;

    @NotBlank(message = "El tipo de pokemon es obligatorio.")
    @Column(length = 1000, nullable = false)
    private String tipo;

    @NotNull(message = "El nivel es obligatorio")
    @Min(value = 1, message = "El nivel mínimo es 1")
    @Max(value = 100, message = "El nivel máximo es de 10")
    @Column(nullable = false)
    private Integer nivel;

    @Positive(message = "El ataque no puede ser negativo.")
    @Column(length = 1000, nullable = false)
    private String ataque;

    @Positive(message = "La defensa no puede ser negativa.")
    @Column(length = 1000, nullable = false)
    private String defensa;

    @Size(max = 255, message = "La descripción no puede exceder de los 255 caracteres.")
    @Column(length = 1000, nullable = false)
    private String descripcion;

    @URL(message = "La imagen debe de ser una URL válida")
    @Column(name = "portada_url")
    private String portadaUrl;

    @PastOrPresent(message = "La fecha de captura no puede ser en el futuro")
    private LocalDate fechaCaptura;

}
