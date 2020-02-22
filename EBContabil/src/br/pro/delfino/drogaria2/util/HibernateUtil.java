package br.pro.delfino.drogaria2.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	// variavel global
	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}

	public static Connection getConexao() {
		Session sessao = fabricaDeSessoes.openSession();
		Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
			@Override
			public Connection execute(Connection conn) throws SQLException {

				return conn;
			}
		});
		return conexao;
	}

	private static SessionFactory criarFabricaDeSessoes() {
		try {

			// nessa linha ele busca o arquivo cfg e lê todas suas linhas
			// - ou seja, carrega todas as suas propriedades
			Configuration configuracao = new Configuration().configure("hibernate.cfg.xml");

			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties())
					.build();

			// variavel local
			SessionFactory fabrica = configuracao.buildSessionFactory(registro);

			return fabrica;

		} catch (Throwable ex) {
			System.err.println("A fábrica de sessoes nao pode ser criada." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
