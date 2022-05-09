package controllers;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.DatabaseConnection;
import database.IResultSetToObject;
import entities.products.BaseProduct;
public class ProductController {
    private static ProductController instance;
	private final DatabaseConnection databaseConnection;
	private static final String TABLE_NAME = "BaseProduct";
	private IResultSetToObject<BaseProduct> rsToProduct;

	private ProductController()
	{
		databaseConnection = DatabaseConnection.getInstance();
		rsToProduct = new IResultSetToObject<BaseProduct>()
		{
			@Override
			public BaseProduct convertToObject(ResultSet rs)
			{
				try
				{
					BaseProduct BaseProduct = new BaseProduct();
					BaseProduct.setProductId(rs.getInt("orderNumber"));
					BaseProduct.setProductName(rs.getString("productName"));
					BaseProduct.setPrice(rs.getFloat("price"));
					BaseProduct.setImage(rs.getBytes("image"));
					return BaseProduct;
				} catch (Exception e)
				{
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	public static ProductController getInstance()
	{
		if (instance == null)
		{
			instance = new ProductController();
		}
		return instance;
	}

    public ArrayList<BaseProduct> getAllProducts()
	{
		return databaseConnection.getAll(TABLE_NAME, rsToProduct);
	}

}
    
