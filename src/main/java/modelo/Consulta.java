package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "Consulta.recuperaListaDeConsultas", query = "select i from Consulta i order by i.id"),
		@NamedQuery(name = "Consulta.recuperaUltimoConsulta", query = "select i from Consulta i where i.animal = ?1 order by i.id desc"),
		@NamedQuery(name = "Consulta.recuperaUmConsultaComAnimal", query = "select i from Consulta i left outer join fetch i.animal where i.id = ?1") })

/* ==> Falta acrescentar a busca Lance.recuperaUmLanceComProduto */

@Entity
@Table(name = "CONSULTA")

public class Consulta {
	private Long id;
	String dataConsulta;
	String horario;
	String protocolo;
	

	// Um lance se refere a um único produto

	private Animal animal;
	// ********* Construtores *********

	public Consulta() {
	}

	public Consulta(String dataConsulta, String horario, String protocolo) {
		this.dataConsulta = dataConsulta;
		this.horario = horario;
		this.protocolo = protocolo;
	}

	public Consulta(String dataConsulta, String horario, String protocolo, Animal animal) {
		this.dataConsulta = dataConsulta;
		this.horario = horario;
		this.protocolo = protocolo;
		this.animal = animal;
	}

	// ********* Métodos do Tipo Get *********

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")

	public Long getId() {
		return id;
	}

	
	@Column(name = "DATA_CONSULTA")
	public String getDataConsulta() {
		return dataConsulta;
	}
	
	@Column(name = "HORARIO")
	public String getHorario() {
		return horario;
	}
	
	@Column(name = "PROTOCOLO")
	public String getProtocolo() {
		return protocolo;
	}

	

	// ********* Métodos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	
	// ********* Métodos para Associações *********

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ANIMAL_ID")
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	
	
}