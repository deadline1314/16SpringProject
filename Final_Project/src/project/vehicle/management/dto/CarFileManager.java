package project.vehicle.management.dto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * 
 * @author Ruotian
 *
 */

public class CarFileManager {
	// CarFileManager -> readCars, writeCars

	//read data from file and change it into Car type
	private ArrayList<Car> carList;
	public ArrayList<Car> readCars(String file) {
		boolean isFirstLine = true;
		carList = new ArrayList<Car>();
		try {
			File f = new File(file);
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);

			while (true) {
				String line = br.readLine();
				// skip the first line
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				if (line == null)
					break;
				String[] temp = line.split("~");
				Car record = new Car(temp[0].trim(), temp[1].trim(), temp[2], Integer.parseInt(temp[3].trim()), temp[4].trim(),
						temp[5].trim(), temp[6].trim(), temp[7].trim(), (double)Double.parseDouble(temp[8].trim()));
				carList.add(record);
			}
			br.close();
			isr.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return carList;

	}
	
	//write the data into file
	//"String file" is the path of file
	public void writeCars(ArrayList<Car> carList, String file) {
		ArrayList<String> temp = new ArrayList<String>();
		for (Car car : carList) {
			String line = car.getVIN() + "~" + car.getDealerName() + "~"+ car.getCondition() + "~" + 
					String.valueOf(car.getYear()) + "~"+ car.getMake() + "~"  + car.getModel() + "~"
					+ car.getTrim() + "~" + car.getType() + "~" + String.valueOf(car.getPrice());
			temp.add(line);
		}
		try {
			File o = new File(file);
			FileOutputStream fos = new FileOutputStream(o);
			String firstLine = "id~webId~category~year~make~model~trim~type~price\n";
			fos.write(firstLine.getBytes());
			for(String str : temp){
				String strln = str+"\n";
				fos.write(strln.getBytes());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CarFileManager cfm = new CarFileManager();
		ArrayList<Car> car;
		car = cfm.readCars("/Users/workspace/Final_Project/src/project/vehicle/data/gmps-aj-dohmann");
		for(Car c : car){
			System.out.println(c);
		}

	}

}
