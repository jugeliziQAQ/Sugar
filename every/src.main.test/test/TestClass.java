package test;

import java.util.LinkedList;

import org.junit.Test;

public class TestClass {

	/**
	 * 数组后移大小为{WINDOW}窗口中最大值
	 */
	@Test
	public void test() {
		int[] array = new int[]{4,3,5,4,3,3,6,7};
		final int WINDOW = 3; 
		LinkedList<Integer> index = new LinkedList<>();
		int[] max = new int[array.length - WINDOW + 1];
		int ind = 0;
		for (int i = 0; i < array.length; i ++) {
			// 队列中存当前最大值及其右边值的降序队列对应的下标,下标前后顺序不可颠倒
			while(!index.isEmpty() && array[index.peekLast()] <= array[i]) {
				index.pollLast();
			}
			index.addLast(i);
			// 最早进队列的下标过期弹出
			if(index.peekFirst() == i - WINDOW) {
				index.pollFirst();
			}
			// 当前循环形成窗口的最大值
			if(i >= WINDOW - 1) {
				max[ind++] = array[index.peekFirst()];
			}
		}
		
		for (int a : max) {
			System.out.println(a);
		}

	}

}
