package minPath.window;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import minPath.Tools.Distance;
import minPath.Tools.Method;
import minPath.entity.Datas;

import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Stack;

public class MainWindow {

	private JFrame frame;
	private JPanel huaban;
	private JTextArea stxtLong;
	private JTextArea stxtLat;
	private JTextArea etxtLong;
	private JTextArea etxtLat;
	private MyCanvas camvas;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Get");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double LongA = 0.0;
				double LatA = 0.0;
				double LongB = 0.0;
				double LatB = 0.0;
				Stack<Datas> datas = new Stack<Datas>();
				//List<Point> map = new ArrayList<Point>();
				List<Point> points = new ArrayList<Point>();
				Datas start = new Datas();
				Datas end = new Datas();
				try
				{
					//System.out.println(new GregorianCalendar().getTime());
				
					LongA = Double.parseDouble(stxtLong.getText());
					LatA = Double.parseDouble(stxtLat.getText());
					LongB = Double.parseDouble(etxtLong.getText());
					LatB = Double.parseDouble(etxtLat.getText());
					start.setLongitude(LongA);
					start.setLatitude(LatA);
					end.setLongitude(LongB);
					end.setLatitude(LatB);
					
					System.out.println("start :" + start.getLongitude() + ":" + start.getLatitude());
					System.out.println("end :" + end.getLongitude() + ":" + end.getLatitude());
					
					camvas.setDatalist(Method.getMap(start,end));
					datas = Method.getResult(start, end);
					points = Method.getPath(datas);
					camvas.setPath(points);
					
					frame.add(camvas);
					//System.out.println(new GregorianCalendar().getTime());
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					System.out.println("Lon:"+"\t\t"+"Lat:"+"\t\t"+ "distance from des:");
					for(Datas data:datas)
					{
						System.out.println(data.getLongitude()+"\t"+data.getLatitude()+"\t\t"+Distance.getDistance(data, end));
					}
				}
				
			}
		});
		btnNewButton.setBounds(881, 528, 93, 23);
		frame.getContentPane().add(btnNewButton);
		
		stxtLong = new JTextArea();
		stxtLong.setText("116.51135");
		stxtLong.setBounds(850, 109, 134, 23);
		frame.getContentPane().add(stxtLong);
		
		stxtLat = new JTextArea();
		stxtLat.setText("39.93883");
		stxtLat.setBounds(850, 138, 134, 23);
		frame.getContentPane().add(stxtLat);
		
		Label label = new Label("\u7ECF\u5EA6");
		label.setBounds(810, 109, 34, 23);
		frame.getContentPane().add(label);
		
		Label label_1 = new Label("\u8D77\u70B9");
		label_1.setBounds(820, 68, 128, 35);
		frame.getContentPane().add(label_1);
		
		Label label_2 = new Label("\u7EAC\u5EA6");
		label_2.setBounds(810, 138, 34, 23);
		frame.getContentPane().add(label_2);
		
		Label label_3 = new Label("\u7EC8\u70B9");
		label_3.setBounds(820, 294, 128, 35);
		frame.getContentPane().add(label_3);
		
		Label label_4 = new Label("\u7ECF\u5EA6");
		label_4.setBounds(810, 335, 34, 23);
		frame.getContentPane().add(label_4);
		
		etxtLong = new JTextArea();
		etxtLong.setText("116.4008");
		etxtLong.setBounds(850, 335, 134, 23);
		frame.getContentPane().add(etxtLong);
		
		Label label_5 = new Label("\u7EAC\u5EA6");
		label_5.setBounds(810, 364, 34, 23);
		frame.getContentPane().add(label_5);
		
		etxtLat = new JTextArea();
		etxtLat.setText("39.9026");
		etxtLat.setBounds(850, 364, 134, 23);
		frame.getContentPane().add(etxtLat);
		/*
		g.setColor(c);
		g.drawRect(5, 5, 5, 5);
		g.drawLine(x1, y1, x2, y2);
		huaban =new JPanel();
		huaban.setBounds(0, 0, 800, 600);
		huaban.setSize(800, 600);
		frame.add(huaban);
		 */
		camvas=new MyCanvas();
		camvas.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(camvas);
		
	}
}





/*
	System.out.println(s.getLongitude() +"+++"+ start.getLongitude());
	System.out.println(s.getLatitude() +"+++"+ start.getLatitude());
	System.out.println(t.getLongitude() +"+++"+ end.getLongitude());
	System.out.println(t.getLongitude() +"+++"+ end.getLongitude());
	System.out.println(s.getLongitude().equals(start.getLongitude()));
	System.out.println(s.getLatitude().equals(start.getLatitude()));
	System.out.println(t.getLongitude().equals(end.getLongitude()));
	System.out.println(t.getLongitude().equals((end.getLongitude())));
*/