package fr.pizzeria.exception;

public class DeletePizzaException extends StockageException {
	public DeletePizzaException(String msg) {
		super("Cette pizza n'existe pas");
	}

}
