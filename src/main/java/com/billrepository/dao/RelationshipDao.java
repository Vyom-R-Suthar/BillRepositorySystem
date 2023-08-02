// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.billrepository.entities.Relationship;
import com.billrepository.utilities.ConnectionProvider;

public class RelationshipDao {

	public boolean isRelationshipExists(Relationship relationship) {
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from relationship where dealerCode=? AND distributorCode=?");
			pstmt.setString(1,relationship.getDealerCode());
			pstmt.setString(2,relationship.getDistributorCode());
			ResultSet rs = pstmt.executeQuery();
			if(rs.isBeforeFirst()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean addRelationshipToDB(Relationship relationship) {
		Connection con = ConnectionProvider.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into relationship(dealerCode,distributorCode) values(?,?)");
			pstmt.setString(1, relationship.getDealerCode());
			pstmt.setString(2,relationship.getDistributorCode());
			int result = pstmt.executeUpdate();
			if(result > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
