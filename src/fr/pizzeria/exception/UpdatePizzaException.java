package fr.pizzeria.exception;

public class UpdatePizzaException extends StockageException{
	public UpdatePizzaException(String msg) {
		super("Cette pizza n'existe pas");
	}

}
