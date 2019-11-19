package com.demo.event;

import com.demo.pojo.TowerOfHanoiOneStep;

public class RunDisplay {
	
	public static void main(String[] args) throws Exception {
		System.out.println("--------Run Begin---------");
		TowerOfHanoiOneStep tower = new TowerOfHanoiOneStep(3);
		int count = tower.handler();
		System.out.println(count);
		System.out.println("--------Run End-----------");
	}

}
