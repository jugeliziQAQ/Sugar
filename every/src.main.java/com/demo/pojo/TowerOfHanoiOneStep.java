package com.demo.pojo;

import java.util.Stack;

/**
 * 每次只能相邻塔移动的汉诺塔
 * @author Virgil
 *
 */
public class TowerOfHanoiOneStep {
	
	int count;
	int max;
	String preMethod;
	private Stack<Integer> left;
	private Stack<Integer> middle;
	private Stack<Integer> right;
	
	public TowerOfHanoiOneStep() {
		count = 0;
		max = 0;
		left = new Stack<>();
		middle = new Stack<>();
		right = new Stack<>();
	}
	
	public TowerOfHanoiOneStep(int max) {
		count = 0;
		this.max = max;
		preMethod = "";
		left = new Stack<>();
		middle = new Stack<>();
		right = new Stack<>();
		for (int i = max; i > 0; i--) {
			left.push(i);
		}
		System.out.println("init size of tower is "+max);
	}
	
	public void leftToMiddle() throws Exception {
		this.check(left, middle);
		this.middle.push(left.pop());
		this.preMethod = "LM";
		count ++;
		System.out.println("move "+middle.peek()+" from left to middle" );
	}
	
	public void middleToRight() throws Exception {
		this.check(middle, right);
		this.right.push(middle.pop());
		this.preMethod = "MR";
		count ++;
		System.out.println("move "+right.peek()+" from middle to right" );
	}
	
	public void rightToMiddle() throws Exception {
		this.check(right, middle);
		this.middle.push(right.pop());
		this.preMethod = "RM";
		count ++;
		System.out.println("move "+middle.peek()+" from right to middle" );
	}
	
	public void middleToLeft() throws Exception {
		this.check(middle, left);
		this.left.push(middle.pop());
		this.preMethod = "ML";
		count ++;
		System.out.println("move "+left.peek()+" from middle to left" );
	}
	
	private void check(Stack<Integer> from, Stack<Integer> to) throws Exception{
		if (from.size() == 0 || (to.size() != 0 && to.peek() < from.peek())) {
			throw new RuntimeException("error step!");
		}
	}
	
	/**
	 * 非递归计算从左到右最少步数
	 * @return
	 * @throws Exception
	 */
	public int handler() throws Exception{
		while (right.size() != max) {
			if (!this.preMethod.equals("ML")) {
				try {
					this.leftToMiddle();
				} catch (Exception e) {
					if (!(e instanceof RuntimeException && e.getMessage().equals("error step!"))) {
						throw e;
					}
				}
			}
			if (!this.preMethod.equals("LM")) {
				try {
					this.middleToLeft();
				} catch (Exception e) {
					if (!(e instanceof RuntimeException && e.getMessage().equals("error step!"))) {
						throw e;
					}
				}
			}
			if (!this.preMethod.equals("RM")) {
				try {
					this.middleToRight();
				} catch (Exception e) {
					if (!(e instanceof RuntimeException && e.getMessage().equals("error step!"))) {
						throw e;
					}
				}
			}
			if (!this.preMethod.equals("MR")) {
				try {
					this.rightToMiddle();
				} catch (Exception e) {
					if (!(e instanceof RuntimeException && e.getMessage().equals("error step!"))) {
						throw e;
					}
				}
			}
		}		
		return count;
		
	}
	
	/**
	 * 递归计算从左到右最少步数
	 * @return
	 * @throws Exception
	 */
	public int handlerByRecursion() throws Exception {
		this.moveNSubOne(max, left, right);
		return count;
	}
	
	private void moveNSubOne(int n, Stack<Integer> from, Stack<Integer> to) throws Exception {
		if (n == 1) {
			if (from == left) {
				if (to == middle) {
					this.leftToMiddle();
				} else {
					this.leftToMiddle();
					this.middleToRight();
				}
			} else if (from == middle) {
				if (to == left) {
					this.middleToLeft();
				} else {
					this.middleToRight();
				}
			} else if (from == right) {
				if (to == middle) {
					this.rightToMiddle();
				} else {
					this.rightToMiddle();
					this.middleToLeft();
				}
			}
		} else {
			if (from == left) {
				if (to == middle) {
					
				} else {
					this.moveNSubOne(n - 1, left, right);
					this.leftToMiddle();
					this.moveNSubOne(n - 1, right, left);
					this.middleToRight();
					this.moveNSubOne(n - 1, left, right);
				}
			} else if (from == middle) {
				if (to == left) {
					
				} else {
					
				}
			} else if (from == right) {
				if (to == middle) {
					
				} else {
					this.moveNSubOne(n - 1, right, left);
					this.rightToMiddle();
					this.moveNSubOne(n - 1, left, right);
					this.middleToLeft();
					this.moveNSubOne(n - 1, right, left);
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("--------Run Begin---------");
		TowerOfHanoiOneStep tower = new TowerOfHanoiOneStep(4);
		int count = tower.handlerByRecursion();
		//int count = tower.handler();
		System.out.println(count);
		System.out.println("--------Run End-----------");
	}
	
}
