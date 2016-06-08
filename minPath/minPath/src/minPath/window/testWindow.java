package minPath.window;

import java.awt.EventQueue;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import minPath.Tools.Method;
import minPath.entity.Datas;

public class testWindow {

	private JFrame frame;
	private MyCanvas mycan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testWindow window = new testWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public testWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 718, 577);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mycan = new MyCanvas();
		List<Point> points = new ArrayList<Point>();
		List<Point> datas = new ArrayList<Point>();
		List<Point> temp  = new ArrayList<Point>();
		points.add(new Point(150,160));
		points.add(new Point(250,260));
		points.add(new Point(350,360));
		points.add(new Point(300,110));
		points.add(new Point(30,100));
		points.add(new Point(50,60));
		points.add(new Point(111,111));
		datas.add(new Point(222,001));
		datas.add(new Point(555,666));
		datas.add(new Point(315,246));
		datas.add(new Point(125,66));
		datas.add(new Point(5,666));
		datas.add(new Point(555,6));
		datas.add(new Point(555,666));
		
		Datas s = new Datas();
		Datas e = new Datas();
		s.setLongitude(116.5);
		s.setLatitude(39.5);
		e.setLongitude(117.0);
		e.setLatitude(40.0);
		
		//mycan.setPath(datas);
		//mycan.setDatalist(Method.getMap(s, e));
		mycan.setDatalist(Method.getMap(s, e));
		frame.add(mycan);
		//frame.repaint();
		
	}

}
