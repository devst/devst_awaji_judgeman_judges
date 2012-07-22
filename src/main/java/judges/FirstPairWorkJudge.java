package judges;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Test;

import features.FirstPairWork;

public class FirstPairWorkJudge extends Judge<FirstPairWork> {

	@Test
	public void メンバーはnullでない() throws Exception {
		assertThat(sut.getMembers(), is(notNullValue()));
	}

	@Test
	public void メンバーは空文字列でない() throws Exception {
		if (sut.getMembers() != null) {
			assertThat(sut.getMembers(), is(not("")));
		}
	}

	@Test
	public void 意気込みはnullでない() throws Exception {
		assertThat(sut.getIkigomi(), is(notNullValue()));
	}

	@Test
	public void 意気込みは空文字列でない() throws Exception {
		if (sut.getIkigomi() != null) {
			assertThat(sut.getIkigomi(), is(not("")));
		}
	}

}
