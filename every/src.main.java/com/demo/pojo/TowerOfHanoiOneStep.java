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
		this.check(middle, left);
		this.middle.push(left.pop());
		this.preMethod = "LM";
		count ++;
		System.out.println("move "+middle.peek()+" from left to middle" );
	}
	
	public void middleToRight() throws Exception {
		this.check(right, middle);
		this.right.push(middle.pop());
		this.preMethod = "MR";
		count ++;
		System.out.println("move "+right.peek()+" from middle to right" );
	}
	
	public void rightToMiddle() throws Exception {
		this.check(middle, right);
		this.middle.push(right.pop());
		this.preMethod = "RM";
		count ++;
		System.out.println("move "+middle.peek()+" from right to middle" );
	}
	
	public void middleToLeft() throws Exception {
		this.check(left, middle);
		this.left.push(middle.pop());
		this.preMethod = "ML";
		count ++;
		System.out.println("move "+left.peek()+" from middle to left" );
	}
	
	private void check(Stack<Integer> to, Stack<Integer> from) throws Exception{
		if (from.size() == 0 || (to.size() != 0 && from.peek() > to.peek())) {
			throw new RuntimeException("error step!");
		}
	}
	
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

	public Stack<Integer> getLeft() {
		return left;
	}
	public void setLeft(Stack<Integer> left) {
		this.left = left;
	}
	public Stack<Integer> getMiddle() {
		return middle;
	}
	public void setMiddle(Stack<Integer> middle) {
		this.middle = middle;
	}
	public Stack<Integer> getRight() {
		return right;
	}
	public void setRight(Stack<Integer> right) {
		this.right = right;
	}
	
	
}
