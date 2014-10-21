package edu.usu.math.roots;

import edu.usu.math.Function;

public class Driver {
	
	public static void main(String[] args)
	{
		question22();
		question23a();
		question23b();
	}
	
	public static void question22()
	{
		Function f = new Function() {

			@Override
			public double evaluate(double... args) {
				return Math.cos(args[0]) + (args[0] / 10);
			}
		};
		
		Function fPrime = new Function() {

			@Override
			public double evaluate(double... args) {
				return (-1 * Math.sin(args[0])) + 0.1;
			}
			
		};
		
		Function fDoublePrime = new Function() {

			@Override
			public double evaluate(double... args) {
				return -1 * Math.cos(args[0]);
			}
			
		};
		
		FunctionMinimizer.getMinimumValue(f, fPrime, fDoublePrime, -10, 10);
	}
	
	public static void question23a()
	{
		Function f = new Function() {

			@Override
			public double evaluate(double... args) {
				return Math.sin(args[0]);
			}
		};
		
		Function fPrime = new Function() {

			@Override
			public double evaluate(double... args) {
				return (Math.cos(args[0]));
			}
			
		};
		
		Function fDoublePrime = new Function() {

			@Override
			public double evaluate(double... args) {
				return -1 * Math.sin(args[0]);
			}
			
		};
		
		FunctionMinimizer.getMinimumValue(f, fPrime, fDoublePrime, -10, 10);
	}
	
	public static void question23b()
	{
		Function f = new Function() {

			@Override
			public double evaluate(double... args) {
				return -1 * Math.sin(args[0]) / args[0];
			}
		};
		
		Function fPrime = new Function() {

			@Override
			public double evaluate(double... args) {
				double x = args[0];
				return (Math.sin(x) - x * Math.cos(x)) / Math.pow(x, 2);
			}
			
		};
		
		Function fDoublePrime = new Function() {

			@Override
			public double evaluate(double... args) {
				double x = args[0];
				return ((Math.pow(x, 2) - 2) * Math.sin(x) + (2 * x * Math.cos(x)) / Math.pow(x, 3));
			}
			
		};
		
		FunctionMinimizer.getMinimumValue(f, fPrime, fDoublePrime, -10, 10);
	}

}
