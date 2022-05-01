package br.com.brq.candidatos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.brq.candidatos.entities.Candidato;
import br.com.brq.candidatos.entities.Certificacao;
import br.com.brq.candidatos.repository.CandidatoRepository;
import br.com.brq.candidatos.repository.CertificacaoRepository;
import br.com.brq.candidatos.resources.exceptions.DatabaseException;

@Service
public class CertificacaoService {

	@Autowired
	private CertificacaoRepository repository;
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	public List<Candidato> findByCertContaining(String certificacao) {
		List<Certificacao> certificacoes = repository.findByCertificadoContainingIgnoreCase(certificacao);
		List<Candidato> candidatos = new ArrayList<>();
		for(Certificacao x : certificacoes) {
			@SuppressWarnings("deprecation")
			Candidato buscaCandidato = candidatoRepository.getOne(x.getCandidato().getIdCandidato());
			candidatos.add(buscaCandidato);
		}	
		
		return candidatos;
	}

	public List<Certificacao> findAll() {
		return repository.findAll();
	}

	public Certificacao findById(Integer id) {
		Optional<Certificacao> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id.toString()));
	}

	public Certificacao insert(Certificacao obj) {
		return repository.save(obj);
	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id.toString());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Certificacao update(Integer id, Certificacao obj) {
		try {
			@SuppressWarnings("deprecation")
			Certificacao entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id.toString());
		}
	}

	private void updateData(Certificacao entity, Certificacao obj) {

		entity.setCertificado(obj.getCertificado());
		entity.setCertificadora(obj.getCertificadora());
		entity.setDtEmissao(obj.getDtEmissao());
		entity.setDtExpiracao(obj.getDtExpiracao());
		entity.setCandidato(obj.getCandidato());

	}
}
