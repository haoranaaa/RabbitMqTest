package com.douban.query;

import java.util.ArrayList;
import java.util.Random;

public class QLearning {

	//贪婪因子
	public static final float GAMMA = 1f;
	//训练次数
	public static final int TRAING_NUM = 1000;
	//实验次数
	public static final int TEST_TIMES = 5;
	//状态数
	public static final int STATE_NUM = 6;
	//目标状态
	public static final int GOAL_STATE = 5;
	//动作次数阀值
	public static final int THREAD_HOLD = 30;

	public static void main(String[] args){
		//初始化 reward矩阵
		int[][] rMatrix = {
				{-100,-100,-100,-100,-1,-100},
				{-100,-100,-100,-2,-100,-5},
				{-100,-100,-100,-1,-100,-100},
				{-100,-2,-1,-100,-3,-100},
				{-1,-100,-100,-3,-100,-5},
				{-100,-5,-100,-100,-5,0}
		};
		float[][] qMatrix = new float[STATE_NUM][STATE_NUM];

		float[][] qualityMatrix = getQMatrix(qMatrix,rMatrix);
		System.out.println("After tring "+TRAING_NUM+" times");
		System.out.println("the qualityMatrix becomes:");
		/**
		 * 得到训练后的quality矩阵
		 */
		showMatrix(qualityMatrix);
		/**
		 * 测试验证
		 */
		testMethod(qMatrix);
	}

	private static float[][] getQMatrix(float[][] qMatrix,int[][] rMatrix){
		int step = 0;
		Random random = new Random();
		while (step < 100000){
			ArrayList<Integer> nextActionList = new ArrayList();
			int state = random.nextInt(STATE_NUM);
			for(int i=0;i<STATE_NUM;i++){
				if(rMatrix[state][i]!=-100){
					nextActionList.add(i);
				}
			}
			int randomIndex = random.nextInt(nextActionList.size());
			int action = nextActionList.get(randomIndex);
			float maxQuality = getMaxQuality(action,qMatrix,rMatrix);
			qMatrix[state][action] = rMatrix[state][action] + GAMMA * maxQuality;
			step++;
		}
		return qMatrix;
	}
	private static float getMaxQuality(int state,float[][] qMatrix,int[][] rMatrix){
		float max = 0;
		for(int i=0;i<STATE_NUM;i++){
			if(rMatrix[state][i]!=0){
				max = Math.max(max,qMatrix[state][i]);
			}
		}
		return max;
	}

	private static void showMatrix(float[][] a){
		System.out.println("[");
		for (int i = 0, j = 0; i < a.length;) {
			System.out.print(a[i][j]+" ");
			j++;
			if (j >= a[i].length) {
				System.out.println();
				i++;
				j = 0;
			}
		}
		System.out.println("]");
	}
	private static void testMethod(float[][] qMatrix){
		int time = 0;
		while (time < TEST_TIMES){
			StringBuilder stringBuilder = new StringBuilder("the route is : ");
			System.out.println("=====================");
			System.out.println("test: "+(time+1));
			int state = time;
			System.out.println("agent start from state: "+state);
			stringBuilder.append(state);
			if(state == GOAL_STATE){
				System.out.println("agent has a break ....");
			}
			int step = 0;
			while (state!=GOAL_STATE){
				if(step > THREAD_HOLD){
					System.out.println("agent can not arrive at GOAL_STATE !!!");
					System.out.println("agent is tired");
					System.out.println("agent quit");
					break;
				}
				int action = getAction(state,qMatrix);
				if(action==GOAL_STATE){
					System.out.println("agent arrives at GOAL_STATE: " +GOAL_STATE);
					stringBuilder.append(" -> "+GOAL_STATE);
				}else {
					System.out.println("agent goes to state: "+action);
					stringBuilder.append(" -> "+action);
				}
				state = action;
			}
			System.out.println(stringBuilder.toString());
			time++;
		}
	}

	private static int getAction(int state,float[][] qMatrix) {
		int action = 0;
		for(int i=1;i<STATE_NUM;i++){
			if(qMatrix[state][i]>qMatrix[state][action]){
				action = i;
			}
		}
		return action;
	}

}
