package edu.usu.math.cla.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.usu.math.iterative.ConjugateGradient;
import edu.usu.math.iterative.GaussSeidel;
import edu.usu.math.iterative.Jacobi;




public class MatrixCalculator {
	
	//multiply a matrix by a matrix
	public static double[][] matrixMultiply(double[][] A, double[][] B)
	{
		double[][] C = new double[A.length][B[0].length];
		
		for (int i = 0; i < A.length; i++)
		{
			for (int j = 0; j < B[0].length; j++)
			{
				for (int k = 0; k < B.length; k++)
				{
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		
		
		return C;
	}
	
	//Multiply a matrix by a vector
	public static double[] matrixVectorMultiply(double[][] A, double[] x)
	{
		double[] b = new double[x.length];
		for (int i = 0; i < x.length; i++)
		{
			double sum = 0;
			for (int j = 0; j < x.length; j++)
			{
				sum += A[i][j] * x[j];
			}
			b[i] = sum;
		}
		
		return b;
	}
	
	//multiply a vector by a scalar
	public static double[] scalarVectorMultiply(double e, double[] x)
	{
		double[] result = new double[x.length];
		for (int i = 0; i < x.length; i++)
		{
			result[i] = x[i] * e;
		}
		
		return result;
	}
	
	//Multiply a matrix by a scalar
	public static double[][] scalarMatrixMultiply(double e, double[][] A)
	{
		double[][] result = new double[A.length][A.length];
		for (int i = 0; i < A.length; i++)
		{
			for (int j = 0; j < A[i].length; j++)
			{
				result[i][j] = A[i][j] * e;
			}
		}
		
		return result;
	}
	
	//Add two matrices
	public static double[][] addMatrices(double[][] A, double[][] B)
	{
		double[][] result = new double[A.length][A.length];
		for (int i = 0; i < A.length; i++)
		{
			for (int j = 0; j < A[i].length; j++)
			{
				result[i][j] = A[i][j] + B[i][j];
			}
		}
		return result;
	}
	
	//Create a bogus diagonally dominant matrix. Gonna be fun.
	public static double[][] makeDiagonallyDominantMatrix(int count)
	{
		double[][] result = new double[count][count];
		for (int i = 0; i < count; i++)
		{
			for (int j = 0; j < count; j++)
			{
				if (i == j)
				{
					result[i][j] = (2.0 - Math.random()) * count;
				}
				else
				{
					result[i][j] = Math.random();
				}
			}
		}
		return result;
	}

	
	//Initialize a random guess vector
	public static double[] makeRandomVector(int i) {
		double[] result = new double[i];
		for (int j = 0; j < i; j++)
		{
			result[j] = Math.random();
		}
		return result;
	}
	
	public static double[] makeOneVector(int i) {
		double[] result = new double[i];
		Arrays.fill(result, 1);
		return result;
	}
	
	//Create a bogus pentadiagonal matrix from chapter 7.
	public static double[][] makePentadiagonalMatrix(int n)
	{
		double[][] A = new double[n][n];
		int N = (int) Math.round(Math.sqrt(n));
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (i == j)
				{
					A[i][j] = 4;
				}
				else if (i == j - 1 || j == i - 1)
				{
					A[i][j] = -1;
				}
				else if (i == j - 3 || j == i - 3)
				{
					A[i][j] = -1;
				}
				else
				{
					A[i][j] = 0;
				}
			}
		}
		return A;
	}
	
	//Do matrix multiplication (quickly) because this is a pentadiagonal matrix
	public static double[] bandedMatrixVectorMultiplication(double[][] A, double[] x)
	{
		double[] result = new double[A.length];
		int n = (int) Math.round(Math.sqrt(A.length));
		for (int i = 0; i < A.length; i++)
		{
			double sum = 0;
			sum += x[i] * A[i][i];
			if (i + 1 < A.length) {
				sum += x[i+1] * A[i][i+1];
			}
			if (i - 1 > - 1){
				sum += x[i-1] * A[i][i - 1];
			}
			
			if (i - (n) > - 1){
				sum += x[i - (n)] * A[i][i - n];
			}
			
			if (i + (n) < A.length){
				sum += x[i + (n)] * A[i][i + n];
			}
			
			result[i] = sum;
		}
		return result;
	}
	
	public static double[] pentadiagonalMatrixVectorMultiplication(double[] ssd, double[] sd, double[] d, double[] ud, double[] uud, double[] x)
	{
		double[] result = new double[x.length];
		for (int i = 0; i < result.length; i++)
		{
			double sum = 0;
			sum += x[i] * d[i];
			if (i+1 < x.length)
				sum += x[i+1] * ud[i];
			if (i+3 < x.length)
				sum += x[i+3] * uud[i];
			if (i-1 >= 0)
				sum += x[i - 1] * sd[i];
			if (i - 3 >= 0)
				sum += x[i - 3] * ssd[i];
			
			result[i] = sum;
		}
		
		return result;
	}

	//Add two vectors together
	public static double[] addVectors(double[] x0, double[] x1) {
		double[] result = new double[x0.length];
		for (int i = 0; i < result.length; i++)
		{
			result[i] = x0[i] + x1[i];
		}
		return result;
	}
	
	//Find the error (or change) between two vectors
	public static double computeError(double[] x0, double[] x1)
	{
		double sum = 0;
		for (int i = 0; i < x0.length; i++)
		{
			sum += ((x0[i] - x1[i]) * (x0[i] - x1[i]));
		}
		return Math.sqrt(sum);
	}
	
	//Subtract two vectors. 
	public static double[] subtractVectors(double[] b, double[] w)
	{
		double[] result = new double[b.length];
		for (int i = 0; i < b.length; i++)
		{
			result[i] = b[i] - w[i];
		}
		return result;
	}
	
	//Initialize an initial guess vector with random values between 0 and 1 for each entry.
	public static double[] makeRandomX0(double[] b)
	{
		double[] x0 = new double[b.length];
		for (int i = 0; i < x0.length; i++)
		{
			x0[i] = Math.random();
		}
		
		return x0;
	}
		
	public static double findGreatestEigenvalue(double[][] A, double tolerance, int maxIterations)
	{
		int count = 0;
		double error = tolerance * 10;
		double lambda0 = Double.MAX_VALUE;
		double lambda1;
		
		double[] z0 = makeRandomX0(A[0]);
		
		double[] z1 = MatrixCalculator.matrixVectorMultiply(A, z0);
		double[] y;
		double[] w;
		
		while (error > tolerance && count < maxIterations)
		{
			y = createNormalizedVector(z1);
			w = matrixVectorMultiply(A, y);
			
			lambda1 = vectorVectorInnerProduct(y, w);
			
			error = computeRelativeError(lambda0, lambda1);
			
			z1 = w;
			lambda0 = lambda1;
			count++;
		}
		
		return lambda0;
	}
	
	public static double findSmallestEigenValueConjugateGradient(double[][] A, double tolerance, int maxIterations)
	{
		int count = 0;
		double error = tolerance * 10;
		double lambda0 = Double.MAX_VALUE;
		double lambda1;
		
		double[] z0 = makeRandomVector(A.length);

		double[] z1 = ConjugateGradient.solveSystem(A, z0, tolerance, maxIterations);
		double[] y;
		double[] w;
		
		while (error > tolerance && count < maxIterations)
		{
			y = createNormalizedVector(z1);
			w = ConjugateGradient.solveSystem(A, y, tolerance, maxIterations);
			
			lambda1 = vectorVectorInnerProduct(y, w);
			
			error = computeRelativeError(lambda0, lambda1);
			
			z1 = w;
			lambda0 = lambda1;
			count++;
		}
		
		return 1/lambda0;
	}
	
	/* Nuke these later. Just for tests! */
	 
	public static double findSmallestEigenValueJacobi(double[][] A, double tolerance, int maxIterations)
	{
		int count = 0;
		double error = tolerance * 10;
		double lambda0 = Double.MAX_VALUE;
		double lambda1;
		
		double[] z0 = makeRandomVector(A.length);

		double[] z1 = Jacobi.solveSystem(A, z0, tolerance, maxIterations);
		double[] y;
		double[] w;
		
		while (error > tolerance && count < maxIterations)
		{
			y = createNormalizedVector(z1);
			w = Jacobi.solveSystem(A, y, tolerance, maxIterations);
			
			lambda1 = vectorVectorInnerProduct(y, w);
			
			error = computeRelativeError(lambda0, lambda1);
			
			z1 = w;
			lambda0 = lambda1;
			count++;
		}
		
		return 1/lambda0;
	}
	
	public static double findSmallestEigenValueGS(double[][] A, double tolerance, int maxIterations)
	{
		int count = 0;
		double error = tolerance * 10;
		double lambda0 = Double.MAX_VALUE;
		double lambda1;
		
		double[] z0 = makeRandomVector(A.length);

		double[] z1 = GaussSeidel.solveSystem(A, z0, tolerance, maxIterations);
		double[] y;
		double[] w;
		
		while (error > tolerance && count < maxIterations)
		{
			y = createNormalizedVector(z1);
			w = GaussSeidel.solveSystem(A, y, tolerance, maxIterations);
			
			lambda1 = vectorVectorInnerProduct(y, w);
			
			error = computeRelativeError(lambda0, lambda1);
			
			z1 = w;
			lambda0 = lambda1;
			count++;
		}
		
		return 1/lambda0;
	}
	
	public static double findPentadiagonalGreatestEigenvalue(double[] ssd, double[] sd, double[] d, double[] ud, double[] uud, double tolerance, int maxIterations)
	{
		int count = 0;
		double error = tolerance * 10;
		double lambda0 = Double.MAX_VALUE;
		double lambda1;
		
		double[] z0 = makeRandomX0(d);
		
		double[] z1 = MatrixCalculator.pentadiagonalMatrixVectorMultiplication(ssd, sd, d, ud, uud, z0);
		double[] y;
		double[] w;
		
		while (error > tolerance && count < maxIterations)
		{
			y = createNormalizedVector(z1);
			w = pentadiagonalMatrixVectorMultiplication(ssd, sd, d, ud, uud, y);
			
			lambda1 = vectorVectorInnerProduct(y, w);
			
			error = computeRelativeError(lambda0, lambda1);
			
			z1 = w;
			lambda0 = lambda1;
			count++;
		}
		
		return lambda0;
	}
	
	public static double findPentadiagonalSmallestEigenValue(double[] ssd, double[] sd, double[] d, double[] ud, double[] uud, double tolerance, int maxIterations)
	{
		int count = 0;
		double error = tolerance * 10;
		double lambda0 = Double.MAX_VALUE;
		double lambda1;
		
		double[] z0 = makeRandomVector(d.length);

		double[] z1 = Jacobi.solvePentadiagonalSystem(ssd, sd, d, ud, uud, z0, tolerance, maxIterations);
		double[] y;
		double[] w;
		
		while (error > tolerance && count < maxIterations)
		{
			y = createNormalizedVector(z1);
			w = Jacobi.solvePentadiagonalSystem(ssd, sd, d, ud, uud, y, tolerance, maxIterations);
			
			lambda1 = vectorVectorInnerProduct(y, w);
			
			error = computeRelativeError(lambda0, lambda1);
			
			z1 = w;
			lambda0 = lambda1;
			count++;
		}
		
		return 1/lambda0;
	}
	
	public static double[] createNormalizedVector(double[] v)
	{
		double[] normal = new double[v.length];
		double magnitude = getVectorMagnitude(v);
		
		for (int i = 0; i < v.length; i++)
		{
			normal[i] = v[i] / magnitude;
		}
		
		return normal;
	}
	
	private static double getVectorMagnitude(double[] v)
	{
		double sumOfSquares = 0;
		for (double d : v)
		{
			sumOfSquares += Math.pow(d, 2);
		}
		
		return Math.sqrt(sumOfSquares);
	}
	
	private static double vectorVectorInnerProduct(double[] v1, double[] v2)
	{
		double sum = 0;
		for (int i = 0; i < v1.length; i++)
		{
			sum += v1[i] * v2[i];
		}
		return sum;
	}
	
	public static double[][] vectorVectorOuterProduct(double[] v1, double[] v2)
	{
		double[][] result = new double[v1.length][v2.length];
		for (int i = 0; i < v1.length; i++)
		{
			for (int j = 0; j < v2.length; j++)
			{
				result[i][j] = v1[i] * v2[j];
			}
		}
		
		return result;
	}
	
	private static double computeRelativeError(double d1, double d2)
	{
		return Math.abs(d2 - d1) / Math.abs(d1);
	}

	public static double[] makeEmptyVector(int length) {
		double[] d = new double[length];
		Arrays.fill(d, 0);
		return d;
	}
	
	public static double findShiftedEigenValue(double[][] A, double shift, double tolerance, int maxIterations)
	{
		//KK, do the algorithm on page 228. Will be better. Word
		//The shift is expressed as a positive number. But we shift it DOWN that much. Weird, right?
		double[] v0 = makeRandomVector(A.length);
		double[][] shiftedA = shiftMatrixDown(A, shift);
		double lambdaK = Double.MAX_VALUE;
		double oldLambda;
		double error = tolerance * 10;
		int count = 0;
		
		while(error > tolerance && count < maxIterations)
		{
			double[] vk = ConjugateGradient.solveSystem(shiftedA, v0, tolerance, maxIterations);
			vk = createNormalizedVector(vk);
			oldLambda = lambdaK;
			lambdaK = vectorVectorInnerProduct(vk, matrixVectorMultiply(A, vk));
			v0 = vk;
			count++;
			error = computeScalarError(lambdaK, oldLambda);
		}
		return lambdaK;
	}
	
	public static double[][] shiftMatrixDown(double[][] A, double shift)
	{
		double[][] d = new double[A.length][A.length];
		for (int i = 0; i < A.length; i++)
		{
			for (int j = 0; j < A.length; j++)
			{
				d[i][j] = A[i][j];
				if (i == j) {
					d[i][j] -= shift;
				}
			}
		}
		return d;
	}
	
	private static double computeScalarError(double a, double b)
	{
		return Math.abs(a - b);
	}
	
	public static void main(String[] args)
	{
		double[][] A = makePentadiagonalMatrix(10);
		shiftMatrixDown(A, 100);
	}
	
	public static List<double[][]> QRFactorization(double[][] A)
	{
		double[][][] q = new double[A.length][A.length][A.length];
		double[][] z = copyMatrix(A);
		double[][] z1;
		
		for (int k = 0; k < A.length - 1; k++)
		{
			double[] e = new double[A.length];
			double[] x = new double[A.length];
			double a;
			
			z1 = createMinorMatrix(z, k);
			z = z1;
			
			x = getNthColumn(z, k);
			a = findVectorNorm(x);
			
			if (A[k][k] > 0) a = -a;
			
			for (int i = 0; i < A.length; i++)
			{
				e[i] = (i == k) ? 1 : 0;
			}
			
			//Should be: e = x + (e * a)
			e = addVectors(x, scalarVectorMultiply(a, e));
			e = createNormalizedVector(e);
			//m = I - vvT
			q[k] = subtractMatrices(createIdentity(A.length), scalarMatrixMultiply(2, vectorVectorOuterProduct(e, e)));
			z1 = matrixMultiply(q[k], z);
			z = z1;
		}
		double[][] Q = q[0];
		double[][] R = matrixMultiply(q[0], A);
		for (int i = 1; i < A.length - 1; i++)
		{
			z1 = matrixMultiply(q[i], Q);
			Q = z1;
		}
		z = matrixMultiply(Q, A);
		R = z;
		Q = matrixTranspose(Q);
		
		List<double[][]> resultList = new ArrayList<double[][]>();
		resultList.add(Q);
		resultList.add(R);
		return resultList;
	}
	
	public static double[][] matrixTranspose(double[][] A)
	{
		double[][] result = new double[A.length][A.length];
		for (int i = 0; i < A.length; i++)
		{
			for (int j = 0; j < A.length; j++)
			{
				result[i][j] = A[j][i];
			}
		}
		
		return result;
	}
	public static double[][] subtractMatrices(double[][] A, double[][] B)
	{
		double[][] result = new double[A.length][A.length];
		for (int i = 0; i < A.length; i++)
		{
			for (int j = 0; j < A.length; j++)
			{
				result[i][j] = A[i][j] - B[i][j];
			}
		}
		return result;
	}

	private static double[][] createIdentity(int length)
	{
		double[][] I = new double[length][length];
		for (int i = 0; i < length; i++)
		{
			I[i][i] = 1;
		}
		return I;
	}
	private static double[][] createMinorMatrix(double[][] A, int d)
	{
		double[][] B = new double[A.length][A.length];
		for (int i = 0; i < d; i++)
		{
			B[i][i] = 1;
		}
		
		for (int i = d; i < A.length; i++)
		{
			for (int j = d; j < A.length; j++)
			{
				B[i][j] = A[i][j];
			}
		}
		
		return B;
	}
	
	private static double[] getNthColumn(double[][] A, int n)
	{
		double[] result = new double[A.length];
		for (int i = 0; i < A.length; i++)
		{
			result[i] = A[i][n];
		}
		
		return result;
	}
	
	private static double findVectorNorm(double[] v)
	{
		double sum = 0;
		for (double d : v)
		{
			sum += (d * d);
		}
		return Math.sqrt(sum);
	}
	
	private static double[][] copyMatrix(double[][] A)
	{
		double[][] tempA = new double[A.length][A.length];
		for (int i = 0; i < A.length; i++)
		{
			for (int j = 0; j < A.length; j++)
			{
				tempA[i][j] = A[i][j];
			}
		}
		
		return tempA;
	}
	
	public static List<Double> findAllEigenValuesQR(double[][] A, double tolerance, int maxIterations)
	{
		List<Double> resultList = new ArrayList<Double>();
		double[][] Ak = A;
		double[][] Q;
		double[][] R;
		double[] diagonal;
		double[] oldDiagonal = new double[A.length];
		double error = Double.MAX_VALUE;
		List<double[][]> qrList;
		int count = 0;
		while (error > tolerance && count < maxIterations)
		{
			qrList = QRFactorization(Ak);
			Q = qrList.get(0);
			R = qrList.get(1);
			Ak = matrixMultiply(R, Q);
			
			diagonal = findDiagonal(Ak);
			error = computeError(diagonal, oldDiagonal);
			
			oldDiagonal = diagonal;
			count++;
		}
		System.out.println("Finished QR in: " + count);
		for (int i = 0; i < A.length; i++)
		{
			resultList.add(Ak[i][i]);
		}
		
		return resultList;
	}
	
	private static double[] findDiagonal(double[][] A)
	{
		double[] result = new double[A.length];
		for (int i = 0; i < A.length; i++)
		{
			result[i] = A[i][i];
		}
		return result;
	}

}