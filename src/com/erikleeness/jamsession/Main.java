package com.erikleeness.jamsession;

import org.jfugue.Pattern;
import org.jfugue.Player;

public class Main
{
    public static void main(String[] args)
	{
		Player pl = new Player();
		BalletSuite piece = new BalletSuite();
		Pattern song = piece.song();

		pl.play(song);
    }
}
