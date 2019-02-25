package test;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.IPizzaDao;

import fr.pizzeria.services.ListerPizzasService;


import static org.junit.Assert.assertEquals;


public class ListerPizzasServiceTest {

	@Rule 
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void testListerPizza() {
		
		IPizzaDao mockedDao = mock(IPizzaDao.class);

		when(mockedDao.findAllPizzas()).thenReturn(new ArrayList<>());

		ListerPizzasService service =new ListerPizzasService();
		String msg = null;

		service.executeUC(new Scanner(System.in), mockedDao);
		msg = "La base de données ne contient pas de pizzas.";

		assertEquals("La base de données ne contient pas de pizzas.", msg);
	}
}
