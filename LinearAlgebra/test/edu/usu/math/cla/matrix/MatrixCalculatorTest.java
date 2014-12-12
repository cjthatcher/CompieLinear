package edu.usu.math.cla.matrix;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import edu.usu.math.cla.matrix.eigen.EigenValueFinder;
import edu.usu.math.cla.matrix.eigen.ParallelEigenValueFinder;

public class MatrixCalculatorTest {

//	@Test
//	public void pentaMatrixMultiplierTest() {
//		int constant = 100;
//		double[][] A = MatrixCalculator.makePentadiagonalMatrix(constant);
//		double[] x = MatrixCalculator.makeRandomVector(constant);
//		
//		double[] ssd = new double[constant];
//		double[] sd = new double[constant];
//		double[] d = new double[constant];
//		double[] ud = new double[constant];
//		double[] uud = new double[constant];
//		Arrays.fill(ssd, -1);
//		Arrays.fill(sd, -1);
//		Arrays.fill(d, 4);
//		Arrays.fill(ud, -1);
//		Arrays.fill(uud, -1);
//		
//		double[] result1 = MatrixCalculator.matrixVectorMultiply(A, x);
//		double[] result2 = MatrixCalculator.pentadiagonalMatrixVectorMultiplication(ssd, sd, d, ud, uud, x);
//		
//		System.out.println(Arrays.toString(result1));
//		System.out.println(Arrays.toString(result2));
//		
//		Assert.assertArrayEquals(result1, result2, 0.000005);
//	}
//
//	@Test
//	public void pentaMatrixEigenTest() {
//		int constant = 100;
//		double[][] A = MatrixCalculator.makePentadiagonalMatrix(constant);
//		
//		double[] ssd = new double[constant];
//		double[] sd = new double[constant];
//		double[] d = new double[constant];
//		double[] ud = new double[constant];
//		double[] uud = new double[constant];
//		Arrays.fill(ssd, -1);
//		Arrays.fill(sd, -1);
//		Arrays.fill(d, 4);
//		Arrays.fill(ud, -1);
//		Arrays.fill(uud, -1);
//		
//		
//		double e1 = MatrixCalculator.findGreatestEigenvalue(A, .00000005, 100000);
//		double e2 = MatrixCalculator.findPentadiagonalGreatestEigenvalue(ssd, sd, d, ud, uud, .00000005, 100000);
//
//		System.out.println(e1 + " " + e2);
//		Assert.assertEquals(e1, e2, 0.00005);
//	}
//	
//	@Test
//	public void matrixSmallestEigenTest() {
//		int constant = 10;
//		double[][] A = MatrixCalculator.makePentadiagonalMatrix(constant);
//		double[] ssd = new double[constant];
//		double[] sd = new double[constant];
//		double[] d = new double[constant];
//		double[] ud = new double[constant];
//		double[] uud = new double[constant];
//		Arrays.fill(ssd, -1);
//		Arrays.fill(sd, -1);
//		Arrays.fill(d, 4);
//		Arrays.fill(ud, -1);
//		Arrays.fill(uud, -1);
//
//		double e1 = MatrixCalculator.findSmallestEigenValue(A, .00000005, 100000);
//		double e2 = MatrixCalculator.findPentadiagonalSmallestEigenValue(ssd, sd, d, ud, uud, .00000005, 100000);
//
//		System.out.println(e1);
//		Assert.assertEquals(e1, 0.6193068951, 0.00005);
//		Assert.assertEquals(e1, e2, 0.00005);
//	}
//	
//	@Test
//	public void matrixSmallestEigenTestTwo() {
//		int constant = 100;
//		double[][] A = MatrixCalculator.makePentadiagonalMatrix(constant);
//		double[] ssd = new double[constant];
//		double[] sd = new double[constant];
//		double[] d = new double[constant];
//		double[] ud = new double[constant];
//		double[] uud = new double[constant];
//		Arrays.fill(ssd, -1);
//		Arrays.fill(sd, -1);
//		Arrays.fill(d, 4);
//		Arrays.fill(ud, -1);
//		Arrays.fill(uud, -1);
//
//		double e1 = MatrixCalculator.findSmallestEigenValue(A, .00000005, 100000);
//		double e2 = MatrixCalculator.findPentadiagonalSmallestEigenValue(ssd, sd, d, ud, uud, .00000005, 100000);
//
//		System.out.println(e1);
//		Assert.assertEquals(e1, e2, 0.00005);
//	}
//	
//	@Test
//	public void shiftedTest() {
//		int constant = 10;
//		double[][] A = MatrixCalculator.makePentadiagonalMatrix(constant);
//		MatrixUtils.show(A);
//		
//		for (double i = 0; i < 10; i+= 0.05)
//		{
//			double e = MatrixCalculator.findShiftedEigenValue(A, i, .00000005, 100000);
//			System.out.println("i = " + i + ", e = " + e);
//		}

//		double e1 = MatrixCalculator.findShiftedEigenValue(A, 0, .00000005, 100000);
//		Assert.assertEquals(e1, 0.61931, 0.0005);
//		double e2 = MatrixCalculator.findShiftedEigenValue(A, 2, .00000005, 100000);
//		Assert.assertEquals(e2, 2.10075, 0.0005);
//		double e3 = MatrixCalculator.findShiftedEigenValue(A, 3.3, .00000005, 100000);
//		Assert.assertEquals(e3, 3.30372, 0.0005);
//		double e4 = MatrixCalculator.findShiftedEigenValue(A, 3.4, .00000005, 100000);
//		Assert.assertEquals(e4, 3.42992, 0.0005);
//		double e5 = MatrixCalculator.findShiftedEigenValue(A, 3.6, .00000005, 100000);
//		Assert.assertEquals(e5, 3.60764, 0.0005);
//		double e6 = MatrixCalculator.findShiftedEigenValue(A, 4.3, .00000005, 100000);
//		Assert.assertEquals(e6, 4.39236, 0.0005);
//		double e7 = MatrixCalculator.findShiftedEigenValue(A, 4.5, .00000005, 100000);
//		Assert.assertEquals(e7, 4.57008, 0.0005);
//		double e8 = MatrixCalculator.findShiftedEigenValue(A, 4.6, .00000005, 100000);
//		Assert.assertEquals(e8, 4.69628, 0.0005);
//		double e9 = MatrixCalculator.findShiftedEigenValue(A, 5.8, .00000005, 100000);
//		Assert.assertEquals(e9, 5.89925, 0.0005);
//		double e10 = MatrixCalculator.findShiftedEigenValue(A, 7.3, .00000005, 100000);
//		Assert.assertEquals(e10, 7.38069, 0.0005);
//	}
	
//	@Test
//	public void parallelShiftedTest() {
//		int constant = 10;
//		double[][] A = MatrixCalculator.makePentadiagonalMatrix(constant);
//		
//		List<Double> eigenList = ParallelEigenValueFinder.findAllEigenvalues(A, 100, .00000000005, 100000);
//		
//		List<Double> goodList = new ArrayList<Double>();
//		goodList.add(Double.valueOf(0.619306897014653));
//		goodList.add(Double.valueOf(2.1007462161768347));
//		goodList.add(Double.valueOf(3.3037208758741086));
//		goodList.add(Double.valueOf(3.429916990307089));
//		goodList.add(Double.valueOf(3.607635433750707));
//		goodList.add(Double.valueOf(4.392364566065165));
//		goodList.add(Double.valueOf(4.57008300965514));
//		goodList.add(Double.valueOf(4.696279123969649));
//		goodList.add(Double.valueOf(5.899253782780065));
//		goodList.add(Double.valueOf(7.3806931025859575));
//		
//		for (int i = 0; i < eigenList.size(); i++)
//		{
//			Assert.assertEquals(eigenList.get(i), goodList.get(i), 0.00005);
//		}
//	}
	
