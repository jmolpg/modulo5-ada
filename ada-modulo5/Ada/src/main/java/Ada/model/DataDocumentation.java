package Ada.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class DataDocumentation {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Preencha a descrição")
    private String description;

    @NotNull(message = "Preencha a data")
    private LocalDate date;

    @NotNull(message = "Preencha o tipo")
    private FinancialDataValues type;

    @NotNull(message = "Preencha a versão")
    private Long version;

    @NotNull(message = "Preencha o nome")
    private String name;



    @NotNull(message = "Preencha a data de expiração")
    @FutureOrPresent(message = "Erro. Data de expiração menor que o dia atual.")
    private LocalDate expirationDate;


}
