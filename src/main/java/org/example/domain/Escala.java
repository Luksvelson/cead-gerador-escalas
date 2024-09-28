package org.example.domain;

import org.example.utils.MapDeserializer;

import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Escala {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate data;
    private Turno turno;

    @ElementCollection
    @MapKeyColumn(name = "posicoes_key")  // Column for the key
    @Column(name = "posicoes_value")      // Column for the value
    @CollectionTable(name = "escala_posicoes", joinColumns = @JoinColumn(name = "escala_id"))
    @JsonbTypeDeserializer(MapDeserializer.class)
    private Map<String, String> posicoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Map<String, String> getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(Map<String, String> posicoes) {
        this.posicoes = posicoes;
    }
}
