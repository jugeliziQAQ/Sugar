package com.demo.pojo;

import java.util.Stack;

/**
 * 获取0,1矩阵中全部为1的最大矩阵大小
 * @author Virgil
 *
 */
public class MaxSizeOfSubMatrix {

	/**
	 * 原矩阵
	 * [[1,0,1,1],     [[1,0,1,1],
	 *  [1,1,1,1],  =>  [2,1,2,2],
	 *  [1,1,1,0]]      [3,2,3,0]]
	 * @return
	 */
	private int[][] array;
	/**
	 * 第i层往上连续1的个数矩阵
	 * [[1,0,1,1],
	 *  [2,1,2,2],
	 *  [3,2,3,0]]
	 * @return
	 */
	private int[][] bottom;
	/**
	 * 获取矩阵第i层往上连续1的矩阵
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
	 * 计算矩阵第i层往上连续1的数组bottom
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
	 * 返回最大全为1子矩阵的大小
	 * @return
	 */
	public int getMaxSizeOfSubMatrix() {
		int max = 0;
		for(int i = 0; i < bottom.length; i++) {
			// 栈内数据为bottom[i]数组的下标,下标顺序不可逆,
			//栈内数据对应的bottom[i]数据是递增的,不能相等
			Stack<Integer> stack = new Stack<>();
			
			for(int j = 0; j < bottom[0].length; j++) {
				while(!stack.isEmpty() && bottom[i][j] <= bottom[i][stack.peek()]) {
					int k = stack.pop();
					// k值处最大矩阵为 stack.peek+1与 j-1 之间的数量乘bottom[i][k]
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
