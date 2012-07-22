package judges;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import features.Myers;

public class MyersJudge extends Judge<Myers> {

	@Test
	public void 正三角形() throws Exception {
		String actual = sut.getName(5, 5, 5);
		assertThat(actual, is("正三角形"));
	}

	@Test
	public void 二等辺三角形() throws Exception {
		String actual = sut.getName(5, 2, 5);
		assertThat(actual, is("二等辺三角形"));
	}

	@Test
	public void 不等辺三角形() throws Exception {
		String actual = sut.getName(4, 8, 5);
		assertThat(actual, is("不等辺三角形"));
	}

	@Test(expected = RuntimeException.class)
	public void 三角形にならない() throws Exception {
		sut.getName(1, 2, 8);
	}

	@Test(expected = RuntimeException.class)
	public void 三角形にならない_境界値() throws Exception {
		sut.getName(3, 5, 8);
	}

	@Test(expected = RuntimeException.class)
	public void 辺の値が負() throws Exception {
		sut.getName(4, -5, 6);
	}
}
