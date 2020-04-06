package desafio.varejo.cellstoreapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "phones")
@Data
@RequiredArgsConstructor
public class Phone {

    @Id
    String id = null;

    @Indexed(unique = true)
    @NotBlank
    @NonNull
    @Size(max = 8, message = "Tamanho do código inválido (maior que {max} caracteres)")
    String code;

    @NotBlank(message = "Modelo não pode estar em branco")
    @NonNull
    @Size(min = 2, max = 255, message = "Tamanho do modelo inválido (menor que {min} caracteres ou maior que {max})")
    String model;

    @NotBlank(message = "Marca não pode estar em branco")
    @NonNull
    @Size(min = 2, max = 255, message = "Tamanho da marca inválido (menor que {min} caracteres ou maior que {max})")
    String brand;

    @NotNull(message = "Preço não pode estar vazio")
    @NonNull
    @Positive(message = "Preço inválido (menor ou igual a zero)")
    BigDecimal price;

    @NotBlank(message = "Foto não pode estar em branco")
    @NonNull
    @Size(max = 255, message = "Tamanho da marca inválido (maior que {max})")
    String photo;

    @NotNull(message = "Data de início da venda não pode estar vazia")
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate startDate;

    @NotNull(message = "Data de término da venda não pode estar vazia")
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate endDate;

    @NotNull(message = "Cor não pode estar vazia")
    @NonNull
    private Color color;
}
