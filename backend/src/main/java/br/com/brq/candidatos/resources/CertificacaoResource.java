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
import br.com.brq.candidatos.entities.Certificacao;
import br.com.brq.candidatos.resources.util.URL;
import br.com.brq.candidatos.services.CandidatoService;
import br.com.brq.candidatos.services.CertificacaoService;

@RestController
@RequestMapping("certificacao")
public class CertificacaoResource {

	@Autowired
	private CertificacaoService service;
	
	@Autowired
	private CandidatoService candidatoService;

	@GetMapping
	public ResponseEntity<List<Certificacao>> findAll(){
		List<Certificacao> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/buscaPorCert")
	public ResponseEntity<List<Candidato>> findByCertificacaoContaining(@RequestParam(value="cert", defaultValue = "") String cert){
		System.out.println(cert);
		cert = URL.decodeParam(cert);
		System.out.println(cert);
		List<Candidato> list = service.findByCertContaining(cert);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Certificacao> findById(@PathVariable Integer id){
		Certificacao obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value = "/{id}")
	public ResponseEntity<Certificacao> insert(@RequestBody Certificacao obj, @PathVariable Integer id){
		Candidato candidato = candidatoService.findById(id);
		obj.setCandidato(candidato);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCeritificacao()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Certificacao> udate(@PathVariable Integer id, @RequestBody Certificacao obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}