package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public class SupprimerPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StockageException{
		System.out.println("Supprimer une pizza");
		
		System.out.println("Code de la pizza à supprimer:");
		String codePizzaSupp = scanner.next();
		
		dao.deletePizza(codePizzaSupp);

	}

}
