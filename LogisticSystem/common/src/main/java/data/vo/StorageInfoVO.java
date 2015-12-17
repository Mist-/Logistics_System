package data.vo;
import java.util.ArrayList;

import data.po.StorageInfoPO;


/**
 * pass
 * 
 * @author xu
 *
 */
public class StorageInfoVO {
	public String[][] orderAndPostitionArray;
	public String[] header = {"������","����","�ź�","�ܺ�","λ��"};
	public StorageInfoVO(StorageInfoPO po){
		ArrayList<String[]> orderAndPostition = new ArrayList<String[]>();
		int num = po.getFlexible()+po.getPlane()+po.getTrain()+po.getTruck();
		System.out.println(num);
		ArrayList<long[][][]> info = po.getStorage();
		for(int i = 0 ;i< info.size();i++){
			long[][][] pos = info.get(i);
			int row = 0;
			if(i == 0) row = po.getPlaneRow();
			else if(i == 1) row = po.getTrainRow();
			else if(i == 2) row = po.getTruckRow();
			else row = po.getFlexibleRow();
			for(int j = 0 ; j < row;j++){
				for(int k = 0 ; k < po.getShelf();k++){
					for(int n = 0; n < po.getNum();n++){
						String[] s = {pos[j][k][n]+"",i+"",j+"",k+"",n+""};
						orderAndPostition.add(s);
					}
				}
			}
		}
		orderAndPostitionArray = new String[orderAndPostition.size()][5];
		for (int i = 0; i <orderAndPostition.size(); i++) {
			orderAndPostitionArray[i] = orderAndPostition.get(i);
			
		}
	}
	
	
	
}
