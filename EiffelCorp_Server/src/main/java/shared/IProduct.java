package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IProduct extends Remote{

	public int getId() throws RemoteException;

	public void setId(int id) throws RemoteException; 
	
	public int getNbSales() throws RemoteException;

	public void setNbSales(int nbSales) throws RemoteException; 
	
	public int getEmployee_id() throws RemoteException;

	public void setEmployee_id(int id) throws RemoteException; 
	
	public boolean getAvailability() throws RemoteException ;
	
	public void setAvailability(boolean availability) throws RemoteException;
	

	public float getRating() throws RemoteException ;

	public void setRating(float rating)  throws RemoteException  ;
	
	public long getPrice() throws RemoteException ;

	public void setPrice(long price)  throws RemoteException  ;

	public String getType() throws RemoteException;

	public void setType(String type)  throws RemoteException;
	
	public String getName() throws RemoteException;

	public void setName(String name)  throws RemoteException;

	public String getDescription()  throws RemoteException ;

	public void setDescription(String description) throws RemoteException ;

	public String getImageUrl() throws RemoteException ;

	public void setImageUrl(String imageUrl) throws RemoteException ;
	
}
