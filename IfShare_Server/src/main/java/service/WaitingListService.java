package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import entity.WaitingList;
import shared.IProductManagement;
import shared.IWaiting;
import shared.IWaitingManagement;

public class WaitingListService extends UnicastRemoteObject implements IWaitingManagement {
	
	List<IWaiting> waitingList;
	
//	IPurchasesManagement rs ;
	
	IProductManagement cs;
	
	 // private constructor restricted to this class itself 
	private WaitingListService() throws RemoteException
    {
		waitingList = new CopyOnWriteArrayList<IWaiting>();
    }
     
    private static WaitingListService single_instance=null; 
    
    // static method to create instance of WaitingListService class 
    public static WaitingListService GetInstance() throws RemoteException
    { 
        // To ensure only one instance is created 
        if (single_instance == null) 
        { 
            single_instance = new WaitingListService(); 
        } 
        return single_instance; 
    }
	
	//[For test use] Add waiting in the waiting list
	public boolean addWaiting(int Product_id, int employee_id) throws RemoteException {
		
	//	rs = PurchasealService.GetInstance() ;
		
	try {
		
		if(this.waitingList.isEmpty()) {
			
			this.waitingList.add(new WaitingList(0,Product_id,employee_id,new Date()));
			System.out.println("[ WaitingListService : addWaiting method : product added to waitingList.");
			//rs.searchPurchaseByProductId(Product_id).
			//set the available date field !!
			
			return true;
			}
		
		else {
			this.waitingList.add(new WaitingList(this.waitingList.get(this.waitingList.size()-1).getId()+1,Product_id,employee_id,new Date()));
			System.out.println("[ WaitingListService : addWaiting method : product added to waitingList.");
			return true;
			}
			
		}

	catch(Exception e) {
		
		System.out.println("An Error has occurend during the addition of waiting process stacktrace : "+ e);
		e.printStackTrace();
		
		}

	return false;
	
}

