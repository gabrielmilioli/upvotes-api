package com.milioli.upvotes.model.repository.postagem.voto;

import com.milioli.upvotes.model.entity.postagem.Postagem;
import com.milioli.upvotes.model.entity.postagem.voto.VotoPostagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoPostagemRepository extends JpaRepository<VotoPostagem, Long> {

    Page<VotoPostagem> findAllByPostagem(Postagem postagem, Pageable pageable);

}
