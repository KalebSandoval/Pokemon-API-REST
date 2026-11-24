package sandoval.brian.pokeApi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Correo no valido")
    @NotBlank(message = "Correo obligatorio")
    private String email;

    @NotBlank(message = "Contraseña obligatoria")
    private String password;
}
