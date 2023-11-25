package util;

public class ObjectArrayUtil {
	public static void swap(Object[] sourceArr, int leftIndex, int rightIndex) {
		if (sourceArr == null) {
			throw new NullPointerException();
		}
		
		Object temp;
		temp = sourceArr[leftIndex];
		sourceArr[leftIndex] = sourceArr[rightIndex];
		sourceArr[rightIndex] = temp;
	}
}
