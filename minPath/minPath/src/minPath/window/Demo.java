package minPath.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;



class MyCanvas extends JPanel {
	private List<Point> datalist=new ArrayList<Point>();
	private List<Point> path = new ArrayList<Point>();
	
	public List<Point> getPath() {
		return path;
	}

	public void setPath(List<Point> path) {
		this.path = path;
	}

	MyCanvas(){
	}
	
	MyCanvas(List<Point> list){
		this.datalist=list;
	}
	
	public void setDatalist(List<Point> list){
		this.datalist=list;
	}
	
	@Override
	public void paint(Graphics g){
		Graphics2D g_2d=(Graphics2D)g;
		if(datalist!=null&&datalist.size()>0){
		     for(Point point:datalist){
		    	 Rectangle2D rect=new Rectangle2D.Double(point.x,point.y,1,1);
		    	 g_2d.setColor(Color.BLACK);
		    	 g_2d.fill(rect);
		    	 //System.out.println("painting dot");
		     }
		}
		if(path!=null&&path.size()>1)
		{
			Point t = path.get(0);
			for(Point p:path)
			{
				if(p.equals(t))
				{
					continue;
				}
				else
				{
					g_2d.setColor(Color.RED);
					g_2d.drawLine((int)t.getX(), (int)t.getY(), (int)p.getX(), (int)p.getY());
					t=p;
					//System.out.println("painting line");
				}
			}
		}

	}
}

public class Demo {
    public static void main(String[] args) {
	   JFrame win=new JFrame("huatu");
       win.setSize(400,400);
       List<Point> datalist=new ArrayList<Point>();
       datalist.add(new Point(10,10));
       datalist.add(new Point(20,20));
       datalist.add(new Point(31,45));
       datalist.add(new Point(60,15));
       datalist.add(new Point(90,78));
       datalist.add(new Point(130,190));
       datalist.add(new Point(145,104));
       win.add(new MyCanvas(datalist));
       win.setVisible(true);
	}
}
