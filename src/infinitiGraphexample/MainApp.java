package infinitiGraphexample;

import java.util.Calendar;

import com.infinitegraph.ConfigurationException;
import com.infinitegraph.StorageException;

import infinitiGraphexample.dao.GrafoDAO;
import infinitiGraphexample.dao.InfinitiGraphFactory;
import infinitiGraphexample.entidades.Membro;
import infinitiGraphexample.entidades.MensagemPrivada;

public class MainApp {

	public static void main(String[] args) throws StorageException, ConfigurationException {

		InfinitiGraphFactory.deleteGrafo();
		// InfinitiGraphFactory.criarDataBase();

		Membro me1 = new Membro("Rafael", "Masculino", 16, 16, "ralf");

		Membro me2 = new Membro("Rafael2", "Masculino", 16, 16, "ralf2");

		Membro me3 = new Membro("Rafael3", "Masculino", 16, 16, "ralf3");

		Membro me4 = new Membro("Rafael4", "Masculino", 16, 16, "ralf4");

		MensagemPrivada mensagemPrivada = new MensagemPrivada("Olá Conseguiu rodar o InfiniteGraph?", "Não",
				Calendar.getInstance());
		
		MensagemPrivada mensagemPrivada2 = new MensagemPrivada("Olá Conseguiu rodar o InfiniteGraph?", "SIM",
				Calendar.getInstance());
		
		MensagemPrivada mensagemPrivad3 = new MensagemPrivada("Olá Conseguiu rodar o InfiniteGraph?", "Não, é uma merda!",
				Calendar.getInstance());


		try {

			GrafoDAO dao = new GrafoDAO();

			dao.iniciarTransacao();
			
			dao.adicionaPessoa(me1);

			System.out.println("CRIADO PESSOA 1");
			
			dao.adicionaPessoa(me2);
			
			System.out.println("CRIADO PESSOA 2");

			dao.adicionaPessoa(me3);
			
			System.out.println("CRIADO PESSOA 3");
			
			dao.adicionaPessoa(me4);
			
			System.out.println("CRIADO PESSOA 4");
			
			dao.adicionarConexaoMensagemUnidericional(me1, me2, mensagemPrivada);
			
			dao.adicionarConexaoMensagemUnidericional(me1, me3, mensagemPrivad3);

			dao.adicionarConexaoMensagemBidericional(me1, me4, mensagemPrivada2);


			
			System.out.println("ADICIONADO CONEXAO DE PESSOA 1 com PESSOA2 E ADICIONADO UMA ARESTA");
			
			dao.colocarNoComoRaiz(me1);
			
			System.out.println("COLOCADO PESSOA 1 COMO NÓ ROOT");
			
			System.out.println(dao.buscarTodos());
			
			dao.fecharConexao();
		
		} catch (StorageException e) {
			e.printStackTrace();

		} catch (ConfigurationException e) {
			e.printStackTrace();

		}

	}

}
