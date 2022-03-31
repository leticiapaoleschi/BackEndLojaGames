package org.generation.lojagames.repository;

import java.util.List;

import org.generation.lojagames.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	public List<Produto> findAllByNomeContainingIgnoreCase(String nome);

	public List<Produto> findAllByLancamento(int lancamento);
	
}
