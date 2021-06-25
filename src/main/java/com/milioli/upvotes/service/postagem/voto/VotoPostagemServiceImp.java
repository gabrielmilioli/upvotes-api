package com.milioli.upvotes.service.postagem.voto;

import com.milioli.upvotes.model.entity.postagem.Postagem;
import com.milioli.upvotes.model.entity.postagem.voto.VotoPostagem;
import com.milioli.upvotes.model.repository.postagem.voto.VotoPostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

public class VotoPostagemServiceImp implements VotoPostagemService {

    @Autowired
    VotoPostagemRepository repository;

    @Transactional
    public VotoPostagem getById(Long id) {
        return repository.getById(id);
    }

    @Override
    @Transactional
    public VotoPostagem salvar(VotoPostagem votoPostagem) {
        return repository.save(votoPostagem);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VotoPostagem> buscar(VotoPostagem votoPostagem) {
        final PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("dataHoraCriacao"));
        return repository.findAll(pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VotoPostagem> buscarPorPostagem(Postagem postagem) {
        final PageRequest pageable = PageRequest.of(0, 10, Sort.by("dataHoraCriacao"));
        return repository.findAllByPostagem(postagem, pageable);
    }

    @Override
    @Transactional
    public void deletar(VotoPostagem votoPostagem) {
        repository.delete(votoPostagem);
    }
}
