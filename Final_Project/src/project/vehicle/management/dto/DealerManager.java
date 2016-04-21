package project.vehicle.management.dto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jia
 *
 */

public class DealerManager {
	
		// DealerManager -> listOfDealers
	public List<String> getDealersName(String file) throws IOException {
		File f = new File(file);
		BufferedReader br = null;
		List<String> dealersNameList = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(f));
			String nameLocWeb;
			while ((nameLocWeb = br.readLine()) != null) {
				String[] dealerInfo = nameLocWeb.split("	");
				dealersNameList.add(dealerInfo[0]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				br.close();
			}
		}
		return dealersNameList;
	}           
}
