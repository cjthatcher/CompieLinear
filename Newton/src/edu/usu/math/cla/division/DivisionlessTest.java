package edu.usu.math.cla.division;

import org.junit.Assert;
import org.junit.Test;

public class DivisionlessTest {

	@Test
	public void test() {
		Assert.assertEquals(Divisionless.doDivision(3, 0.4, 0.000005), 1.0/3, 0.00005);
	}

}
