// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.managers;

import com.billrepository.dao.RelationshipDao;
import com.billrepository.entities.Relationship;

public class RelationshipManager {
	private static RelationshipManager instance = new RelationshipManager();
	private static RelationshipDao relationshipDao = new RelationshipDao();
	private RelationshipManager() {
	}
	public static RelationshipManager getInstance() {
		return instance;
	}
	public Relationship createRelationship(String dealerCode, String distributorCode) {
		Relationship relationship = new Relationship();
		relationship.setDealerCode(dealerCode);
		relationship.setDistributorCode(distributorCode);
		return relationship;
	}
	public boolean isRelationshipExists(Relationship relationship) {
		boolean relationshipExists = relationshipDao.isRelationshipExists(relationship);
		return relationshipExists;
	}
	public boolean addRelationshipToDB(Relationship relationship) {
		boolean relationshipAdded = relationshipDao.addRelationshipToDB(relationship);
		return relationshipAdded;
	}
	
}
