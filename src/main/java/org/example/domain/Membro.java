package org.example.domain;

import javax.json.bind.annotation.JsonbNillable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@JsonbNillable
@Entity
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Boolean ativoEscala;
    private LocalDate ultimaEscala;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Habilidade> habilidades;

    @ManyToMany
    @JsonbTransient
    private List<Ministerio> ministerios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivoEscala() {
        return ativoEscala;
    }

    public void setAtivoEscala(Boolean ativoEscala) {
        this.ativoEscala = ativoEscala;
    }

    public LocalDate getUltimaEscala() {
        return ultimaEscala;
    }

    public void setUltimaEscala(LocalDate ultimaEscala) {
        this.ultimaEscala = ultimaEscala;
    }

    public List<Ministerio> getMinisterios() {
        return ministerios;
    }

    public void setMinisterios(List<Ministerio> ministerios) {
        this.ministerios = ministerios;
    }

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }
}
