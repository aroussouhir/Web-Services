package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import service.EmployeeService;

import shared.IEmployManagement;
import shared.IProductManagement;

import shared.IWaitingManagement;


public class ServerEiffelCorp {

	
	public static IWaitingManagement Waitings = null;
		
	public static IProductManagement Products = null;
	
	
	 private ServerEiffelCorp() {
		try {
			
			
			Waitings = (IWaitingManagement) Naming.lookup("rmi://localhost:1100/WaitingManagement");
				
			Products = (IProductManagement) Naming.lookup("rmi://localhost:1100/ProductsManagement");
			
		
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An Error Has Occured when calling Waiting list and Rentlas Manager for IfsCars server stacktrace : " + e);
			 
		}
		
		
		
	}
	
	 public static IWaitingManagement GetInstanceWaitings() throws MalformedURLException, RemoteException, NotBoundException 
	    {
		 
		 // To ensure only one instance is created 
		        if (Waitings == null) 
		        { 
		        	Waitings = (IWaitingManagement) Naming.lookup("rmi://localhost:1100/WaitingManagement");
		    		
		        } 
		        return Waitings; 
		 
		 
	      
	    }

	 
	 public static IProductManagement GetInstanceProducts() throws MalformedURLException, RemoteException, NotBoundException 
	    {
		 
		 // To ensure only one instance is created 
		        if (Products == null) 
		        { 
		        	Products = (IProductManagement) Naming.lookup("rmi://localhost:1100/ProductsManagement");
		    		
		        } 
		        return Products; 
		 
		 
	      
	    }
	 
	 
		
	
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		// TODO Auto-generated method stub

		try {
			
			
			 System.out.println("[Eiffel Corps]: Employee managing server is ON");
				
			 
			LocateRegistry.createRegistry(1099);
			
			IEmployManagement emp = EmployeeService.GetInstance();
			
			
			Naming.rebind("rmi://localhost:1099/EmployeeManagement",emp);
			
			}
		
		catch (Exception e) {
			
			 System.out.println("An Error Has Occured while running the server for EiffelCorp stacktrace : " + e.getMessage());
			 
			 }
	
	}

}
