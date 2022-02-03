package entity;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import shared.IFeedBack;
import shared.IWaiting;

public class FeedBack extends UnicastRemoteObject implements IFeedBack {

	int id, product_id, employee_id, rating;
	
	String description;

	Date send_date;
	
	
	
	public FeedBack() throws RemoteException {
		super();
	}





	public FeedBack(int id, int product_id, int employee_id, int rating, String description) throws RemoteException {
		super();
		this.id = id;
		this.product_id = product_id;
		this.employee_id = employee_id;
		this.rating = rating;
		this.description = description;
		this.send_date = new Date();
		
	}





	public Date getSend_date() throws RemoteException  {
		return send_date;
	}





	public void setSend_date(Date send_date) throws RemoteException  {
		this.send_date = send_date;
	}





	public int getId() throws RemoteException {
		return id;
	}





	public void setId(int id) throws RemoteException {
		this.id = id;
	}





	public int getProduct_id() throws RemoteException {
		return product_id;
	}





	public void setProduct_id(int product_id) throws RemoteException {
		this.product_id = product_id;
	}





	public int getEmployee_id() throws RemoteException {
		return employee_id;
	}





	public void setEmployee_id(int employee_id) throws RemoteException {
		this.employee_id = employee_id;
	}





	public int getRating() throws RemoteException {
		return rating;
	}





	public void setRating(int rating) throws RemoteException {
		this.rating = rating;
	}





	public String getDescription() throws RemoteException {
		return description;
	}





	public void setDescription(String description) throws RemoteException {
		this.description = description;
	}





	@Override
	public String toString() {
		return "FeedBack [id=" + id + ", product_id=" + product_id + ", employee_id=" + employee_id + ", rating=" + rating
				+ ", description=" + description + "]";
	}
	
	
	
	
	
}
