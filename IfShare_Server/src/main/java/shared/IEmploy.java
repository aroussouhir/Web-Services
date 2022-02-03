package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IEmploy extends Remote {
	

	public int getId() throws RemoteException;
	
	public void setId(int id) throws RemoteException;
	
	public int getPhoneNumber() throws RemoteException ;
	
	public void setPhoneNumber(int phoneNumber)  throws RemoteException ;

	public String getFirstName() throws RemoteException ;

	public void setFirstName(String firstName) throws RemoteException;

	public void setGender(char gender) throws RemoteException  ;

	public String getLastName() throws RemoteException ;

	public void setLastName(String lastName) throws RemoteException ;

	public char getGender() throws RemoteException ;
	
	public String getEmail() throws RemoteException;

	public void setEmail(String email) throws RemoteException ;

	public String getAddress()  throws RemoteException ;

	public void setAddress(String address) throws RemoteException  ;


	public int getAge() throws RemoteException ; 

	public void setAge(int age) throws RemoteException ;
}
