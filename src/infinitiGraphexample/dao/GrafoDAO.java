package infinitiGraphexample.dao;

import com.infinitegraph.AccessMode;
import com.infinitegraph.ConfigurationException;
import com.infinitegraph.EdgeKind;
import com.infinitegraph.GraphDatabase;
import com.infinitegraph.StorageException;
import com.infinitegraph.Transaction;

import infinitiGraphexample.entidades.MensagemPrivada;
import infinitiGraphexample.entidades.Pessoa;

public class GrafoDAO {

	private GraphDatabase grafo;

	/**
	 * Adiciona no grafo um novo nó
	 * 
	 * @param membro
	 * @throws ConfigurationException
	 * @throws StorageException
	 */
	public void adicionaPessoa(Pessoa pessoa) throws StorageException, ConfigurationException {

		grafo = InfinitiGraphFactory.getGraph();
		Transaction tx = grafo.beginTransaction(AccessMode.READ_WRITE);

		try {
			grafo.addVertex(pessoa);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			tx.complete();
			grafo.close();
		}

	}

	/**
	 * Coloca o nó já criado como raiz para buscas pelo nome.
	 * 
	 * @param pessoa
	 * @throws ConfigurationException
	 * @throws StorageException
	 */
	public void colocarNoComoRaiz(Pessoa pessoa) throws StorageException, ConfigurationException {
		grafo = InfinitiGraphFactory.getGraph();
		Transaction tx = grafo.beginTransaction(AccessMode.READ_WRITE);

		try {
			grafo.nameVertex(pessoa.getNome(), pessoa);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			tx.complete();
			grafo.close();
		}
	}

	/**
	 * Cria um ligação entre p1 e p2 sendo p1 para p2 de forma unidericional
	 * 
	 * @param p1
	 * @param p2
	 * @param msg
	 * @throws StorageException
	 * @throws ConfigurationException
	 */
	public void adicionarConexaoMensagemUnidericional(Pessoa p1, Pessoa p2, MensagemPrivada msg)
			throws StorageException, ConfigurationException {
		adicionarConexaoMensagem(p1, p2, msg, AccessMode.READ_WRITE, EdgeKind.OUTGOING);
	}
	
	/**
	 * Cria uma conexao entre p1 e p2 com aresta Mensagem privada de forma biderecional
	 * @param p1
	 * @param p2
	 * @param msg
	 * @throws StorageException
	 * @throws ConfigurationException
	 */
	public void adicionarConexaoMensagemBidericional(Pessoa p1, Pessoa p2, MensagemPrivada msg)
			throws StorageException, ConfigurationException{
		adicionarConexaoMensagem(p1, p2, msg, AccessMode.READ_WRITE, EdgeKind.BIDIRECTIONAL);
	}

	private void adicionarConexaoMensagem(Pessoa p1, Pessoa p2, MensagemPrivada msg, AccessMode modoAcesso,
			EdgeKind tipoRelacao) throws StorageException, ConfigurationException {
		grafo = InfinitiGraphFactory.getGraph();
		Transaction tx = grafo.beginTransaction(modoAcesso);

		try {
			p1.addEdge(msg, p2, tipoRelacao, (short) 0);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			tx.complete();
			grafo.close();
		}
	}

}
