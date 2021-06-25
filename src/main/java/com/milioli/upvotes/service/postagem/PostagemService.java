package com.milioli.upvotes.service.postagem;

import com.milioli.upvotes.model.entity.postagem.Postagem;
import org.springframework.data.domain.Page;

public interface PostagemService {

    Postagem salvar(Postagem postagem);

    Page<Postagem> buscar(Postagem postagem);

    void deletar(Postagem postagem);

}
