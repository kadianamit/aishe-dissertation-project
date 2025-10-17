package org.nicexam.authorizationservice.userdao;


public interface TableIdGenerationCounterDao {
	public Long fetchOrUpdateTableIdSequence(String tablename); 
}
