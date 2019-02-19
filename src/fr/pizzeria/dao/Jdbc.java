package fr.pizzeria.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.model.Pizza;

public class Jdbc implements IPizzaDao {

	public Jdbc() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Pizza> findAllPizzas() {
		Connection pizzeria;
		ResultSet findpizza = null;
		List<Pizza> pizzalist = new ArrayList<Pizza>();
		try {
			pizzeria = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/pizzeria","root","");
			Statement Pizza = pizzeria.createStatement();
			findpizza = Pizza.executeQuery("Select * from pizza_list");
			
			
			while(findpizza.next()) {
				Integer id = findpizza.getInt("ID");
				String code = findpizza.getString("code");
				String libelle = findpizza.getString("libelle");
				double prix = findpizza.getDouble("prix");
				
				Pizza p = new Pizza(id, code, libelle, prix);
				pizzalist.add(p);
				
			}
			findpizza.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return pizzalist; 	
				
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		// TODO Auto-generated method stub
		Connection pizzeria;
		
		try {
			pizzeria = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/pizzeria","root","");
		PreparedStatement newpizza = pizzeria.prepareStatement("insert into pizza_list(code,libelle, prix) values (?,?,?)");
			newpizza.setString(1, pizza.getCode());
			newpizza.setString(2, pizza.getLibelle());
			newpizza.setDouble(3, pizza.getPrix());
			newpizza.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		// TODO Auto-generated method stub
		Connection pizzeria;
		
		try {
			pizzeria = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/pizzeria","root","");
			
			PreparedStatement updatepizza = pizzeria.prepareStatement("update pizza_list set code = ?, libelle = ?, prix = ? where code = ?");
			updatepizza.setNString(4, pizza.getCode());
			updatepizza.setString(1, pizza.getCode());
			updatepizza.setString(2, pizza.getLibelle());
			updatepizza.setDouble(3, pizza.getPrix());
			updatepizza.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void deletePizza(String codePizza) {
		// TODO Auto-generated method stub
		Connection pizzeria;
		
		try {
			pizzeria = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/pizzeria","root","");
			
			PreparedStatement deletepizza = pizzeria.prepareStatement("delete from pizza_list where code = codePizza");
			deletepizza.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		// TODO Auto-generated method stub
		return false;
	}
	


}
