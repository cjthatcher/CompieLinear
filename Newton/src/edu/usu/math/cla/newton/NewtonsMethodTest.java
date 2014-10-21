package edu.usu.math.cla.newton;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NewtonsMethodTest {

	@Test
	public void test() {
		Function f = (x-> x[0]*x[0]);
		
		Function fPrime = (x -> 2*x[0]);
		
		assertEquals(0.0, NewtonsMethod.findRoot(f, fPrime, 1, 0.0000005), 0.000005);
	}

}
