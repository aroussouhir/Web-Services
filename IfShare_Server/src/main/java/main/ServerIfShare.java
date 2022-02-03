package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import entity.Product;
import service.FeedBackService;
import service.ProductService;
import service.PurchaseService;
import service.WaitingListService;
import shared.IProductManagement;
import shared.IPurchaseManagement;
import shared.IEmployManagement;
import shared.IFeedBackManagement;
import shared.IProduct;
import shared.IWaitingManagement;

public class ServerIfShare{
	
	
	public static IEmployManagement Employees = null;
	
	public static IEmployManagement GetInstance() throws MalformedURLException, RemoteException, NotBoundException 
	    {
		 
		 // To ensure only one instance is created 
		        if (Employees == null) 
		        { 
		        	Employees = (IEmployManagement) Naming.lookup("rmi://localhost:1099/EmployeeManagement");
		        } 
		        return Employees; 
		 
		 
	      
	    }
	

	 private ServerIfShare() {
		try {
			
			Employees = (IEmployManagement) Naming.lookup("rmi://localhost:1099/EmployeeManagement");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An Error Has Occured when calling Employee Manager for Eiffel Corp server stacktrace : " + e);
			 
		}
	 }

	
 public static void main(String[] args) throws RemoteException, MalformedURLException {
		
		try {
			
			
			 System.out.println("[IfShare Server]: Product managing server is ON");
				
			LocateRegistry.createRegistry(1100);
			
			
			IProductManagement pm =  ProductService.GetInstance();
			IFeedBackManagement fd= FeedBackService.GetInstance();		
			IPurchaseManagement purchases = PurchaseService.GetInstance();
			
			
			IWaitingManagement wm = WaitingListService.GetInstance();
			
			Naming.rebind("rmi://localhost:1100/WaitingManagement",wm);
			
			
			Naming.rebind("rmi://localhost:1100/productManagement",pm);
			Naming.rebind("rmi://localhost:1100/FeedBackManagement",fd);
			Naming.rebind("rmi://localhost:1100/purchases",purchases);
			
		
		}catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
		
		
		
		
	}

	
	
	
		
		
		
	}
	




/*

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	try {
				
				LocateRegistry.createRegistry(1100);
				
				IProductManagement Products =  ProductService.GetInstance();
				
			//	IRentalsManagement rentals = RentalService.GetInstance();
				
				IWaitingManagement waitings = WaitingListService.GetInstance();
				
				//IFeedBackManagement feedBacks = FeedBackService.GetInstance();
				
			
				
				
				
				Naming.rebind("rmi://localhost:1100/ProductsManagement",Products);
	//			Naming.rebind("rmi://localhost:1100/RentalsManagement",rentals);
				Naming.rebind("rmi://localhost:1100/WaitingManagement",waitings);
		//		
				
				}
			
			catch (Exception e) {
				
				 System.out.println("An Error Has Occured while running the server  stacktrace : " + e.getMessage());
				 
				 }
		
		}*/

	


