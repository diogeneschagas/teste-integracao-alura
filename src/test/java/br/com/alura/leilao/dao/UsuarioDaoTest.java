package br.com.alura.leilao.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;

class UsuarioDaoTest {

	private UsuarioDao dao;

	@Test
	void deveriaEncontrarUsuarioCadastrado() {
		EntityManager em = JPAUtil.getEntityManager();
		this.dao = new UsuarioDao(em);
		
		Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
		Usuario encontrado = this.dao.buscarPorUsername(usuario.getNome());
		Assert.assertNotNull(encontrado);
	}
	
	@Test
	void naoDeveriaEncontrarUsuarioNaoCadastrado() {
		EntityManager em = JPAUtil.getEntityManager();
		this.dao = new UsuarioDao(em);
		
		Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
		
		Assert.assertThrows(NoResultException.class, 
				()-> this.dao.buscarPorUsername("jurema"));
	}

}
