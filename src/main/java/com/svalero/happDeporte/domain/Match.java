package com.svalero.happDeporte.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.svalero.happDeporte.Util.Literal.LITERAL_NOT_BLANK;
import static com.svalero.happDeporte.Util.Literal.LITERAL_NOT_NULL;

/**
 * Anotación Lombok: Genera los Getters, Setters, constructor vacío y constructor con todos los argumentos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = LITERAL_NOT_BLANK)
    @NotNull(message = LITERAL_NOT_NULL)
    private String teamB;

    @Column
    @NotBlank(message = LITERAL_NOT_BLANK)
    @NotNull(message = LITERAL_NOT_NULL)
    private int markerA;

    @Column
    @NotBlank(message = LITERAL_NOT_BLANK)
    @NotNull(message = LITERAL_NOT_NULL)
    private int markerB;

    @Column
    private String analysis;

    @Column
    private String location;

    @Column
    @NotBlank(message = LITERAL_NOT_BLANK)
    @NotNull(message = LITERAL_NOT_NULL)
    private Date dateMatch;

    @Column
    @NotBlank(message = LITERAL_NOT_BLANK)
    @NotNull(message = LITERAL_NOT_NULL)
    private String hourMatch;

    /**
     * Siempre en las N:1 (ManyToOne se define la clave ajena en el lado N (Many)
     * Para relacionar un equipo con un partido
     * @ManyToOne: Muchos partidos asociados a un equipo N:1
     * @JoinColumn(name = "team_id") como queremos que se llame la tabla de la relación N:1
     */
    @ManyToOne
    @JoinColumn(name = "team_id")
    private User teamInMatch;
}
