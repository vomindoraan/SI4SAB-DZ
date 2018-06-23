import operations.*;
import student.*;
import tests.TestHandler;
import tests.TestRunner;

public class StudentMain {
	public static void main(String[] args) {
		CityOperations cityOperations = new dk140414_CityOperations();
		DistrictOperations districtOperations = new dk140414_DistrictOperations();
		CourierOperations courierOperations = new dk140414_CourierOperations();
		CourierRequestOperation courierRequestOperation = new dk140414_CourierRequestOperation();
		GeneralOperations generalOperations = new dk140414_GeneralOperations();
		UserOperations userOperations = new dk140414_UserOperations();
		VehicleOperations vehicleOperations = new dk140414_VehicleOperations();
		PackageOperations packageOperations = new dk140414_PackageOperations();

		TestHandler.createInstance(
				cityOperations,
				courierOperations,
				courierRequestOperation,
				districtOperations,
				generalOperations,
				userOperations,
				vehicleOperations,
				packageOperations);

		TestRunner.runTests();
	}
}
