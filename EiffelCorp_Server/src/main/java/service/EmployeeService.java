package service;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import entity.Employee;
import entity.User;
import main.ServerEiffelCorp;

import shared.IEmploy;
import shared.IEmployManagement;
import shared.IProduct;
import shared.IProductManagement;

import shared.IUser;
import shared.IWaiting;
import shared.IWaitingManagement;



public class EmployeeService extends UnicastRemoteObject implements IEmployManagement  {

	
	 List<IEmploy> employees; 
	 List<IUser> users; 
	 
	 
	 IProductManagement pd ;
	
	 	// private constructor restricted to this class itself 
		private EmployeeService() throws RemoteException
	    {
			employees = new CopyOnWriteArrayList<IEmploy>();
			users = new CopyOnWriteArrayList<IUser>();
	    }
	     
	    private static EmployeeService single_instance=null; 
	    
	  
	    // static method to create instance of EmployeeService class 
	    public static EmployeeService GetInstance() throws RemoteException
    { 
        // To ensure only one instance is created 
        if (single_instance == null) 
        { 
            single_instance = new EmployeeService(); 
        } 
        return single_instance; 
    }
	

	
	//[For test purpose] Add employee to Employee list
	public boolean addEmployee(String firstName, String lastName, String email, String address, int age) throws RemoteException {
		
		try {
			System.out.println("Size of List Employees : "+this.employees.size());
			
			if(this.employees.isEmpty()) {
				
				
				this.employees.add(new Employee(0,firstName,lastName,email,address,age));
				this.users.add(new User(0,email,firstName+lastName,0));
				
				return true;
				}
			
			else {
				
				
				this.employees.add(new Employee(this.employees.get(this.employees.size()-1).getId()+1,firstName,lastName,email,address,age));
				this.users.add(new User(this.users.get(this.users.size()-1).getId()+1,email,firstName+lastName,this.employees.get(this.employees.size()-1).getId()));
				
				return true;
				}
				
			}
	
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the addition of employee  process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return false;
		
	}
	
	//Add employee to Employee list
	public boolean addEmployee(String firstName, String lastName, String email, String address, int age , char gender, int phoneNumber) throws RemoteException {
			
			try {
				System.out.println("Size of List Employees : "+this.employees.size());
				
				if(this.employees.isEmpty()) {
					
					
					this.employees.add(new Employee(0,firstName,lastName,email,address,age,gender,phoneNumber));
					this.users.add(new User(0,firstName,firstName,0));
					
					return true;
					}
				
				else {
					
					this.employees.add(new Employee(this.employees.get(this.employees.size()-1).getId()+1,firstName,lastName,email,address,age,gender,phoneNumber));
					this.users.add(new User(this.users.get(this.users.size()-1).getId()+1,firstName,firstName,this.employees.get(this.employees.size()-1).getId()));
					
					return true;
					}
					
				}
		
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the addition of employee  process stacktrace : "+ e);
				e.printStackTrace();
				
				}
		
			return false;
			
		}
	
	//Search employee by first name
	public List<IEmploy> searchEmployeeByFirstName(String firstName) throws RemoteException {
		
		
		
		
		try {
				
			List result = new ArrayList<IEmploy>();
			
			for(IEmploy emp : employees ) {
				
				if(emp.getFirstName().toLowerCase().equals(firstName.toLowerCase())) 
					
					result.add(emp);
				
				}
					 
			System.out.println("Result for search : "+ result.get(0));		
			return result;	 
			}
					
		
		
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of employee by firstname process stacktrace : "+ e.getMessage());
			
		}
		
		
		return null;
	}
	
	//Search employee by last name
	public List<IEmploy> searchEmployeeByLastName(String lastName) throws RemoteException {
		
		
		
		
		try {
				
			List result = new ArrayList<IEmploy>();
			
			for(IEmploy emp : employees ) {
				
				if(emp.getLastName().toLowerCase().equals(lastName.toLowerCase())) 
					
					result.add(emp);
				
				}
					 
			System.out.println("Result for search : "+ result.get(0));		
			return result;	 
			}
					
		
		
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of employee by firstname process stacktrace : "+ e.getMessage());
			
		}
		
		
		return null;
	}
	
	//Search employee by first name and last name
	public List<IEmploy> searchEmployeeByFirstNameAndLastName(String firstName,String lastName) throws RemoteException {
		
		
		
		
		try {
				
			List result = new ArrayList<IEmploy>();
			
			for(IEmploy emp : employees ) {
				
				if(emp.getFirstName().toLowerCase().equals(firstName.toLowerCase()) && emp.getLastName().toLowerCase().equals(lastName.toLowerCase())) 
					
					result.add(emp);
				
				}
					 
			System.out.println("Result for search : "+ result.get(0));		
			return result;	 
			}
					
		
		
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of employee by firstname process stacktrace : "+ e.getMessage());
			
		}
		
		
		return null;
	}
	
	
	//Search employee by it's id
	public IEmploy searchEmployeeById(int id) throws RemoteException {
			
		try {
	
			for(IEmploy emp : employees ) {
				
				if(emp.getId()==id) 
					
					return emp;
				
				}
		}
	
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of employee by id process stacktrace : "+ e.getMessage());
			
		}	
		return null;
	}
	
	
	//Search user account by employee id
	public IUser searchUserLoginMdp(String login, String password) throws RemoteException {
			
		try {
	
			for(IUser u : this.users ) {
				
				if(u.getLogin().equals(login) && u.getPassword().equals(password)) 
					
					return u;
				
				}
		}
	
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of user by login and password process stacktrace : "+ e.getMessage());
			
		}	
		return null;
	}
	
	
	//Search user account by employee id
	public IUser searchUserByEmployeeId(int id) throws RemoteException {
			
		try {
	
			for(IUser u : this.users ) {
				
				if(u.getEmployee_id()==id) 
					
					return u;
				
				}
		}
	
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of user with employee by id process stacktrace : "+ e.getMessage());
			
		}	
		return null;
	}
	
	//Remove employee by it's id
	public boolean removeEmployeeById(int id) throws RemoteException {
			
		boolean result = false;
		
		try {
			
		//	pd = ServerEiffelCorp.GetInstanceProducts();
			
			result = this.employees.remove(this.searchEmployeeById(id));
			result = this.users.remove(this.searchUserByEmployeeId(id));
			//IProduct p = pd.searchProductByEmployeeId(id);
			 
			/*if(p!=null) {
				
				pd.removeProductById(p.getId());
				
			}*/
				
			
			
			
			return result;
		}
		
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the deletion of employee by id process stacktrace : "+ e.getMessage());
			
		}
		
		return result;
		
		}
	
	//Remove employee by it's first name
	public boolean removeEmployeeByFirstName(String firstName) throws RemoteException {
		
		try {
			
			this.employees.remove(this.searchEmployeeByFirstName(firstName));
			
			return true;
		}
		
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the deletion of employee by firstnameZ process stacktrace : "+ e.getMessage());
			
		}
		
		return false;
		
		}

	//Getter of Employee list
	public List<IEmploy> getEmployees() throws RemoteException {
		return employees;
	}

	//Setter of Employee list
	public void setEmployees(List<IEmploy> employees) throws RemoteException {
		this.employees = employees;
	}



	public List<IUser> getUsers() throws RemoteException {
		return users;
	}



	public void setUsers(List<IUser> users) throws RemoteException {
		this.users = users;
	}

	
	
		
	
}
