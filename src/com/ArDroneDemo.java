package com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ArDroneDemo extends JFrame {
	
	static int speed = 50;

	public static ARDrone ardrone = null;

	private MyPanel panel1 = new MyPanel(73);
	static JPanel panel2 = new Start3D(10);
	static JLabel bat;
	
	public static void setCommand(String s){
		if (s !=null){
		System.out.println("ADRONE "+s);
		switch (s) {
		case "VK_ENTER":
			ardrone.takeOff();

			break;
		case "VK_SPACE":
			ardrone.landing();

			break;
		case "VK_Z":
			ardrone.stop();

			break;
		case "VK_LEFTS":
				// ardrone.spinLeft();
				ardrone.move3D(0, 0, 0, speed);
			break;
		case "VK_LEFT":
				ardrone.move3D(0, speed, 0, 0);
			// ardrone.goLeft();
			break;
		case "VK_RIGHTS":
				// ardrone.spinRight();
				ardrone.move3D(0, 0, 0, -speed);
			break;
		case "VK_RIGHT":
				ardrone.move3D(0, -speed, 0, 0);
			// ardrone.goRight();
			break;
		case "VK_UPS":
				// ardrone.ahead();
				ardrone.move3D(0, 0, -speed, 0);
			break;
		case "VK_UP":
				ardrone.move3D(speed, 0, 0, 0);
			// ardrone.forward();
			break;
		case "VK_DOWNS":
				// ardrone.down();
				ardrone.move3D(0, 0, speed, 0);
			break;
		case "VK_DOWN":
				ardrone.move3D(-speed, 0, 0, 0);
			// ardrone.backward();
			break;
		case "VK_R":
			// ardrone.spinRight();
			ardrone.move3D(0, 0, 0, speed);
			break;
		case "VK_L":
			// ardrone.spinLeft();
			ardrone.move3D(0, 0, 0, -speed);
			break;
		case "VK_W":
			// ardrone.up();
			ardrone.move3D(0, 0, -speed, 0);

			break;
		case "VK_S":
			// ardrone.down();
			ardrone.move3D(0, 0, speed, 0);

			break;
		case "VK_E":
			ardrone.reset();
			break;
		}
		}
		
	}
	public static void main() throws UnknownHostException,
			IOException {

		
	  SwingUtilities.invokeLater(new Runnable() {
		  
		  @Override public void run() {
		  
	   final ArDroneDemo thisClass = new ArDroneDemo();
		  
		  
		  
		  thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  thisClass.addWindowListener(new WindowAdapter() {
		  
		  @Override public void windowOpened(WindowEvent e) {
		  System.out.println("WindowOpened"); }
		  
		  @Override public void windowClosing(WindowEvent e) {
		  thisClass.dispose(); 
		  } 
		  }); 
		  
		  thisClass.setVisible(true);
		 
 
		

		}
		 });
		  
    // com.start();
	

	}

	public ArDroneDemo() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p = new JPanel();
		setContentPane(p);
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.setSize(800, 600);

		panel2.setBackground(Color.WHITE);
		p.add(panel1);
		p.add(panel2);
		setSize(800, 600);

		panel2.setLayout(null);

		//JButton cam = new JButton();
		//cam.setBounds(600, 70, 30, 30);
		//panel2.add(cam);
		panel1.setFocusable(true);
//		cam.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (c) 
//				{ardrone.setVerticalCamera();
//				c=false;}
//				else
//				{
//					ardrone.setHorizontalCamera();
//					c=true;
//			
//				}
//
//			}
//		});

		String b = "100";
		bat = new JLabel(b + " % ");
		bat.setBounds(650, 50, 150, 60);
		panel2.add(bat);

		setVisible(true);

		initialize();

		panel1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ardrone.stop();
			}

			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				int mod = e.getModifiersEx();

				boolean shiftflag = false;
				if ((mod & InputEvent.SHIFT_DOWN_MASK) != 0) {
					shiftflag = true;
				}

				switch (key) {
				case KeyEvent.VK_ENTER:	
					System.out.println("enter");
					ardrone.takeOff();
					//ardrone.reset();
					break;
				case KeyEvent.VK_SPACE:
					ardrone.landing();

					break;
				case KeyEvent.VK_Z:
					ardrone.stop();

					break;
					
				case KeyEvent.VK_ESCAPE :
					System.out.println("reset");
					ardrone.reset();
					
					break;
										
					
				case KeyEvent.VK_LEFT:
					if (shiftflag) {
						// ardrone.spinLeft();
						ardrone.move3D(0, 0, 0, speed);

						shiftflag = false;
					} else
						ardrone.move3D(0, speed, 0, 0);
					// ardrone.goLeft();
					break;
				case KeyEvent.VK_RIGHT:
					if (shiftflag) {
						// ardrone.spinRight();
						ardrone.move3D(0, 0, 0, -speed);

						shiftflag = false;
					} else
						ardrone.move3D(0, -speed, 0, 0);
					// ardrone.goRight();
					break;
				case KeyEvent.VK_UP:
					if (shiftflag) {
						// ardrone.ahead();
						ardrone.move3D(0, 0, -speed, 0);

						shiftflag = false;
					} else
						ardrone.move3D(speed, 0, 0, 0);
					// ardrone.forward();
					break;
				case KeyEvent.VK_DOWN:
					if (shiftflag) {
						// ardrone.down();
						ardrone.move3D(0, 0, speed, 0);

						shiftflag = false;
					} else
						ardrone.move3D(-speed, 0, 0, 0);
					// ardrone.backward();
					break;
				case KeyEvent.VK_R:
					// ardrone.spinRight();
					ardrone.move3D(0, 0, 0, speed);
					break;
				case KeyEvent.VK_L:
					// ardrone.spinLeft();
					ardrone.move3D(0, 0, 0, -speed);
					break;
				case KeyEvent.VK_W:
					// ardrone.up();
					ardrone.move3D(0, 0, -speed, 0);

					break;
				case KeyEvent.VK_S:
					// ardrone.down();
					System.out.println("Down");
					ardrone.move3D(0, 0, speed, 0);

					break;
				
					
				case KeyEvent.VK_Q:
					ardrone.toggleCamera();
					break;
				}
			}
		});

	}

	private void initialize() {

		ardrone = new ARDrone("192.168.1.1");
		System.out.println("connect drone controller");
		ardrone.connect();
		System.out.println("connect drone navdata");
		ardrone.connectNav();
		System.out.println("connect drone video");
		ardrone.connectVideo();
		System.out.println("start drone");
		ardrone.start();

		ardrone.addImageUpdateListener(new ImageListener() {
			@Override
			public void imageUpdated(BufferedImage image) {

				panel1.setImage(image);
				panel1.repaint();

			}
		});

		ardrone.addAttitudeUpdateListener(new AttitudeListener() {
			@Override
			public void attitudeUpdated(float pitch, float roll, float yaw,
					int altitude) {
				// System.out.println("pitch: " + pitch + ", roll: " + roll
				// + ", yaw: " + yaw + ", altitude: " + altitude);
			}
		});

		ardrone.addBatteryUpdateListener(new BatteryListener() {
			@Override
			public void batteryLevelChanged(int percentage) {
				// System.out.println("battery: " + percentage + " %");
				 bat.setText("battery "+ percentage +" % ");
			}
		});

		ardrone.addStateUpdateListener(new StateListener() {
			@Override
			public void stateChanged(DroneState state) {
				// System.out.println("state: " + state);
			}
		});

		ardrone.addVelocityUpdateListener(new VelocityListener() {
			@Override
			public void velocityChanged(float vx, float vy, float vz) {
				// System.out.println("vx: " + vx + ", vy: " + vy + ", vz: " +
				// vz);
			}
		});

		this.setTitle("ArDrone");

	}

	private class MyPanel extends Start3D {

		public MyPanel(int persent) {
			super(persent);

		}

		private static final long serialVersionUID = -7635284252404123776L;

		/** ardrone video image */
		private BufferedImage image = null;

		public void setImage(BufferedImage image) {
			panel1.image = image;
		}

		public void paint(Graphics g) {
			g.setColor(Color.white);
			g.fillRect(0, 0, panel1.getWidth(), panel1.getHeight());
			if (image != null)
				g.drawImage(image, 0, 0, panel1.getWidth(), panel1.getHeight(),
						null);
		}
	}
}
