package edu.usu.math.roots;

import java.util.ArrayList;
import java.util.List;

import edu.usu.math.Function;

public class HybridRootFinder {

	/*
	 * This function uses the secant method to find the roots of a given function
	 */
	public static List<Double> findRoots(Function f, double startPoint, double endPoint, int numberOfProbes, double tolerance)
	{
		ArrayList<Double> resultList = new ArrayList<Double>();
		double[] evaluationPoints = new double[numberOfProbes];
		double distance = Math.abs((endPoint - startPoint) / numberOfProbes);
		List<Double> startingPointsToCheck = new ArrayList<Double>();
		
		/*
		 * Find all the points where we will probe the function value.
		 */
		for (int i =0; i < numberOfProbes; i++)
		{
			evaluationPoints[i] = startPoint + (i * distance);
		}
		
		/*
		 * Probe the function, and find any intervals where the sign changes.
		 * Add those intervals to the "startingPointsToCheck" list
		 */
		
		for (int i = 0; i < numberOfProbes - 1; i++)
		{
			double firstValue = f.evaluate(evaluationPoints[i]);
			double nextValue = f.evaluate(evaluationPoints[i + 1]);
			
			if (firstValue * nextValue < 0)
			{
				startingPointsToCheck.add(evaluationPoints[i]);
				continue;
			}
			if (firstValue * nextValue == 0)
			{
				startingPointsToCheck.add(evaluationPoints[i]);
				i++;
				continue;
			}
		}
		
		
		/*
		 * For each interval where the sign changed, used the Secant method to find the root.
		 * If secant method fails, do Bisection three times on the interval and try again
		 */
		for (int i = 0; i < startingPointsToCheck.size(); i++)
		{
			double start = startingPointsToCheck.get(i);
			double end = start + distance;
			
			double currentLocation = (start + end) / 2;
			double oldLocation = currentLocation + 0.0005;
			
			double currentValue = f.evaluate(currentLocation);
			double nextLocation = getNextSecantLocation(f, currentLocation, oldLocation);
			double nextValue = f.evaluate(nextLocation);
			
			boolean wasFound = false;
			
			while (Math.abs(nextValue) < 0.5 * Math.abs(currentValue))
			{
				if (Math.abs(nextValue) < tolerance) //We found the root. w00t!
				{
					resultList.add(nextLocation);
					wasFound = true;
					break;
				}
				
				oldLocation = currentLocation;
				currentLocation = nextLocation;
				nextLocation = getNextSecantLocation(f, currentLocation, oldLocation);
				nextValue = f.evaluate(nextLocation);
			}
			
			if (wasFound == false)
			{
				//Do bisection three times. Add the new starting point to the list
				double tempStart = start;
				double tempEnd = end;
				double mid = (tempStart + tempEnd) / 2;
				for (int j = 0; j < 3; j++)
				{
					double startVal = f.evaluate(tempStart);
					double endVal = f.evaluate(tempEnd);
					double midVal = f.evaluate(mid);
					
					if (startVal * midVal <= 0)
					{
						tempEnd = mid;
						continue;
					}
					else if (midVal * endVal <= 0)
					{
						tempStart = mid;
						continue;
					}
				}
				
				startingPointsToCheck.add(tempStart);
			}
		}
		
		return resultList;
	}
	
	
	/*
	 * This method uses the secant method to find x k + 1;
	 */
	public static double getNextSecantLocation(Function f, double origLocation, double previousLocation)
	{
		double origValue = f.evaluate(origLocation);
		double h = origLocation - previousLocation;
		double oldValue = f.evaluate(previousLocation);
		
		return origLocation - ((origValue * h) / (origValue - oldValue));
	}
	
	public static double findRootSecantStyle(Function f, double startGuess, double tolerance)
	{
		double oldLocation = startGuess;
		double currentLocation = oldLocation + 0.0005;
		double currentValue = f.evaluate(startGuess);
		double nextLocation = getNextSecantLocation(f, oldLocation, currentLocation);
		double nextValue = f.evaluate(nextLocation);
		
		while (Math.abs(nextValue) > tolerance)
		{			
			oldLocation = currentLocation;
			currentLocation = nextLocation;
			nextLocation = getNextSecantLocation(f, currentLocation, oldLocation);
			nextValue = f.evaluate(nextLocation);
		}
		
		return nextValue;
	}
	
	public static void main(String[] args)
	{
		Function f = new Function() {

			@Override
			public double evaluate(double... args) {
				if (Double.compare(args[0], 0) != 0)
				{
					return Math.sin(args[0]) / args[0];
				}
				else
				{
					return 1;
				}
			}
			
		};
		
		List<Double> roots = HybridRootFinder.findRoots(f, -10, 10, 1000, 0.0000000000005);
		
		for (Double d : roots)
		{
			System.out.println(d);
		}
	}
}
