package com.milioli.upvotes.model.entity.postagem.voto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.milioli.upvotes.model.entity.postagem.Postagem;
import com.milioli.upvotes.model.entity.usuario.Usuario;
import com.milioli.upvotes.util.AppUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "postagens_votos", schema = AppUtils.SCHEMA_DEFAULT)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoPostagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany
    @JoinColumn(name = "i_postagens")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Postagem postagem;

    @ManyToOne
    @JoinColumn(name = "i_usuarios")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo")
    private TipoVotoPostagem tipo;

    @Column(name = "dh_criacao")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime dataHoraCriacao;

}
