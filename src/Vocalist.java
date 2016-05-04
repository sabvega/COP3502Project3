/**
 * Vocalist class uses the information that was made in the Guitar Class
 * to then find the frequency in each chord that is closet to the middle Range Frequency entered by the user.
 * It then creates a melody 2D array that stores that frequency along with its duration. The Vocalist class then
 * runs through this array and plays each freq throuh for that time by implementing the StdAudio class
 * @author Sabrina
 *
 */
public class Vocalist {

	//class variables
	private double midFreqRange;
	private double[][] melody;
	private int songLength;

	//constructor
	/**
	 * Constructor for Vocalist Class that instantiates the songLength, midFreqRange and melody variables
	 * Also calls the createVocalist() method which outputs the creates melody array to the console as a String
	 * @param middleFreq
	 * @return Nothing
	 */
	public Vocalist(double middleFreq){
		songLength = Guitar.getSongColNum();
		midFreqRange = middleFreq;
		melody = new double[2][songLength];
		createVocalist();
	}

	//Methods
	/*** This method creates the Vocalist as it will appear on the 
	 * console, creates the chordArray that is shifted through to find
	 * the number that is the closest to the given midRangeFreq inputed by the user
	 * @param None
	 * @return void
	 */
	public void createVocalist(){
		double[] chordArray= new double[Guitar.getSongRow()];
		int iteration;
		double closestNum=0;
		System.out.print("\n");
		System.out.println("Vocalist(): midRangeFreq: "+midFreqRange);
		System.out.println("Vocalist(): songlength: "+ songLength);

		//sorts through each column in the array
		for(int a =0;a<songLength;a++){
			chordArray = Guitar.getChordArray(a);
			//goes through the selected chord (column) and finds the number with the smallest difference from midRangeFreq
			double smallestDiff = 99999;
			for(iteration =0;iteration<chordArray.length-1;iteration++){
				double diff = Math.abs(chordArray[iteration]-midFreqRange);
				if(diff<smallestDiff){
					smallestDiff=diff;
					closestNum= chordArray[iteration];
				}
			}
			//inputs the closest values that were found above into the melody array
			melody[0][a]=closestNum;
			melody[1][a]=chordArray[chordArray.length-1];
		}
	}
	/**
	 * This method takes in each beat and its duration and the plays that frequency for 
	 * that amount of time using the StdAudio Class
	 * @param Nothing
	 * @return void
	 */
	public void sing(){
		//uses for loop to go through 
		for(int beatCol=0;beatCol<melody[0].length;beatCol++){
			double freq = melody[0][beatCol];
			double duration = melody[1][beatCol];
			StdAudio.playTone(freq, duration);
		}
	}

	/**
	 * This method returns the melody 2D array as a 2 line String that is formatted with the frequencies having
	 * 2 decimal places and the duration only having 1
	 * @param Nothing
	 * @return String
	 */
	public String toString(){
		String result="";
		//concatenates the first row (freqs) to the result String
		for(int i = 0;i<Guitar.getSongColNum();i++){
			double part=melody[0][i];
			result+=String.format("%8.2f", part);
		}
		result+="\n";

		//concatenates the second row(duration)to the result String
		for(int j = 0;j<Guitar.getSongColNum();j++){
			double part=melody[1][j];
			result+= String.format("%8.1f", part);
		}
		return result;
	}
}
