package entity;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import shared.IPurchase;

public class Purchase extends UnicastRemoteObject implements IPurchase {

	int id, product_id, employee_id ; 

	String status;
	
	public Purchase() throws RemoteException {
		super();
	}

	public Purchase(int id, int product_id, int employee_id)  throws RemoteException {
		super();
		this.id = id;
		this.product_id = product_id;
		this.employee_id = employee_id;
		this.status = "IN PROCESS";
	
	}

	
	
	
	public int getId()  throws RemoteException {
		return id;
	}

	public void setId(int id)  throws RemoteException {
		this.id = id;
	}

	public int getProduct_id()  throws RemoteException {
		return product_id;
	}

	public void setProduct_id(int product_id)  throws RemoteException {
		this.product_id = product_id;
	}

	public String getStatus() throws RemoteException {
		return status;
	}

	public void setStatus(String status)  throws RemoteException {
		this.status = status;
	}

	public int getEmployee_id()  throws RemoteException {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) throws RemoteException {
		this.employee_id = employee_id;
	}


	@Override
	public String toString() {
		return "Rental [id=" + id + ", product_id=" + product_id + ", employee_id=" + employee_id +"]";
	}
	
	
	
	
}
