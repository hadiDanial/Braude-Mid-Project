package controllers;

import java.sql.ResultSet;

import database.DatabaseConnection;
import database.IResultSetToObject;

public class ProductController {
    private static ProductController instance;
	private final DatabaseConnection databaseConnection;
	private static final String TABLE_NAME = "Products";
	private IResultSetToObject<Products> rsToProduct;

	private ProductController()
	{
		databaseConnection = DatabaseConnection.getInstance();
		rsToProduct = new IResultSetToObject<Products>()
		{
			@Override
			public Products convertToObject(ResultSet rs)
			{
				try
				{
					Products Products = new Products();
					Products.setOrderId(rs.getInt("orderNumber"));
					Products.setTotalCost(rs.getFloat("price"));
					Products.setGreetingCard(rs.getString("greetingCard"));
					Products.setColor(ColorEnum.valueOf(rs.getString("color")));
					Products.setOrderDetails(rs.getString("dOrder"));
					Products.setBranch(new Branch(rs.getString("shop")));
					Products.setDeliveryDate(rs.getTimestamp("date").toInstant());
					Products.setOrderDate(rs.getTimestamp("orderDate").toInstant());
					return Products;
				} catch (Exception e)
				{
					e.printStackTrace();
					return null;
				}
			}

            @Override
            public Products convertToObject(ResultSet rs) {
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

    public ArrayList<Products> getAllProducts()
	{
		return databaseConnection.getAllFromDB(TABLE_NAME, rsToProduct);
	}

}
    
