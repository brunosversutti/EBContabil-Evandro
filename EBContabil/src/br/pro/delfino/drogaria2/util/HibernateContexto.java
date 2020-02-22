package br.pro.delfino.drogaria2.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent evento) {
		// TODO Auto-generated method stub
		HibernateUtil.getFabricaDeSessoes(); 
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		HibernateUtil.getFabricaDeSessoes().close();
	}	
	
}
