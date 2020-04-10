public class NBody {
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planetsArray = readPlanets(filename);

		StdDraw.setScale(-radius, radius);
		// StdDraw.picture( 0, 0, "images/starfield.jpg", 2 * radius, 2 * radius);
		// for(Planet planet : planetsArray){
			// planet.draw();
		// }
		double counter = 0.0;
		
		while(counter < T){
			Double[] xForcesArray = new Double[planetsArray.length];
			Double[] yForcesArray = new Double[planetsArray.length];
			for(int i = 0 ; i < planetsArray.length; i++){
				xForcesArray[i] = planetsArray[i].calcNetForceExertedByX(planetsArray);
				yForcesArray[i] = planetsArray[i].calcNetForceExertedByY(planetsArray);
			}
			for(int i = 0; i < planetsArray.length; i++) {
				planetsArray[i].update(dt, xForcesArray[i], yForcesArray[i]);
			}
			StdDraw.picture( 0, 0, "images/starfield.jpg", 2 * radius, 2 * radius);
			for(Planet planet : planetsArray){
				planet.draw();
			}
			StdDraw.enableDoubleBuffering();
			StdDraw.show();
			StdDraw.pause(10);
			counter += dt;	
		}

		// System.out.println(planetsArray.length);
		// System.out.println(radius);
		// for (int i = 0; i < planetsArray.length; i++) {
            // 
            // System.out.println(planetsArray[i].xxPos + " " + planetsArray[i].yyPos + " " + planetsArray[i].xxVel + " " +
                  // planetsArray[i].yyVel + " " + planetsArray[i].mass + " " + planetsArray[i].imgFileName);   
		// }

		StdOut.printf("%d\n", planetsArray.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planetsArray.length; i++) {
   			 StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planetsArray[i].xxPos, planetsArray[i].yyPos, planetsArray[i].xxVel,
                  planetsArray[i].yyVel, planetsArray[i].mass, planetsArray[i].imgFileName);   
		}

		// StdDraw.enableDoubleBuffering();
		// StdDraw.show();


	}
	public static double readRadius(String filePath){
		In in = new In(filePath);
		in.readInt();
		return in.readDouble();
	}

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