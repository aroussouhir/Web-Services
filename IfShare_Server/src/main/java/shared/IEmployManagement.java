package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;




public interface IEmployManagement extends Remote {

	public boolean addEmployee(String firstName, String lastName, String email, String address, int age) throws RemoteException;
	
	public List<IEmploy> searchEmployeeByFirstName(String firstName) throws RemoteException;
	
	public boolean removeEmployeeById(int id) throws RemoteException;
	
	public IEmploy searchEmployeeById(int id) throws RemoteException;
	
	public boolean removeEmployeeByFirstName(String firstName) throws RemoteException;
	
	public void setEmployees(List<IEmploy> employees) throws RemoteException ;
	
	public List<IEmploy> getEmployees() throws RemoteException;
	
	public List<IEmploy> searchEmployeeByFirstNameAndLastName(String firstName,String lastName) throws RemoteException;
	
	public List<IEmploy> searchEmployeeByLastName(String lastName) throws RemoteException;
	
	public boolean addEmployee(String firstName, String lastName, String email, String address, int age , char gender, int phoneNumber) throws RemoteException;

	public IUser searchUserByEmployeeId(int id) throws RemoteException;
	
	public void setUsers(List<IUser> users) throws RemoteException ;
	
	public List<IUser> getUsers() throws RemoteException ;
	
	public IUser searchUserLoginMdp(String login, String password) throws RemoteException;
}
