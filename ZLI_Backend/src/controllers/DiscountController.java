package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.DatabaseConnection;
import database.IObjectToPreparedStatementParameters;
import database.Tables;
import entities.discounts.Discount;
import entities.discounts.PercentageDiscount;
import entities.discounts.ValueDiscount;
import entities.products.CatalogItem;

public class DiscountController
{
	private static DiscountController instance;
	private final DatabaseConnection databaseConnection;
	float discountedOrder = 0;
	
	private static final String ID_FIELD_NAME = "discountId";

	private DiscountController()
	{
		databaseConnection = DatabaseConnection.getInstance();
	}

	public static synchronized DiscountController getInstance()
	{
		if (instance == null)
		{
			instance = new DiscountController();
		}
		return instance;
	}
	public ArrayList<Discount> getAllDiscounts()
	{
		ResultSet resultSet=databaseConnection.getAll(Tables.DISCOUNTS_TABLE_NAME);
		return convertRSToDiscountArrayList(resultSet);
	}
	public void addProductsDiscount(Discount discount)
	{
		Discount discount2;
		ResultSet rs=databaseConnection.getByID(discount.getDiscountId(), Tables.DISCOUNTS_TABLE_NAME, ID_FIELD_NAME);
		discount2=convertRSToDiscount(rs, true, true);
		rs=databaseConnection.getBySimpleCondition("discountId",discount.getDiscountId()+"", Tables.DISCOUNTS_PRODUCTS_TABLE_NAME);
		for(CatalogItem catalogItem: discount.getProducts()){
		if(!discount2.getProducts().contains(catalogItem))
		{
			databaseConnection.insertToDatabase(Tables.DISCOUNTS_PRODUCTS_TABLE_NAME, Tables.discountsProductsColumnNames,new IObjectToPreparedStatementParameters<Discount>()
			{
				@Override
				public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
				{
					statementToPrepare.setInt(1,discount.getDiscountId());
					statementToPrepare.setInt(2,catalogItem.getBaseProduct().getProductId());
					statementToPrepare.setInt(3,catalogItem.getBranch().getBranchId());
				}
			});
		}
	}
	}
	private ArrayList<Discount> convertRSToDiscountArrayList(ResultSet rs) {
		ArrayList<Discount> discounts = new ArrayList<Discount>();
		try
		{
			while (rs.next())
			{
				Discount discount = convertRSToDiscount(rs, false,false);
				discounts.add(discount);
			}
			rs.close();
			return discounts;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public boolean createDiscount(Discount discount) 
	{
		int res = databaseConnection.insertToDatabase(Tables.DISCOUNTS_TABLE_NAME, Tables.discountsProductsColumnNames,
				new IObjectToPreparedStatementParameters<Discount>()
				{
					@Override
					public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
					{
						statementToPrepare.setInt(1, discount.getDiscountId());
						statementToPrepare.setTimestamp(2, Timestamp.from(discount.getDiscountStartDate()));
						statementToPrepare.setTimestamp(3, Timestamp.from(discount.getDiscountEndDate()));
						statementToPrepare.setString(4, discount.getDiscountName());
						statementToPrepare.setFloat(5, discount.getDiscountValue());
					}
				});
	
		return res == 1;
	}
	/*public ArrayList<Discount> getDiscountsByBranch(int branchId)
	{
		ArrayList<String> joins = new ArrayList<String>();
		joins.add(Tables.DISCOUNTS_PRODUCTS_TABLE_NAME);
		joins.add(Tables.DISCOUNTS_TABLE_NAME);
		String conditions = "catalog.catalogId = catalogiteminbranch.catalogId AND catalogiteminbranch.branchId = "
				+ branchId + " AND catalogiteminbranch.quantityInStock > 0";
		Branch branch = BranchController.getInstance().getBranchById(branchId);
		ResultSet rs = databaseConnection.getSimpleJoinResult(joins, conditions);
		return convertRSToCatalogItemArray(rs, branch);
	}
	/*public boolean removeDiscount(Discount discount, int userId)
	{
		if (discount.getDiscountEndDate().equals(Instant.now()))
		{
			ArrayList<String> keys = new ArrayList<>();
			keys.add("totalCost");
			databaseConnection.updateById(userId, ID_FIELD_NAME, Tables.DISCOUNTS_TABLE_NAME, keys,
					new IObjectToPreparedStatementParameters<Order>()
					{
						@Override
						public void convertObjectToPSQuery(PreparedStatement statementToPrepare) throws SQLException
						{
	                       statementToPrepare.setFloat(1,order.getTotalCost());
							
						}
					});
		}
		return false;
	}*/
	public Discount convertRSToDiscount(ResultSet rs,boolean closeRS,boolean goToNext)
	{
		try
		{
			String[] discountColumnNames = Tables.discountColumnNames;
			Discount discount;
			if(goToNext && !rs.next())
			{
				return null;
			}
			if (rs.getString("discountType").equals(Discount.PERCENTAGE_DISCRIMINATOR))
			{
			    discount =new PercentageDiscount();
				discount.setDiscountId(rs.getInt(discountColumnNames[0]));
				Timestamp discountStartDate = rs.getTimestamp(discountColumnNames[1]);
				discount.setDiscountStartDate(
						(discountStartDate == null) ? null : discountStartDate.toInstant());
				Timestamp discountEndDate = rs.getTimestamp(discountColumnNames[2]);
				discount.setDiscountEndDate((discountEndDate == null) ? null : discountEndDate.toInstant());
				discount.setDiscountName(rs.getString(discountColumnNames[3]));
				discount.setDiscountValue(rs.getInt(discountColumnNames[4]));
				if (closeRS)
					rs.close();
				return discount;
			}
			else
			{
				discount = new ValueDiscount();
				discount.setDiscountId(rs.getInt(discountColumnNames[0]));
				Timestamp discountStartDate = rs.getTimestamp(discountColumnNames[1]);
				discount.setDiscountStartDate(
						(discountStartDate == null) ? null : discountStartDate.toInstant());
				Timestamp discountEndDate = rs.getTimestamp(discountColumnNames[2]);
				discount.setDiscountEndDate((discountEndDate == null) ? null : discountEndDate.toInstant());
				discount.setDiscountName(rs.getString(discountColumnNames[3]));
				discount.setDiscountValue(rs.getInt(discountColumnNames[4]));
				if (closeRS)
					rs.close();
				return discount;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
