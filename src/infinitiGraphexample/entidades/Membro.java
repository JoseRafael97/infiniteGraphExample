package infinitiGraphexample.entidades;

public class Membro extends Pessoa{

	private int seguidores;
	private String nomeUsuario;
	
	public Membro(String nome, String sexo, int idade, int seguidores, String nomeUsuario) {
		super(nome, sexo, idade);
		this.seguidores = seguidores;
		this.nomeUsuario = nomeUsuario;
	}


	public int getSeguidores() {
		fetch();
		return seguidores;
	}

	public void setSeguidores(int seguidores) {
		markModified();
		this.seguidores = seguidores;
	}


	public String getNomeUsuario() {
		fetch();
		return nomeUsuario;
	}


	public void setNomeUsuario(String nomeUsuario) {
		markModified();
		this.nomeUsuario = nomeUsuario;
	}


	@Override
	public String toString() {
		fetch();
		return "Membro [seguidores=" + seguidores + ", nomeUsuario=" + nomeUsuario + "]";
	}
	
	
	
	
	

}
