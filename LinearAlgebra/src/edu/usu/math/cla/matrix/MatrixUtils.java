package edu.usu.math.cla.matrix;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixUtils {

	public static void show(double[][] A)
	{
		DecimalFormat df = new DecimalFormat("#,0.000");
		for (int i = 0; i < A.length; i++)
		{
			for (int j = 0; j < A[i].length; j++)
			{
				String s = A[i][j] >= 0 ? " " : "";
				System.out.print(s + df.format(A[i][j]));
				if (j < A[i].length - 1)
				{
					System.out.print(",  ");					
				}
				else
					System.out.println(";");
			}
		}
	}
	
	public static double[][] getPentaArray(int n)
	{
		double[][] array = new double[5][n];
		double[] lld = new double[n];
		double[] ld = new double[n];
		double[] d = new double[n];
		double[] ud = new double[n];
		double[] uud = new double[n];
		Arrays.fill(lld, -1);
		Arrays.fill(ld, -1);
		Arrays.fill(d, 4);
		Arrays.fill(ud, -1);
		Arrays.fill(uud, -1);
		
		array[0] = lld;
		array[1] = ld;
		array[2] = d;
		array[3] = ud;
		array[4] = uud;
		return array;
	}
}
