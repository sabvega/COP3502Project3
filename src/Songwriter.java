
/**
 * Purpose of this program is to create a randomly generated table of frequencies and durations which they should be played
 * by creating a Guitar variable that then simulates the song through periods on the console.
 * Then, creates a Vocalist variable that finds the frequency closest to midFreqRange that was inputted by the user.
 * After that it uses StdAudio to play the frequencies for that time through computer audio
 * @author Sabrina Vega
 *
 */
public class Songwriter {

	//class variables
	private static int stringNum;
	private static int chordNum;
	private static double midFreq;
	
	public static void main(String[] args) {
		stringNum= Integer.parseInt(args[0]);
		chordNum = Integer.parseInt(args[1]);
		midFreq = Integer.parseInt(args[2]);
	
		Guitar g = new Guitar(stringNum, chordNum);
		g.simulateSong();
		Vocalist v = new Vocalist(midFreq);
		System.out.println(v);
		v.sing();
		
	}

}
