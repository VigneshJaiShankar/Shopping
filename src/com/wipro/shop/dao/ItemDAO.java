package com.wipro.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wipro.shop.bean.CouponBean;
import com.wipro.shop.bean.ItemBean;
import com.wipro.shop.util.DBUtil;

public class ItemDAO {
	public List<ItemBean> findAll()
	{
		List<ItemBean> ls=new ArrayList<ItemBean>();
		Connection con=new DBUtil().getDBConnection();
		String sql="Select * from Items_tbl";
		if(con!=null)
		{
			
			try {
				PreparedStatement ps= con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					ItemBean ib=new ItemBean();	
				    ib.setItemCode(rs.getString(1));
				    ib.setName(rs.getString(2));
				    ib.setQuantity(rs.getInt(3));
				    ib.setCost(rs.getFloat(4));
				    ls.add(ib);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error");
			}
		}
		return ls;
	}
	public ItemBean findItemByItemCode(String itemCode)
	{
		ItemBean ib=new ItemBean();
		Connection con=new DBUtil().getDBConnection();
		String sql="Select * from Items_tbl where itemCode=?";
		if(con!=null)
		{
			 
			try {
			    PreparedStatement ps = con.prepareStatement(sql);
			    ps.setString(1,itemCode);
			    ResultSet rs=ps.executeQuery();
			    if(rs.next())
			    {
			    	ib.setItemCode(rs.getString(1));
			        ib.setName(rs.getString(2));
			    	ib.setQuantity(rs.getInt(3));
			    	ib.setCost(rs.getFloat(4));
			    	return ib;
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}

}
