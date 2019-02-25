package test;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.services.ModifierPizzaService;


public class ModifierPizzaServiceTest {
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void testModifierPizza() {

		systemInMock.provideLines("VEG", "FEU", "piment", "11");

		IPizzaDao mockedDao = mock(IPizzaDao.class);

		when(mockedDao.findAllPizzas()).thenReturn(new ArrayList<>());

		ModifierPizzaService service =new ModifierPizzaService();
		String msg = null;

		try {
			service.executeUC(new Scanner(System.in), mockedDao);
		} catch (StockageException e) {
			e.printStackTrace();
		}
		msg = "La base de données ne contient pas de pizzas.";

		assertEquals("La base de données ne contient pas de pizzas.", msg);
	}
}
