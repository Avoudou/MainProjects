package graphicsPentris;
public class ArraysMethods {
	/** Returns a new identical array of the input 2D array */
	public static int[][] deepCopy2DArray(int[][] array2D) {
		int[][] copyOfarray = new int[array2D.length][];
		for (int i = 0; i < array2D.length; i++) {
			copyOfarray[i] = new int[array2D[i].length];
			for (int j = 0; j < array2D[i].length; j++) {
				copyOfarray[i][j] = array2D[i][j];
			}

		}
		return copyOfarray;
	}

	/** Returns a new identical array of the input 2D array */
	public static double[][] deepCopy2DArray(double[][] array2D) {
		double[][] copyOfarray = new double[array2D.length][array2D[0].length];
		for (int i = 0; i < array2D.length; i++) {
			copyOfarray[i] = new double[array2D[i].length];
			for (int j = 0; j < array2D[i].length; j++) {
				copyOfarray[i][j] = array2D[i][j];
			}

		}
		return copyOfarray;
	}

	/** Returns a new identical array of the input 2D array */
	public static String[][] deepCopy2DArray(String[][] array2D) {
		String[][] copyOfarray = new String[array2D.length][array2D[0].length];
		for (int i = 0; i < array2D.length; i++) {
			copyOfarray[i] = new String[array2D[i].length];
			for (int j = 0; j < array2D[i].length; j++) {
				copyOfarray[i][j] = array2D[i][j];
			}

		}
		return copyOfarray;
	}

	/** prints in console the input 2D-array */
	public static void print2DArray(int[][] array2D) {

		for (int i = 0; i < array2D.length; i++) {
			System.out.println();
			for (int j = 0; j < array2D[i].length; j++) {
				System.out.print(array2D[i][j] + " ");
			}
		}

	}

	/** prints in console the input 2D-array */
	public static void print2DArray(double[][] array2D) {

		for (int i = 0; i < array2D.length; i++) {
			System.out.println();
			for (int j = 0; j < array2D[i].length; j++) {
				System.out.print(array2D[i][j] + " ");
			}
		}

	}

	/** prints in console the input 2D-array */
	public static void print2DArray(String[][] array2D) {

		for (int i = 0; i < array2D.length; i++) {
			System.out.println();
			for (int j = 0; j < array2D[i].length; j++) {
				System.out.print(array2D[i][j] + " ");
			}
		}

	}

	/** finds the maximum double value in an 1D-array */
	public static double arrayMaxValue(double[] array1D) {
		double maxArrayValue = Double.NEGATIVE_INFINITY;
		for (int arrayIndex = 0; arrayIndex < array1D.length; arrayIndex++) {
			maxArrayValue = Math.max(maxArrayValue, array1D[arrayIndex]);
		}
		return maxArrayValue;
	}

	/** finds the maximum integer value in an 1D-array */
	public static int arrayMaxValue(int[] array1D) {
		int maxArrayValue = Integer.MIN_VALUE;
		for (int arrayIndex = 0; arrayIndex < array1D.length; arrayIndex++) {
			maxArrayValue = Math.max(maxArrayValue, array1D[arrayIndex]);
		}
		return maxArrayValue;
	}

	/** finds the minimum double value in an 1D-array */
	public static double arrayMinValue(double[] array1D) {
		double minArrayValue = Double.MAX_VALUE;
		for (int arrayIndex = 0; arrayIndex < array1D.length; arrayIndex++) {
			minArrayValue = Math.min(minArrayValue, array1D[arrayIndex]);
		}
		return minArrayValue;
	}

	/** finds the minimum integer value in an 1D-array */
	public static int arrayMinValue(int[] array1D) {
		int minArrayValue = Integer.MAX_VALUE;
		for (int arrayIndex = 0; arrayIndex < array1D.length; arrayIndex++) {
			minArrayValue = Math.min(minArrayValue, array1D[arrayIndex]);
		}
		return minArrayValue;
	}

	/**
	 * Increases the length of a 1d-Array by one and adds the parameter value to
	 * the last index
	 */
	public static int[] addArrayElement(int[] array1D, int arbitrInteger) {
		int[] increasedArray1D = new int[array1D.length + 1];
		for (int arrayIndex = 0; arrayIndex < array1D.length; arrayIndex++) {
			increasedArray1D[arrayIndex] = array1D[arrayIndex];
		}
		increasedArray1D[increasedArray1D.length - 1] = arbitrInteger;

		return increasedArray1D;
	}

	/**
	 * Increases the length of a 1d-Array by one and adds the parameter value to
	 * the last index
	 */
	public static double[] addArrayElement(double[] array1D, int arbitrDouble) {
		double[] increasedArray1D = new double[array1D.length + 1];
		for (int arrayIndex = 0; arrayIndex < array1D.length; arrayIndex++) {
			increasedArray1D[arrayIndex] = array1D[arrayIndex];
		}
		increasedArray1D[increasedArray1D.length - 1] = arbitrDouble;

		return increasedArray1D;
	}

	/**
	 * Increases the length of a 1d-Array by one and adds the parameter value to
	 * the last index
	 */
	public static String[] addArrayElement(String[] array1D, String inputString) {
		String[] increasedArray1D = new String[array1D.length + 1];
		for (int arrayIndex = 0; arrayIndex < array1D.length; arrayIndex++) {
			increasedArray1D[arrayIndex] = array1D[arrayIndex];
		}
		increasedArray1D[increasedArray1D.length - 1] = inputString;

		return increasedArray1D;
	}

}
