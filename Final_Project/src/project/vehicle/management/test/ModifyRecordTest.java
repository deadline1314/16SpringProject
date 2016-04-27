package project.vehicle.management.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import project.vehicle.management.dto.Car;
import project.vehicle.management.dto.CarDataManager;
import project.vehicle.management.dto.Screen_ModifyRecord;

public class ModifyRecordTest {

	private static Car car1 = new Car("1", "1", "1", 1, "NEW" ,"1970", "1", "1", 1.0);
	private static Car car2 = new Car("2", "1", "1", 1, "OLD" ,"1970", "1", "1", 1.0);
	@Test
	public void test() {
		ArrayList<Car> carList = new ArrayList<>(Arrays.asList(car1,car2));
		Screen_ModifyRecord smr = new Screen_ModifyRecord(car1, "sb", carList);
		CarDataManager cdm = smr.dataManager;
		Car car1 = new Car("1", "sa", "dora", 1, "dora" ,"1970", "sa", "sa", 1.0);
		 cdm.modifyCar(car1);
		assertTrue(carList.get(0).getCondition() == "dora");
	}

}
