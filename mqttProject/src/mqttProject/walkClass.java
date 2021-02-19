package mqttProject;

public class walkClass extends AccData {
	private double gravityX=1.0;
	private double gravityY=1.0;
	private double gravityZ=1.0;
	private double ipassAccX=0;
	private double ipassAccY=0;
	private double ipassAccZ=0;
	private final double alpha=0.8;
	protected final double epsilon=0.43;
	protected final double lambda=7.0;

	
	public double getEpsilon() {
		return epsilon;
	}

	public walkClass(String mac,String timeStamp) {
		super(mac,timeStamp);
	}

	//f(i2)
	public void walkingOrStanding(double x) {
		if(x>lambda) {
			
	  System.out.println("walking");
	  }
	
		else if(x<lambda) {
		System.out.println("standing");
		
		}
	}
	
	
	//calculate the gravity and the ipass and then set them
	public void gravityIpassX(AccData newAcc) {
	double gravityx=(alpha*gravityX)+((1-alpha)*newAcc.getX());
	double ipassx=(newAcc.getX()-(gravityX));
	setGravityX(gravityx);
	setIpassAccX(ipassx);
			
	}
	public void gravityIpassY(AccData newAcc) {
		double gravityy=(alpha*gravityY)+((1-alpha)*newAcc.getY());
		double ipassy=(newAcc.getY()-(gravityY));
		setGravityY(gravityy);
		setIpassAccY(ipassy);
				
		}
	
	public void gravityIpassZ(AccData newAcc) {
		double gravityz=(alpha*gravityZ)+((1-alpha)*newAcc.getZ());
		double ipassz=(newAcc.getZ()-(gravityZ));	
		setGravityZ(gravityz);
		setIpassAccZ(ipassz);
				
		}
	
	
	
	
	
	
	
	public double getGravityX() {
		return gravityX;
	}

	public void setGravityX(double gravityX) {
		this.gravityX = gravityX;
	}


	public double getGravityY() {
		return gravityY;
	}


	public void setGravityY(double gravityY) {
		this.gravityY = gravityY;
	}


	public double getGravityZ() {
		return gravityZ;
	}


	public void setGravityZ(double gravityZ) {
		this.gravityZ = gravityZ;
	}


	public double getIpassAccX() {
		return ipassAccX;
	}


	public void setIpassAccX(double ipassAccX) {
		this.ipassAccX = ipassAccX;
	}


	public double getIpassAccY() {
		return ipassAccY;
	}


	public void setIpassAccY(double ipassAccY) {
		this.ipassAccY = ipassAccY;
	}


	public double getIpassAccZ() {
		return ipassAccZ;
	}


	public void setIpassAccZ(double ipassAccZ) {
		this.ipassAccZ = ipassAccZ;
	}


	public double getAlpha() {
		return alpha;
	}
	
	

}
