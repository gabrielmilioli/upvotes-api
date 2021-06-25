package com.milioli.upvotes.service.postagem.voto;

import com.milioli.upvotes.model.entity.postagem.Postagem;
import com.milioli.upvotes.model.entity.postagem.voto.VotoPostagem;
import org.springframework.data.domain.Page;

public interface VotoPostagemService {

    VotoPostagem salvar(VotoPostagem votoPostagem);

    Page<VotoPostagem> buscar(VotoPostagem votoPostagem);

    Page<VotoPostagem> buscarPorPostagem(Postagem postagem);

    void deletar(VotoPostagem votoPostagem);

}
