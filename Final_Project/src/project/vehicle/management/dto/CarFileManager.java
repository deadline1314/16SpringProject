package project.vehicle.management.dto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import project.vehicle.management.dto.Car.Condition;

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
				Car record = new Car(temp[0], temp[1], Integer.parseInt(temp[2]), temp[3], Condition.valueOf(temp[4]),
						temp[5], temp[6], temp[7], Double.parseDouble(temp[8]));
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
			String line = car.getVIN() + "~" + car.getDealerName() + "~" + String.valueOf(car.getYear()) + "~"
					+ car.getMake() + "~" + String.valueOf(car.getCondition()) + "~" + car.getModel() + "~"
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
		// TODO Auto-generated method stub

	}

}
