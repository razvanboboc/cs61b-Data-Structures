public class Planet { 
	
	public double xxPos; // current x position
	public double yyPos; // current y position
	public double xxVel; // current velocity in the x direction
	public double yyVel; // current velocity in the y direction
	public double mass; // mass
	public String imgFileName; // name of the file that corresponds to the image that depicts the planet, e.g. jupiter.gif
	
	private static final double gravitationalConstant = 6.67 * Math.pow(10,-11); // gravitational constant G
	private Planet[] planetsArray; // array containing all Planets

	/**	
        Constructor to initialize an instance of the Planet class with given parameters
        @param xP: current x position
        @param yP: current y position
        @param xV: current velocity in the x direction
        @param yV: current velocity in the y direction
        @param m: mass
        @param img: path to an image file that depicts the planet
    	*/
	public Planet(double xPos, double yPos, double xVel, double yVel, double m, String img){
		xxPos = xPos;
		yyPos = yPos;
		xxVel = xVel;
		yyVel = yVel;
		mass = m;
		imgFileName = img;
	}
	/** Constructor to copy given Planet object */
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	/** Calculates the distance between two Planets */
    
	public double calcDistance(Planet p){
		// return Math.sqrt((this.xxPos-p.xxPos) * (this.xxPos-p.xxPos) + (this.yyPos-p.yyPos) * (this.yyPos-p.yyPos));
		double xDifference = p.xxPos - this.xxPos;
		double yDifference = p.yyPos - this.yyPos;
		double distance = Math.sqrt(xDifference * xDifference + yDifference * yDifference);
		return distance;
	}
	/** Calculates the force exerted on this Planet by the given Planet */
    
	public double calcForceExertedBy(Planet p){
		// return (gravitationalConstant * this.mass *  p.mass) / (this.calcDistance(p) * this.calcDistance(p));
		double r = calcDistance(p);
		double F =  gravitationalConstant * mass * p.mass / (r * r);
		return F;
	}
	/** Calculates the force exerted in the X direction */
    
	public double calcForceExertedByX (Planet p) {
		// return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
		double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double diffX = p.xxPos - xxPos;
        double fX = F * diffX / r;
        return fX;

	}
	/** Calculates the force exerted in the Y direction */
	public double calcForceExertedByY (Planet p) {	
		// return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
		double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double diffY = p.yyPos - yyPos;
        double fY = F * diffY / r;
        return fY;
	}
	/** Calculates the net X force exerted by all Planets in given array */
	public double calcNetForceExertedByX (Planet[] planetsArray){
		
		double netForceSum = 0;
		for (Planet planet : planetsArray) {
			if(!this.equals(planet)){
				netForceSum += this.calcForceExertedByX(planet);
			}
		}
		return netForceSum;
	}
	/** Calculates the net Y force exerted by all Planets in given array */
	public double calcNetForceExertedByY (Planet[] planetsArray){

		double netForceSum = 0;
		for (Planet planet : planetsArray) {
			if(!this.equals(planet)){
				netForceSum += this.calcForceExertedByY(planet);
			}
		}
		return netForceSum;
	}
	/** Updates the Planet's velocity and position while given time duration */
	public void update(double dt, double fX, double fY) {

		double netAccelerationX = fX / this.mass;
		double netAccelerationY = fY / this.mass;
		xxVel = xxVel + dt * netAccelerationX;
		yyVel = yyVel + dt * netAccelerationY;
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}
	/** Draws this.Planet at its position */
	public void draw() {
		String imagePath = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imagePath);
	}

}