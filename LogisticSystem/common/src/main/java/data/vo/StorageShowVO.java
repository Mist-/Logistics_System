package data.vo;

import java.util.ArrayList;

/**
 * 
 * @author xu
 *
 */
public class StorageShowVO {
	public ArrayList<Long> getInstitution() {
		return institution;
	}

	public void setInstitution(ArrayList<Long> institution) {
		this.institution = institution;
	}

	public int[] getAmount() {
		return amount;
	}

	public void setAmount(int[] amount) {
		this.amount = amount;
	}

	ArrayList<Long> institution;
	int[] amount;
	
	public StorageShowVO(ArrayList<Long> ins,int[] amount){
		this.institution = ins;
		this.amount = amount;
	}
}
