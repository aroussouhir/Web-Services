package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.List;





public interface IProductManagement extends Remote {
	
	public void setAvailability(int product_id, boolean waitingList) throws RemoteException;
	
	public List<IProduct> searchProductByType(String type) throws RemoteException ;
	
	public IProduct searchProductById(int id) throws RemoteException ;
	
	public boolean removeProductById(int id) throws RemoteException ;
	
	public List<IProduct> getProducts() throws RemoteException ;
	
	public List<IProduct> getProductsNotEmployee(int employee_id) throws RemoteException ;
	
	public void setProducts(List<IProduct> products)  throws RemoteException;
	
	public boolean addProduct(long price) throws RemoteException;
	
	public List<IProduct> getAllSoldProductsAtLeastOneTime() throws RemoteException;
	
	public List<IProduct> getAvailableAndSoldProducts() throws RemoteException;
	
	public boolean addProduct(String name, String type,String description,String imageUrl,long price,int employee_id) throws RemoteException;

	public IProduct searchProductByEmployee_id (int employee_id) throws RemoteException;
}
