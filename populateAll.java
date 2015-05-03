package java_oracle_spatialdatabase_demo;

import java.sql.Connection;
import java.util.ArrayList;

public class populateAll{
	public static void main(String[] args){
		
		//create conntection to DB
		connectDB DB = new connectDB();
		Connection connection = DB.Connect();
		
		
		//create an array to store every data to populate
		ArrayList<populate> population = new ArrayList<populate>();
		population.add(new populateBuilding());
		population.add(new populateStudent());
		population.add(new populateAS());
		
		for(populate x:population){
			x.populateData(connection);
		}
		
		//close DB connection after population
		DB.Close(connection);
	}
}
