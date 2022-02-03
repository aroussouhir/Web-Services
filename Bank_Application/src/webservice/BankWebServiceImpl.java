package webservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.faces.component.html.HtmlSelectOneMenu;

import com.neovisionaries.i18n.CountryCode;
import com.sun.faces.taglib.html_basic.SelectOneMenuTag;

import service.BankService;


public class BankWebServiceImpl {

	

	BankService bankService;
	
	
	
	
	public BankWebServiceImpl() {
		
		try {
			bankService=BankService.GetInstance();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public boolean retrieveBalanceToAccount(int price, int iban,String ownerFirstName,String ownerLastName) {
	
		return bankService.retrieveBalanceToAccount(price, iban, ownerFirstName,ownerLastName);
		
		
	}
	


	
	
}
