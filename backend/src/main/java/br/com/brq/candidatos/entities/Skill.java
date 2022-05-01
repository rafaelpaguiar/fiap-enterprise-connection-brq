package br.com.brq.candidatos.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.brq.candidatos.entities.enums.Nivel;

@Entity
@Table(name = "tbl_skill")
public class Skill {

	@Id
	@SequenceGenerator(name = "skills", sequenceName = "sq_tbl_skill", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skills")
	@Column(name = "id_skill")
	private Integer idSkill;

	@Column(name = "skill")
	private String skill;

	@Column(name = "nivel")
	@Enumerated(EnumType.STRING)
	private Nivel nivel;

	@ManyToOne
	@JoinColumn(name = "id_candidato")
	@JsonIgnore
	private Candidato candidato;

	public Skill() {
	}

	public Skill(Integer idSkill, String skill, Nivel nivel, Candidato candidato) {
		super();
		this.idSkill = idSkill;
		this.skill = skill;
		this.nivel = nivel;
		this.candidato = candidato;
	}

	public Integer getIdSkill() {
		return idSkill;
	}

	public void setIdSkill(Integer idSkill) {
		this.idSkill = idSkill;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

}
