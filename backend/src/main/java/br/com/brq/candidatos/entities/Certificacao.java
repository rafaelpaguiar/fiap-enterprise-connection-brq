package br.com.brq.candidatos.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tbl_certificacao")
public class Certificacao {

	@Id
	@SequenceGenerator(name = "certicacoes", sequenceName = "sq_tbl_certicacao", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certificacoes")
	@Column(name = "id_certificacao")
	private Integer idCeritificacao;
	
	@Column(name = "certificado")
	private String certificado;
	
	@Column(name = "data_emissao")
	@Temporal(TemporalType.DATE)
	private Date dtEmissao;
	
	@Column(name = "data_expiracao")
	@Temporal(TemporalType.DATE)
	private Date dtExpiracao;
	
	@Column(name = "certificadora")
	private String certificadora;

	@ManyToOne
	@JoinColumn(name = "id_candidato")
	@JsonIgnore
	private Candidato candidato;
	
	public Certificacao() {
	}

	public Certificacao(Integer idCeritificacao, String certificado, Date dtEmissao, Date dtExpiracao,
			String certificadora, Candidato candidato) {
		this.idCeritificacao = idCeritificacao;
		this.certificado = certificado;
		this.dtEmissao = dtEmissao;
		this.dtExpiracao = dtExpiracao;
		this.certificadora = certificadora;
		this.candidato = candidato;
	}

	public Integer getIdCeritificacao() {
		return idCeritificacao;
	}

	public void setIdCeritificacao(Integer idCeritificacao) {
		this.idCeritificacao = idCeritificacao;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public Date getDtEmissao() {
		return dtEmissao;
	}

	public void setDtEmissao(Date dtEmissao) {
		this.dtEmissao = dtEmissao;
	}

	public Date getDtExpiracao() {
		return dtExpiracao;
	}

	public void setDtExpiracao(Date dtExpiracao) {
		this.dtExpiracao = dtExpiracao;
	}

	public String getCertificadora() {
		return certificadora;
	}

	public void setCertificadora(String certificadora) {
		this.certificadora = certificadora;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	

}
