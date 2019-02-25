package fr.pizzeria.exception;

public class SavePizzaException extends StockageException {
	public SavePizzaException(String msg)  {
		super("Cette pizza existe déjà");
	}

}
