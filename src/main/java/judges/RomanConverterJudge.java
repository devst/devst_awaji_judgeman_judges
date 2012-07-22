package judges;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import features.RomanConverter;


public class RomanConverterJudge extends Judge<RomanConverter> {

	/**
	 * 引数がnullの場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testNullArgument() {
		assertRuntimeExceptionThrown(null);
	}

	/**
	 * 引数が空文字列の場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testEmptyStringArgument() {
		assertRuntimeExceptionThrown("");
	}

	/**
	 * 1000の位の数字のみの場合。
	 */
	@Test
	public void testThousandsPlace() {
		assertThat(sut.toArabic("MMM"), is(3000));
		assertThat(sut.toArabic("MM"), is(2000));
		assertThat(sut.toArabic("M"), is(1000));
	}

	/**
	 * 100の位の数字のみの場合。
	 */
	@Test
	public void testHundredsPlace() {
		assertThat(sut.toArabic("CM"), is(900));
		assertThat(sut.toArabic("DCCC"), is(800));
		assertThat(sut.toArabic("DCC"), is(700));
		assertThat(sut.toArabic("DC"), is(600));
		assertThat(sut.toArabic("D"), is(500));
		assertThat(sut.toArabic("CD"), is(400));
		assertThat(sut.toArabic("CCC"), is(300));
		assertThat(sut.toArabic("CC"), is(200));
		assertThat(sut.toArabic("C"), is(100));
	}

	/**
	 * 10の位の数字のみの場合。
	 */
	@Test
	public void testTensPlace() {
		assertThat(sut.toArabic("XC"), is(90));
		assertThat(sut.toArabic("LXXX"), is(80));
		assertThat(sut.toArabic("LXX"), is(70));
		assertThat(sut.toArabic("LX"), is(60));
		assertThat(sut.toArabic("L"), is(50));
		assertThat(sut.toArabic("XL"), is(40));
		assertThat(sut.toArabic("XXX"), is(30));
		assertThat(sut.toArabic("XX"), is(20));
		assertThat(sut.toArabic("X"), is(10));
	}

	/**
	 * 1の位の数字のみの場合。
	 */
	@Test
	public void testOnesPlace() {
		assertThat(sut.toArabic("IX"), is(9));
		assertThat(sut.toArabic("VIII"), is(8));
		assertThat(sut.toArabic("VII"), is(7));
		assertThat(sut.toArabic("VI"), is(6));
		assertThat(sut.toArabic("V"), is(5));
		assertThat(sut.toArabic("IV"), is(4));
		assertThat(sut.toArabic("III"), is(3));
		assertThat(sut.toArabic("II"), is(2));
		assertThat(sut.toArabic("I"), is(1));
	}

	/**
	 * 1の位から1000の位までの数字を複数組み合わせた数の場合。
	 */
	@Test
	public void testConbinationOfEachPlace() {
		assertThat(sut.toArabic("MCCCLXXXIV"), is(1384));	// 1000, 100, 10, 1

		assertThat(sut.toArabic("MMCDXC"),     is(2490));	// 1000, 100, 10, -
		assertThat(sut.toArabic("MMMDV"),      is(3505));	// 1000, 100, --, 1
		assertThat(sut.toArabic("MXVI"),       is(1016));	// 1000, ---, 10, 1
		assertThat(sut.toArabic("DCXXVII"),    is( 627));	// ----, 100, 10, 1

		assertThat(sut.toArabic("MMDCC"),      is(2700));	// 1000, 100, --, -
		assertThat(sut.toArabic("MMMVIII"),    is(3008));	// 1000, ---, --, 1
		assertThat(sut.toArabic("XXXIX"),      is(  39));	// ----, ---, 10, 1
		assertThat(sut.toArabic("MXL"),        is(1040));	// 1000, ---, 10, -
		assertThat(sut.toArabic("DCCCI"),      is( 801));	// ----, 100, --, 1
		assertThat(sut.toArabic("CML"),        is( 950));	// ----, 100, 10, -
	}

	/**
	 * 適当にいくつかの例。(Wikipediaに書かれていた例。)
	 */
	@Test
	public void testSomeCases() {
		assertThat(sut.toArabic("XI"), is(11));
		assertThat(sut.toArabic("XII"), is(12));
		assertThat(sut.toArabic("XIV"), is(14));
		assertThat(sut.toArabic("XVIII"), is(18));
		assertThat(sut.toArabic("XXIV"), is(24));
		assertThat(sut.toArabic("XLIII"), is(43));
		assertThat(sut.toArabic("XCIX"), is(99));
		assertThat(sut.toArabic("CDXCV"), is(495));
		assertThat(sut.toArabic("MDCCCLXXXVIII"), is(1888));
		assertThat(sut.toArabic("MCMXLV"), is(1945));
		assertThat(sut.toArabic("MMMCMXCIX"), is(3999));
	}

	/**
	 * ローマ数字に使用されない文字が含まれていた場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testNonRomanNumeralCharacters() {
		assertRuntimeExceptionThrown("O");
		assertRuntimeExceptionThrown("iii");
		assertRuntimeExceptionThrown("200");
		assertRuntimeExceptionThrown("三千九百九十九");
	}

	/**
	 * ローマ数字に使用される文字だが、正しくない並びの場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testIncorrectSequences() {
		assertRuntimeExceptionThrown("IIII");
		assertRuntimeExceptionThrown("VIIII");
		assertRuntimeExceptionThrown("IIIV");
		assertRuntimeExceptionThrown("LXL");
		assertRuntimeExceptionThrown("MMMCMXCIXI");
	}

	/**
	 * argにtoIntメソッドを適用し、RuntimeExceptionが投げられることを確認する。
	 * 
	 * @param arg toIntメソッドを適用する引数。
	 */
	private void assertRuntimeExceptionThrown(String arg) {
		try {
			this.sut.toArabic(arg);
			fail("例外が投げられなかった。");
		} catch (Exception e) {
			if (!RuntimeException.class.equals(e.getClass())) {
				fail("RuntimeException以外の例外が投げられた。");
			}
			// success.
		}
	}

}
