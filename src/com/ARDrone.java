package com;



import java.awt.image.BufferedImage;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;


import com.ARDroneInterface;
public class ARDrone implements ARDroneInterface {

	private String ipaddr = null;
	private InetAddress inetaddr = null;

	// managers
	private CommandManager manager = null;
	private VideoManager videoManager = null;
	private NavDataManager navdataManager = null;

	// listeners
	private ImageListener imageListener = null;
	private AttitudeListener attitudeListener = null;
	private BatteryListener batteryListener = null;
	private StateListener stateListener = null;
	private VelocityListener velocityListener = null;

	private ARDroneVersion ardroneVersion = null;

	/** constructor */
	public ARDrone() {
		this(ARDroneConstants.IP_ADDRESS, null);
	}

	/**
	 * constructor
	 * 
	 * @param ipaddr
	 */
	public ARDrone(String ipaddr) {
		this(ipaddr, null);
	}

	/**
	 * constructor
	 * 
	 * @param ardroneVersion
	 */
	public ARDrone(ARDroneVersion ardroneVersion) {
		this(ARDroneConstants.IP_ADDRESS, ardroneVersion);
	}

	/**
	 * constructor
	 * 
	 * @param ipaddr
	 * @param ardroneVersion
	 */
	public ARDrone(String ipaddr, ARDroneVersion ardroneVersion) {
		this.ipaddr = ipaddr;
		this.ardroneVersion = ardroneVersion;
		/*System.out.println("hoge");
		if (ardroneVersion == null)
			ardroneVersion = new ARDroneInfo().getDroneVersion();
		System.out.println("AR.Drone version:" + ardroneVersion);*/
	}


	
	/** connect to AR.Drone */
	@Override
	public boolean connect() {
		return connect(false);
	}

	private boolean connect(boolean useHighRezVideoStreaming) {
		if (inetaddr == null) {
			inetaddr = getInetAddress(ipaddr);
		}
		if (ardroneVersion == null)
			ardroneVersion = new ARDroneInfo().getDroneVersion();			
		
		//System.out.println("(connect) AR.Drone version:" + ardroneVersion);

		//if (ardroneVersion == ARDroneVersion.ARDRONE1)
			//manager = new CommandManager1(inetaddr, useHighRezVideoStreaming);
		//else if (ardroneVersion == ARDroneVersion.ARDRONE2)
			manager = new CommandManager2(inetaddr, useHighRezVideoStreaming);
		//else {
		//	error("Cannot create Control manager", this);
		//	error("Maybe this is not AR.Drone?", this);
		//	return false;
	//	}

		return manager.connect(ARDroneConstants.PORT);
	}

	/** connect video */
	@Override
	public boolean connectVideo() {
		if (inetaddr == null) {
			inetaddr = getInetAddress(ipaddr);
		}
		//System.out.println("(connect video) AR.Drone version:" + ardroneVersion);

		//if (ardroneVersion == ARDroneVersion.ARDRONE1) {
		//	videoManager = new VideoManager1(inetaddr, manager);
	///	} else if (ardroneVersion == ARDroneVersion.ARDRONE2) {
			videoManager = new VideoManager2(inetaddr, manager);
		//} else {
		//	error("Cannot create Video manager", this);
		//	error("Maybe this is not AR.Drone?", this);
		//	return false;
		//}

		videoManager.setImageListener(new ImageListener() {
			@Override
			public void imageUpdated(BufferedImage image) {
				if (imageListener != null) {
					imageListener.imageUpdated(image);
				}
			}
		});
		return videoManager.connect(ARDroneConstants.VIDEO_PORT);
	}

	/** connect navdata */
	@Override
	public boolean connectNav() {
		if (inetaddr == null) {
			inetaddr = getInetAddress(ipaddr);
		}
		
		//System.out.println("(connect nav) AR.Drone version:" + ardroneVersion);

		//if (ardroneVersion == ARDroneVersion.ARDRONE1) {
		//	navdataManager = new NavDataManager1(inetaddr, manager);
		//} else if (ardroneVersion == ARDroneVersion.ARDRONE2) {
			navdataManager = new NavDataManager2(inetaddr, manager);
		//} else {
		//	error("Cannot create NavData manager", this);
		//	error("Maybe this is not AR.Drone?", this);
		//	return false;
		//}
		navdataManager.setAttitudeListener(new AttitudeListener() {
			@Override
			public void attitudeUpdated(float pitch, float roll, float yaw,
					int altitude) {
				if (attitudeListener != null) {
					attitudeListener
							.attitudeUpdated(pitch, roll, yaw, altitude);
				}
			}
		});
		navdataManager.setBatteryListener(new BatteryListener() {
			@Override
			public void batteryLevelChanged(int percentage) {
				if (batteryListener != null) {
					batteryListener.batteryLevelChanged(percentage);
				}
			}
		});
		navdataManager.setStateListener(new StateListener() {
			@Override
			public void stateChanged(DroneState state) {
				if (stateListener != null) {
					stateListener.stateChanged(state);
				}
			}
		});
		navdataManager.setVelocityListener(new VelocityListener() {
			@Override
			public void velocityChanged(float vx, float vy, float vz) {
				if (velocityListener != null) {
					velocityListener.velocityChanged(vx, vy, vz);
				}
			}
		});

		return navdataManager.connect(ARDroneConstants.NAV_PORT);
	}

