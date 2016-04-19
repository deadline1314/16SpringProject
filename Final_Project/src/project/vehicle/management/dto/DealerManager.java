package project.vehicle.management.dto;
/**
 * 
 * @author Jia
 *
 */

public class DealerManager {
	
	//DealerManager -> listOfDealers
		// DealerManager -> listOfDealers
	public List<String> getDealersName(File f) throws IOException {
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
