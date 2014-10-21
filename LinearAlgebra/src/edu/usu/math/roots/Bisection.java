package edu.usu.math.roots;
import edu.usu.math.Function;


public class Bisection {

	/*
	 * Uses the bisection method to find the root of a function.
	 * 
	 * Takes a Formula object, which is really a wrapper for your mathematical function.
	 * Takes the start and end points of the range you are checking in
	 * Take a double tolerance
	 * 
	 * Returns the approximation of the root if it exists.
	 */
	public double findRoot(Function formula, double startPoint, double endPoint, double tolerance)
	{
		
		double startValue = formula.evaluate(startPoint);
		double endValue = formula.evaluate(endPoint);
		if (startValue * endValue > 0)
		{
			System.out.println("Failure! function at end points doesn't guarantee a root between");
			return 0;
		}
		
		double midPoint = (startPoint + endPoint) / 2;
		double midValue = formula.evaluate(midPoint);
		
		double error = tolerance * 10;
		int iterCount = 0;
		
		while (error > tolerance)
		{
			if (startValue * midValue < 0)
			{
				endPoint = midPoint;
			}
			else
			{
				startPoint = midPoint;
			}
			
			midPoint = (startPoint + endPoint) / 2;
			midValue = formula.evaluate(midPoint);
			
			iterCount++;
			error = Math.abs(midValue);
		}
		
		System.out.println("Iterations = " + iterCount);
		System.out.println("Error = " + error);
		return midPoint;
	}
	
	public static void main(String[] args)
	{
		Function f = new Function(){

			@Override
			public double evaluate(double... args) {
				return Math.sqrt(args[0]) - 1.1d;
			}
			
		};
		
		Bisection bisection = new Bisection();
		System.out.println(bisection.findRoot(f, 0, 2, Math.pow(10, -8)));
	}
	
}
