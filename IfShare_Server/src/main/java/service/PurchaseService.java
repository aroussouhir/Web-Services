package service;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import entity.Product;
import entity.Purchase;
import main.ServerIfShare;
import shared.IProduct;
import shared.IProductManagement;
import shared.IEmployManagement;
import shared.IFeedBack;
import shared.IFeedBackManagement;
import shared.IPurchase;
import shared.IPurchaseManagement;
import shared.IWaiting;
import shared.IWaitingManagement;

public class PurchaseService extends UnicastRemoteObject implements IPurchaseManagement  {

	List<IPurchase> purchases ;
	
	IProductManagement cs ;
	
	IFeedBackManagement feedbacks; 
	
	IWaitingManagement waitingList;
	
	IEmployManagement emp;
	
	// private constructor restricted to this class itself 
	private PurchaseService() throws RemoteException
    {
		purchases = new CopyOnWriteArrayList<IPurchase>();
    }
     
    private static PurchaseService single_instance=null; 
    
   // static method to create instance of PurchaseService class 
    public static PurchaseService GetInstance() throws RemoteException
    { 
        // To ensure only one instance is created 
        if (single_instance == null) 
        { 
            single_instance = new PurchaseService(); 
        } 
        return single_instance; 
    }
    

	//Add purchase to Purchase list 
	public boolean addPurchase(int product_id,int employee_id) throws RemoteException {
		
		cs=ProductService.GetInstance();
		try {
			emp = ServerIfShare.GetInstance();
			
			if (emp.searchEmployeeById(employee_id) != null) {
				
				
				 if(this.purchases.isEmpty()) {
						
						this.purchases.add(new Purchase(0,product_id,employee_id));
						
						cs.searchProductById(product_id).setAvailability(false);
						cs.searchProductById(product_id).setNbSales(cs.searchProductById(product_id).getNbSales()+1);
						
						return true;
					}
					else {
						
						
						this.purchases.add(new Purchase(this.purchases.get(this.purchases.size()-1).getId()+1,product_id,employee_id));
						cs.searchProductById(product_id).setAvailability(false);
						cs.searchProductById(product_id).setNbSales(cs.searchProductById(product_id).getNbSales()+1);
						return true;
						
					}
				 			
			
			 
				 
					
				
				
		}} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//updateProductAvailability();
		System.out.println("the Employee does not exist");
		
		return false;
		
		
	}

  
	//Update out dated purchases with setting product availablity to true if there is no waitings in waiting list or get next purchase from waiting list
	public void updatePurchases() throws RemoteException  {
		
		//todo : change method name
		try {
			
			for(IPurchase r : purchases) {
				
				//System.out.println("i'm in for loop ------");
				
				if( waitingList.searchWaitingByProductId(r.getProduct_id()).isEmpty()) {
					
					cs.searchProductById(r.getProduct_id()).setAvailability(true);
					
					r.setStatus("FINISHED");
					
					
				}
				else  {
					
					IWaiting w = waitingList.getNextPurchase(r.getProduct_id(), r.getId());
					
					if(w!=null) {
						r.setStatus("FINISHED");
						cs.searchProductById(r.getProduct_id()).setAvailability(true);
						this.addPurchase(w.getProduct_id(), w.getEmployee_id());
						waitingList.removeWaiting(w.getId());
					}
					else {
						System.out.println("[updatePurchases in PurchaseService] : The next purchase is null please check the updatePurchases function ");
						
					}
				
				}
				
			}
			
		}
		catch(Exception e) {
			
			System.out.println("[updatePurchases in PurchaseService] : An Error has Occured in Update Product Availabilty function stacktrace :"+ e);
			e.printStackTrace();
		}
	
	
	}
	
