package br.pro.delfino.drogaria.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.pro.delfino.drogaria2.util.HibernateUtil;

public class GenericDAO<Entidade> {

	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // inicia a transacao
// qualquer coisa que for feito a partir dessa linha, se der algum problema, ele desfaz
			sessao.save(entidade);
			transacao.commit(); // confirma a transação, termina a transacao

		} catch (RuntimeException erro) {

			if (transacao != null) {
// aqui só e diferente de null quando "transacao = sessao.beginTransaction();" deu certo
				transacao.rollback();
			}
			throw erro; // repropaga o erro
		} finally {
			sessao.close(); // encerra a sessao
		}
	}

	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			 Criteria consulta = sessao.createCriteria(classe);
			 List<Entidade> resultado = consulta.list();
			 return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		}finally {
			sessao.close(); // encerra a sessao
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listar(String campoOrdenacao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			 Criteria consulta = sessao.createCriteria(classe);
			 consulta.addOrder(Order.asc(campoOrdenacao));
			 List<Entidade> resultado = consulta.list();
			 return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		}finally {
			sessao.close(); // encerra a sessao
		}
	}
	
	@SuppressWarnings("unchecked")
	public Entidade buscar(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			 Criteria consulta = sessao.createCriteria(classe);
			 consulta.add(Restrictions.idEq(codigo)); // esse é a clausula where do banco
			 Entidade resultado = (Entidade) consulta.uniqueResult();
			 return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		}finally {
			sessao.close(); // encerra a sessao
		}
	}
	
	public void excluir(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // inicia a transacao
// qualquer coisa que for feito a partir dessa linha, se der algum problema, ele desfaz
			sessao.delete(entidade);
			transacao.commit(); // confirma a transação, termina a transacao

		} catch (RuntimeException erro) {

			if (transacao != null) {
// aqui só e diferente de null quando "transacao = sessao.beginTransaction();" deu certo
				transacao.rollback();
			}
			throw erro; // repropaga o erro
		} finally {
			sessao.close(); // encerra a sessao
		}
	}
	
	public void editar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // inicia a transacao
     // qualquer coisa que for feito a partir dessa linha, se der algum problema, ele desfaz
			sessao.update(entidade);
			transacao.commit(); // confirma a transação, termina a transacao

		} catch (RuntimeException erro) {

			if (transacao != null) {
    // aqui só e diferente de null quando "transacao = sessao.beginTransaction();" deu certo
				transacao.rollback();
			}
			throw erro; // repropaga o erro
		} finally {
			sessao.close(); // encerra a sessao
		}
	}
	
	@SuppressWarnings("unchecked")
	public Entidade merge(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // inicia a transacao
			Entidade retorno = (Entidade) sessao.merge(entidade);
			transacao.commit(); // confirma a transação, termina a transacao
			return retorno;
			
		} catch (RuntimeException erro) {

			if (transacao != null) {
				transacao.rollback();
			}
			throw erro; // repropaga o erro
		} finally {
			sessao.close(); // encerra a sessao
		}
	}
}
