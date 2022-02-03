package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IFeedBack extends Remote {

	public int getId() throws RemoteException  ;
	
	public void setId(int id) throws RemoteException ;
	
	public int getProduct_id() throws RemoteException ;
	
	public void setProduct_id(int product_id) throws RemoteException ;
	
	public int getEmployee_id() throws RemoteException ;
	
	public void setEmployee_id(int employee_id) throws RemoteException ;
	
	public int getRating() throws RemoteException ;
	
	public void setRating(int rating) throws RemoteException ;
	
	public String getDescription() throws RemoteException ;
	
	public void setDescription(String description) throws RemoteException ;
	
	public Date getSend_date() throws RemoteException ;

	public void setSend_date(Date send_date) throws RemoteException  ;
}
