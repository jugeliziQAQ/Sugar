package com.demo.pojo;

import java.util.LinkedList;

/**
 * ��ȡarray�й̶���С���������Ʋ��������ֵ
 * Ԥ�ڽ��[2,3,6,5,7,4],3 => [6,6,7,7]
 * 
 * [[2,3,6],5,7,4] => 6
 * [2,[3,6,5],7,4] => 6
 * [2,3,[6,5,7],4] => 7
 * [2,3,6,[5,7,4]] => 7
 * @author Virgil
 *
 */
public class WindowInArray {
	
	public int[] getMaxInWindow(int[] array, int WINDOW) {
		
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
		
		return max;
	}

	public static void main(String[] args) {
		//int[] array = new int[]{4,3,5,4,3,3,6,7};
		int[] array = new int[]{2,3,6,5,7,4};
		final int WINDOW = 3; 
		WindowInArray windowInArray = new WindowInArray();
		int[] max = windowInArray.getMaxInWindow(array, WINDOW);
		for (int m : max) {
			System.out.println(m);
		}
	}
}
