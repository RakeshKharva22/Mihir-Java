package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.bean.Contact;
import com.util.MihirUtil;

public class ContactDao {

	public static void insertContact(Contact c)
	{
		try {
			Connection conn = MihirUtil.createConnection();
			String sql = "insert into contact (fname,lname,email,mobile,address) values (?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, c.getFname());
			pst.setString(2, c.getLname());
			pst.setString(3, c.getEmail());
			pst.setLong(4, c.getMobile());
			pst.setString(5, c.getAddress());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
