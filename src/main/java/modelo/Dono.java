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
		@NamedQuery(name = "Dono.recuperaUmDonoEAnimais", query = "select n from Dono n left outer join fetch n.animais where n.id = ?1"),
		@NamedQuery(name = "Dono.recuperaListaDeDonos", query = "select n from Dono n order by n.id"),
		@NamedQuery(name = "Dono.recuperaListaDeDonosEAnimais", query = "select distinct n from Dono n left outer join fetch n.animais order by n.id asc"),
		@NamedQuery(name = "Dono.recuperaConjuntoDeDonosEAnimais", query = "select n from Dono n left outer join fetch n.animais order by n.id asc"),
		@NamedQuery(name = "Dono.recuperaPrimeiroDono", query = "select n from Dono n order by n.nome asc") })

@Entity
@Table(name = "DONO")

public class Dono {
	private Long id;
	private String nome;
	private String telefone;
	private String email;
	private String endereco;

	

	private List<Animal> animais = new ArrayList<Animal>();

	// ********* Construtores *********

	public Dono() {
	}

	public Dono(String nome, String tipo_vendido, String email, String endereco) {
		this.nome = nome;
		this.telefone = tipo_vendido;
		this.email = email;
		this.endereco = endereco;
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

	@Column(name = "TELEFONE")
	public String getTelefone() {
		return telefone;
	}
	
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}
	
	@Column(name = "ENDERECO")
	public String getEndereco() {
		return endereco;
	}

	// ********* Métodos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	// ********* Métodos para Associações *********

	@OneToMany(mappedBy = "dono")
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
