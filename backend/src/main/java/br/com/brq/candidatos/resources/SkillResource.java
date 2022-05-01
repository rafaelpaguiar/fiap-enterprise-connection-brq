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
import br.com.brq.candidatos.entities.Skill;
import br.com.brq.candidatos.resources.util.URL;
import br.com.brq.candidatos.services.CandidatoService;
import br.com.brq.candidatos.services.SkillService;

@RestController
@RequestMapping("skill")
public class SkillResource {

	@Autowired
	private SkillService service;
	
	@Autowired
	private CandidatoService candidatoService;
	
	@GetMapping(value="/buscaPorSkill")
	public ResponseEntity<List<Candidato>> findBySkillContaining(@RequestParam(value="skill", defaultValue = "") String skill){
		System.out.println(skill);
		skill = URL.decodeParam(skill);
		System.out.println(skill);
		List<Candidato> list = service.findBySkillContaining(skill);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/rankCandidato")
	public ResponseEntity<List<Candidato>> rankCandidatoContaining(@RequestParam(value="skill", defaultValue = "") String skill){
		System.out.println(skill);
		skill = URL.decodeParam(skill);
		System.out.println(skill);
		List<Candidato> list = service.findBySkillContaining(skill);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping
	public ResponseEntity<List<Skill>> findAll(){
		List<Skill> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Skill> findById(@PathVariable Integer id){
		Skill obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value = "/{id}")
	public ResponseEntity<Skill> insert(@RequestBody Skill obj, @PathVariable Integer id){
		Candidato candidato = candidatoService.findById(id);
		obj.setCandidato(candidato);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSkill()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Skill> udate(@PathVariable Integer id, @RequestBody Skill obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

}
