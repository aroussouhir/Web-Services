package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface IWaitingManagement extends Remote {
	
	public boolean isEmpty() throws RemoteException;
	public boolean addWaiting(int Product_id, int employee_id) throws RemoteException ;
	
	public List<IWaiting> searchWaitingByProductId(int Product_id) throws RemoteException ;
	
	public List<IWaiting> searchWaitingByEmployeeId(int employee_id) throws RemoteException;
	
	public IWaiting searchWaitingById(int id) throws RemoteException;
	
	public boolean removeWaiting(int id) throws RemoteException;	

	public void setWaitingList(List<IWaiting> waitingList) throws RemoteException;
	
	public boolean removeFirst () throws RemoteException;
	
	public List<IWaiting> getWaitingList() throws RemoteException;
	
	 public boolean removeWaitingByEmployeeId(int employee_id) throws RemoteException;
	
	 public IWaiting getNextPurchase(int Product_id, int purchase_id) throws RemoteException ;
	 
	 public Date getMinDateRequest(List<IWaiting> waitingList) throws RemoteException ;
	 
	 public IWaiting searchWaitingByRequestDate(Date request_date, List<IWaiting> waitingList) throws RemoteException;
	 
	 public boolean removeWaitingByProductId(int Product_id) throws RemoteException;
	 
	 public int countWainting(int product_id) throws RemoteException;
}
