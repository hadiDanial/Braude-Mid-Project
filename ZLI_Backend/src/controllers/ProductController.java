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

public class ProductController
{
	private static ProductController instance;
	private final DatabaseConnection databaseConnection;
	private static final String ALL_PRODUCTS_TABLE_NAME = "Catalog";
	private static final String PRODUCTS_IN_BRANCH_TABLE_NAME = "CatalogItemInBranch";
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
					if (rs.getString("productOrItem").equals(BaseProduct.ITEM_DISCRIMINATOR))
					{
						Item item = new Item();
						item.setProductId(rs.getInt("catalogId"));
						item.setProductName(rs.getString("productName"));
						item.setPrice(rs.getFloat("price"));
						item.setImage(rs.getBytes("image"));
						item.setItemType(ItemType.valueOf(rs.getString("type")));
						item.setPrimaryColor(ColorEnum.valueOf(rs.getString("primaryColor")));
						return item;
					} else
					{
						Product product = new Product();
						product.setProductId(rs.getInt("catalogId"));
						product.setProductName(rs.getString("productName"));
						product.setPrice(rs.getFloat("price"));
						product.setImage(rs.getBytes("image"));
						product.setProductType(ProductType.valueOf(rs.getString("type")));
						return product;
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

	/**
	 * Gets the entire catalog
	 * 
	 * @return
	 */
	public ArrayList<BaseProduct> getAllProducts()
	{
		return databaseConnection.getAll(ALL_PRODUCTS_TABLE_NAME, rsToProduct);
	}

	/**
	 * Gets the catalog by branch
	 * 
	 * @return
	 */
	public ArrayList<BaseProduct> getCatalogByBranch(int branchId)
	{
		String conditions = "catalog.catalogId = catalogiteminbranch.catalogId AND " + "catalogiteminbranch.branchId = "
				+ branchId + " AND catalogiteminbranch.quantityInStock > 0";
		return databaseConnection.getJoinResultWithSimpleConditions(PRODUCTS_IN_BRANCH_TABLE_NAME,
				ALL_PRODUCTS_TABLE_NAME, conditions, rsToProduct);
	}

}
