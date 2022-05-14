package controllers;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.DatabaseConnection;
import database.IResultSetToObject;
import entities.products.BaseProduct;
import entities.products.Item;
import entities.products.Product;
import enums.ColorEnum;
import enums.ItemType;
import enums.ProductType;

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
					if(rs.getString("productOrItem") == BaseProduct.ITEM_DISCRIMINATOR){
						Item baseProduct = new Item();
						baseProduct.setProductId(rs.getInt("orderNumber"));
						baseProduct.setProductName(rs.getString("productName"));
						baseProduct.setPrice(rs.getFloat("price"));
						baseProduct.setImage(rs.getBytes("image"));
						baseProduct.setItemType(ItemType.valueOf(rs.getString("type")));
						baseProduct.setPrimaryColor(ColorEnum.valueOf(rs.getString("primaryColor")));
						return baseProduct;
					}
					else{
						Product baseProduct = new Product();
						baseProduct.setProductId(rs.getInt("orderNumber"));
						baseProduct.setProductName(rs.getString("productName"));
						baseProduct.setPrice(rs.getFloat("price"));
						baseProduct.setImage(rs.getBytes("image"));
						baseProduct.setProductType(ProductType.valueOf(rs.getString("type")));
						return baseProduct;
					}
				} catch (Exception e)
				{
					e.printStackTrace();
					return null;
				}
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

    public ArrayList<BaseProduct> getAllProducts()
	{
		return databaseConnection.getAll(TABLE_NAME, rsToProduct);
	}

}
    
