package edu.usu.math.polynomial;

public class HornerEvaluation {
	
	/*
	 * Evaluates a given polynomial using Horner Evaluation (Nested Evaluation)
	 * Takes an array of double coefficients, starting at the highest power and going to the lowest.
	 * Also takes a double point, the point at which we are evaluating the function
	 */
	public static double evaluate(double[] coefficients, double point)
	{
		double value = 0d;
		
		for (double coefficient : coefficients)
		{
			value = (value * point) + coefficient;
		}
		
		return value;
	}
	
	/*
	 * Manually evalutes a function. Used for a homework assignment. Ignore this.
	 */
	public static double longEvaluate(double d)
	{
		double value = d - 2;
		for (int i = 0; i < 8; i++)
		{
			value = value * (d - 2);
		}
		
		return value;
	}
	
	
	/*
	 * Driver to pull off a homework assignment. Ignore.
	 */
	public static void main(String[] args)
	{
		double[] coefficients = {1, -18, 144, -672, 2016, -4032, 5376, -4608, 2304, -512};
		
		for (double d = 1.92; d <= 2.08; d += ((2.08 - 1.92) / 160))
		{
			double hornerValue = evaluate(coefficients, d);
			double regularValue = longEvaluate(d);
			System.out.println(d + "\t" + hornerValue + "\t" + regularValue);
		}
	}

}
