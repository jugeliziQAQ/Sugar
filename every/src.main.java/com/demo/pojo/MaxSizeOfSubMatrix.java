package com.demo.pojo;

import java.util.Stack;

/**
 * ��ȡ0,1������ȫ��Ϊ1���������С
 * @author Virgil
 *
 */
public class MaxSizeOfSubMatrix {

	/**
	 * ԭ����
	 * [[1,0,1,1],     [[1,0,1,1],
	 *  [1,1,1,1],  =>  [2,1,2,2],
	 *  [1,1,1,0]]      [3,2,3,0]]
	 * @return
	 */
	private int[][] array;
	/**
	 * ��i����������1�ĸ�������
	 * [[1,0,1,1],
	 *  [2,1,2,2],
	 *  [3,2,3,0]]
	 * @return
	 */
	private int[][] bottom;
	/**
	 * ��ȡ�����i����������1�ľ���
	 * [[1,0,1,1],     [[1,0,1,1],
	 *  [1,1,1,1],  =>  [2,1,2,2],
	 *  [1,1,1,0]]      [3,2,3,0]]
	 * @return
	 */	
	public MaxSizeOfSubMatrix(int[][] array) {
		this.array = array;
		this.bottom = new int[array.length][array[0].length];
		this.getContinuityWithHeight();
	}
	/**
	 * ��������i����������1������bottom
	 * [[1,0,1,1],     [[1,0,1,1],
	 *  [1,1,1,1],  =>  [2,1,2,2],
	 *  [1,1,1,0]]      [3,2,3,0]]
	 * @return
	 */
	public void getContinuityWithHeight() {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length; j++) {
				if (i == 0) {
					bottom[i][j] = array[i][j];
					continue;
				}
				if (array[i][j] != 0) {
					bottom[i][j] = bottom[i-1][j] + array[i][j];
				}
			}
		}
	}
	
	/**
	 * �������ȫΪ1�Ӿ���Ĵ�С
	 * @return
	 */
	public int getMaxSizeOfSubMatrix() {
		int max = 0;
		for(int i = 0; i < bottom.length; i++) {
			// ջ������Ϊbottom[i]������±�,�±�˳�򲻿���,
			//ջ�����ݶ�Ӧ��bottom[i]�����ǵ�����,�������
			Stack<Integer> stack = new Stack<>();
			
			for(int j = 0; j < bottom[0].length; j++) {
				while(!stack.isEmpty() && bottom[i][j] <= bottom[i][stack.peek()]) {
					int k = stack.pop();
					// kֵ��������Ϊ stack.peek+1�� j-1 ֮���������bottom[i][k]
					int maxk = bottom[i][k] *(j - 1 - (stack.isEmpty() ? -1 : stack.peek()));

					max = Math.max(max, maxk);
				}
				stack.push(j);
			}
			while (!stack.isEmpty()) {
				int k = stack.pop();
				int maxk = bottom[i][k] * (bottom[i].length - 1 -(stack.isEmpty() ? -1 : stack.peek()));
				
				max = Math.max(max, maxk);
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[][] array = {{1,0,1,1},{1,1,1,1},{1,1,1,0}};
		MaxSizeOfSubMatrix subMatrix = new MaxSizeOfSubMatrix(array);
		System.out.println(subMatrix.getMaxSizeOfSubMatrix());
	}
}
