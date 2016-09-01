package infinitiGraphexample.entidades;

import com.infinitegraph.BaseVertex;

public class Pessoa extends BaseVertex{
	
	private String nome;
	private String sexo;
	private int idade;
	
	public Pessoa(String nome, String sexo, int idade) {
		this.idade = idade;
		this.nome = nome;
		this.sexo = sexo;
	}
	
	public String getNome() {
		fetch();
		return nome;
	}
	
	public void setNome(String nome) {
		markModified();
		this.nome = nome;
	}
	
	public String getSexo() {
		fetch();
		return sexo;
	}
	
	public void setSexo(String sexo) {
		markModified();
		this.sexo = sexo;
	}

	public int getIdade() {
		markModified();
		return idade;
	}

	public void setIdade(int idade) {
		fetch();
		this.idade = idade;
	}

	@Override
	public String toString() {
		fetch();
		return "Pessoa [nome=" + nome + ", sexo=" + sexo + ", idade=" + idade + "]";
	}

	
	
	
}
