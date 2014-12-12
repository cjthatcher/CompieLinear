package edu.usu.math.cla.matrix.eigen;

import java.util.ArrayList;
import java.util.List;

import edu.usu.math.cla.matrix.MatrixCalculator;

public class EigenValueFinder {
	
	public static List<Double> findAllEigenvalues(double[][] A, int numberOfDivisions, double tolerance, int maxIterations)
	{
		double largest = MatrixCalculator.findGreatestEigenvalue(A, tolerance, maxIterations);
		double smallest = MatrixCalculator.findSmallestEigenValueConjugateGradient(A, tolerance, maxIterations);
		double distance = largest - smallest;
		double step = distance / numberOfDivisions;
		
		List<Double> eigenValueList = new ArrayList<Double>();
		
		for (int i = 0; i < numberOfDivisions; i++)
		{
			double e = MatrixCalculator.findShiftedEigenValue(A, (step * i) + step * 0.5, tolerance, maxIterations);
			addToListIfNotDuplicate(e, eigenValueList, tolerance);
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