	//Update purchase after removing and employee with setting the status to "CANCELED" and get next purchase from waiting list
	public void updatePurchaseAfterRemovingEmployee(int purchase_id) throws RemoteException  {
			
			try {
				
				IPurchase r = this.searchPurchaseById(purchase_id);
				
				r.setStatus("CANCELED");
				cs.searchProductById(r.getProduct_id()).setAvailability(true);
				waitingList.removeWaitingByEmployeeId(r.getEmployee_id());
				
			
				IWaiting w = waitingList.getNextPurchase(r.getProduct_id(), r.getId());
					
					if(w!=null) {
						this.addPurchase(w.getProduct_id(), w.getEmployee_id());
						
					}
					
					
			}
			catch(Exception e) {
				
				System.out.println("An Error has Occured in update Purchases after removing employee function stacktrace :"+ e);
				e.printStackTrace();
			}
		
		
		}

	//Update purchase after removing and employee with setting the status to "CANCELED" and get next purchase from waiting list
	public void updatePurchaseAfterRemovingProduct(int product_id) throws RemoteException  {
			
			try {
				
				List<IPurchase> purchaseedProducts = this.searchPurchaseByProductId(product_id);
				waitingList.removeWaitingByProductId(product_id);
				
				for(IPurchase r : purchaseedProducts) {
					
					if(r.getStatus().equals("IN_PROCESS")) {
						
						r.setStatus("CANCELED");
						
						
					}	
				}
		

				
			}
			catch(Exception e) {
				
				System.out.println("An Error has Occured in Update Product Availabilty function stacktrace :"+ e);
				e.printStackTrace();
			}
		
		
		}

	//Search a purchase from Purchase list with the given employee by it's id
	public List<IPurchase> searchPurchaseByEmployeeId(int employee_id) throws RemoteException {
			
			try {
				
				List result = new ArrayList<Purchase>(); 
				
				for(IPurchase r :this.purchases) {
					
					if(r.getEmployee_id()==employee_id) {
						 
						result.add(r);
						
					}
				
				}
				
				return 	result;
					
				}
				
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the research of purchaseal by employee id process stacktrace : "+ e);
				e.printStackTrace();
				
				}
		
