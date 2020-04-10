/**
    Actually runs our simulation of an universe specified in one of the data files
 */
public class NBody {
	public static void main(String[] args){
		/** Read command line argments */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		
		/** Read Planets and the universe radius defined in filename */
		double radius = readRadius(filename);
		Planet[] planetsArray = readPlanets(filename);
		/** Scale the universe to r */
		StdDraw.setScale(-radius, radius);
		// time variable
		double counter = 0.0;
		/** In this main loop, updating and rendering each Planet's movements with increasing time variable: counter */

		while(counter < T){
			/** Declaring net forces arrays */			
			Double[] xForcesArray = new Double[planetsArray.length];
			Double[] yForcesArray = new Double[planetsArray.length];
			
			/** Calculate net forces for each Planet */
			for(int i = 0 ; i < planetsArray.length; i++){
				xForcesArray[i] = planetsArray[i].calcNetForceExertedByX(planetsArray);
				yForcesArray[i] = planetsArray[i].calcNetForceExertedByY(planetsArray);
			}

			/** Update each Planet's members */
			for(int i = 0; i < planetsArray.length; i++) {
				planetsArray[i].update(dt, xForcesArray[i], yForcesArray[i]);
			}

			/* Show the background */
			StdDraw.picture( 0, 0, "images/starfield.jpg", 2 * radius, 2 * radius);
			
			/** Draw all of the Planets */
			for(Planet planet : planetsArray){
				planet.draw();
			}
			
			/** Enables double buffering for smooth animation rendering and prevent it from flickering */
			StdDraw.enableDoubleBuffering();
			
			/* Shows the drawing to the screen */
			StdDraw.show();
			StdDraw.pause(10);
			counter += dt;	
		}

		
		
        /** Outputs the final states of each Planet for autograder to work correctly */
      
		StdOut.printf("%d\n", planetsArray.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planetsArray.length; i++) {
   			 StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planetsArray[i].xxPos, planetsArray[i].yyPos, planetsArray[i].xxVel,
                  planetsArray[i].yyVel, planetsArray[i].mass, planetsArray[i].imgFileName);   
		}

	}
	/** Returns a double corresponding to the radius of the universe in given file */
    
	public static double readRadius(String filePath){
		In in = new In(filePath);
		in.readInt();
		return in.readDouble();
	}
	/** Returns an array of Planets corresponding to the planets defined in given file */
	public static Planet[] readPlanets(String filePath){
		
		In in = new In(filePath);
		Planet[] planetsArray = new Planet[in.readInt()];
		in.readDouble();
		
		for(int i = 0; i < planetsArray.length ; i++) {
			planetsArray[i] = new Planet(in.readDouble(), in.readDouble(), 
				in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}

		return planetsArray;
	} 
}