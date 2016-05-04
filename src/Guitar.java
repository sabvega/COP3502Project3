/**
 * Guitar class creates a randomly generated table of frequency values and time periods
 * based on the dimensions inputted from the user. It prints it out to the console first as a full table and 
 * then as a list of each chord with a visual representation of the chord directly after.
 * @author Sabrina
 *
 */
public class Guitar {

	//class variables
	private int numString;
	private int numberChord;
	private static double[][] song;

	/**
	 * Constructor for Guitar object that takes in the first two parameters entered by the user. 
	 * Creates the song array using the information given as size dimensions
	 * Generates the actual song with random frequencies and durations
	 * @param numberString
	 * @param numChrd
	 */
	public Guitar(int numberString, int numChrd ){
		numString = numberString;
		numberChord=numChrd;
		song = new double[numString+1][numberChord];
		generateSong();
	} 

	//Methods
	/**
	 * This method  produces the randomized frequencies along with the duration in seconds that each note should be played for
	 * @param None
	 * @return void
	 */
	public void generateSong(){
		System.out.println("Guitar(): Generated new guitar with " +numString+ 
				" strings. Song length is "+numberChord+" chords.");
		for(int row =0;row<numString+1;row++){
			for(int col=0;col<numberChord;col++){
				//creates the last row of the table that is a time period between 0(exclusive)-3(inclusive) seconds
				//once the last row index is reached
				if(row==numString){
					song[row][col]=0.1+Math.random()*3;
					System.out.printf("%8.1f",song[row][col]);
				}
				//creates the  song array with the randomly generated frequencies
				else{
					song[row][col]= 27.5+ Math.random()*4158.5;
					System.out.printf("%8.2f", song[row][col]);
				}
			}
			//passes to the next row
			System.out.println();
		}
	}

	/**
	 * This method prints out the same information that was created in the generateString() method 
	 * but instead flips the chords so that they are listed horizontally and then visually 
	 * represents the duration of that chord by printing a dot at one-second intervals that reflect 
	 * the seconds value that was generated earlier
	 * @param None
	 * @return void
	 */
	public void simulateSong(){
		System.out.print("\n");
		System.out.println("Guitar.simulateSong()");
		double[][]matrixNew = new double[numberChord][numString];
		//
		for(int i=0;i<song[0].length;i++){
			for(int j=0;j<song.length-1;j++){
				matrixNew[i][j]=song[j][i];
				System.out.printf("%8.2f", matrixNew[i][j]);
			}
			double beatNum = song[numString][i];
			int roundUp = (int)Math.ceil(beatNum);
			for(int loop=0;loop<roundUp;loop++){
				if(loop==roundUp-1){
					if(beatNum%1!=0){
						try{
							System.out.print(".");
							Thread.sleep((long) ((beatNum%1)*1000));
						} catch (InterruptedException ex){	
						}
					}else{
						try{
							System.out.print(".");
							Thread.sleep((long)(1*1000));
						} catch (InterruptedException ex){	
						}
					}
				}else{
					try{
						System.out.print(".");
						Thread.sleep((long)(1*1000));
					} catch (InterruptedException ex){	
					}
				}

			}
			System.out.println();
		}


	}

	/**
	 * Static method that returns the desired chord as a 1D double array, it is static so that it can
	 * be used by other classes without requiring direct access to the object
	 * @param int chordSelection
	 * @return double[]
	 */
	public static double[] getChordArray(int chordSelection){
		double[] chordArray =  new double[song.length];
		if(!((chordSelection<song[0].length) && (chordSelection>=0))){
			System.out.println("No such chord!");
			return null;
		}else{
		for(int row =0;row<song.length;row++){
			chordArray[row]=song[row][chordSelection];
		}
		}
		return chordArray;
	}

	/**
	 * Static method that returns the number of columns in the generated song
	 * @param None
	 * @return int
	 */
	public static int getSongColNum(){
		return song[0].length;
	}
	
	/**
	 * Static method that returns the number of rows in the generated song
	 * @param None
	 * @return int
	 */
	public static int getSongRow(){
		return song.length;
	}

}
