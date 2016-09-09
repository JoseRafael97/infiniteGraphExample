package br.edu.ifpb.infinitiGraphexample.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import com.infinitegraph.Vertex;
import com.infinitegraph.navigation.Guide;
import com.infinitegraph.navigation.Navigator;
import com.infinitegraph.navigation.Path;
import com.infinitegraph.navigation.Qualifier;
import com.infinitegraph.navigation.handlers.JSONOutputResultHandler;
import com.infinitegraph.navigation.handlers.PathCollector;
import com.infinitegraph.navigation.handlers.PrintResultHandler;
import com.infinitegraph.navigation.qualifiers.EdgeTypes;
import com.infinitegraph.navigation.qualifiers.VertexIdentifier;
import com.infinitegraph.navigation.qualifiers.VertexPredicate;

import br.edu.ifpb.infinitiGraphexample.entidades.ChatDeBatePapo;
import br.edu.ifpb.infinitiGraphexample.entidades.MensagemPrivada;
import br.edu.ifpb.infinitiGraphexample.entidades.Pessoa;
import br.edu.ifpb.infinitiGraphexample.entidades.SuperAresta;

public class GrafoDAO {

	private GraphDatabase grafo;
	private Transaction tx;

	/**
	 * Método que criar uma transação e uma instância do GRAFO, para serem usada
	 * nos métodos.
	 * 
	 * @throws StorageException
	 * @throws ConfigurationException
	 */
	public void iniciarTransacao() throws StorageException, ConfigurationException {
		try {
			InfinitiGraphFactory.criarDataBase();
			grafo = InfinitiGraphFactory.getGraph();
			System.out.println("____ GRAFO CRIADO ___");
		} catch (StorageException e) {
			InfinitiGraphFactory.criarDataBase();
		} catch (ConfigurationException e) {

			e.printStackTrace();
		}

		this.tx = grafo.beginTransaction(AccessMode.READ_WRITE);
		System.out.println("____ TRANSAÇÂO INICIADA ___");
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

		} catch (Exception e) {
			e.printStackTrace();
			this.tx.rollback();
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

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	/**
	 * Adiciona Uma conexão para Pessoa 1 de forma unidericional com uma
	 * mensagem, considerando da pessoa 1 para p2. Para funcionamento correto do
	 * método, ele deve ser executado na mesma transação do adicionamento dos
	 * nos
	 * 
	 * @param p1
	 * @param p2
	 * @param chat
	 * @throws StorageException
	 * @throws ConfigurationException
	 */
	public void adicionarConexaoMensagemUnidericional(Pessoa p1, Pessoa p2, MensagemPrivada msg)
			throws StorageException, ConfigurationException {
		adicionarConexaoMensagem(p1, p2, msg, AccessMode.READ_WRITE, EdgeKind.OUTGOING);
	}

	/**
	 * Adiciona Uma conexão para Pessoa 1 de forma Biderecional adicionando a
	 * mensagem trocada por eles. Para funcionamento correto do método, ele deve
	 * ser executado na mesma transação do adicionamento dos nos
	 * 
	 * @param p1
	 * @param p2
	 * @param chat
	 * @throws StorageException
	 * @throws ConfigurationException
	 */
	public void adicionarConexaoMensagemBidericional(Pessoa p1, Pessoa p2, MensagemPrivada msg)
			throws StorageException, ConfigurationException {
		adicionarConexaoMensagem(p1, p2, msg, AccessMode.READ_WRITE, EdgeKind.BIDIRECTIONAL);
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
	private void adicionarConexaoMensagem(Pessoa p1, Pessoa p2, MensagemPrivada msg, AccessMode modoAcesso,
			EdgeKind tipoRelacao) throws StorageException, ConfigurationException {

		try {
			p1.addEdge(msg, p2, tipoRelacao, (short) 0);

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	/**
	 * Adiciona Uma conexão para Pessoa 1 de forma unidericional com instacia do
	 * chat, considerando da pessoa 1 para p2. Para funcionamento correto do
	 * método, ele deve ser executado na mesma transação do adicionamento dos
	 * nos
	 * 
	 * @param p1
	 * @param p2
	 * @param chat
	 * @throws StorageException
	 * @throws ConfigurationException
	 */
	public void adicionarConexaoCharUnidericional(Pessoa p1, Pessoa p2, ChatDeBatePapo chat)
			throws StorageException, ConfigurationException {
		adicionarConexaoChat(p1, p2, chat, AccessMode.READ_WRITE, EdgeKind.OUTGOING);

	}

	/**
	 * Adiciona Uma conexão para Pessoa 1 de forma Biderecional com uma instacia
	 * do chat. Para funcionamento correto do método, ele deve ser executado na
	 * mesma transação do adicionamento dos nos
	 * 
	 * @param p1
	 * @param p2
	 * @param chat
	 * @throws StorageException
	 * @throws ConfigurationException
	 */
	public void adicionarConexaoCharBiderecional(Pessoa p1, Pessoa p2, ChatDeBatePapo chat)
			throws StorageException, ConfigurationException {
		adicionarConexaoChat(p1, p2, chat, AccessMode.READ_WRITE, EdgeKind.BIDIRECTIONAL);
	}

	/**
	 * Adiciona uma conexão de uma pessoa 1 para outra 2 adicionando a aresta
	 * com instância do chat.
	 * 
	 * @param p1
	 * @param p2
	 * @param chat
	 * @param modoAcesso
	 * @param tipoRelacao
	 * @throws StorageException
	 * @throws ConfigurationException
	 */
	private void adicionarConexaoChat(Pessoa p1, Pessoa p2, ChatDeBatePapo chat, AccessMode modoAcesso,
			EdgeKind tipoRelacao) throws StorageException, ConfigurationException {

		try {
			p1.addEdge(chat, p2, tipoRelacao, (short) 0);

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	/**
	 * Busca uma pessoa com nome passado
	 * 
	 * @param nome
	 * @return
	 * @throws StorageException
	 * @throws ConfigurationException
	 */
	public Pessoa buscarPorNome(String nome) throws StorageException, ConfigurationException {

		Query<Pessoa> query = grafo.createQuery(Pessoa.class.getName(), "nome='" + nome + "'");
		Pessoa pessoa = null;

		try {
			Iterator<Pessoa> memberItr = query.execute();

			while (memberItr.hasNext()) {
				pessoa = memberItr.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return pessoa;
	}

	/**
	 * 
	 * @return
	 */
	public List<Pessoa> buscarTodos() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		try {
			Iterator<Vertex> memberItr = grafo.getVertices().iterator();

			while (memberItr.hasNext()) {
				pessoas.add((Pessoa) memberItr.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return pessoas;
	}

	/**
	 * Busca pessoas ligada a pessoas passada
	 * 
	 * @param pessoa
	 */
	public void buscarPessoasLigadasDiretamenteOuIndiretamenteLogger(Pessoa pessoa) {
		PrintResultHandler printResults = new PrintResultHandler();

		VertexPredicate myVertexPred = new VertexPredicate(grafo.getTypeId(Pessoa.class.getName()),
				"nome=~'" + pessoa.getNome() + "'");

		Navigator myNavigator = pessoa.navigate(null, Guide.SIMPLE_BREADTH_FIRST, Qualifier.FOREVER, myVertexPred, null,
				printResults);

		myNavigator.getResultHandler();

	}

	/**
	 * Busca pessoas ligada a pessoas passada
	 * 
	 * @param pessoa
	 */
	public List<Pessoa> buscarPessoasLigadasOuIndiretamentellection(Pessoa pessoa) {

		PathCollector resultPaths = new PathCollector();

		Navigator myNavigator = pessoa.navigate(null, Guide.SIMPLE_BREADTH_FIRST, Qualifier.FOREVER, Qualifier.ANY,
				null, resultPaths);

		myNavigator.start();
		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		for (Path path : resultPaths.getPaths()) {
			pessoas.add((Pessoa) path.getFinalHop().getVertex());
			// Perform operation on result path, for example:
			// logger.info("Preceding edge:" +
			// path.getFinalHop().getEdge().getClass().getName());
		}
		return pessoas;
	}

	/**
	 * Busca pessoas ligada a pessoas passada
	 * 
	 * @param pessoa
	 * @throws FileNotFoundException
	 */
	public void buscarPessoasLigadasDiretamenteOuIndiretamenteJSON(Pessoa pessoa) {
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream("consultas/json.txt");
			JSONOutputResultHandler json = new JSONOutputResultHandler(stream);

			Navigator navigator = pessoa.navigate(null, Guide.SIMPLE_BREADTH_FIRST, Qualifier.FOREVER, Qualifier.ANY,
					null, json);
			navigator.start();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception e) {
				}
			}

		}

	}

	/**
	 * Método que busca as mensagens enviadas pela pessoa1 para pessoa 2
	 * 
	 * @return
	 */
	public List<SuperAresta> mensagemTrocadaDaEntreDuasPessoas(Pessoa pessoa, Pessoa pessoa2) {

		PathCollector resultPaths = new PathCollector();

		EdgeTypes myEdgeTypes = new EdgeTypes(grafo.getTypeId(MensagemPrivada.class.getName()));
		VertexIdentifier myDanaVertex = new VertexIdentifier(pessoa);

		Navigator myNavigator = pessoa2.navigate(null, null, myEdgeTypes, myDanaVertex, null, resultPaths);

		myNavigator.start();
		List<SuperAresta> chatsDeBatePapo = new ArrayList<SuperAresta>();

		for (Path path : resultPaths.getPaths()) {
			chatsDeBatePapo.add((SuperAresta) path.getFinalHop().getEdge());
		}
		return chatsDeBatePapo;
	}
	
	

	/**
	 * Busca pessoas ligada a pessoas passada
	 * 
	 * @param pessoa
	 */
	public List<Pessoa> buscarPessoasLigadasCollection(Pessoa pessoa) {

		PathCollector resultPaths = new PathCollector();

		Navigator myNavigator = pessoa.navigate(null, Guide.SIMPLE_BREADTH_FIRST, Qualifier.FOREVER, Qualifier.ANY,
				null, resultPaths);

		myNavigator.start();
		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		for (Path path : resultPaths.getPaths()) {
			pessoas.add((Pessoa) path.getFinalHop().getVertex());
		}
		return pessoas;
	}


	/**
	 * Fecha a trasação e faz commit e fecha o banco grafo
	 */
	public void fecharConexao() {
		tx.commit();
		tx.complete();
		grafo.close();
	}

}
