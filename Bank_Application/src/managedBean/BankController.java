package managedBean;

import java.rmi.RemoteException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.model.SelectItem;

import com.sun.faces.taglib.html_basic.SelectOneMenuTag;

import service.BankService;
import webservice.Account;
import webservice.Bank;
import webservice.BankWebServiceImpl;

@ManagedBean(name="BankController")
@SessionScoped
public class BankController {

	BankService bankService;

	private String iban,ownerFirstName, ownerLastName, ownerEmail,ownerCountry,currency,balance,bank_id;
	
	private String account_id;

	
	
	@PostConstruct
	public void init() {
		
			try {
				bankService =  BankService.GetInstance();
				
			}
			catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			this.account_id="";
	}
	
	
	public List<Bank> getBanks(){
		return bankService.getBankList();
	}
	
	public List<Account> getAccounts(){
		return bankService.getAccountList();
	}


	
	
	public BankService getBankService() {
		return bankService;
	}


	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}


	public List<SelectItem> getAllCountries(){
		
		String[] countryCodes = Locale.getISOCountries();
		List<SelectItem> items = new ArrayList<SelectItem>();
		
		Map<String,String>countryNames = new HashMap<String,String>();
		
		for (String countryCode : countryCodes) {
		
		    Locale obj = new Locale("", countryCode);
		
		    countryNames.put(obj.getISO3Country(),obj.getDisplayCountry().toUpperCase());

			}
		
		Map<String, String> sortedMap = 
				countryNames.entrySet().stream()
			    .sorted(Entry.comparingByValue())
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) -> e1, LinkedHashMap::new));
		
	//	java.util.Collections.sort(countryNames, Collator.getInstance());
	    for (Map.Entry m : sortedMap.entrySet()) {
           // System.out.println("ISO3: "+m.getKey()+", Nom: "+m.getValue());
            
            items.add(new SelectItem(m.getKey() , m.getValue()+""));
        }
			
		  
			

			

		   return items;
	}
	
	public List<SelectItem> getAllBanks(){
		
		
		List<SelectItem> items = new ArrayList<SelectItem>();
		
		
	//	System.out.println("\n[getAllBanks function] : ----Size bank list ; "+this.bankService.getBankList().size() ); 
		for (Bank b : this.bankService.getBankList()) {
	
		//	System.out.println("[getAllBanks function] : bank id "+b.getId());
			
		    items.add(new SelectItem(b.getId(),b.getName()));

			}
		   return items;
	}
	
	public void removeAccount(Account a) {
		try {
			
			System.out.println("[removeAccount function] : removing account");
			this.bankService.getAccountList().remove(a);
		}
		catch(Exception e){
			System.out.println("[removeAccount function] : An error has occured during deleting account process from the managed bean of bank page stacktrace : "+e);
			e.printStackTrace();
		}
	}
	
	
	
	public void editAccount(Account a) {
		try {
			
			this.iban = ""+a.getIban();
			this.ownerFirstName=a.getOwnerFirstName();
			this.ownerLastName=a.getOwnerLastName();
			this.ownerEmail=a.getOwnerEmail();
			this.ownerCountry=a.getOwnerCountry();
			this.balance=""+a.getBalance();
			this.account_id=""+a.getId();
			
			System.out.println("[editAccount function] :account id is : "+this.account_id);
			
	
		}
		catch(Exception e){
			System.out.println("[editAccount function] : An error has occured during the edit account process from the managed bean of bank page stacktrace : "+e);
			e.printStackTrace();
		}
	}
	
	public void addAccount(String account_id) {
		try {
				
			
			
				if(iban.isEmpty()||Integer.valueOf(iban)==null) {
					
					System.out.println("You have to input an Iban");
				}
				else if(balance.isEmpty()||Float.valueOf(balance)==null) {
					System.out.println("You have to input an balance");
				}
				else if(ownerFirstName.isEmpty()) {
					System.out.println("You have to input the Owner First Name");
				}else if(ownerLastName.isEmpty()) {
					
					System.out.println("You have to input the Owner Last Name");
				}else if(ownerEmail.isEmpty()) {
					
					System.out.println("You have to input the Owner Email");
				}else if(ownerCountry.isEmpty()) {
					
					System.out.println("You have to input the Owner country");
				}
			
				System.out.println("[addAccount function] : Selected ISO for ownerCountry is esqual to :"+ownerCountry);
			
			if(account_id.isEmpty()) {
				
				this.bankService.addAccount(Integer.valueOf(iban), Integer.valueOf(bank_id), Float.valueOf(balance), ownerFirstName, ownerLastName, ownerEmail, ownerCountry);
			
			}else{
				
				System.out.println("[addAccount function] : accound id is esqual to :"+account_id);
				Account a = this.bankService.searchAccountById(Integer.valueOf(account_id));
				
				if(a!=null) {
					a.setIban(Integer.valueOf(iban));
					a.setOwnerFirstName(ownerFirstName);
					a.setOwnerLastName(ownerLastName);
					a.setOwnerEmail(ownerEmail);
					a.setOwnerCountry(ownerCountry);
					a.setBank_id(Integer.valueOf(bank_id));
					a.setBalance(Float.valueOf(balance));
				}
				
			}
			
				
			
			
				
				System.out.println("[addAccount function] : bank id = " +Integer.valueOf(bank_id));
				
			
				
				this.account_id="";
				
		}catch(NumberFormatException i) {
			System.out.println("[addAccount function] : You have to input an number for the Iban and balance field");
		}
		catch(Exception e){
			System.out.println("[addAccount function] : An error has occured during the add account process from the managed bean of bank page stacktrace : "+e);
			e.printStackTrace();
		}
	}


	public String getIban() {
		return iban;
	}


	public void setIban(String iban) {
		this.iban = iban;
	}


	public String getBalance() {
		return balance;
	}

	

	public String getAccount_id() {
		return account_id;
	}


	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}


	public void setBalance(String balance) {
		this.balance = balance;
	}


	public String getOwnerFirstName() {
		return ownerFirstName;
	}


	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}


	public String getOwnerLastName() {
		return ownerLastName;
	}


	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}


	public String getOwnerEmail() {
		return ownerEmail;
	}


	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}


	public String getOwnerCountry() {
		return ownerCountry;
	}


	public void setOwnerCountry(String ownerCountry) {
		this.ownerCountry = ownerCountry;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getBank_id() {
		return bank_id;
	}


	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	
	
	
	
	
	
}
