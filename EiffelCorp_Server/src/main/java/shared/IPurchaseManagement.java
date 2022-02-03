package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface IPurchaseManagement extends Remote {

	public boolean addPurchase(int product_id,int employee_id) throws RemoteException ;
	
	
	public boolean removePurchase(int id) throws RemoteException;
	
	public List<IPurchase> searchPurchaseByEmployeeId(int employee_id) throws RemoteException;
	
	public List<IPurchase> searchPurchaseByProductId(int product_id) throws RemoteException;
	
	public IPurchase searchPurchaseById(int id) throws RemoteException;
	
	public void setPurchases(List<IPurchase> purchases) throws RemoteException;
	
	public List<IPurchase> getPurchases() throws RemoteException ;
	
	public IPurchase getPurchaseInProcessByProductId(int product_id) throws RemoteException ;
	
	void updatePurchases() throws RemoteException ;
	
	public IPurchase getPurchaseInProcessByEmployeeId(int employee_id) throws RemoteException;

	public void updatePurchaseAfterRemovingEmployee(int purchase_id) throws RemoteException;
	
	public void updatePurchaseAfterRemovingProduct(int product_id) throws RemoteException;
	
	public List<IPurchase> getFinishedPurchaseByEmployeeId(int employee_id) throws RemoteException;

	
	public IPurchase getLastPurchaseByEmployeeId(int employee_id) throws RemoteException;
}
