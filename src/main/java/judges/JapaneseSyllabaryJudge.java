package judges;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import features.JapaneseSyllabary;

public class JapaneseSyllabaryJudge extends Judge<JapaneseSyllabary>{

	@Test
	public void 母音() throws Exception {
		assertThat(sut.execute('a'), is("あ"));
		assertThat(sut.execute('i'), is("い"));
		assertThat(sut.execute('u'), is("う"));
		assertThat(sut.execute('e'), is("え"));
		assertThat(sut.execute('o'), is("お"));
	}

	@Test
	public void 子音と母音() throws Exception {
		assertThat(sut.execute('k', 'a'), is("か"));
		assertThat(sut.execute('m', 'u'), is("む"));
	}

	@Test
	public void 特記_ち() throws Exception {
		assertThat(sut.execute('t', 'i'), is("ち"));
	}

	@Test(expected = RuntimeException.class)
	public void 三文字以上はNG() throws Exception {
		sut.execute('k', 'y', 'a');
	}

	@Test(expected = RuntimeException.class)
	public void 母音続きNG() throws Exception {
		sut.execute('a', 'a');
	}

	@Test(expected = RuntimeException.class)
	public void 子音のみNG() throws Exception {
		sut.execute('y');
	}

	@Test
	public void NはOK() throws Exception {
		assertThat(sut.execute('n'), is("ん"));
		assertThat(sut.execute('n', 'n'), is("ん"));
	}
}
