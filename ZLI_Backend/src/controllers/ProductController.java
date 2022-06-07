package controllers;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import database.DatabaseConnection;
import database.IObjectToPreparedStatementParameters;
import database.Tables;
import entities.other.Branch;
import entities.products.BaseProduct;
import entities.products.CatalogItem;
import entities.products.Item;
import entities.products.Product;
import enums.ColorEnum;
import enums.ItemType;
import enums.ProductType;
import requests.EntityRequestWithId;

public class ProductController
{
	private static ProductController instance;
	private final DatabaseConnection databaseConnection;

	private ProductController()
	{
		databaseConnection = DatabaseConnection.getInstance();
	}

	
	/** 
	 * @return ProductController
	 */
	public static synchronized ProductController getInstance()
	{
		if (instance == null)
		{
			instance = new ProductController();
		}
		return instance;
	}

	/**
	 * Add a new product to the database.
	 * 
	 * @param newProduct Product to add.
	 * @return True if product was successfully added to the database.
	 */
	public boolean addProduct(BaseProduct newProduct)
	{
		String[] columns = Arrays.copyOfRange(Tables.allProductsColumnNames, 1,
				Tables.allProductsColumnNames.length);
		int res = databaseConnection.insertAndReturnGeneratedId(Tables.ALL_PRODUCTS_TABLE_NAME, columns,
				convertBaseProductToPS(newProduct));
		if (res == -1)
			return false;
		return true;
	}

	/**
	 * Update base product.
	 * 
	 * @param updateRequest UpdateEntityRequest<BaseProduct> containing the id and
	 *                      updated properties of the base product.
	 * @return
	 */
	public boolean updateBaseProduct(EntityRequestWithId<BaseProduct> updateRequest)
	{
		// String conditionFieldName, String conditionValue, String tableName,
		// ArrayList<String> keys, IObjectToPreparedStatementParameters<T> objToPS
		int id = updateRequest.getEntityId();
		List<String> keys = Arrays
				.asList(Arrays.copyOfRange(Tables.allProductsColumnNames, 0, Tables.allProductsColumnNames.length));
		return databaseConnection.updateAllMatchingCondition(Tables.allProductsColumnNames[0], id + "",
				Tables.ALL_PRODUCTS_TABLE_NAME, new ArrayList<String>(keys),
				convertBaseProductToPS(updateRequest.getEntity()));
	}

	/**
	 * Gets the entire catalog.
	 */
	public ArrayList<CatalogItem> getAllCatalogItems()
	{
		ResultSet rs = databaseConnection.getAll(Tables.ALL_PRODUCTS_TABLE_NAME);
		return convertRSToCatalogItemArray(rs, null);
	}

	
	/** 
	 * @return ArrayList<Item>
	 */
	public ArrayList<Item> getAllItems()
	{
		ResultSet rs = databaseConnection.getBySimpleCondition("productOrItem", BaseProduct.ITEM_DISCRIMINATOR, Tables.ALL_PRODUCTS_TABLE_NAME);
		return convertRSToItemArray(rs);		
	}
	

	/**
	 * Gets the catalog by branch.
	 */
	public ArrayList<CatalogItem> getCatalogByBranch(int branchId)
	{
		ArrayList<String> joins = new ArrayList<String>();
		joins.add(Tables.PRODUCTS_IN_BRANCH_TABLE_NAME);
		joins.add(Tables.ALL_PRODUCTS_TABLE_NAME);
		String conditions = "catalog.catalogId = catalogiteminbranch.catalogId AND catalogiteminbranch.branchId = "
				+ branchId + " AND catalogiteminbranch.quantityInStock > 0";
		Branch branch = BranchController.getInstance().getBranchById(branchId);
		ResultSet rs = databaseConnection.getSimpleJoinResult(joins, conditions);
		return convertRSToCatalogItemArray(rs, branch);
	}

	/**
	 * Convert ResultSet to CatalogItem array
	 * 
	 * @param rs     ResultSet containing CatalogItem records.
	 * @param branch The branch of the catalog.
	 * @return List of CatalogItem contained in the ResultSet.
	 */
	public ArrayList<CatalogItem> convertRSToCatalogItemArray(ResultSet rs, Branch branch)
	{
		ArrayList<CatalogItem> products = new ArrayList<CatalogItem>();
		try
		{
			while (rs.next())
			{
				BaseProduct p = convertRSToBaseProduct(rs, false);
				CatalogItem item = new CatalogItem();
				item.setBaseProduct(p);
				if (branch != null)
				{
					item.setBranch(branch);
					item.setQuantityInStock(rs.getInt("quantityInStock"));
				}
				products.add(item);
			}
			rs.close();
			return products;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	/** 
	 * @param rs
	 * @return ArrayList<Item>
	 */
	private ArrayList<Item> convertRSToItemArray(ResultSet rs)
	{
		ArrayList<Item> items = new ArrayList<Item>();
		try
		{
			while (rs.next())
			{
				items.add((Item) convertRSToBaseProduct(rs, false));
			}
			rs.close();
			return items;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Convert a ResultSet to a BaseProduct.
	 * 
	 * @param rs             ResultSet containing a base product.
	 * @param isSingleRecord Should the resultSet be closed after we're done?
	 * @return BaseProduct generated from the ResultSet
	 */
	public BaseProduct convertRSToBaseProduct(ResultSet rs, boolean isSingleRecord)
	{
		try
		{
			if (isSingleRecord)
				if (!rs.next())
					return null;
			if (rs.getString("productOrItem").equals(BaseProduct.ITEM_DISCRIMINATOR))
			{
				Item item = new Item();
				item.setProductId(rs.getInt("catalogId"));
				item.setProductName(rs.getString("productName"));
				item.setPrice(rs.getFloat("price"));
				item.setImage(rs.getBytes("image"));
				item.setItemType(ItemType.valueOf(rs.getString("type")));
				item.setPrimaryColor(ColorEnum.valueOf(rs.getString("primaryColor")));
				if (isSingleRecord)
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
				if (isSingleRecord)
					rs.close();
				return product;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	
	/** 
	 * @param newProduct
	 * @return IObjectToPreparedStatementParameters<BaseProduct>
	 */
	private IObjectToPreparedStatementParameters<BaseProduct> convertBaseProductToPS(BaseProduct newProduct)
	{
		return new IObjectToPreparedStatementParameters<BaseProduct>()
		{

			@Override
			public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
			{
				// { "catalogId", "productName", "price", "image", "type", "primaryColor",
				// "productOrItem" };
				statementToPrepare.setString(1, newProduct.getProductName());
				statementToPrepare.setFloat(2, newProduct.getPrice());
					
				Blob blob = databaseConnection.createBlob();
				if(newProduct.getImage() != null)				
					blob.setBytes(1, newProduct.getImage());
				else
					blob.setBytes(1, new byte[1]);
				statementToPrepare.setBlob(3, blob);
				statementToPrepare.setBlob(3, blob);
				
				if (newProduct.isProduct())
				{
					statementToPrepare.setString(4, ((Product) newProduct).getProductType().name());
					statementToPrepare.setString(5, ColorEnum.None.name());
					statementToPrepare.setString(6, BaseProduct.PRODUCT_DISCRIMINATOR);
				} else
				{
					statementToPrepare.setString(4, ((Item) newProduct).getItemType().name());
					statementToPrepare.setString(5, ((Item) newProduct).getPrimaryColor().name());
					statementToPrepare.setString(6, BaseProduct.ITEM_DISCRIMINATOR);
				}
			}
		};
	}
}