	//Search the waitings in the waiting list for the given Product by it's id
	public List<IWaiting> searchWaitingByProductId(int product_id) throws RemoteException {
		
		try {
			
			List result = new ArrayList<WaitingList>(); 
			
			for(IWaiting w :this.waitingList) {
				
				if(w.getProduct_id()==product_id) {
					 
					result.add(w);
					
				}
			
			}
			
			return 	result;
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of waiting by Product id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}
	
	//Search the waitings in the waiting list for the given employee by it's id
	public List<IWaiting> searchWaitingByEmployeeId(int employee_id) throws RemoteException{
		
		try {
			
			List result = new ArrayList<WaitingList>(); 
			
			for(IWaiting w :this.waitingList) {
				
				if(w.getEmployee_id()==employee_id) {
					 
					result.add(w);
					
				}
			
			}
			
			return 	result;
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of waiting by employee id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}
	
	
	//Search waiting in the waiting list by it's id
	public IWaiting searchWaitingById(int id) throws RemoteException {
		
		try {
			
			for(IWaiting w :this.waitingList) {
				
				if(w.getId()==id) {
					 
					return 	(IWaiting) w;
					
				}
			
			}
			
			
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of waiting by id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}
	
	//Search waiting by it's request date
	public IWaiting searchWaitingByRequestDate(Date request_date, List<IWaiting> waitingList) throws RemoteException {
		
		try {
			
			if(!waitingList.isEmpty()) {
				
				for(IWaiting w : waitingList) {
					if(w.getRequest_date().equals(request_date)) {
						return w;
					}
					
					return null;
				}
				
	
				 
			}
			
			System.out.println("The waiting list past in parameter is empty please check function");
			return null;
			
		}
		catch(Exception e){
			System.out.println("An Error has occurend during the search  of waiting list in process by request date stacktrace : "+ e);
			e.printStackTrace();
			
		}
		return null ;
		
	}
	
	//Remove the waiting from the waiting list by it's id
	public boolean removeWaiting(int id) throws RemoteException {
	
		try {
			
			for(IWaiting w :this.waitingList) {
				
				if(w.getId()==id) {
					 
					this.waitingList.remove(w);
					return true;
				}
			
			}
			
				
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the deletion of waiting process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return false;
		
	}
	
	//Remove the waitings from the waiting list for the given employee by it's id 
	public boolean removeWaitingByEmployeeId(int employee_id) throws RemoteException {
		
		try {
					
				List<IWaiting> results = this.searchWaitingByEmployeeId(employee_id);
				
				if(!results.isEmpty()) {
					for(IWaiting w :this.waitingList) {
							 this.waitingList.remove(w);							
						}
					return true;
					
					}
				return true;
					
				}
				
					
				catch(Exception e) {
					
					System.out.println("An Error has occurend during the deletion of waiting by employee id process stacktrace : "+ e);
					e.printStackTrace();
					
					}
			
				return false;
		
		}
	
	//Remove the waitings from the waiting list for the given employee by it's id 
	public boolean removeWaitingByProductId(int product_id) throws RemoteException {
		
		try {
					
				List<IWaiting> results = this.searchWaitingByProductId(product_id);
				
				if(!results.isEmpty()) {
					for(IWaiting w :this.waitingList) {
							 this.waitingList.remove(w);
							 //notify employee
						}
					return true;
					
					}
				return true;
					
				}
				
					
				catch(Exception e) {
					
					System.out.println("An Error has occurend during the deletion of waiting by employee id process stacktrace : "+ e);
					e.printStackTrace();
					
					}
			
				return false;
		
		}


	
	//Get minimum resquest date of a waiting in the given waiting list
	public Date getMinDateRequest(List<IWaiting> waitingList) throws RemoteException {
		
		try {
			 
			Date min_date = new Date() ;
			
			if(!waitingList.isEmpty()) {
				
				for(IWaiting w : waitingList) {
					if(w.getRequest_date().before(min_date)) {
						min_date = w.getRequest_date();
					}
					
					return min_date;
				}
				
	
				 
			}
			
			System.out.println("The waiting list past in parameter is empty please check function");
			return null;
			
		}
		catch(Exception e){
			System.out.println("An Error has occurend during the search minimum date of request in waiting list stacktrace : "+ e);
			e.printStackTrace();
			
		}
		return null ;
		
	}
	
	//Get the next purchase by Request Date
	public IWaiting getNextPurchase(int Product_id, int purchase_id) throws RemoteException {
		try{
			
			List<IWaiting>waitingList = searchWaitingByProductId(Product_id);
			
			List<IWaiting> toSortWaiting ;
		
			System.out.println("[getNextPurchase function in WaintingListService] : The waiting list is empty or the purchaseal is null please check function");
			return null;

		}
		catch(Exception e){
			System.out.println("[getNextPurchase function in WaintingListService] : An Error has occurend during the check prioprity by request date of waiting list stacktrace : "+ e);
			e.printStackTrace();
			
		}
		return null ;
		
	}

	//Getter of the waiting list 
	public List<IWaiting> getWaitingList() throws RemoteException {
		return waitingList;
	}

	//Setter of the waiting list 
	public void setWaitingList(List<IWaiting> waitingList) throws RemoteException {
		
		this.waitingList = waitingList;

		}

	@Override
	public boolean removeFirst() throws RemoteException {
		// TODO Auto-generated method stub
		if(this.waitingList.isEmpty())
		{
			return false;
		}
		
		this.waitingList.remove(0);
		return true;
	}

	@Override
	public boolean isEmpty() throws RemoteException {
		// TODO Auto-generated method stub
		return this.waitingList.isEmpty();
	}

	@Override
	public int countWainting(int product_id) throws RemoteException {
		// TODO Auto-generated method stub
		List<IWaiting> list = searchWaitingByProductId(product_id);
		return list.size();
	}
	
	
	
	
	

}
