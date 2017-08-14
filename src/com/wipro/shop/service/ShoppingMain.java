package com.wipro.shop.service;

import java.util.ArrayList;
import java.util.List;

import com.wipro.shop.bean.CouponBean;
import com.wipro.shop.bean.ItemBean;
import com.wipro.shop.dao.CouponDAO;
import com.wipro.shop.dao.ItemDAO;

public class ShoppingMain {
	public String doShopping(String itemCode,String userID, String couponCode)
	{
		double cost;
		ItemDAO id=new ItemDAO();
		ItemBean ib=new ItemBean();
		CouponDAO cd=new CouponDAO();
		CouponBean cb=new CouponBean();
		CouponBean cb1=new CouponBean();
		if(itemCode==null || userID==null || couponCode==null)
			return null;
		else 
		{
		    ib=id.findItemByItemCode(itemCode);	
		    cb=cd.findCouponByUserID(userID);
		    cb1=cd.findCouponByCouponCode(couponCode);
		    if(userID.equals("G") && couponCode.equals("NO"))
		    {
		    	cost=ib.getCost();
		    	return String.valueOf(cost);
		    }
		    else if(ib==null)
		    {
		    	return "INVALID ITEM CODE";
		    }
		    else if(cb1==null)
		    {
		    	try {
					throw new InvalidException("INVALID COUPON CODE");
				} catch (InvalidException e) {
					// TODO Auto-generated catch block
					return e.toString();
				}
		    }
		    else if(!cb.getCouponCode().equals(couponCode))
		    {
		    	return "DATA MISMATCH";
		    }
		    else if(cb1.getStatus()==0)
		    {
		    	return "USED COUPON";
		    }
		    else
		    {
		    	cost=ib.getCost();
		    	cost=cost-(cost*(20/100.0));
		    	cd.updateCouponByCouponCode(couponCode);
		    	return String.valueOf(cost);
		    }
		}
	}
	

public List<ItemBean> viewAllItems()
{
	List<ItemBean> ls=new ArrayList<ItemBean>();
	ItemDAO id=new ItemDAO();
	ls=id.findAll();
	return ls;
}
public static void main(String[] args) {
	ShoppingMain shopping = new ShoppingMain();
    List<ItemBean> listItems = shopping.viewAllItems();
    for(ItemBean ib:listItems)
    {
    	System.out.printf("%s,%s,%d,%.2f\n",ib.getItemCode(),ib.getName(),ib.getQuantity(),ib.getCost());
    }
    String itemCode = "TI1000";
    String userID = "AA1000";
    String couponCode = "AB10CD20";
   
    String result = shopping.doShopping(itemCode, userID, couponCode);
    System.out.println(result);
    
}

}
