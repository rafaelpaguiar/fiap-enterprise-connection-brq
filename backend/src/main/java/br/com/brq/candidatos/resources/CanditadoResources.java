package br.com.brq.candidatos.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import br.com.brq.candidatos.entities.Candidato;
import br.com.brq.candidatos.resources.util.URL;
import br.com.brq.candidatos.services.CandidatoService;


@RestController
@RequestMapping("/candidato")
public class CanditadoResources {

	@Autowired
	private CandidatoService service;

	@GetMapping(value="/buscaPorNome")
	public ResponseEntity<List<Candidato>> findByNomeContaining(@RequestParam(value="nome", defaultValue = "") String nome){
		System.out.println(nome);
		nome = URL.decodeParam(nome);
		System.out.println(nome);
		List<Candidato> list = service.findByNomeContaining(nome);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/buscaPorEmail")
	public ResponseEntity<List<Candidato>> findByEmailContaining(@RequestParam(value="email", defaultValue = "") String email){
		System.out.println(email);
		email = URL.decodeParam(email);
		System.out.println(email);
		List<Candidato> list = service.findByEmailContaining(email);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/buscaPorCpf")
	public ResponseEntity<Candidato> findByCpfContaining(@RequestParam(value="cpf", defaultValue = "") String cpf){
		System.out.println(cpf);
		cpf = URL.decodeParam(cpf);
		System.out.println(cpf);
		Candidato obj = service.findByCpfContaining(cpf);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Candidato>> findAll(){
		List<Candidato> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Candidato> findById(@PathVariable Integer id){
		Candidato obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Candidato> insert(@RequestBody Candidato obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCandidato()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Candidato> udate(@PathVariable Integer id, @RequestBody Candidato obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

}
