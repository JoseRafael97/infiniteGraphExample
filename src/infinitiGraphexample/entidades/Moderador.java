package infinitiGraphexample.entidades;

public class Moderador extends Pessoa{
	
	private boolean superUsuario;
	
	public Moderador(String nome, String sexo, int idade) {
		super(nome,sexo,idade);
	}

	public boolean isSuperUsuario() {
		fetch();
		return superUsuario;
	}

	public void setSuperUsuario(boolean superUsuario) {
		markModified();
		this.superUsuario = superUsuario;
	}
}
