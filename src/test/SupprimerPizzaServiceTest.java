package test;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

import fr.pizzeria.services.SupprimerPizzaService;

public class SupprimerPizzaServiceTest {
	@Rule 
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void testSupprimerPizzaService() {
	
	systemInMock.provideLines("FEU");

	IPizzaDao mockedDao = mock(IPizzaDao.class);

	try {
		doThrow(new NullPointerException()).when(mockedDao).saveNewPizza(any(Pizza.class));
	} catch (SavePizzaException e) {
		
		e.printStackTrace();
	}

	SupprimerPizzaService service = new SupprimerPizzaService();
	
	try {
		service.executeUC(new Scanner(System.in), mockedDao);
	} catch (StockageException e) {
		e.printStackTrace();
	}
}
}
