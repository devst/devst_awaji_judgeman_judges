package features;

import java.util.ArrayList;
import java.util.List;

import judges.CalculatorJudge;
import judges.CoinJudge;
import judges.FirstPairWorkJudge;
import judges.FizzBuzzJudge;
import judges.Judge;
import judges.MyersJudge;
import judges.PokerJudge;
import judges.RomanConverterJudge;
import judges.TsurukamezanJudge;

public enum Feature {

	ENQ("意気込み", FirstPairWork.class, FirstPairWorkJudge.class),

	FIZZ_BUZZ("FizzBuzz", FizzBuzz.class, FizzBuzzJudge.class),
	MYERS("Myers", Myers.class, MyersJudge.class),
	CALC("計算機", Calculator.class, CalculatorJudge.class),
	TSURU_KAME("鶴亀算", Tsurukamezan.class, TsurukamezanJudge.class),

	POKER("ポーカー", Poker.class, PokerJudge.class, false),
	ROMAN("数字変換", RomanConverter.class, RomanConverterJudge.class, false),
	COIN("コイン", Coin.class, CoinJudge.class, true),
	;
	public final String label;
	public final Class<?> feature;
	public final Class<? extends Judge<?>> judge;
	public final boolean visible;

	<T> Feature(String label, Class<T> feature, Class<? extends Judge<T>> judge, boolean visible) {
		this.label = label;
		this.feature = feature;
		this.judge = judge;
		this.visible = visible;
	}

	<T> Feature(String label, Class<T> feature, Class<? extends Judge<T>> judge) {
		this(label, feature, judge, true);
	}

	@Override
	public String toString() {
		return label;
	}

	public static List<Feature> getVisibleList() {
		List<Feature> features = new ArrayList<Feature>();
		for (Feature f : Feature.values()) {
			if (f.visible) features.add(f);
		}
		return features;
	}
}
