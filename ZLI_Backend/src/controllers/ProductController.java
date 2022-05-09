package controllers;

import java.sql.ResultSet;

import database.DatabaseConnection;
import database.IResultSetToObject;

public class ProductController {
    private static ProductController instance;
	private final DatabaseConnection databaseConnection;
	private static final String TABLE_NAME = "baseProduct";
	private IResultSetToObject<baseProduct> rsToProduct;

	private ProductController()
	{
		databaseConnection = DatabaseConnection.getInstance();
		rsToProduct = new IResultSetToObject<baseProduct>()
		{
			@Override
			public baseProduct convertToObject(ResultSet rs)
			{
				try
				{
					baseProduct baseProduct = new baseProduct();
					baseProduct.setOrderId(rs.getInt("orderNumber"));
					baseProduct.setTotalCost(rs.getFloat("price"));
					baseProduct.setGreetingCard(rs.getString("greetingCard"));
					baseProduct.setColor(ColorEnum.valueOf(rs.getString("color")));
					baseProduct.setOrderDetails(rs.getString("dOrder"));
					baseProduct.setBranch(new Branch(rs.getString("shop")));
					baseProduct.setDeliveryDate(rs.getTimestamp("date").toInstant());
					baseProduct.setOrderDate(rs.getTimestamp("orderDate").toInstant());
					return baseProduct;
				} catch (Exception e)
				{
					e.printStackTrace();
					return null;
				}
			}

            @Override
            public baseProduct convertToObject(ResultSet rs) {
                // TODO Auto-generated method stub
                return null;
            }
		};
	}

	public static ProductController getInstance()
	{
		if (instance == null)
		{
			instance = new ProductController();
		}
		return instance;
	}

    public ArrayList<baseProduct> getAllProducts()
	{
		return databaseConnection.getAllFromDB(TABLE_NAME, rsToProduct);
	}

}
    
