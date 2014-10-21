package edu.usu.math.cla.division;

public class Divisionless {
	
	public static double doDivision(double b, double initialGuess, double tolerance)
	{
		double guess = initialGuess;
		double difference = tolerance * 10;
		double oldGuess;
		
		while (difference > tolerance)
		{
			oldGuess = guess;
			guess = guess * (2 - (b * guess));
			difference = Math.abs(oldGuess - guess);
		}
		
		return guess;
	}
	
	public static void main(String[] args)
	{
		System.out.println(doDivision(Math.PI, 1, Math.pow(10, -10)));
		System.out.println(doDivision(Math.PI, 0.1, Math.pow(10, -10)));
	}
	

}
