package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = "Especie.recuperaUmEspecieEAnimais", query = "select n from Especie n left outer join fetch n.animais where n.id = ?1"),
		@NamedQuery(name = "Especie.recuperaListaDeEspecies", query = "select n from Especie n order by n.id"),
		@NamedQuery(name = "Especie.recuperaListaDeEspeciesEAnimais", query = "select distinct n from Especie n left outer join fetch n.animais order by n.id asc"),
		@NamedQuery(name = "Especie.recuperaConjuntoDeEspeciesEAnimais", query = "select n from Especie n left outer join fetch n.animais order by n.id asc"),
		@NamedQuery(name = "Especie.recuperaPrimeiroEspecie", query = "select n from Especie n order by n.nome asc") })

@Entity
@Table(name = "ESPECIE")

public class Especie {
	private Long id;
	private String nome;
	
	

	private List<Animal> animais = new ArrayList<Animal>();

	// ********* Construtores *********

	public Especie() {
	}

	public Especie(String nome) {
		this.nome = nome;
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



	// ********* Métodos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	


	// ********* Métodos para Associações *********

	@OneToMany(mappedBy = "especie")
	@OrderBy
	/*
	 * Com o atributo mappedBy. Sem ele a JPA irá procurar pela tabela
	 * PRODUTO_LANCE. Com ele, ao se tentar recuperar um produto e todos os seus
	 * lances, o join de PRODUTO e LANCE irá acontecer através da chave estrangeira
	 * existente em LANCE.
	 */
	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	

	
}
