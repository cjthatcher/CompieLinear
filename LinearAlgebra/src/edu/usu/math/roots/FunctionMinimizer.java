package edu.usu.math.roots;

import java.util.ArrayList;
import java.util.List;

import edu.usu.math.Function;

public class FunctionMinimizer {
	
	/*
	 * This function finds the global minimum of a function f on a given interval. 
	 * It uses the secant method to find the roots of f', and then evalutes them at f'' to see if they are minima
	 * It then finds the smallest minimum.
	 */
	public static double getMinimumValue(Function f, Function fPrime, Function fDoublePrime, double startPoint, double endPoint)
	{
		/*
		 * Find roots of f' using secant method
		 */
		System.out.println("Critical Points of f':");
		List<Double> rootList = HybridRootFinder.findRoots(fPrime, startPoint, endPoint, 1000, Math.pow(10, -8));
		
		for (Double d : rootList)
		{
			System.out.println(d);
		}
		
		System.out.println();
		
		
		/*
		 * Determine which minima have f'' > 0
		 */
		ArrayList<Double> minimaList = new ArrayList<Double>();
		System.out.println("Local Minimum Points of f'");
		for (Double d : rootList)
		{
			double value = fDoublePrime.evaluate(d);
			if (value > 0)
			{
				System.out.println(d);
				minimaList.add(d);
			}
		}
		
		System.out.println();
		
		
		/*
		 * Find the function values for the local minima, and print out the smallest one.
		 * If there are multiples with the same value, print them.
		 */
		double minValue = Double.MAX_VALUE;
		List<Double> minLocation = new ArrayList<Double>();
		
		for (Double d: minimaList)
		{
			double tempValue = f.evaluate(d);
			if (tempValue < minValue)
			{
				minLocation.clear();
				minValue = tempValue;
				minLocation.add(d);
				continue;
			}
			
			if (tempValue == minValue)
			{
				minLocation.add(d);
			}
			
		}
		
		for (Double d : minLocation)
		{
			System.out.println("Min location = " + d + ", minValue = " + minValue);
		}
		return minValue;
	}
	
	
	public static void testOne()
	{
		Function f = new Function() {

			@Override
			public double evaluate(double... args) {
				return Math.sin(args[0]) + (args[0] / 2);
			}
		};
		
		Function fPrime = new Function() {

			@Override
			public double evaluate(double... args) {
				return Math.cos(args[0]) + 0.5;
			}
			
		};
		
		Function fDoublePrime = new Function() {

			@Override
			public double evaluate(double... args) {
				return -1 * Math.sin(args[0]);
			}
			
		};
		
		getMinimumValue(f, fPrime, fDoublePrime, -10, 10);
	}
	public static void main(String[] args)
	{

		
		Function f = new Function() {

			@Override
			public double evaluate(double... args) {
				return Math.sin(args[0]) + (args[0] / 2);
			}
		};
		
		Function fPrime = new Function() {

			@Override
			public double evaluate(double... args) {
				return Math.cos(args[0]) + 0.5;
			}
			
		};
		
		Function fDoublePrime = new Function() {

			@Override
			public double evaluate(double... args) {
				return -1 * Math.sin(args[0]);
			}
			
		};
		
		getMinimumValue(f, fPrime, fDoublePrime, -10, 10);
	}

}
