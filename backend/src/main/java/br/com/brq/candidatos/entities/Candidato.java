package br.com.brq.candidatos.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.com.brq.candidatos.entities.enums.Genero;


@Entity(name = "candidato")
@Table(name = "tbl_candidatos")
public class Candidato {
	
	@Id
	@SequenceGenerator(name = "candidatos", sequenceName = "sq_tbl_candidato", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidatos")
	@Column(name = "id_candidato")
	private Integer idCandidato;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "genero")
	@Enumerated(EnumType.STRING)
	private Genero genero;
	
	@Column(name = "data_de_nasc")
	@Temporal(TemporalType.DATE)
	private Date dataDeNascimento;
	
	
	@OneToMany(mappedBy = "candidato")
	@Cascade(CascadeType.ALL)
	private List<Certificacao> certificados = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "candidato")
	private List<Skill> skill = new ArrayList<>();

	public Candidato() {
	}

	public Candidato(Integer idCandidato, String nome, String cpf, String email, String telefone, Genero genero,
			Date dataDeNascimento,List<Certificacao> certificados, List<Skill> skill) {
		this.idCandidato = idCandidato;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.genero = genero;
		this.dataDeNascimento = dataDeNascimento;
		this.certificados = certificados;
		this.skill = skill;
	}

	public Integer getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public List<Certificacao> getCertificados() {
		return certificados;
	}

	public void setCertificados(List<Certificacao> certificados) {
		this.certificados = certificados;
	}

	public List<Skill> getSkill() {
		return skill;
	}

	public void setSkill(List<Skill> skill) {
		this.skill = skill;
	}

}
