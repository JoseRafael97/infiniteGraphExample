package br.edu.ifpb.infinitiGraphexample;

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
		
		Membro me5 = new Membro("Rafael5", "Masculino", 16, 16, "ralf5");


		MensagemPrivada mensagemPrivada = new MensagemPrivada("Olá Conseguiu rodar o InfiniteGraph?", "Não",
				Calendar.getInstance());
		
		MensagemPrivada mensagemPrivada2 = new MensagemPrivada("Olá Conseguiu rodar o InfiniteGraph?", "SIM",
				Calendar.getInstance());
		
		MensagemPrivada mensagemPrivad3 = new MensagemPrivada("Olá Conseguiu rodar o InfiniteGraph?", "Não, é uma merda!",
				Calendar.getInstance());
		
		MensagemPrivada mensagemPrivada4 = new MensagemPrivada("Olá Conseguiu rodar o InfiniteGraph?", "Não, é uma merda!",
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
			
			dao.adicionaPessoa(me5);
			
			System.out.println("CRIADO PESSOA 5");

			
			dao.adicionarConexaoMensagemUnidericional(me1, me2, mensagemPrivada);
			
			dao.adicionarConexaoMensagemUnidericional(me1, me3, mensagemPrivad3);

			dao.adicionarConexaoMensagemBidericional(me1, me4, mensagemPrivada2);

			dao.adicionarConexaoMensagemBidericional(me4, me5, mensagemPrivada4);
			
			//dao.adicionarConexaoMensagemBidericional(me3, me5, mensagemPrivada);

			
			System.out.println("ADICIONADO CONEXAO DE PESSOA 1 com PESSOA2 E ADICIONADO UMA ARESTA");
			
			dao.colocarNoComoRaiz(me1);
			
			System.out.println("COLOCADO PESSOA 1 COMO NÓ ROOT");
			
			System.out.println(dao.buscarPorNome(me2.getNome()));
			
			System.out.println("TESTE?");
			
		//	VertexPredicate myVertexPred = new VertexPredicate(InfinitiGraphFactory.getGraph().getTypeId(Pessoa.class.getName()), "name=='Rafael4'");
			
			dao.buscarPessoasLigadasDiretamente(me1);
			
			System.out.println(dao.buscarTodos());
			
			dao.fecharConexao();
		
		} catch (StorageException e) {
			e.printStackTrace();

		} catch (ConfigurationException e) {
			e.printStackTrace();

		}

	}

}
