package com.wipro.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.shop.bean.CouponBean;
import com.wipro.shop.util.DBUtil;

public class CouponDAO {
	public CouponBean findCouponByCouponCode(String couponCode)
	{
		CouponBean cb=null;
	    Connection con=new DBUtil().getDBConnection();
	    if(con!=null)
	    {
	    	String sql="Select * from Coupons_tbl";
	    	try {
				PreparedStatement ps=con.prepareStatement(sql);
	     		ResultSet rs=ps.executeQuery();
	     		while(rs.next())
				if(couponCode.equals(rs.getString(1)))
				{
					cb=new CouponBean();
					cb.setCouponCode(rs.getString(1));
					cb.setUserID(rs.getString(2));
					cb.setStatus(rs.getInt(3));
					return cb;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		return cb;
	}
	public boolean updateCouponByCouponCode(String code)
	{
		Connection con=new DBUtil().getDBConnection();
		String sql="Update Coupons_tbl set status=0 where couponCode=?";
		if(con!=null)
		{
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,code);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
					return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public CouponBean findCouponByUserID(String userID)
	{
		CouponBean cb=new CouponBean();
		Connection con=new DBUtil().getDBConnection();
		String sql="Select * from Coupons_tbl where userID=?";
		if(con!=null)
		{
			 
			try {
			    PreparedStatement ps = con.prepareStatement(sql);
			    ps.setString(1, userID);
			    ResultSet rs=ps.executeQuery();
			    if(rs.next())
			    {
			    	cb.setCouponCode(rs.getString(1));
			    	cb.setUserID(rs.getString(2));
			    	cb.setStatus(rs.getInt(3));
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return cb;
		
	}

}
