package judges;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import features.Calculator;

public class CalculatorJudge extends Judge<Calculator> {

	@Test
	public void 加算() {
		assertThat(sut.execute("1+2"), is("3"));
	}

	@Test
	public void 減算() {
		assertThat(sut.execute("1-3"), is("-2"));
	}

	@Test
	public void 乗算() {
		assertThat(sut.execute("3*2"), is("6"));
	}

	@Test
	public void 除算_割り切れる() {
		assertThat(sut.execute("9/3"), is("3"));
	}

	@Test
	public void 除算_四捨五入切り捨て() {
		assertThat(sut.execute("100/3"), is("33.333"));
	}

	@Test
	public void 除算_四捨五入切り上げ() {
		assertThat(sut.execute("1/16"), is("0.063"));
	}

	@Test(expected = RuntimeException.class)
	public void 左辺ゼロ() throws Exception {
		sut.execute("0+1");
	}

	@Test(expected = RuntimeException.class)
	public void 右辺ゼロ() throws Exception {
		sut.execute("1-0");
	}

	@Test(expected = RuntimeException.class)
	public void 数式として成立しない_左辺のみ() throws Exception {
		sut.execute("10-");
	}

	@Test(expected = RuntimeException.class)
	public void 数式として成立しない_右辺のみ() throws Exception {
		sut.execute("*10");
	}

	@Test(expected = RuntimeException.class)
	public void 演算子二つ以上() throws Exception {
		sut.execute("1+2-3");
	}
}
