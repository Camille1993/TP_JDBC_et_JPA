package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
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
	public void testSaveNewPizza() throws SavePizzaException {
		assertFalse("tableau vide", dao.findAllPizzas().isEmpty());
		try {
			dao.saveNewPizza(new Pizza("TES", "test", 10));
		} catch (SavePizzaException e) {
			
			e.printStackTrace();
		}
		assertTrue("Je dois trouver ma pizza", dao.pizzaExists("TES"));
	}


	@Test
	public void testUpdatePizza() throws UpdatePizzaException{
		try {
			dao.saveNewPizza(new Pizza("FIR", "test", 10));
		} catch (SavePizzaException e) {
			e.printStackTrace();
		}
		dao.updatePizza("FIR", new Pizza("FIR", "essai", 12));
		assertTrue("Je dois avoir modifier ma pizza", dao.findPizzaByCode("FIR").getCode().equals("FIR")); 

	}

	@Test
	public void testdeletePizza() throws DeletePizzaException{
		try {
			dao.saveNewPizza(new Pizza("SAU", "test", 10));
		} catch (SavePizzaException e) {
			e.printStackTrace();
		}
		dao.deletePizza("SAU");
		assertFalse("Je dois avoir supprimer ma pizza", dao.pizzaExists("SAU")); 

	}

}
