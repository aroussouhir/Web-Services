package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import entity.FeedBack;
import shared.IProduct;
import shared.IProductManagement;
import shared.IFeedBack;
import shared.IFeedBackManagement;
import shared.IPurchase;

public class FeedBackService extends UnicastRemoteObject implements IFeedBackManagement {
	
		List<IFeedBack> feedBacks;

		// private constructor restricted to this class itself 
		private FeedBackService() throws RemoteException
	    {
			this.feedBacks = new CopyOnWriteArrayList<IFeedBack>();
	    }
	     
	    private static FeedBackService single_instance=null; 
	    
	  
	    // static method to create instance of FeedBackService class 
	    public static FeedBackService GetInstance() throws RemoteException
	    { 
	        // To ensure only one instance is created 
	        if (single_instance == null) 
	        { 
	            single_instance = new FeedBackService(); 
	        } 
	        return single_instance; 
	    }
	
	
	//Add feed back to Feed-Back list 
	public boolean addFeedBack(int product_id, int employee_id, int rating, String description) throws RemoteException {
		
		try {
			
			IProductManagement pm = ProductService.GetInstance();
			IProduct p = pm.searchProductById(product_id);
			
			float r = p.getRating();

			int nb = searchFeedBackByProductId(product_id).size();
			
			if (nb==0)
			{
				p.setRating(rating);
			}
			else {
				System.out.println("r="+ r+", nb="+ nb+", rating="+ rating);
				p.setRating(((r*nb)+rating)/(nb+1));
				System.out.println("final moy = "+ p.getRating());
			}
			
			
			if(this.feedBacks.isEmpty()) {
				this.feedBacks.add(new FeedBack(0,product_id,employee_id,rating,description));
				return true;
				}
			
			else {
				this.feedBacks.add(new FeedBack(this.feedBacks.get(this.feedBacks.size()-1).getId()+1,product_id,employee_id,rating,description));
				return true;
				}
				
			}
	
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the addition of feedback process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return false;
		
	}
	
	//Search feed back from Feed-Back list with the given product by it's id
	public List<IFeedBack> searchFeedBackByProductId(int product_id) throws RemoteException {
		
		try {
			List result = new ArrayList<FeedBack>(); 
			
			for(IFeedBack f :this.feedBacks) {
				
				if(f.getProduct_id()==product_id) {
					 
					result.add(f);
					
				}
			
			}
			System.out.println("[ FeedBackService : searchFeedBackByProductId ] : Product_id = "+product_id+", Nb of feedbacks found = "+result.size());
			return 	result;
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of feedback by product id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}
	
	//Search feed back from Feed-Back list with the given employee by it's id
	public List<IFeedBack> searchFeedBackByEmployeeId(int employee_id) throws RemoteException{
		
		try {
			
			List result = new ArrayList<FeedBack>(); 
			
			for(IFeedBack f :this.feedBacks) {
				
				if(f.getEmployee_id()==employee_id) {
					 
					result.add(f);
					
				}
			
			}
			
			return 	result;
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of feedback by employee id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}
	
	//Search feed back from Feed-Back list by it's id
	public IFeedBack searchFeedBackById(int id) throws RemoteException {
		
		try {
				
			for(IFeedBack f :this.feedBacks) {
				
				if(f.getId()==id) {
					 
					return 	f;
					
				}
			
			}
			
			
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of feedback by id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}
	
	//Remove feed back from Feed-Back list by it's id
	public boolean removeFeedBack(int id) throws RemoteException {
	
		try {
			
			for(IFeedBack f :this.feedBacks) {
				
				if(f.getId()==id) {
					 
					this.feedBacks.remove(f);
					return true;
				}
			
			}
			
				
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the deletion of feedBack process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return false;
		
	}
	

	public Date getFeedbackMaxSendDateByEmplpoyee(int employee_id) throws RemoteException {
		
		//max waiting in process
		
		try{
			
			Date max_date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1999") ;
			
			List<IFeedBack> productsFeedBack = this.searchFeedBackByEmployeeId(employee_id);
			//System.out.println(waitingList.size() );
			
			if(productsFeedBack!=null) {
				for(IFeedBack f : productsFeedBack) {
					// System.out.println("in loop of check ---" );
					 if(f.getSend_date().after(max_date)) {
						 
						 
						 max_date = f.getSend_date();
						 //System.out.println("Max Date :"+ max_date.toString() );
							
						 
					 }
					
				 }
				return max_date;
			}
	
			 
			 return null;
			
		}
		catch(Exception e){
			System.out.println("An Error has occurend during the getFeedbackMaxSendDateByEmplpoyee  stacktrace : "+ e);
			e.printStackTrace();
			
		}
		return null ;
		
	}
	
	
	//Getter of Feed-Back list
	public List<IFeedBack> getFeedBacks() throws RemoteException {
		return feedBacks;
	}

	//Setter of Feed-Back list
	public void setFeedBacks(List<IFeedBack> feedBacks) throws RemoteException {
		this.feedBacks = feedBacks;
	}
	
	
	

}
