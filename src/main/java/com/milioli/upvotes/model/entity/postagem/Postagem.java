package com.milioli.upvotes.model.entity.postagem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.milioli.upvotes.model.entity.usuario.Usuario;
import com.milioli.upvotes.util.AppUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "postagens", schema = AppUtils.SCHEMA_DEFAULT)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Informe um título válido")
    @Column(name = "titulo")
    private String titulo;

    @NotEmpty(message = "Informe um conteúdo válido")
    @Column(name = "conteudo")
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "i_usuarios")
    @NotNull(message = "Informe um usuário válido")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @Column(name = "dh_criacao")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime dataHoraCriacao;

}
