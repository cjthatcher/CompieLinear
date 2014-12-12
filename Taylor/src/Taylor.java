
public class Taylor {
	
	public static void main(String[] args)
	{
//		double realValue = sinx(Math.PI / 4, 0);
//		double approx1 = sinx(Math.PI / 4, 0.001);
//		double approx2 = sinx(Math.PI / 4, 0.01);
//		double approx3 = sinx(Math.PI / 4, 0.001);
//		
//		System.out.println((approx1 - Math.sin(Math.PI /4 + 0.001)) + ", " + (((approx1 - Math.sin(Math.PI / 4 + 0.001))) / Math.sin(Math.PI / 4 + 0.001)));
//		System.out.println((approx2 - Math.sin(Math.PI / 4 + 0.01)) + ", " + ((approx2 - Math.sin(Math.PI / 4 + 0.01)) / Math.sin(Math.PI / 4 + 0.01)));
//		System.out.println((approx3 - Math.sin(Math.PI / 4 + 0.001)) + ", " + ((approx3- Math.sin(Math.PI / 4 + 0.001)) / Math.sin(Math.PI / 4 + 0.001)));
		
//		double pi8 = Math.PI / 8;
//		
//		System.out.println(approxSin2x(pi8, 0.1) - realSin2x(pi8, 0.1) + ", " + ((approxSin2x(pi8, 0.1) - realSin2x(pi8, 0.1)) / realSin2x(pi8, 0.1)));
//		System.out.println(approxSin2x(pi8, 0.01) - realSin2x(pi8, 0.01) + ", " + ((approxSin2x(pi8, 0.01) - realSin2x(pi8, 0.01)) / realSin2x(pi8, 0.01)));
//		System.out.println(approxSin2x(pi8, 0.001) - realSin2x(pi8, 0.001) + ", " + ((approxSin2x(pi8, 0.001) - realSin2x(pi8, 0.001)) / realSin2x(pi8, 0.001)));

//		System.out.println(approxLn(1.0, 0.1) - realLn(1.0, 0.1) + ", " + ((approxLn(1.0, 0.1) - realLn(1.0, 0.1)) / realLn(1.0, 0.1)));
//		System.out.println(approxLn(1.0, 0.01) - realLn(1.0, 0.01) + ", " + ((approxLn(1.0, 0.01) - realLn(1.0, 0.01)) / realLn(1.0, 0.01)));
//		System.out.println(approxLn(1.0, 0.001) - realLn(1.0, 0.001) + ", " + ((approxLn(1.0, 0.001) - realLn(1.0, 0.001)) / realLn(1.0, 0.001)));

		System.out.println(calculateMachineEpsilonFloat());
	}
	
	public static double approxLn(double x0, double h)
	{
		return Math.log(1 + 2*x0) +
				(2 / (double) (2*x0 + 1) * h) -
				(4 / Math.pow(2 * x0 + 1, 2) * Math.pow(h, 2) / fac(2)) +
				(16 / Math.pow(2 * x0 + 1, 3) * Math.pow(h, 3) / fac(3)) -
				(96 / Math.pow(2 * x0 + 1, 4) * Math.pow(h, 4) / fac(4)) +
				(768 / Math.pow(2 * x0 + 1, 5) * Math.pow(h, 5) / fac(5));
	}
	
	public static double realLn(double x0, double h)
	{
		return Math.log(1 + (2 * x0 + h));
	}
	
	public static double sinx(double x0, double h)
	{
		return Math.sin(x0) + 
				(Math.cos(x0) * h) - 
				(((Math.sin(x0)) / fac(2)) * Math.pow(h, 2)) -
				((Math.cos(x0) / fac(3) * Math.pow(h, 3))) +
				((Math.sin(x0) / fac(4) * Math.pow(h, 4))) +
				((Math.cos(x0) / fac(5) * Math.pow(h, 5)));
	}
	
	public static double approxSin2x(double x0, double h)
	{
		return Math.sin(2 * x0) +
				(2 * Math.cos(2 * x0) * h) -
				(2 * Math.sin(2 * x0) * Math.pow(h, 2)) -
				(8 * Math.cos(2 * x0) * Math.pow(h, 3) / 6) +
				(16 * Math.sin(2 * x0) * Math.pow(h, 4) / 24) +
				(32 * Math.cos(2 * x0) * Math.pow(h, 5) / 120);
	}
	
	public static double realSin2x(double x0, double h)
	{
		return Math.sin(2 * x0 + h);
	}
	
	public static int fac(int base)
	{
		for (int i = base - 1; i > 0; i--)
		{
			base *= i;
		}
		return base;
	}
	
	private static float calculateMachineEpsilonFloat() {
        float machineEpsilon = 1.0f;
 
        do
           machineEpsilon /= 2.0f;
        while ((float) (1.0 + (machineEpsilon / 2.0)) != 1.0);
 
        return machineEpsilon;
    }

}
