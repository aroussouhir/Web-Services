package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface IFeedBackManagement extends Remote {

	public boolean addFeedBack(int product_id, int employee_id, int rating, String description) throws RemoteException;
	
	public boolean removeFeedBack(int id) throws RemoteException;
	
	public IFeedBack searchFeedBackById(int id) throws RemoteException;
	
	public List<IFeedBack> searchFeedBackByEmployeeId(int employee_id) throws RemoteException;
	
	public List<IFeedBack> searchFeedBackByProductId(int product_id) throws RemoteException;
	
	public List<IFeedBack> getFeedBacks() throws RemoteException ;
	
	public void setFeedBacks(List<IFeedBack> feedBacks) throws RemoteException ;
	
	public Date getFeedbackMaxSendDateByEmplpoyee(int employee_id) throws RemoteException ;
	
	
}
