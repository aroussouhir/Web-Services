package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import entity.Product;
import shared.IProduct;
import shared.IProductManagement;
import shared.IPurchase;
import shared.IPurchaseManagement;
import shared.IWaiting;
import shared.IWaitingManagement;

public class ProductService extends UnicastRemoteObject implements IProductManagement {

	
	List<IProduct> products;
	
	IPurchaseManagement purchaseManagement;
	IWaitingManagement wm;

	 // private constructor restricted to this class itself 
		private ProductService() throws RemoteException
	    {
			products = new CopyOnWriteArrayList<IProduct>();
	    }
	     
	    private static ProductService single_instance=null; 
	    
	  
	    // static method to create instance of ProductService class 
	    public static ProductService GetInstance() throws RemoteException
	    { 
	        // To ensure only one instance is created 
	        if (single_instance == null) 
	        { 
	            single_instance = new ProductService(); 
	        } 
	        return single_instance; 
	    }

	//[For test use] Add product in Product list 
	public boolean addProduct(long price) throws RemoteException {
		
		try {
			
			if(this.products.isEmpty()) {
				this.products.add(new Product(0,price));
				return true;
				}
			
			else {
				this.products.add(new Product(this.products.get(this.products.size()-1).getId()+1, price));
				return true;
				}
				
			}
	
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the addition of product process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return false;
		
	}
	
	
	@Override
	public boolean addProduct(String name, String type, String description, String imageUrl, long price,
			int employee_id) throws RemoteException {
		
		try {
			
			if(this.products.isEmpty()) {
				System.out.println("Empty");
				this.products.add(new Product(0, price, employee_id,  type, description, imageUrl, name));
				System.out.println("[addproduct function in ProductService] : Product Added  ");
				
				return true;
				}
			
			else {
				this.products.add(new Product(this.products.get(this.products.size()-1).getId()+1,price,employee_id, type, description, imageUrl,name));
				System.out.println("[addproduct function in ProductService] : Product Added  with id = "+this.products.get(this.products.size()-1).getId());
				
				return true;
				
			}
				
			}
	
		catch(Exception e) {
			
			System.out.println("[addproduct function in ProductService] : An Error has occurend during the addition of product process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return false;
	}

	public List<IProduct> getAvailableAndSoldProducts() throws RemoteException {
		try {
					
			
				
						List<IProduct> soldAvailableproducts = new CopyOnWriteArrayList<IProduct>();
						
					for(IProduct r : products) {
							
							if(r.getNbSales()>0 && r.getAvailability()){
								//Add Waiting list condition !! 
									soldAvailableproducts.add(r);
							}
					}
						return soldAvailableproducts;
						
					}
					catch(Exception e) {
						System.out.println("An Error has occurend during the research of one time at least rented products stacktrace : "+ e);
						e.printStackTrace();
					}
					
					return null;

				
				}


	
	//Search product from Product list by it's registration Number
	public IProduct searchProductByEmployee_id (int employee_id) throws RemoteException {
		
		try {
			
			for(IProduct c :this.products) {
				
				if(c.getEmployee_id() == employee_id) {
					 
					return c ;
					
				}
				
				
			
			}
			
			
			
			return 	null;
				
			}
			
		catch(Exception e) {
			
			System.out.println("[searchproductByModel function in productService] : An Error has occurend during the research of product by registration number process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}

	
	
	//Search product from Product list by its type
	public List<IProduct> searchProductByType(String type) throws RemoteException {
		
		try {
				
			
			List result = new ArrayList<Product>(); 
			
			for(IProduct c :this.products) {
				
				if(c.getType().toLowerCase().equals(type.toLowerCase())) {
					 
					result.add(c);
					
				}
				
				
			
			}
			
			
			
			return 	result;
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of product by model process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}

	
	//Search product from Product list by it's id
	public IProduct searchProductById(int id) throws RemoteException {
		
		try {
			
			for(IProduct c :this.products) {
				
				if(c.getId()==id) {
					 
					
					return (IProduct) c;
				}
			
			}
			
				
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the research of product by id process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return null;
		
	}
	
	//Remove product from Product list by it's id and remove all other fields containing it from waiting list and purchase list
	public boolean removeProductById(int id) throws RemoteException {
	
		try {
			
	//		rs = PurchaseService.GetInstance();
			
			for(IProduct c :this.products) {
				
				if(c.getId()==id) {
					 
		//			rs.updateRentAfterRemovingproduct(c.getId());
					this.products.remove(c);
					
					return true;
				}
			
			}
			
				
				
			}
			
		catch(Exception e) {
			
			System.out.println("An Error has occurend during the deletion of product process stacktrace : "+ e);
			e.printStackTrace();
			
			}
	
		return false;
		
	}
	
	//get one time at least sold products 
	public List<IProduct> getAllSoldProductsAtLeastOneTime() throws RemoteException {
			
			try {
				
	//			rs = PurchaseService.GetInstance();
				
				List<IProduct> soldproducts = new CopyOnWriteArrayList<IProduct>();
				
		/*		for(IPurchase r : rs.getPurchases()) {
					
					if(r.getStatus().equals("FINISHED")){
						IProduct c = this.searchproductById(r.getproduct_id());
						
						if(!c.getState().equals("SOLD"))
							soldproducts.add(this.searchproductById(r.getproduct_id()));
					}
			}*/
				return soldproducts;
				
			}
			catch(Exception e) {
				System.out.println("An Error has occurend during the research of one time at least sold products stacktrace : "+ e);
				e.printStackTrace();
			}
			
			return null;

		
		}
	
	//Getter of Product list 
	public List<IProduct> getProducts() throws RemoteException {
		return products;
	}
	

	@Override
	public List<IProduct> getProductsNotEmployee(int employee_id) throws RemoteException {
		// TODO Auto-generated method stub
		
		List<IProduct> list = new ArrayList<IProduct>();
		List<IPurchase> listP = new ArrayList<IPurchase>();
		List<IWaiting> listW = new ArrayList<IWaiting>();
		
		purchaseManagement = PurchaseService.GetInstance();
		wm = WaitingListService.GetInstance();
		
		listW = wm.searchWaitingByEmployeeId(employee_id);
		listP = purchaseManagement.searchPurchaseByEmployeeId(employee_id);
		
		for(IProduct c :this.products) {
			
			int x=0;
			if(listP!=null)
			{
				for(IPurchase p : listP)
				{
					
					if(p.getProduct_id()== c.getId())
					{
						x++;
					}
				}
			}
			if(listW!=null && x==0) 
			{
				for(IWaiting p : listW)
				{
					
					if(p.getProduct_id()== c.getId())
					{
						x++;
					}
				}
			}
			
			if(c.getEmployee_id()!=employee_id ) {
		
				if(x==0)
				{
					list.add(c);
				}
			}
	
		
		}
		return list;
	}

	//Setter of  Product list
	public void setProducts(List<IProduct> products)  throws RemoteException {
		this.products = products;
	}


	@Override
	public void setAvailability(int id, boolean waitingList) throws RemoteException {
		
		IProduct p = searchProductById(id);
		
		if (p.getAvailability())
		{
			p.setAvailability(false);
		}
		else if(waitingList /*is empty*/)
		{
			p.setAvailability(true);
		}
		else
		{
			p.setAvailability(false);
		}
		
	}




	
	
	
}
