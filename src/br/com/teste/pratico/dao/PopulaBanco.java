package br.com.teste.pratico.dao;

import javax.persistence.EntityManager;

public class PopulaBanco {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		
		em.getTransaction().commit();
		em.close();
		
	}

}
