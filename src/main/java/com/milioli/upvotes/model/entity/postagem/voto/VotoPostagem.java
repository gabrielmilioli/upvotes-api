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
import javax.validation.constraints.NotNull;
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

    @ManyToOne
    @JoinColumn(name = "i_postagens")
    @NotNull(message = "Informe uma postagem válida")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Postagem postagem;

    @ManyToOne
    @JoinColumn(name = "i_usuarios")
    @NotNull(message = "Informe um usuário válido")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Informe um tipo válido")
    @Column(name = "tipo")
    private TipoVotoPostagem tipo;

    @Column(name = "dh_criacao")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime dataHoraCriacao;

}
