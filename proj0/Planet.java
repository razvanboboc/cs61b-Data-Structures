public class Planet { 
	
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double gravitationalConstant = 6.67 * Math.pow(10,-11);
	private Planet[] planetsArray;


	public Planet(double xPos, double yPos, double xVel, double yVel, double m, String img){
		xxPos = xPos;
		yyPos = yPos;
		xxVel = xVel;
		yyVel = yVel;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		return Math.sqrt((this.xxPos-p.xxPos) * (this.xxPos-p.xxPos) + (this.yyPos-p.yyPos) * (this.yyPos-p.yyPos));
	}

	public double calcForceExertedBy(Planet p){
		return (gravitationalConstant * this.mass *  p.mass) / (this.calcDistance(p) * this.calcDistance(p));
	}

	public double calcForceExertedByX (Planet p) {
		
		return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);

	}

	public double calcForceExertedByY (Planet p) {
		
		return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
	}

	public double calcNetForceExertedByX (Planet[] planetsArray){
		
		double netForceSum = 0;
		for (Planet planet : planetsArray) {
			if(!this.equals(planet)){
				netForceSum += this.calcForceExertedByX(planet);
			}
		}
		return netForceSum;
	}

	public double calcNetForceExertedByY (Planet[] planetsArray){

		double netForceSum = 0;
		for (Planet planet : planetsArray) {
			if(!this.equals(planet)){
				netForceSum += this.calcForceExertedByY(planet);
			}
		}
		return netForceSum;
	}

	public void update(double dt, double fX, double fY) {

		double netAccelerationX = fX / this.mass;
		double netAccelerationY = fY / this.mass;
		xxVel = xxVel + dt * netAccelerationX;
		yyVel = yyVel + dt * netAccelerationY;
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}

	public void draw() {
		String imagePath = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imagePath);
	}

}