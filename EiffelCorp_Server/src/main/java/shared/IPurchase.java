package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IPurchase extends Remote {


	public int getId()  throws RemoteException;

	public void setId(int id)  throws RemoteException;
	
	public int getProduct_id()  throws RemoteException; 

	public void setProduct_id(int product_id)  throws RemoteException;

	public int getEmployee_id()  throws RemoteException;
	
	public void setEmployee_id(int employee_id) throws RemoteException;

	public String getStatus() throws RemoteException ;
	
	public void setStatus(String status)  throws RemoteException;
}
