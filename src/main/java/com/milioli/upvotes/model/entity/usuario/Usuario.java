package com.milioli.upvotes.model.entity.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milioli.upvotes.util.AppUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "usuarios", schema = AppUtils.SCHEMA_DEFAULT)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Informe um nome válido")
    @Column(name = "nome")
    private String nome;

    @NotEmpty(message = "Informe um e-mail válido")
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @NotEmpty(message = "Informe uma senha válida")
    @Column(name = "senha")
    private String senha;

    @Column(name = "dh_criacao")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime dataHoraCriacao;

    public static String convertSenha(String senha) {
        byte[] bytesOfMessage = senha.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return Arrays.toString(md.digest(bytesOfMessage));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
