package br.com.brq.candidatos.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.brq.candidatos.entities.Candidato;
import br.com.brq.candidatos.repository.CandidatoRepository;
import br.com.brq.candidatos.resources.exceptions.DatabaseException;

@Service
public class CandidatoService {

	@Autowired
	private CandidatoRepository repository;
	
	public List<Candidato> findAll() {
		return repository.findAll();
	}
	
	public List<Candidato> findByNomeContaining(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}
	
	public List<Candidato> findByEmailContaining(String email) {
		return repository.findByEmailContainingIgnoreCase(email);
	}

	public Candidato findByCpfContaining(String cpf) {
		return repository.findByCpfContaining(cpf);
	}
	
	public Candidato findById(Integer id) {
		Optional<Candidato> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id.toString()));
	}

	public Candidato insert(Candidato obj) {
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

	public Candidato update(Integer id, Candidato obj) {
		try {
			@SuppressWarnings("deprecation")
			Candidato entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id.toString());
		}
	}
		private void updateData(Candidato entity, Candidato obj) {
		
			entity.setNome(obj.getNome());;
			entity.setCPF(obj.getCpf());
			entity.setEmail(obj.getEmail());
			entity.setGenero(obj.getGenero());
			entity.setDataDeNascimento(obj.getDataDeNascimento());
			
			
		}	
}
