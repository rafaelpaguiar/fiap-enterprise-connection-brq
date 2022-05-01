package br.com.brq.candidatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brq.candidatos.entities.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {

	List<Candidato> findByNomeContainingIgnoreCase (String nome);
	List<Candidato> findByEmailContainingIgnoreCase (String email);
	Candidato findByCpfContaining (String cpf);
}
