package edu.usu.math.roots;
import edu.usu.math.Function;


public class FixedPointEvaluation {
	
	/*
	 * Performs fixed point evaluation on a function. Takes a formula, an initial guess, and a tolerance.
	 * 
	 * Returns the fixed point, if it exists. 
	 * returns -1 if no fixed point after 1000 iterations
	 */
	public static double findFixedPoint(Function f, double point, double tolerance)
	{
		double value = f.evaluate(point);
		
		double error = Math.abs(value - point);
		double oldValue = point;
		int iter = 0;
		
		while (error > tolerance)
		{
			oldValue = value;
			value = f.evaluate(value);
			error = Math.abs(value - oldValue);
			iter++;
			if (iter > 1000)
			{
				System.out.println("No fixed point! Breaking after 1000 tries");
				return -1;
			}
		}
		
		return value;
	}
	
	public static void main(String[] args)
	{
		Function f = new Function() {

			@Override
			public double evaluate(double... args) {
				double x = args[0];
				return (x * x) + 3d/16;
			}
			
		};
		
		for (double d = -10; d < 10; d+=0.05)
		{
			System.out.println(findFixedPoint(f, d, Math.pow(10, -6)));
		}
	}

}
