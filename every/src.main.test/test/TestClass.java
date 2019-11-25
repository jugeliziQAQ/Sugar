package test;

import java.util.LinkedList;

import org.junit.Test;

public class TestClass {

	/**
	 * ������ƴ�СΪ{WINDOW}���������ֵ
	 */
	@Test
	public void test() {
		int[] array = new int[]{4,3,5,4,3,3,6,7};
		final int WINDOW = 3; 
		LinkedList<Integer> index = new LinkedList<>();
		int[] max = new int[array.length - WINDOW + 1];
		int ind = 0;
		for (int i = 0; i < array.length; i ++) {
			// �����д浱ǰ���ֵ�����ұ�ֵ�Ľ�����ж�Ӧ���±�,�±�ǰ��˳�򲻿ɵߵ�
			while(!index.isEmpty() && array[index.peekLast()] <= array[i]) {
				index.pollLast();
			}
			index.addLast(i);
			// ��������е��±���ڵ���
			if(index.peekFirst() == i - WINDOW) {
				index.pollFirst();
			}
			// ��ǰѭ���γɴ��ڵ����ֵ
			if(i >= WINDOW - 1) {
				max[ind++] = array[index.peekFirst()];
			}
		}
		
		for (int a : max) {
			System.out.println(a);
		}

	}

}
