package edu.usu.math.cla.newton;

public class NewtonsMethod {

	public static double findRoot(Function f, Function fPrime, double startGuess, double tolerance)
	{
		double difference = tolerance * 10;
		double guess = startGuess;
		int maxIterations = 10000;
		int count = 0;
		
		while (difference > tolerance && count < maxIterations)
		{
			double oldValue = guess;
			guess = guess - (f.evaluate(guess) / fPrime.evaluate(guess));
			difference = Math.abs(oldValue - guess);
			count++;
		}
		
		return guess;
	}
	
	public static void main(String[] args)
	{
		Function f = (x-> (x[0] - 1)*(x[0] -1)*Math.pow(Math.E,x[0]));
		Function fPrime = (x-> (x[0]*x[0] - 1) * Math.pow(Math.E, x[0]));
		System.out.println(NewtonsMethod.findRoot(f, fPrime, 2.0, 0.0000005));
	}
	
	
}
