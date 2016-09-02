package infinitiGraphexample.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.infinitegraph.AccessMode;
import com.infinitegraph.ConfigurationException;
import com.infinitegraph.EdgeKind;
import com.infinitegraph.GraphDatabase;
import com.infinitegraph.Query;
import com.infinitegraph.StorageException;
import com.infinitegraph.Transaction;

import infinitiGraphexample.entidades.ChatDeBatePapo;
import infinitiGraphexample.entidades.MensagemPrivada;
import infinitiGraphexample.entidades.Pessoa;

public class GrafoDAO {

	private GraphDatabase grafo;
	private Transaction tx;

	public GrafoDAO() {
		try {
			grafo = InfinitiGraphFactory.getGraph();
			tx = grafo.beginTransaction(AccessMode.READ_WRITE);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Adiciona no grafo um novo nó
	 * 
	 * @param membro
	 * @throws ConfigurationException
	 * @throws StorageException
	 */
	public void adicionaPessoa(Pessoa pessoa) throws StorageException, ConfigurationException {

		try {
			grafo.addVertex(pessoa);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
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
		try {
			grafo.nameVertex(pessoa.getNome(), pessoa);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
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
		adicionarConexaoMensagem(p1, p2, msg, EdgeKind.OUTGOING);
	}

	/**
	 * Cria uma conexao entre p1 e p2 com aresta Mensagem privada de forma
	 * biderecional
	 * 
	 * @param p1
	 * @param p2
	 * @param msg
	 * @throws StorageException
	 * @throws ConfigurationException
	 */
	public void adicionarConexaoMensagemBidericional(Pessoa p1, Pessoa p2, MensagemPrivada msg)
			throws StorageException, ConfigurationException {
		adicionarConexaoMensagem(p1, p2, msg, EdgeKind.BIDIRECTIONAL);
	}

	/**
	 * Método utilizado pelo método de criar conexao unidirecional e
	 * bidirecional
	 * 
	 * @param p1
	 * @param p2
	 * @param msg
	 * @param modoAcesso
	 * @param tipoRelacao
	 * @throws StorageException
	 * @throws ConfigurationException
	 */
	private void adicionarConexaoMensagem(Pessoa p1, Pessoa p2, MensagemPrivada msg ,
			EdgeKind tipoRelacao) throws StorageException, ConfigurationException {
		
		try {
			p1.addEdge(msg, p2, tipoRelacao, (short) 0);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} 
	}

	public void adicionarConexaoCharUnidericional(Pessoa p1, Pessoa p2, ChatDeBatePapo chat)
			throws StorageException, ConfigurationException {
		adicionarConexaoChat(p1, p2, chat, AccessMode.READ_WRITE, EdgeKind.OUTGOING);

	}

	public void adicionarConexaoCharBiderecional(Pessoa p1, Pessoa p2, ChatDeBatePapo chat)
			throws StorageException, ConfigurationException {
		adicionarConexaoChat(p1, p2, chat, AccessMode.READ_WRITE, EdgeKind.BIDIRECTIONAL);
	}

	private void adicionarConexaoChat(Pessoa p1, Pessoa p2, ChatDeBatePapo chat, AccessMode modoAcesso,
			EdgeKind tipoRelacao) throws StorageException, ConfigurationException {

		grafo = InfinitiGraphFactory.getGraph();
		tx = grafo.beginTransaction(modoAcesso);

		try {
			p1.addEdge(chat, p2, tipoRelacao, (short) 0);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} 
	}

	public List<Pessoa> buscarTodos() throws StorageException, ConfigurationException {

		grafo = InfinitiGraphFactory.getGraph();

		Transaction tx = grafo.beginTransaction(AccessMode.READ_WRITE);

		Query<Pessoa> memberQuery = grafo.createQuery(Pessoa.class.getName(), "name=='Rafael'");
		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		try {
			Iterator<Pessoa> memberItr = memberQuery.execute();
			while (memberItr.hasNext()) {
				Pessoa myMember = memberItr.next();
				pessoas.add(myMember);

			}
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} 
		return pessoas;
	}

	public void fecharConnection() {
		tx.complete();
		grafo.close();
	}

}
