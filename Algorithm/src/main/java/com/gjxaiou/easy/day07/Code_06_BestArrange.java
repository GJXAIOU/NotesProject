package com.gjxaiou.easy.day07;

import java.util.Arrays;
import java.util.Comparator;

public class Code_06_BestArrange {

	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static class ProgramComparator implements Comparator<Program> {
		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}
	}

	public static int bestArrange(Program[] programs, int currentTime) {
		Arrays.sort(programs, new ProgramComparator());
		int result = 0;
		for (int i = 0; i < programs.length; i++) {
			// 如果当前时间小于项目的开始时间，则项目可以安排
			if (currentTime <= programs[i].start) {
				// 项目可以做，即项目数 + 1;
				result++;
				// 当前时间来到项目的结束时间，表示项目做完了
				currentTime = programs[i].end;
			}
		}
		return result;
	}

	public static void main(String[] args) {

	}
}
