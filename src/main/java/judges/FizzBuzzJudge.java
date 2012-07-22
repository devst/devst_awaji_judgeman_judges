package judges;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import features.FizzBuzz;


public class FizzBuzzJudge extends Judge<FizzBuzz> {

	@Test
	public void 三の倍数() throws Exception {
		assertThat(sut.fizzBuzz(3), is("Fizz"));
		assertThat(sut.fizzBuzz(18), is("Fizz"));
	}

	@Test
	public void 五の倍数() throws Exception {
		assertThat(sut.fizzBuzz(5), is("Buzz"));
		assertThat(sut.fizzBuzz(35), is("Buzz"));
	}

	@Test
	public void 三と五の倍数() throws Exception {
		assertThat(sut.fizzBuzz(15), is("FizzBuzz"));
		assertThat(sut.fizzBuzz(150), is("FizzBuzz"));
	}

	@Test
	public void その他() throws Exception {
		assertThat(sut.fizzBuzz(4), is("4"));
		assertThat(sut.fizzBuzz(7), is("7"));
	}

	@Test(expected=RuntimeException.class)
	public void ゼロの場合() throws Exception {
		sut.fizzBuzz(0);
	}

	@Test(expected=RuntimeException.class)
	public void マイナスの場合() throws Exception {
		sut.fizzBuzz(-3);
	}
}
