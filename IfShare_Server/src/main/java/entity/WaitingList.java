package entity;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import shared.IWaiting;

public class WaitingList extends UnicastRemoteObject implements IWaiting {

	
	private int id,product_id,employee_id;
	private Date request_date;
	
	public WaitingList() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}



	public WaitingList(int id, int product_id, int employee_id/*, Date date_available*/) throws RemoteException {
		super();
		this.id = id;
		this.product_id = product_id;
		this.employee_id = employee_id;
		this.request_date = new Date();
		//this.date_available = date_available;
			
		
	}
	



	public WaitingList(int id, int product_id, int employee_id, Date request_date) throws RemoteException {
		super();
		this.id = id;
		this.product_id = product_id;
		this.employee_id = employee_id;
		this.request_date = request_date;
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


	public Date getRequest_date() throws RemoteException {
		return request_date;
	}



	public void setRequest_date(Date request_date) throws RemoteException {
		this.request_date = request_date;
	}



	@Override
	public String toString() {
		return "WaitingList [id=" + id + ", product_id=" + product_id + ", employee_id=" + employee_id +  ", request_date=" + request_date + "]";
	}



	
	
	
	
}
