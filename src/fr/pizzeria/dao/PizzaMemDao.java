package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaMemDao implements IPizzaDao {


	private List<Pizza> pizzas = new ArrayList<Pizza>();

	public PizzaMemDao(){
		pizzas.add(new Pizza("PEP", "P�p�roni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REIN", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L�orientale", 13.50));
		pizzas.add(new Pizza("IND", "L�indienne", 14.00));
	}

	public List<Pizza> findAllPizzas() {
		return pizzas;
	}

	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		if (pizzaExists(pizza.getCode())){
			throw new SavePizzaException("");
		}
		pizzas.add(pizza);

	}

	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException{
		for (Pizza p: pizzas){
			if (p.getCode().equals(codePizza)){
				p.setCode(pizza.getCode());
				p.setLibelle(pizza.getLibelle());
				p.setPrix(pizza.getPrix());
				return;
			}
		}
		throw new UpdatePizzaException("");

	}

	public void deletePizza(String codePizza) throws DeletePizzaException{
		Iterator<Pizza> it = pizzas.iterator();
		while (it.hasNext()){
			Pizza p = it.next();
			if (p.getCode().equals(codePizza)){
				it.remove();
			}
		}
		throw new DeletePizzaException("");
	}

	public Pizza findPizzaByCode(String codePizza) {
		for (Pizza p : pizzas) {
			if (p.getCode().equals(codePizza)) {
				return p;
			}
		}
		return null;
	}

	public boolean pizzaExists(String codePizza) {
		for (Pizza p : pizzas) {

			if (p.getCode().equals(codePizza)) {
				return true;
			}
		}

		return false;
	}

}
