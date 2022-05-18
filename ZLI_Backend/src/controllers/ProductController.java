package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseConnection;
import database.Tables;
import entities.products.BaseProduct;
import entities.products.Item;
import entities.products.Product;
import enums.ColorEnum;
import enums.ItemType;
import enums.ProductType;

public class ProductController
{
	private static ProductController instance;
	private final DatabaseConnection databaseConnection;

	private ProductController()
	{
		databaseConnection = DatabaseConnection.getInstance();
	}

	public static ProductController getInstance()
	{
		if (instance == null)
		{
			instance = new ProductController();
		}
		return instance;
	}

	/**
	 * Gets the entire catalog
	 * 
	 * @return
	 */
	public ArrayList<BaseProduct> getAllProducts()
	{
		ResultSet rs = databaseConnection.getAll(Tables.ALL_PRODUCTS_TABLE_NAME); 
		return convertRSToBaseProductArray(rs);
	}

	/**
	 * Gets the catalog by branch
	 * 
	 * @return
	 */
	public ArrayList<BaseProduct> getCatalogByBranch(int branchId)
	{
		ArrayList<String> joins = new ArrayList<String>();
		joins.add(Tables.PRODUCTS_IN_BRANCH_TABLE_NAME);
		joins.add(Tables.ALL_PRODUCTS_TABLE_NAME);
		String conditions = "catalog.catalogId = catalogiteminbranch.catalogId AND catalogiteminbranch.branchId = "
				+ branchId + " AND catalogiteminbranch.quantityInStock > 0";
		ResultSet rs = databaseConnection.getSimpleJoinResult(joins, conditions);
		return convertRSToBaseProductArray(rs);
	}
	
	public ArrayList<BaseProduct> convertRSToBaseProductArray(ResultSet rs)
	{
		ArrayList<BaseProduct> products = new ArrayList<BaseProduct>();
		try
		{
			while (rs.next())
			{
				products.add(convertRSToBaseProduct(rs, false));
			}
			rs.close();
			return products;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public BaseProduct convertRSToBaseProduct(ResultSet rs, boolean closeRS)
	{
		try
		{
			if (rs.getString("productOrItem").equals(BaseProduct.ITEM_DISCRIMINATOR))
			{
				Item item = new Item();
				item.setProductId(rs.getInt("catalogId"));
				item.setProductName(rs.getString("productName"));
				item.setPrice(rs.getFloat("price"));
				item.setImage(rs.getBytes("image"));
				item.setItemType(ItemType.valueOf(rs.getString("type")));
				item.setPrimaryColor(ColorEnum.valueOf(rs.getString("primaryColor")));
				if(closeRS)
					rs.close();
				return item;
			} else
			{
				Product product = new Product();
				product.setProductId(rs.getInt("catalogId"));
				product.setProductName(rs.getString("productName"));
				product.setPrice(rs.getFloat("price"));
				product.setImage(rs.getBytes("image"));
				product.setProductType(ProductType.valueOf(rs.getString("type")));
				if(closeRS)
					rs.close();
				return product;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
