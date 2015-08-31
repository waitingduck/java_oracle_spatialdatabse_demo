
import java.sql.Connection;
import java.util.ArrayList;

public class populateAll{
	public static void main(String[] args){
		
		//create conntection to DB
		Connection connection = ConnectDB.Connect();
		
		
		//create an array to store every data to populate
		ArrayList<populate> population = new ArrayList<populate>();
		population.add(new populateBuilding());
		population.add(new populateStudent());
		population.add(new populateAS());
		
		for(populate x:population){
			x.populateData(connection);
		}
		
	}
}
