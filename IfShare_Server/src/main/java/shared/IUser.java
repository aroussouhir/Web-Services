package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUser extends Remote {


	public int getId() throws RemoteException;
	public void setId(int id) throws RemoteException ;
	public String getLogin() throws RemoteException ;
	public void setLogin(String login) throws RemoteException ;
	public String getPassword()  throws RemoteException ;
	public void setPassword(String password) throws RemoteException ;
	public int getEmployee_id()  throws RemoteException ;
	public void setEmployee_id(int employee_id)  throws RemoteException;
	
}
