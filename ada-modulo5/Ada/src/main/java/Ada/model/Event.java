package Ada.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Slf4j
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Preencha o nome")
    private String name;

    @Column(columnDefinition = "DATE")
    @NotNull(message = "Preencha a data")
    private LocalDate date;

    @OneToMany(fetch = FetchType.LAZY)
    @NotNull(message = "Preencha os documentos")
    private List<DataDocumentation> documents;

    @NotNull(message = "Preencha o tipo de evento")
    private EventType eventType;
}
