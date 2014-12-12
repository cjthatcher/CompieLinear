package edu.usu.math.cla.matrix.eigen;

import java.util.concurrent.Callable;

import edu.usu.math.cla.matrix.MatrixCalculator;

public class EigenValueWorker implements Callable<Double> {
	
	double[][] A;
	double shift;
	double eigenValue;
	double tolerance;
	int maxIterations;
	
	public EigenValueWorker(double[][] A, double shift, double tolerance, int maxIterations)
	{
		this.A = A;
		this.shift = shift;
		this.tolerance = tolerance;
		this.maxIterations = maxIterations;
	}
	
	public double getEigenValue()
	{
		return this.eigenValue;
	}

	@Override
	public Double call() throws Exception {
		double result = MatrixCalculator.findShiftedEigenValue(A, shift, tolerance, maxIterations);
		return result;
	}

}
