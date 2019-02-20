package fr.pizzeria.dao;

import java.util.Properties;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class Jdbc implements IPizzaDao {

	private String urlDb;
	private String user;
	private String password;
	private String driverClass;
	
	public Jdbc() {
		// Recup le fichier properties 
		// Ranger les infos du fichier 
		// properties dan les attributs correspondants
		Properties prop = new Properties();
		InputStream input = null;
		
			try {
				input = new FileInputStream("config.properties");
				prop.load(input);
				
				urlDb = prop.getProperty("urlDb");
				user = prop.getProperty("user");
				password = prop.getProperty("password");
				driverClass = prop.getProperty("driverClass");
				
					try {
						Class.forName(driverClass);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				
			} catch (IOException ex) {
				ex.printStackTrace();
			} 
	}
				
		@Override
	public List<Pizza> findAllPizzas() {
		Connection pizzeria;
		ResultSet findpizza = null;
		List<Pizza> pizzalist = new ArrayList<Pizza>();
		try {
			pizzeria = DriverManager
						.getConnection(urlDb,user,password);
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
					.getConnection(urlDb,user,password);
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
					.getConnection(urlDb,user,password);
			
			PreparedStatement updatepizza = pizzeria.prepareStatement("update pizza_list set code = ?, libelle = ?, prix = ? where code = ?");
			
			updatepizza.setString(4, pizza.getCode());
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
					.getConnection(urlDb,user,password);
			
			PreparedStatement deletepizza = pizzeria.prepareStatement("delete from pizza_list where code = ?");
			deletepizza.setString(1, codePizza);
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
