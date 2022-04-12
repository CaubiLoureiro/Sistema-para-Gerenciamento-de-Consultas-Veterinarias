package modelo;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = "Animal.recuperaUmAnimalEConsultas", query = "select j from Animal j left outer join fetch j.consultas where j.id = ?1"),
		@NamedQuery(name = "Animal.recuperaListaDeAnimais", query = "select j from Animal j order by j.id"),
		@NamedQuery(name = "Animal.recuperaListaDeAnimaisEConsultas", query = "select distinct j from Animal j left outer join fetch j.consultas order by j.id asc"),
		@NamedQuery(name = "Animal.recuperaConjuntoDeAnimaisEConsultas", query = "select j from Animal j left outer join fetch j.consultas order by j.id asc"),
		@NamedQuery(name = "Animal.recuperaPrimeiroAnimal", query = "select j from Animal j order by j.nome asc") })

@Entity
@Table(name = "ANIMAL")

public class Animal {
	private Long id;
	private String nome;
	private String dataNasc;
	private String raca;

	// Um jogador possui inventarios

	private List<Consulta> consultas = new ArrayList<Consulta>();
	private Dono dono;
	private Especie especie;

	// ********* Construtores *********

	public Animal() {
	}

	public Animal(String nome, String nivel, String classe, Dono dono, Especie especie) {
		this.nome = nome;
		this.dataNasc = nivel;
		this.raca = classe;
		this.dono = dono;
		this.especie = especie;
	}

	// ********* Métodos do Tipo Get *********

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	@Column(name = "NOME")
	public String getNome() {
		return nome;
	}

	@Column(name = "DATA_NASC")
	public String getDataNasc() {
		return dataNasc;
	}
	
	@Column(name = "RACA")
	public String getRaca() {
		return raca;
	}
	

	// ********* Métodos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	public void setRaca(String raca) {
		this.raca = raca;
	}

	// ********* Métodos para Associações *********

	@OneToMany(mappedBy = "animal")
	@OrderBy
	/*
	 * Com o atributo mappedBy. Sem ele a JPA irá procurar pela tabela
	 * PRODUTO_LANCE. Com ele, ao se tentar recuperar um produto e todos os seus
	 * lances, o join de PRODUTO e LANCE irá acontecer através da chave estrangeira
	 * existente em LANCE.
	 */
	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DONO_ID")
	public Dono getDono() {
		return dono;
	}

	public void setDono(Dono dono) {
		this.dono = dono;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ESPECIE_ID")
	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}
}
