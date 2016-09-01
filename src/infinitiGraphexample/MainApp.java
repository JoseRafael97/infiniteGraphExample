package infinitiGraphexample;

import java.util.Calendar;

import com.infinitegraph.AccessMode;
import com.infinitegraph.ConfigurationException;
import com.infinitegraph.GraphDatabase;
import com.infinitegraph.GraphFactory;
import com.infinitegraph.StorageException;
import com.infinitegraph.Transaction;

import infinitiGraphexample.dao.GrafoDAO;
import infinitiGraphexample.dao.InfinitiGraphFactory;
import infinitiGraphexample.entidades.Membro;
import infinitiGraphexample.entidades.MensagemPrivada;
import infinitiGraphexample.entidades.Pessoa;

public class MainApp {

	public static void main(String[] args) throws StorageException, ConfigurationException {

		GrafoDAO dao = new GrafoDAO();

		GraphFactory.delete("chat", "config/confLocalDosDados.properties");

		GraphFactory.create("chat", "config/confLocalDosDados.properties");

		Membro me1 = new Membro("Rafael", "Masculino", 16, 16, "ralf");

		Membro me2 = new Membro("Rafael2", "Masculino", 16, 16, "ralf2");

		Membro me3 = new Membro("Rafael3", "Masculino", 16, 16, "ralf3");

		Membro me4 = new Membro("Rafael4", "Masculino", 16, 16, "ralf4");

		MensagemPrivada mensagemPrivada = new MensagemPrivada("Olá COnseguiu rodar o InfiniteGraph?", "Não",
				Calendar.getInstance());

		try {
			

			dao.adicionaPessoa(me1);

			dao.adicionaPessoa(me2);

			dao.adicionaPessoa(me3);

			dao.adicionaPessoa(me4);
			
			dao.adicionarConexaoMensagemUnidericional((Pessoa)me1, (Pessoa)me2, mensagemPrivada);


			// System.out.println(dao.buscarTodos());

		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigurationException e) {
			InfinitiGraphFactory.criarDataBase();
			e.printStackTrace();
		}

	}

}
