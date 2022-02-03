package entity;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import shared.IEmploy;
import shared.IUser;

public class User extends UnicastRemoteObject implements IUser {

	private String login,password;
	private int id,employee_id;
	
	public User() throws RemoteException {
		super();

	}
	
	
	public User(int id, String login, String password, int employee_id) throws RemoteException {
		super();
		this.id=id;
		this.login = login;
		this.password = password;
		this.employee_id = employee_id;
	}
	
	
	
	public int getId() throws RemoteException {
		return id;
	}


	public void setId(int id) throws RemoteException {
		this.id = id;
	}


	public String getLogin() throws RemoteException {
		return login;
	}
	public void setLogin(String login) throws RemoteException {
		this.login = login;
	}
	public String getPassword() throws RemoteException  {
		return password;
	}
	public void setPassword(String password) throws RemoteException {
		this.password = password;
	}
	public int getEmployee_id() throws RemoteException  {
		return employee_id;
	}
	public void setEmployee_id(int employee_id)  throws RemoteException {
		this.employee_id = employee_id;
	}
	
	
	
}
