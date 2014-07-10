package com;



public class ARDroneConstants {
	
	/** default IP address */
	public static final String IP_ADDRESS = "192.168.1.1";
	
	/** default PORT */
	public static final int PORT = 5556;
	public static final int VIDEO_PORT = 5555;
	public static final int NAV_PORT = 5554;
	public static final int FTP_PORT = 5551;
	
	/** default ID, for AR.Drone 2.0 */
	public static final String SESSION_ID = "d2e081a3"; 
	public static final String PROFILE_ID = "be27e2e4";
	public static final String APPLICATION_ID = "d87f7e0c";
	
	/** video codec */
	public static final String VIDEO_CODEC_UVLC = "0x20"; // 320x240, 15fps for AR.Drone 1.0
	public static final String VIDEO_CODEC_H264 = "0x40"; // 640x360, 20fps for AR.Drone 1.0
	public static final String VIDEO_CODEC_360P = "0x81"; // 360p, for AR.Drone 2.0
	public static final String VIDEO_CODEC_720P = "0x83"; // 720p, for AR.Drone 2.0

}