			return null;
			
		}
	
	//Search a purchase from Purchase list with the given product by it's id
	public List<IPurchase> searchPurchaseByProductId(int product_id) throws RemoteException {
			try {
				
				List result = new ArrayList<Purchase>(); 
				
				for(IPurchase r :this.purchases) {
					
					if(r.getProduct_id()==product_id) {
						 
						result.add(r);
						
					}
				
				}
				
				return 	result;
					
				}
				
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the research of purchaseal by product id process stacktrace : "+ e);
				e.printStackTrace();
				
				}
		
			return null;
			
		}
		
	//Search a purchase from Purchase list with the given purchase by it's id
	public IPurchase searchPurchaseById(int id) throws RemoteException {
			
			try {
				
				for(IPurchase r :this.purchases) {
					
					if(r.getId()==id) {
						 
						
						return (IPurchase) r;
					}
				
				}
				
					
					
				}
				
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the research of purchaseal by id process stacktrace : "+ e);
				e.printStackTrace();
				
				}
		
			return null;
			
		}
	
	public IPurchase getLastPurchaseByEmployeeId(int employee_id) throws RemoteException {
		try {
			
			List<IPurchase> purchaseedProducts = this.getFinishedPurchaseByEmployeeId(employee_id);
			
			cs = ProductService.GetInstance();
			
				
			
			for(IPurchase r : purchaseedProducts) {
					return r;
			
			
			
		}
		
			return null;
			
		}catch(Exception e) {
			
			System.out.println("[getLastProductPurchaseedByEmployeeId function in PurchaseService] : An Error has occurend during the getLastProductPurchaseedByEmployeeId process stacktrace : "+ e);
			e.printStackTrace();
			
			}
		return null;
	}
	
	
			
	

	//Get a list of finished purchases by employee id
	public List<IPurchase> getFinishedPurchaseByEmployeeId(int employee_id) throws RemoteException {
		
		try {
			
			List<IPurchase> purchaseedProducts = this.searchPurchaseByEmployeeId(employee_id);
			List<IPurchase> result = new CopyOnWriteArrayList<IPurchase>();
			
			if(purchaseedProducts!=null) {
				for(IPurchase r : purchaseedProducts ) {
					if(r.getStatus().equals("FINISHED")) {
						result.add(r);
					}
				}
				
				return result;
			}
			
			return null;
			
		}catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of finished purchases by employee id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
	}

	
	//Get a purchase in process for the given product by it's id
	public IPurchase getPurchaseInProcessByProductId(int product_id) throws RemoteException {
		
		try {
			
			List<IPurchase> purchaseedProducts = searchPurchaseByProductId(product_id);
			
			for(IPurchase r : purchaseedProducts) {
					return r;
				
		}
			
		}
		catch(Exception e) {
			System.out.println("An Error has occurend during the research of Purchase In Process Purchase By product id process stacktrace : "+ e);
			e.printStackTrace();
		}
		
		return null;

	
	}
	
	//Get a purchase in process for the given employee by it's id
	public IPurchase getPurchaseInProcessByEmployeeId(int employee_id) throws RemoteException {
		
		try {
			
			List<IPurchase> purchaseedProducts = searchPurchaseByEmployeeId(employee_id);
			
			for(IPurchase r : purchaseedProducts) {
				
					return r;
				
		}
			
		}
		catch(Exception e) {
			System.out.println("An Error has occurend during the research of Purchase In Process Purchase By employee id process stacktrace : "+ e);
			e.printStackTrace();
		}
		
		return null;

	
	}
	
	//Getter of the Purchase list

	public List<IPurchase> getPurchases() throws RemoteException  {
			return purchases;
		}

	//Remove a purchase from Purchase list by it's id
	public boolean removePurchase(int id) throws RemoteException {
			
			try {
				
				for(IPurchase r :this.purchases) {
					
					if(r.getId()==id) {
						 if(r.getStatus().equals("IN_PROCESS")) {
							 
							 
							 
							 IWaiting w = waitingList.getNextPurchase(r.getProduct_id(), r.getId());
								
								if(w!=null) {
									
									purchases.remove(r);
									cs.searchProductById(r.getProduct_id()).setAvailability(true);
									this.addPurchase(w.getProduct_id(), w.getEmployee_id());
									waitingList.removeWaiting(w.getId());
									System.out.println("The purchase was removed and a next one took his place from waitinglist");
								}
								else {
									System.out.println("The purchase was removed ");
									purchases.remove(r);
								}
								
								
								
						 }
						 else {
							 purchases.remove(r);
							System.out.println("The purchase was removed ");
								
						 }
						
						//set product to available !!
						
						return true;
					}
				
				}
						
			}
				
			catch(Exception e) {
				
				System.out.println("An Error has occurend during the deletion of purchaseal process stacktrace : "+ e);
				e.printStackTrace();
				
				}
		
			return false;
		}
	
	//Compare only the dates without timestamps	
	public int compareDates(Date date1, Date date2) throws RemoteException
	{
	    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

	    try
	    {
	        date1 = sdf.parse(sdf.format(date1));
	        date2 = sdf.parse(sdf.format(date2));
	    }
	    catch (ParseException e) {
	        e.printStackTrace();
	        return -2;
	    }

	    Calendar cal1 = new GregorianCalendar();
	    Calendar cal2 = new GregorianCalendar();

	    cal1.setTime(date1);
	    cal2.setTime(date2);

	    if(cal1.equals(cal2))
	    {
	        return 0;
	    }
	    else if(cal1.after(cal2))
	    {
	        return 1;
	    }
	    else if(cal1.before(cal2))
	    {
	        return -1;
	    }

	    return -2;
	}
	
	//Setter of the Purchase list
	public void setPurchases(List<IPurchase> purchases) throws RemoteException  {
			this.purchases = purchases;
		}


		
			
	
}