	@Test
	public void parallelTimedTest() {
		int constant = 30;
		double[][] A = MatrixCalculator.makePentadiagonalMatrix(constant);
		
		long time = System.currentTimeMillis();
		List<Double> eigenList = ParallelEigenValueFinder.findAllEigenvalues(A, constant * 100, .00000000005, 100000);
		long dur = System.currentTimeMillis() - time;
		
		System.out.println("Parallel took " + dur);
		System.out.println("There are " + eigenList.size() + " values");
		
		time = System.currentTimeMillis();
		eigenList = EigenValueFinder.findAllEigenvalues(A, constant * 100, .00000000005, 100000);
		dur = System.currentTimeMillis() - time;
		
		System.out.println("Serial took " + dur);
		System.out.println("There are " + eigenList.size() + " values");
		
		time = System.currentTimeMillis();
		eigenList = MatrixCalculator.findAllEigenValuesQR(A, .00000000005, 100000);
		dur = System.currentTimeMillis() - time;
		
		System.out.println("QR took " + dur);
		System.out.println("There are " + eigenList.size() + " values");
	}
	
//	@Test
//	public void serialShiftedTest() {
//		int constant = 10;
//		double[][] A = MatrixCalculator.makePentadiagonalMatrix(constant);
//		
//		List<Double> eigenList = EigenValueFinder.findAllEigenvalues(A, 100, .00000000005, 100000);
//		
//		System.out.println("**********Start!*************");
//		for (double d: eigenList)
//		{
//			System.out.println(d);
//		}
//		System.out.println("**********End!*************");
//		List<Double> goodList = new ArrayList<Double>();
//		goodList.add(Double.valueOf(0.619306897014653));
//		goodList.add(Double.valueOf(2.1007462161768347));
//		goodList.add(Double.valueOf(3.3037208758741086));
//		goodList.add(Double.valueOf(3.429916990307089));
//		goodList.add(Double.valueOf(3.607635433750707));
//		goodList.add(Double.valueOf(4.392364566065165));
//		goodList.add(Double.valueOf(4.57008300965514));
//		goodList.add(Double.valueOf(4.696279123969649));
//		goodList.add(Double.valueOf(5.899253782780065));
//		goodList.add(Double.valueOf(7.3806931025859575));
//		
//		for (int i = 0; i < eigenList.size(); i++)
//		{
//			Assert.assertEquals(eigenList.get(i), goodList.get(i), 0.00005);
//		}
//	}
//	
//	@Test
//	public void outerTest() {
//		double[] b = new double[]{1, 2, 3};
//		double[] a = new double[]{0.5, 1, 1.5};
//		
//		double[][] matrix = MatrixCalculator.vectorVectorOuterProduct(a, b);
//		MatrixUtils.show(matrix);
//	}
	
//	@Test
//	public void QRTest() {
//		double[][] A = MatrixCalculator.makeDiagonallyDominantMatrix(100);
//		//MatrixUtils.show(A);
//		
//		List<double[][]> resultList = MatrixCalculator.QRFactorization(A);
//		double[][] Q = resultList.get(0);
//		double[][] R = resultList.get(1);
//		
//		double[][] rehydrated = MatrixCalculator.matrixMultiply(Q, R);
//		for (int i = 0; i < rehydrated.length; i++)
//		{
//			Assert.assertArrayEquals(rehydrated[i], A[i], 0.000005);
//		}
//	}
//	
//	@Test
//	public void QRFindEigenValuesTest() {
//		double[][] A = MatrixCalculator.makePentadiagonalMatrix(30);
//		double tolerance = 0.000005;
//		int maxIterations = 10000;
//		//MatrixUtils.show(A);
//		
//		List<Double> resultList = MatrixCalculator.findAllEigenValuesQR(A, tolerance, maxIterations);
//		for (Double d : resultList)
//		{
//			System.out.println(d);
//		}
//	}
}
