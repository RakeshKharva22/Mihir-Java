package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.Product;
import com.util.MihirUtil;

public class ProductDao {

	public static void insertProduct(Product p)
	{
		try {
			Connection conn = MihirUtil.createConnection();
			String sql = "insert into product (uid,product_category,product_name,product_model,product_desc,product_image,product_price) values (?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, p.getUid());
			pst.setString(2, p.getProduct_category());
			pst.setString(3, p.getProduct_name());
			pst.setString(4, p.getProduct_model());
			pst.setString(5, p.getProduct_desc());
			pst.setString(6, p.getProduct_image());
			pst.setLong(7, p.getProduct_price());
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static List<Product> getAllProduct()
	{
		List<Product> list = new ArrayList<Product>();
		try {
			Connection conn = MihirUtil.createConnection();
			String sql = "select * from product";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				Product p = new Product();
				p.setPid(rs.getInt("pid"));
				p.setProduct_category(rs.getString("product_category"));
				p.setProduct_name(rs.getString("product_name"));
				p.setProduct_model(rs.getString("product_model"));
				p.setProduct_desc(rs.getString("product_desc"));
				p.setProduct_image(rs.getString("product_image"));
				p.setProduct_price(rs.getLong("product_price"));
				list.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static Product getProductById(int pid)
	{
			Product p = null;
		
		try {
			Connection conn = MihirUtil.createConnection();
			String sql = "select * from product where pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pid);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
			    p = new Product();
				p.setPid(rs.getInt("pid"));
				p.setProduct_category(rs.getString("product_category"));
				p.setProduct_name(rs.getString("product_name"));
				p.setProduct_model(rs.getString("product_model"));
				p.setProduct_desc(rs.getString("product_desc"));
				p.setProduct_image(rs.getString("product_image"));
				p.setProduct_price(rs.getLong("product_price"));
				p.setUid(rs.getInt("uid"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
}
