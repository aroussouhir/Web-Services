package entity;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import shared.IProduct;

public class Product extends UnicastRemoteObject implements /*Serializable,*/IProduct {
	
//	private static final long serialVersionUID = 1L;

	private int id, nbSales, employee_id;
	private float rating;
	private long price;
	private boolean availability;
	private String type,description,imageUrl, name;
	
	
	
	public Product() throws RemoteException  {
		super();
	}


	public Product(int id) throws RemoteException {
		super();
		this.id = id;
		this.availability = true ;
		this.rating=0;
	}




	

	public Product(int id,long price) throws RemoteException {
		super();
		this.id = id;
		this.price = price;
		this.availability = true ;
		this.rating=0;
	}





	public Product(int id, int nbSales, int employee_id, long price, boolean availability, String type,
			String description) throws RemoteException {
		super();
		this.id = id;
		this.nbSales = nbSales;
		this.employee_id = employee_id;
		this.price = price;
		this.availability = availability;
		this.type = type;
		this.description = description;
		this.rating=0;
	}





	public Product(int id, int employee_id) throws RemoteException {
		super();
		this.id = id;
		this.employee_id = employee_id;
		this.rating=0;
	}





	public Product(int id, int nbSales, int employee_id, long price, boolean availability) throws RemoteException {
		super();
		this.id = id;
		this.nbSales = nbSales;
		this.employee_id = employee_id;
		this.price = price;
		this.availability = availability;
		this.rating=0;
	}

	public Product(int id, long price, int employee_id , String type, String description, String imageUrl, String name)
			throws RemoteException {
		super();
		this.id = id;
		this.employee_id = employee_id;
		this.price = price;
		this.type = type;
		this.description = description;
		this.imageUrl = imageUrl;
		this.name = name;
		this.availability = true ;
		this.rating=0;
	}


	public int getId() throws RemoteException {
		return id;
	}





	public void setId(int id) throws RemoteException {
		this.id = id;
	}






	public String getType() throws RemoteException {
		return type;
	}





	public void setType(String type)  throws RemoteException {
		this.type = type;
	}





	public String getDescription()  throws RemoteException {
		return description;
	}





	public void setDescription(String description) throws RemoteException  {
		this.description = description;
	}





	public String getImageUrl() throws RemoteException  {
		return imageUrl;
	}





	public void setImageUrl(String imageUrl) throws RemoteException  {
		this.imageUrl = imageUrl;
	}


	public boolean getAvailability() throws RemoteException {
		return availability;
	}





	public void setAvailability(boolean availability) throws RemoteException {
		this.availability = availability;
	}

	

	@Override
	public String toString() {
		return "Product [id=" + id + ", availability=" + availability + ", price=" +price+"]";
	}





	@Override
	public long getPrice() throws RemoteException {
		// TODO Auto-generated method stub
		return price;
	}





	@Override
	public void setPrice(long price) throws RemoteException {
		this.price=price;
		
	}





	@Override
	public int getNbSales() throws RemoteException {
		// TODO Auto-generated method stub
		return nbSales;
	}





	@Override
	public void setNbSales(int nbSales) throws RemoteException {
		
		this.nbSales= nbSales;
	}





	@Override
	public int getEmployee_id() throws RemoteException {
		// TODO Auto-generated method stub
		return employee_id;
	}





	public float getRating() {
		return rating;
	}


	public void setRating(float rating) {
		this.rating = rating;
	}


	@Override
	public void setEmployee_id(int id) throws RemoteException {
		employee_id=id;
		
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	} 
	
	
	
	
	
	
	
	
}