	@Override
	public void disconnect() {
		stop();
		landing();
		manager.close();
		if (videoManager != null)
			videoManager.close();
		if (navdataManager != null)
			navdataManager.close();
	}

	@Override
	public void start() {
		if (manager != null)
			new Thread(manager).start();
		if (videoManager != null)
			new Thread(videoManager).start();
		if (navdataManager != null)
			new Thread(navdataManager).start();
		
	}

	@Override
	public void setHorizontalCamera() {
		if (manager != null)
			manager.setHorizontalCamera();
	}

	@Override
	public void setVerticalCamera() {
		if (manager != null)
			manager.setVerticalCamera();
	}

	@Override
	public void setHorizontalCameraWithVertical() {
		if (manager != null)
			manager.setHorizontalCameraWithVertical();
	}

	@Override
	public void setVerticalCameraWithHorizontal() {
		if (manager != null)
			manager.setVerticalCameraWithHorizontal();
	}

	@Override
	public void toggleCamera() {
		if (manager != null)
			manager.toggleCamera();
	}

	@Override
	public void landing() {
		if (manager != null)
			{manager.landing();
			 }
	}

	@Override
	public void takeOff() {
		if (manager != null){
			//manager.reset();
			manager.takeOff();}
	}

	@Override
	public void reset() {
		if (manager != null)
			manager.reset();
	}

	@Override
	public void forward() {
		if (manager != null)
			manager.forward();
	}

	@Override
	public void forward(int speed) {
		if (manager != null)
			manager.forward(speed);
	}

	@Override
	public void backward() {
		if (manager != null)
			manager.backward();
	}

	@Override
	public void backward(int speed) {
		if (manager != null)
			manager.backward(speed);
	}

	@Override
	public void spinRight() {
		if (manager != null)
			manager.spinRight();
	}

	@Override
	public void spinRight(int speed) {
		if (manager != null)
			manager.spinRight(speed);
	}

	@Override
	public void spinLeft() {
		if (manager != null)
			manager.spinLeft();
	}

	@Override
	public void spinLeft(int speed) {
		if (manager != null)
			manager.spinLeft(speed);
	}

	@Override
	public void up() {
		if (manager != null)
			manager.up();
	}

	@Override
	public void up(int speed) {
		if (manager != null)
			manager.up(speed);
	}

	@Override
	public void down() {
		if (manager != null)
			manager.down();
	}

	@Override
	public void down(int speed) {
		if (manager != null)
			manager.down(speed);
	}

	@Override
	public void goRight() {
		if (manager != null)
			manager.goRight();
	}

	@Override
	public void goRight(int speed) {
		if (manager != null)
			manager.goRight(speed);
	}

	@Override
	public void goLeft() {
		if (manager != null)
			manager.goLeft();
	}

	@Override
	public void goLeft(int speed) {
		if (manager != null)
			manager.goLeft(speed);
	}

	@Override
	public void setSpeed(int speed) {
		if (manager != null)
			manager.setSpeed(speed);
	}

	@Override
	public void stop() {
		if (manager != null)
			manager.stop();
	}

	/**
	 * 0.01-1.0 -> 1-100%
	 * 
	 * @return 1-100%
	 */
	@Override
	public int getSpeed() {
		if (manager == null)
			return -1;
		return manager.getSpeed();
	}

	@Override
	public void setMaxAltitude(int altitude) {
		if (manager != null)
			manager.setMaxAltitude(altitude);
	}

	@Override
	public void setMinAltitude(int altitude) {
		if (manager != null)
			manager.setMinAltitude(altitude);
	}

	@Override
	public void move3D(int speedX, int speedY, int speedZ, int speedSpin) {
		if (manager != null)
			manager.move3D(speedX, speedY, speedZ, speedSpin);
	}

	// update listeners
	public void addImageUpdateListener(ImageListener imageListener) {
		this.imageListener = imageListener;
	}

	public void addAttitudeUpdateListener(AttitudeListener attitudeListener) {
		this.attitudeListener = attitudeListener;
	}

	public void addBatteryUpdateListener(BatteryListener batteryListener) {
		this.batteryListener = batteryListener;
	}

	public void addStateUpdateListener(StateListener stateListener) {
		this.stateListener = stateListener;
	}

	public void addVelocityUpdateListener(VelocityListener velocityListener) {
		this.velocityListener = velocityListener;
	}

	// remove listeners
	public void removeImageUpdateListener() {
		imageListener = null;
	}

	public void removeAttitudeUpdateListener() {
		attitudeListener = null;
	}

	public void removeBatteryUpdateListener() {
		batteryListener = null;
	}

	public void removeStateUpdateListener() {
		stateListener = null;
	}

	public void removeVelocityUpdateListener() {
		velocityListener = null;
	}

	/**
	 * print error message
	 * 
	 * @param message
	 * @param obj
	 */
	public static void error(String message, Object obj) {
		System.err.println("[" + obj.getClass() + "] " + message);
	}

	private InetAddress getInetAddress(String ipaddr) {
		InetAddress inetaddr = null;
		StringTokenizer st = new StringTokenizer(ipaddr, ".");
		byte[] ipBytes = new byte[4];
		if (st.countTokens() == 4) {
			for (int i = 0; i < 4; i++) {
				ipBytes[i] = (byte) Integer.parseInt(st.nextToken());
			}
		} else {
			error("Incorrect IP address format: " + ipaddr, this);
			return null;
		}
		try {
			inetaddr = InetAddress.getByAddress(ipBytes);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return inetaddr;
	}
}