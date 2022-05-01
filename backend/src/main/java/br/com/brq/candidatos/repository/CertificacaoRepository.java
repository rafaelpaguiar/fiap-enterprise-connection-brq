package br.com.brq.candidatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brq.candidatos.entities.Certificacao;

public interface CertificacaoRepository extends JpaRepository<Certificacao, Integer> {
	List<Certificacao> findByCertificadoContainingIgnoreCase(String certificacao);
}
