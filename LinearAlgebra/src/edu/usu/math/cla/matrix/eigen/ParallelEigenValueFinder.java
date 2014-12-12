package edu.usu.math.cla.matrix.eigen;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import edu.usu.math.cla.matrix.MatrixCalculator;

public class ParallelEigenValueFinder {
	
	public static List<Double> findAllEigenvalues(double[][] A, int numberOfDivisions, double tolerance, int maxIterations)
	{
		double largest = MatrixCalculator.findGreatestEigenvalue(A, tolerance, maxIterations);
		double smallest = MatrixCalculator.findSmallestEigenValueConjugateGradient(A, tolerance, maxIterations);
		double distance = largest - smallest;
		double step = distance / numberOfDivisions;
		
		ExecutorService executor = Executors.newCachedThreadPool();
		List<Future<Double>> futureList = new ArrayList<Future<Double>>();
		
		for (int i = 0; i < numberOfDivisions; i++)
		{
			futureList.add(executor.submit(new EigenValueWorker(A, (step * i) + step * 0.5, tolerance, maxIterations)));
		}
		
		List<Double> eigenValueList = new ArrayList<Double>();
		
		try {
		for (Future<Double> f : futureList)
		{
			double e = f.get();
			addToListIfNotDuplicate(e, eigenValueList, tolerance);
		}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		return eigenValueList;
	}
	
	private static void addToListIfNotDuplicate(double e, List<Double> list, double tolerance)
	{
		for (double d : list)
		{
			if (Math.abs(d - e) < tolerance * 1000)
				return;
		}
		
		list.add(e);
	}

}
