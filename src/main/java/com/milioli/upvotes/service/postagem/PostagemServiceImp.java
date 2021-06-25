package com.milioli.upvotes.service.postagem;

import com.milioli.upvotes.model.entity.postagem.Postagem;
import com.milioli.upvotes.model.repository.postagem.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class PostagemServiceImp implements PostagemService {

    @Autowired
    PostagemRepository repository;

    @Override
    public Postagem salvar(Postagem postagem) {
        return repository.save(postagem);
    }

    @Override
    public Page<Postagem> buscar(Postagem postagem) {
        final PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("dataHoraCriacao"));

        Example<Postagem> example = Example.of(postagem,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return repository.findAll(example, pageRequest);
    }

    @Override
    public void deletar(Postagem postagem) {
        repository.delete(postagem);
    }
}
