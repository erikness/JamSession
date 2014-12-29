package com.erikleeness.jamsession;

import org.jfugue.Pattern;
import org.jfugue.PatternTransformer;
import org.jfugue.extras.DiatonicIntervalPatternTransformer;

/**
 * Created by Erik Ness at 12/29/14 1:28 PM
 *
 * Musical score of the beginning of Shostakovich's ballet suite no. 4, used in Phantom Regiment's "Defiant Heart"
 */
public class BalletSuite
{
	private Pattern completeSong;

	public BalletSuite()
	{
		Pattern song = new Pattern();
		Pattern tempo = new Pattern("T[80]");
		song.add(tempo);

		Pattern celloVoice = new Pattern("V0");
		celloVoice.add("I[Cello]");
		Pattern bassoonVoice = new Pattern("V1");
		bassoonVoice.add("I[Bassoon]");
		Pattern hornVoice = new Pattern("V2");
		hornVoice.add("I[French_Horn]");
		Pattern fluteVoice = new Pattern("V3");
		fluteVoice.add("I[Flute]");

		Pattern eightMeasureRest = new Pattern("Rwwwwwwww");

		// 8 measures
		celloVoice.add(introMotive());
		bassoonVoice.add(eightMeasureRest);
		hornVoice.add(eightMeasureRest);
		fluteVoice.add(eightMeasureRest);

		// 8 measures
		celloVoice.add(introMotive());
		bassoonVoice.add(upOctave(introMotive()));
		hornVoice.add(eightMeasureRest);
		fluteVoice.add(eightMeasureRest);

		// 8 measures
		celloVoice.add(introMotive());
		bassoonVoice.add(eightMeasureRest);
		hornVoice.add(introCounterpoint());
		fluteVoice.add(introFluteTopping());

		song.add(celloVoice, bassoonVoice, hornVoice, fluteVoice);
		completeSong = song;
	}

	public Pattern song()
	{
		return completeSong;
	}

	private Pattern introMotive()
	{
		Pattern measure1 = new Pattern("B2hi D3i F#3i D3i");
		Pattern measure2 = new Pattern("B2hq B3i A3i");
		Pattern measure3 = new Pattern("G3h C3q D3i E3i");
		Pattern measure4 = new Pattern("F#3qi D3i B2h");
		Pattern subMotive1 = new Pattern(measure1, measure2, measure3, measure4);

		Pattern measure5 = new Pattern("A2h G2q A2i B2i");
		Pattern measure6 = new Pattern("C#3q A2q F#2h");
		Pattern measure7 = new Pattern("B2qi A2i G2q F#2i E2i");
		Pattern measure8 = new Pattern("F#2qi G2i F#2h");
		Pattern subMotive2 = new Pattern(measure5, measure6, measure7, measure8);

		return new Pattern(subMotive1, subMotive2);
	}

	private Pattern introCounterpoint()
	{
		Pattern measure1 = new Pattern("F#4q D5i B4i F#4h-");
		Pattern measure2 = new Pattern("F#4q F#5i E5i B4h");
		Pattern measure3 = new Pattern("E5i B4i E5i F#5i G5q F#5i E5i");
		Pattern measure4 = new Pattern("D5q F#5h E5i D5i");

		Pattern measure5 = new Pattern("C#5q F#4q B4h");
		Pattern measure6 = new Pattern("A4hi B4i A4i G4i");
		Pattern measure7 = new Pattern("F#4h B4h");
		Pattern measure8 = new Pattern("E5h C#5h");

		return new Pattern(measure1, measure2, measure3, measure4,
				measure5, measure6, measure7, measure8);
	}

	private Pattern introFluteTopping()
	{
		Pattern basicMotive = new Pattern("Ri D6i F#6i D6i F#i Rqi");
		Pattern up1 = new Pattern("Ri E6i G6i E6i G6i Rqi");
		Pattern down1 = new Pattern("Ri C#6i E6i C#6i E6i Rqi");
		Pattern down1lastDown = new Pattern("Ri C#6i E6i C#6i D6i Rqi");
		Pattern down1lastUp = new Pattern("Ri C#6i E6i C#6i F#6i Rqi");

		return new Pattern(basicMotive, basicMotive, up1, basicMotive,
				down1lastDown, down1, basicMotive, down1lastUp);

	}

	private Pattern upOctave(Pattern p)
	{
		PatternTransformer transformer = new DiatonicIntervalPatternTransformer(8);
		return transformer.transform(p);
	}
}
