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

	
	/** function the gets instance of the controller
	 * @return instance DiscountController
	 */
	public static synchronized DiscountController getInstance()
	{
		if (instance == null)
		{
			instance = new DiscountController();
		}
		return instance;
	}
	
	/** getter for all discounts from the database
	 * @return ArrayList<Discount> of all discounts table details
	 */
	public ArrayList<Discount> getAllDiscounts()
	{
		ResultSet resultSet=databaseConnection.getAll(Tables.DISCOUNTS_TABLE_NAME);
		return convertRSToDiscountArrayList(resultSet);
	}
	
	
	/** function that adds input discount to database
	 * @param discount
	 */	
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
	
	/** function that moves all the discounts from the database and put it in arraylist of discounts
	 * @param rs used to get input from SQL database
	 * @return ArrayList<Discount> of discounts from the database
	 */
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

	
	/** function that creates input discount and insert it to the database
	 * @param discount
	 * @return boolean if we succeeded to connect to the database and insert discount details
	 */
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
	
	/**function that initializes discount details from database and put it in input
	 * 
	 * @param rs input to SQL
	 * @param closeRS if query is closed
	 * @param goToNext if there's still columns from the table
	 * @return
	 */
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
