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
import br.com.brq.candidatos.entities.Skill;
import br.com.brq.candidatos.repository.CandidatoRepository;
import br.com.brq.candidatos.repository.CertificacaoRepository;
import br.com.brq.candidatos.repository.SkillRepository;
import br.com.brq.candidatos.resources.exceptions.DatabaseException;

@Service
public class SkillService {

	@Autowired
	private SkillRepository repository;
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
	private CertificacaoRepository certificadoRepository;

	public List<Skill> findAll() {
		return repository.findAll();
	}
	
	public List<Candidato> findBySkillContaining(String skill) {
		List<Skill> skills = repository.findBySkillContainingIgnoreCase(skill);
		List<Candidato> candidatos = new ArrayList<>();
		for(Skill x : skills) {
			@SuppressWarnings("deprecation")
			Candidato buscaCandidato = candidatoRepository.getOne(x.getCandidato().getIdCandidato());
			candidatos.add(buscaCandidato);
		}	
		
		return candidatos;
	}

	public List<Candidato> rankCandidatoContaining(String skill) {
		List<Skill> skills = repository.findBySkillContainingIgnoreCase(skill);
		List<Candidato> candidatos = new ArrayList<>();
		for(Skill x : skills) {
			
			@SuppressWarnings("deprecation")
			Candidato buscaCandidato = candidatoRepository.getOne(x.getCandidato().getIdCandidato());
			candidatos.add(buscaCandidato);
		}	
		
		
		
		List<Certificacao> certificacoes = certificadoRepository.findByCertificadoContainingIgnoreCase(skill);
		for(Certificacao x : certificacoes) {
			@SuppressWarnings("deprecation")
			Candidato buscaCandidato = candidatoRepository.getOne(x.getCandidato().getIdCandidato());
			if(candidatos.contains(buscaCandidato)) {
				candidatos.remove(buscaCandidato);
				candidatos.add(0, buscaCandidato);
			}
		}
		
		
		return candidatos;
	}
	
	public Skill findById(Integer id) {
		Optional<Skill> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id.toString()));
	}

	public Skill insert(Skill obj) {
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

	public Skill update(Integer id, Skill obj) {
		try {
			@SuppressWarnings("deprecation")
			Skill entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id.toString());
		}
	}

	private void updateData(Skill entity, Skill obj) {

		entity.setCandidato(obj.getCandidato());
		entity.setSkill(obj.getSkill());
		entity.setNivel(obj.getNivel());

	}
}
