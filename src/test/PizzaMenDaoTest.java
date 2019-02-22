package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class PizzaMenDaoTest {
	IPizzaDao dao;

	@Before
	public void setUp(){
		dao = new PizzaMemDao();

	}

	@Test
	public void testFindAllPizza() {
		assertFalse("tableau vide", dao.findAllPizzas().isEmpty());
	}

	@Test
	public void testSaveNewPizza() {
		assertFalse("tableau vide", dao.findAllPizzas().isEmpty());
		dao.saveNewPizza(new Pizza("TES", "test", 10));
		assertTrue("Je dois trouver ma pizza", dao.pizzaExists("TES"));
	}


	@Test
	public void testUpdatePizza() {
		dao.saveNewPizza(new Pizza("FIR", "test", 10));
		dao.updatePizza("FIR", new Pizza("FIR", "essai", 12));
		assertTrue("Je dois avoir modifier ma pizza", dao.findPizzaByCode("FIR").getCode().equals("FIR")); 

	}

	@Test
	public void testdeletePizza() {
		dao.saveNewPizza(new Pizza("SAU", "test", 10));
		dao.deletePizza("SAU");
		assertFalse("Je dois avoir supprimer ma pizza", dao.pizzaExists("SAU")); 

	}

}
