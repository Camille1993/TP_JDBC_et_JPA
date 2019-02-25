package test;


import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import java.util.Scanner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.services.AjouterPizzaService;


public class AjouterPizzaServiceTest {

	@Rule 
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void testAjouterPizzaService(){
		
		systemInMock.provideLines("FEU", "piment", "13");

		IPizzaDao mockedDao = mock(IPizzaDao.class);

		try {
			doThrow(new StockageException(null)).when(mockedDao).saveNewPizza(any(Pizza.class));
		} catch (SavePizzaException e) {
			e.printStackTrace();
		}

		AjouterPizzaService ajout =new AjouterPizzaService();

		try {
			ajout.executeUC(new Scanner(System.in), mockedDao);
		} catch (StockageException e) {
			e.printStackTrace();
		}


	}

}
