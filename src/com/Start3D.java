package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Start3D extends JPanel
{


		public int percent;
		public Start3D(int percent) {
		this.percent = percent;
		}

		@Override
		public Dimension getPreferredSize() {
		Dimension d = getParent().getSize();
		int w = d.width * percent / 100;
		int h = d.height * percent / 100;
		return new Dimension(w,h);
		}
		}

		