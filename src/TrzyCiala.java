/*
*NUMERYCZNA ANALIZA ZAGADNIENIA TRZECH CIAL*

Program do pracy licencjackiej
Autor: Grzegorz Banaszek
Opiekun: prof. Ryszard Kutner

*/

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TrzyCiala extends JFrame implements ActionListener, ItemListener, ChangeListener{

	Legenda leg = new Legenda();

	Rysuj rys;
	GotoweZestawy gZ = new GotoweZestawy();
	
	private Graphics g;

	JPanel pWykres = new JPanel();
	JPanel pOpcje = new JPanel();
	JPanel pCiala  = new JPanel();
	JPanel pEnergiaPedMomet = new JPanel();
	JPanel pLegenda = new JPanel();
	JPanel pSuwak = new JPanel();

	JButton bStart, bStop, bPauza, bWznow, bReset;

	JSlider sDt;
	JLabel lDt;
	
	boolean fStart = false;
	boolean fUklWsp = false;
	boolean fDane = false;

	Choice chGotoweZestawy;
	Checkbox chUklWsp = new Checkbox("Uklad wspolrzednych");

	JTextField tm1;
	JTextField tx1;
	JTextField ty1;
	JTextField tVx1;
	JTextField tVy1;

	JLabel lpx;
	JLabel lpy;
	JLabel lL;

	JTextField tm2;
	JTextField tx2;
	JTextField ty2;
	JTextField tVx2;
	JTextField tVy2;

	JTextField tm3;
	JTextField tx3;
	JTextField ty3;
	JTextField tVx3;
	JTextField tVy3;

	JLabel lEnergia;

	Color tlo = new Color(50,100,150);
	
	int xscr1, yscr1, xscr2, yscr2, xscr3, yscr3;
	
	double x1, x2, x3, y1, y2, y3;
	double Vx1, Vx2, Vx3, Vy1, Vy2, Vy3;
	
	double x21, x31, x32, y21, y31, y32;
	double r21, r31, r32;
	double ax1, ax2, ax3, ay1, ay2, ay3;
	double m1, m2, m3;
	
	double px;
	double py;
	double L;
	double E ;
	
	double xx1[] = new double[4];
	double xx2[] = new double[4];
	double xx3[] = new double[4];
	
	double yy1[] = new double[4];
	double yy2[] = new double[4];
	double yy3[] = new double[4];
	
	double Vxx1[] = new double[4];
	double Vxx2[] = new double[4];
	double Vxx3[] = new double[4];
	
	double Vyy1[] = new double[4];
	double Vyy2[] = new double[4];
	double Vyy3[] = new double[4];

	double dt;
	
	int w, h;
	
	public TrzyCiala(){

		setTitle("Zagadnienie Trzech Ciał v. 1.01");

		setLayout(null);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setSize(855,575);
		setBackground(tlo);


		rys = new Rysuj();
		
		w = 400;
		h = 400;
		
		pWykres.setBackground(Color.white);
		pWykres.setBorder(BorderFactory.createTitledBorder(""));
		pWykres.setBounds(10,10,w, h);
		add(pWykres);

		pOpcje.repaint();
		pOpcje.setBackground(tlo);
		pOpcje.setBorder(BorderFactory.createTitledBorder(""));
		pOpcje.setBounds(420,10,410, 520);
		pOpcje.setLayout(null);
		add(pOpcje);
		
		pCiala.setBorder(BorderFactory.createTitledBorder(""));
		pCiala.setBackground(tlo);
		pCiala.setLayout(new GridLayout(5, 3, 5, 5));
		pCiala.setBounds(7,50,395,247);
		pOpcje.add(pCiala);
				
		tm1 = new JTextField();		
		tm1.setBorder(BorderFactory.createTitledBorder("m1"));
		pCiala.add(tm1);
		
		tm2 = new JTextField();
		tm2.setBorder(BorderFactory.createTitledBorder("m2"));
		pCiala.add(tm2);
		
		tm3 = new JTextField();
		tm3.setBorder(BorderFactory.createTitledBorder("m3"));
		pCiala.add(tm3);
		
		tx1 = new JTextField();
		tx1.setBorder(BorderFactory.createTitledBorder("x1"));
		pCiala.add(tx1);
		
		tx2 = new JTextField();
		tx2.setBorder(BorderFactory.createTitledBorder("x2"));
		pCiala.add(tx2);
		
		tx3 = new JTextField();
		tx3.setBorder(BorderFactory.createTitledBorder("x3"));
		pCiala.add(tx3);

		ty1 = new JTextField();
		ty1.setBorder(BorderFactory.createTitledBorder("y1"));
		pCiala.add(ty1);

		ty2 = new JTextField();
		ty2.setBorder(BorderFactory.createTitledBorder("y2"));
		pCiala.add(ty2);
		
		ty3 = new JTextField();
		ty3.setBorder(BorderFactory.createTitledBorder("y3"));
		pCiala.add(ty3);
		
		tVx1 = new JTextField();
		tVx1.setBorder(BorderFactory.createTitledBorder("Vx1"));
		pCiala.add(tVx1);
		
		tVx2 = new JTextField();
		tVx2.setBorder(BorderFactory.createTitledBorder("Vx2"));
		pCiala.add(tVx2);
		
		tVx3 = new JTextField();
		tVx3.setBorder(BorderFactory.createTitledBorder("Vx3"));
		pCiala.add(tVx3);

		tVy1 = new JTextField();
		tVy1.setBorder(BorderFactory.createTitledBorder("Vy1"));
		pCiala.add(tVy1);
				
		tVy2 = new JTextField();
		tVy2.setBorder(BorderFactory.createTitledBorder("Vy2"));
		pCiala.add(tVy2);	
				
		tVy3 = new JTextField();
		tVy3.setBorder(BorderFactory.createTitledBorder("Vy3"));
		pCiala.add(tVy3);
		
		pEnergiaPedMomet.setBorder(BorderFactory.createTitledBorder(""));
		pEnergiaPedMomet.setBackground(tlo);
		pEnergiaPedMomet.setLayout(new GridLayout(2, 2, 1, 1));
		pEnergiaPedMomet.setBounds(7,305,396,100);
		
		lpx = new JLabel();
		lpx.setBorder(BorderFactory.createTitledBorder("px"));
		pEnergiaPedMomet.add(lpx);
		
		lEnergia = new JLabel();
		lEnergia.setBorder(BorderFactory.createTitledBorder("ENERGIA CA�KOWITA"));
		pEnergiaPedMomet.add(lEnergia);
				
		lpy = new JLabel();
		lpy.setBorder(BorderFactory.createTitledBorder("py"));
		pEnergiaPedMomet.add(lpy);
		
		lL = new JLabel();
		lL.setBorder(BorderFactory.createTitledBorder("L"));
		pEnergiaPedMomet.add(lL);	
		
		pOpcje.add(pEnergiaPedMomet);		
		
		pLegenda.setBorder(BorderFactory.createTitledBorder(""));
		pLegenda.setBackground(tlo);
		pLegenda.setBounds(7,10,395,35);
		pLegenda.setLayout(null);
		leg.setBounds(10,8,370,30);
		pLegenda.add(leg);
		pOpcje.add(pLegenda);
		
		pSuwak.setBorder(BorderFactory.createTitledBorder("KROK DYSKRETYZACJI CZASU"));
		pSuwak.setBackground(tlo);
		pSuwak.setBounds(5,405,400,80);
		pSuwak.setLayout(null);
				
		lDt = new JLabel("0.0005");
		pSuwak.add(lDt);
		
		sDt = new JSlider(0, 1, 100, 50);
		sDt.setBackground(tlo);
		sDt.setBounds(10,20,200,50);
		sDt.setPaintTicks(true);
		sDt.setMajorTickSpacing(20);
		sDt.setMinorTickSpacing(5);
		sDt.addChangeListener(this);
		pSuwak.add(sDt);
		lDt.setBounds(280,15,50,50);
		pSuwak.add(lDt);
		pOpcje.add(pSuwak);
		
		chGotoweZestawy = new Choice();
		chGotoweZestawy.add("WYBIERZ GOTOWY ZESTAW");
		chGotoweZestawy.add("ZESTAW 1");
		chGotoweZestawy.add("ZESTAW 2");
		chGotoweZestawy.add("ZESTAW 3");
		chGotoweZestawy.add("ZESTAW 4");
		chGotoweZestawy.add("ZESTAW 5");
		chGotoweZestawy.add("ZESTAW 6");
		chGotoweZestawy.add("ZESTAW 7");
		chGotoweZestawy.add("ZESTAW 8");
		
		chGotoweZestawy.addItemListener(this);
		chGotoweZestawy.setBounds(205, 490, 200, 100);
		pOpcje.add(chGotoweZestawy);
		
		chUklWsp.addItemListener(this);
		chUklWsp.setBounds(15, 490, 200, 20);
		pOpcje.add(chUklWsp);
		
				
		bStart = new JButton("START");
		bStart.setBounds(10,  420, 120, 30);
		bStart.addActionListener(this);
		add(bStart);
		
		bPauza = new JButton("PAUZA");
		bPauza.setEnabled(false);
		bPauza.setBounds(150,  420, 120, 30);
		bPauza.addActionListener(this);
		add(bPauza);
				
		bWznow = new JButton("WZNOW");
		bWznow.setEnabled(false);
		bWznow.setBounds(290,  420, 120, 30);
		bWznow.addActionListener(this);
		add(bWznow);
			
		bStop = new JButton("STOP");
		bStop.setBounds(150,  460, 120, 30);
		bStop.addActionListener(this);
		add(bStop);

		bReset = new JButton("RESET");
		bReset.setBounds(290,  460, 120, 30);
		bReset.addActionListener(this);
		add(bReset);

		getContentPane().setBackground(tlo);
		getContentPane().repaint();
		pOpcje.revalidate();
	}

	public void czysc()
	{
		g = pWykres.getGraphics();
		g.clearRect(0,0,w,h);
		g.setColor(Color.white);
		g.fillRect(0,0,w,h);
	}
	
	public void pobierzDane()
	{
		try
		{
			m1 = Double.parseDouble(tm1.getText());
			xx1[0] = Double.parseDouble(tx1.getText());
			yy1[0] = Double.parseDouble(ty1.getText());
			Vxx1[0] = Double.parseDouble(tVx1.getText());
			Vyy1[0] = Double.parseDouble(tVy1.getText());
			
			m2 = Double.parseDouble(tm2.getText());
			xx2[0] = Double.parseDouble(tx2.getText());
			yy2[0] = Double.parseDouble(ty2.getText());
			Vxx2[0] = Double.parseDouble(tVx2.getText());
			Vyy2[0] = Double.parseDouble(tVy2.getText());
			
			m3 = Double.parseDouble(tm3.getText());
			xx3[0] = Double.parseDouble(tx3.getText());
			yy3[0] = Double.parseDouble(ty3.getText());
			Vxx3[0] = Double.parseDouble(tVx3.getText());
			Vyy3[0] = Double.parseDouble(tVy3.getText());
						
			dt = Double.parseDouble(lDt.getText());
				
			fDane = true;
				
		}
		catch(NumberFormatException e)
		{
			fDane = false;
			fStart = false;
						
			JOptionPane.showMessageDialog(this,
			                              "Wprowad� poprawne dane!!!",
			                              " B��D",
			                              JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{			
		if(e.getSource() == bStart)
		{
			fStart = true;
			
			pobierzDane();
			
			rys.rowPocz();
			
			rys.start();
				
			lEnergia.setEnabled(false);
			lpx.setEnabled(false);
			lpy.setEnabled(false);
			lL.setEnabled(false);
			
			bPauza.setEnabled(false);
			bWznow.setEnabled(false);
			
			if(fDane)
			{
				bStart.setEnabled(false);
				bPauza.setEnabled(true);
				bReset.setEnabled(false);
				sDt.setEnabled(false);
				chGotoweZestawy.setEnabled(false);
				tm1.setEnabled(false);
				tx1.setEnabled(false); 
				ty1.setEnabled(false);
				tVx1.setEnabled(false);
				tVy1.setEnabled(false);
				tm2.setEnabled(false);
				tx2.setEnabled(false); 
				ty2.setEnabled(false);
				tVx2.setEnabled(false);
				tVy2.setEnabled(false);
				tm3.setEnabled(false);
				tx3.setEnabled(false); 
				ty3.setEnabled(false);
				tVx3.setEnabled(false);
				tVy3.setEnabled(false);
			}
		}
		
		if(e.getSource() == bPauza)
		{
			rys.stop();
						
			bReset.setEnabled(false);
			bStart.setEnabled(false);											 
			bPauza.setEnabled(false);
			bWznow.setEnabled(true);
		}
		
		if(e.getSource() == bWznow)
		{
			fStart = true;
			rys.start();		
			bReset.setEnabled(false);
			bStart.setEnabled(false);
			bWznow.setEnabled(false);
			bPauza.setEnabled(true);
		}


		if(e.getSource() == bStop)
		{
			rys.stop();
			bReset.setEnabled(true);
			bPauza.setEnabled(false);
			bWznow.setEnabled(false);
											 
		}
		
		if(e.getSource() == bReset)
		{
			bPauza.setEnabled(false);
			bWznow.setEnabled(false);

			lEnergia.setText("");
			lpx.setText("");
			lpy.setText("");
			lL.setText("");
			
			chUklWsp.setState(false);
			fUklWsp = false;
		
			chGotoweZestawy.select(0);
			chGotoweZestawy.getItem(0);
			gZ.pusteDane();
			
			sDt.setValue(50);
			
			czysc();
			
			xx1[0] = 0; 
			xx2[0] = 0;	
			xx3[0] = 0;			
			yy1[0] = 0;
			yy2[0] = 0;
			yy3[0] = 0;							

			Vxx1[0] = 0; 
			Vxx2[0] = 0;	
			Vxx3[0] = 0;			
			Vyy1[0] = 0;
			Vyy2[0] = 0;
			Vyy3[0] = 0;
			
			m1 = 0;
			m2 = 0;
			m3 = 0;
			
			bStart.setEnabled(true);
			sDt.setEnabled(true);
			chGotoweZestawy.setEnabled(true);
			tm1.setEnabled(true);
			tx1.setEnabled(true); 
			ty1.setEnabled(true);
			tVx1.setEnabled(true);
			tVy1.setEnabled(true);
			tm2.setEnabled(true);
			tx2.setEnabled(true); 
			ty2.setEnabled(true);
			tVx2.setEnabled(true);
			tVy2.setEnabled(true);
			tm3.setEnabled(true);
			tx3.setEnabled(true); 
			ty3.setEnabled(true);
			tVx3.setEnabled(true);
			tVy3.setEnabled(true);
		}
	}
	
	public void stateChanged(ChangeEvent e)
	{
		sDt = (JSlider)e.getSource();
		double dt;		
		dt = 0.00001 * sDt.getValue();
		
		lDt.setText("" + dt);
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getItemSelectable() == chUklWsp)
		{
			fUklWsp = !fUklWsp;
			g = pWykres.getGraphics();
			
			if(fUklWsp == true)
			{
				g.setColor(Color.black);
				g.drawLine(0,h/2,w,h/2);
				g.drawLine(w/2,0,w/2,h);
			}
			else
			{
				g.setColor(Color.white);
				g.drawLine(0,h/2,w,h/2);
				g.drawLine(w/2,0,w/2,h);
			}
		}
		
		if(e.getItemSelectable() == chGotoweZestawy)
		{			
			if(((Choice)e.getItemSelectable()).getSelectedIndex() == 0)
			{
				gZ.pusteDane();
			}		
			
			if(((Choice)e.getItemSelectable()).getSelectedIndex() == 1)
			{
				gZ.zestaw1();
			}
			
			if(((Choice)e.getItemSelectable()).getSelectedIndex() == 2)
			{
				gZ.zestaw2();
			}		
			
			if(((Choice)e.getItemSelectable()).getSelectedIndex() == 3)
			{
				gZ.zestaw3();
			}
			if(((Choice)e.getItemSelectable()).getSelectedIndex() == 4)
			{
				gZ.zestaw4();
			}		
			if(((Choice)e.getItemSelectable()).getSelectedIndex() == 5)
			{	
				gZ.zestaw5();
			}
			if(((Choice)e.getItemSelectable()).getSelectedIndex() == 6)
			{
				gZ.zestaw6();
			}		
			if(((Choice)e.getItemSelectable()).getSelectedIndex() == 7)
			{
				gZ.zestaw7();
			}
			if(((Choice)e.getItemSelectable()).getSelectedIndex() == 8)
			{
				gZ.zestaw8();
			}

		}
	}

	public static void main(String [] args){
		new TrzyCiala();
	}
	
	private class Rysuj  implements Runnable{
		Thread wRysuj = null;
	
		Rysuj(){
			super();
		}
			
		public void start(){
			wRysuj = new Thread(this);
			wRysuj.start();	
		}
		
		//rownania rozruchowe do metody zabiego skoku
		public void rowPocz(){
			//dane wprowadzane przez uzytkownika
			//xx1[0] ->x1(t=0)
			//xx2[0] ->x2(t=0)
			//xx3[0] ->x3(t=0)
			//yy1[0] ->y1(t=0)
			//yy2[0] ->y2(t=0)
			//yy3[0] ->y3(t=0)
			
			//rownania poczatkowe
			//r1 = r0 + dtV0
			xx1[1] = xx1[0] + dt * Vxx1[0];			
			xx2[1] = xx2[0] + dt * Vxx2[0];		
			xx3[1] = xx3[0] + dt * Vxx3[0];
			yy1[1] = yy1[0] + dt * Vyy1[0];
			yy2[1] = yy2[0] + dt * Vyy2[0];
			yy3[1] = yy3[0] + dt * Vyy3[0];
			
			//wspolrzedne wektorow odleglosci od siebie poszczegolnych mas
			x21 = xx2[0] - xx1[0];
			x31 = xx3[0] - xx1[0];
			x32 = xx3[0] - xx2[0];
			y21 = yy2[0] - yy1[0];
			y31 = yy3[0] - yy1[0];
			y32 = yy3[0] - yy2[0];
			
			//odleglosci od siebie poszczegolnych mas
			r21 = Math.sqrt(Math.pow(x21, 2) + Math.pow(y21, 2));
			r31 = Math.sqrt(Math.pow(x31, 2) + Math.pow(y31, 2));
			r32 = Math.sqrt(Math.pow(x32, 2) + Math.pow(y32, 2));
			
			E = m1*(Math.pow(Vxx1[0], 2)+Math.pow(Vyy1[0],2))/2 + 
					m2*(Math.pow(Vxx2[0],2)+Math.pow(Vyy2[0],2))/2 + 
					m3*(Math.pow(Vxx3[0], 2)+Math.pow(Vyy3[0],2)) /2 -
					m1*m2/r21 - m1*m3/r31 - m2*m3/r32;
					 	
			lEnergia.setText("" + String.valueOf(E));	
			
			px = m1*Vxx1[0]+m2*Vxx2[0]+m3*Vxx3[0];
			lpx.setText(""+String.valueOf(px));

			py = m1*Vyy1[0]+m2*Vyy2[0]+m3*Vyy3[0];
			lpy.setText(""+String.valueOf(py));
			
			//ca�kowity moment pedu
			L = m1*(xx1[0]*Vyy1[0]-yy1[0]*Vxx1[0])+ 
					m2*(xx2[0]*Vyy2[0]-yy2[0]*Vxx2[0])+
					m3*(xx3[0]*Vyy3[0]-yy3[0]*Vxx3[0]);
			lL.setText(""+String.valueOf(L));

			
			//a(r0)
			ax1 = m2 *x21 / Math.pow(r21, 3) +  m3 * x31 / Math.pow(r31, 3);
			ay1 = m2 *y21 / Math.pow(r21, 3) +  m3 * y31 / Math.pow(r31, 3);
			ax2 = - m1 *x21 / Math.pow(r21, 3) +  m3 * x32 / Math.pow(r32, 3);
			ay2 = - m1 *y21 / Math.pow(r21, 3) +  m3 * y32 / Math.pow(r32, 3);
			ax3 = - m1 *x31 / Math.pow(r31, 3) -  m2 * x32 / Math.pow(r32, 3);
			ay3 = - m2 *y31 / Math.pow(r31, 3) -  m2 * y32 / Math.pow(r32, 3);

			//V1 = V0 + dt*a(r0)
			//r2 = r0 + 2*dt*V1
			Vxx1[1] = Vxx1[0] + dt*ax1;
			Vyy1[1] = Vyy1[0] + dt*ay1;
			Vxx2[1] = Vxx2[0] + dt*ax2;
			Vyy2[1] = Vyy2[0] + dt*ay2;
			Vxx3[1] = Vxx3[0] + dt*ax3;
			Vyy3[1] = Vyy3[0] + dt*ay3;
			
			xx1[2] = xx1[0] + 2*dt*Vxx1[1];
			yy1[2] = yy1[0] + 2*dt*Vyy1[1];
			xx2[2] = xx2[0] + 2*dt*Vxx2[1];
			yy2[2] = yy2[0] + 2*dt*Vyy2[1];
			xx3[2] = xx3[0] + 2*dt*Vxx3[1];
			yy3[2] = yy3[0] + 2*dt*Vyy3[1];
			
			//wektory odleglosci od siebie poszczegolnych mas
			x21 = xx2[1] - xx1[1];
			x31 = xx3[1] - xx1[1];
			x32 = xx3[1] - xx2[1];
			y21 = yy2[1] - yy1[1];
			y31 = yy3[1] - yy1[1];
			y32 = yy3[1] - yy2[1];
			
			//odleglosci mas od siebie
			r21 = Math.sqrt(Math.pow(x21, 2) + Math.pow(y21, 2));
			r31 = Math.sqrt(Math.pow(x31, 2) + Math.pow(y31, 2));
			r32 = Math.sqrt(Math.pow(x32, 2) + Math.pow(y32, 2));
			
			E = m1*(Math.pow(Vxx1[1], 2)+Math.pow(Vyy1[1],2))/2 + 
					m2*(Math.pow(Vxx2[1],2)+Math.pow(Vyy2[1],2))/2 + 
					m3*(Math.pow(Vxx3[1], 2)+Math.pow(Vyy3[1],2)) /2 -
					m1*m2/r21 - m1*m3/r31 - m2*m3/r32;
	
			lEnergia.setText("" + String.valueOf(E));
			
			px = m1*Vxx1[1]+m2*Vxx2[1]+m3*Vxx3[1];
			lpx.setText(""+String.valueOf(px));

			py = m1*Vyy1[1]+m2*Vyy2[1]+m3*Vyy3[1];
			lpy.setText(""+String.valueOf(py));
			
			//ca�kowity moment pedu
			L = m1*(xx1[0]*Vyy1[0]-yy1[0]*Vxx1[0])+ 
					m2*(xx2[0]*Vyy2[0]-yy2[0]*Vxx2[0])+
					m3*(xx3[0]*Vyy3[0]-yy3[0]*Vxx3[0]);
			lL.setText(""+String.valueOf(L));
			
			//a(r1)
			ax1 = m2 *x21 / Math.pow(r21, 3) +  m3 * x31 / Math.pow(r31, 3);
			ay1 = m2 *y21 / Math.pow(r21, 3) +  m3 * y31 / Math.pow(r31, 3);
			ax2 = - m1 *x21 / Math.pow(r21, 3) +  m3 * x32 / Math.pow(r32, 3);
			ay2 = - m1 *y21 / Math.pow(r21, 3) +  m3 * y32 / Math.pow(r32, 3);
			ax3 = - m1 *x31 / Math.pow(r31, 3) -  m2 * x32 / Math.pow(r32, 3);
			ay3 = - m2 *y31 / Math.pow(r31, 3) -  m2 * y32 / Math.pow(r32, 3);

			//V2 = V0 + 2*dt*a(r1)
			//r3 = r1 + 2*dt*V2
			Vxx1[2] = Vxx1[0] + 2*dt*ax1;
			Vxx2[2] = Vxx2[0] + 2*dt*ax2;
			Vxx3[2] = Vxx3[0] + 2*dt*ax3;
			Vyy1[2] = Vyy1[0] + 2*dt*ay1;
			Vyy2[2] = Vyy2[0] + 2*dt*ay2;
			Vyy3[2] = Vyy3[0] + 2*dt*ay3;
			
			xx1[3] = xx1[1] + 2*dt*Vxx1[2];
			xx2[3] = xx2[1] + 2*dt*Vxx2[2];			
			xx3[3] = xx3[1] + 2*dt*Vxx3[2];	
			yy1[3] = yy1[1] + 2*dt*Vyy1[2];
			yy2[3] = yy2[1] + 2*dt*Vyy2[2];
			yy3[3] = yy3[1] + 2*dt*Vyy3[2];
			
		}
		
		//funkcja zawiera algorytm oraz rysuje
		public void run()
		{ 
			g = pWykres.getGraphics();
								
			while(fStart)
			{	
				/**************************************/
				
				xscr1 = (int)(w/2 *( 1 + xx1[0]/3));
				yscr1 = (int)(h/2  *(1 - yy1[0]/3));
				xscr2 = (int)(w/2 * (1 + xx2[0]/3));
				yscr2 = (int)(h/2 * (1 - yy2[0]/3));
				xscr3 = (int)(w/2 *(1 + xx3[0]/3));
				yscr3 = (int)(h/2 *(1 - yy3[0]/3));
							
				g.setColor(Color.blue);
				g.fillOval(xscr1, yscr1, 2, 2);
				g.setColor(Color.red);
				g.fillOval(xscr2, yscr2, 2, 2);
				g.setColor(Color.GREEN);
				g.fillOval(xscr3, yscr3, 2, 2);
								
				/**************************************/
				
				xscr1 = (int)(w/2 *( 1 + xx1[1]/3));
				yscr1 = (int)(h/2  *(1 - yy1[1]/3));
				xscr2 = (int)(w/2 * (1 + xx2[1]/3));
				yscr2 = (int)(h/2 * (1 - yy2[1]/3));
				xscr3 = (int)(w/2 *(1 + xx3[1]/3));
				yscr3 = (int)(h/2 *(1 - yy3[1]/3));
				
				g.setColor(Color.blue);
				g.fillOval(xscr1, yscr1, 2, 2);	
				g.setColor(Color.red);
				g.fillOval(xscr2, yscr2, 2, 2);
				g.setColor(Color.GREEN);
				g.fillOval(xscr3, yscr3, 2, 2); 		
				
				/**************************************/
				
				xscr1 = (int)(w/2 *( 1 + xx1[2]/3));
				yscr1 = (int)(h/2  *(1 - yy1[2]/3));
				xscr2 = (int)(w/2 * (1 + xx2[2]/3));
				yscr2 = (int)(h/2 * (1 - yy2[2]/3));
				xscr3 = (int)(w/2 *(1 + xx3[2]/3));
				yscr3 = (int)(h/2 *(1 - yy3[2]/3));
			
				g.setColor(Color.blue);
				g.fillOval(xscr1, yscr1, 2, 2);
				g.setColor(Color.white);
				g.setColor(Color.red);
				g.fillOval(xscr2, yscr2, 2, 2);
				g.setColor(Color.GREEN);
				g.fillOval(xscr3, yscr3, 2, 2); 
				
				/**************************************/
				
				xscr1 = (int)(w/2 *( 1 + xx1[3]/3));
				yscr1 = (int)(h/2  *(1 - yy1[3]/3));
				xscr2 = (int)(w/2 * (1 + xx2[3]/3));
				yscr2 = (int)(h/2 * (1 - yy2[3]/3));
				xscr3 = (int)(w/2 *(1 + xx3[3]/3));
				yscr3 = (int)(h/2 *(1 - yy3[3]/3));
		
				g.setColor(Color.blue);
				g.fillOval(xscr1, yscr1, 2, 2);
				g.setColor(Color.red);
				g.fillOval(xscr2, yscr2, 2, 2);
				g.setColor(Color.GREEN);
				g.fillOval(xscr3, yscr3, 2, 2); 

				/**************************************/
				
				x21 = xx2[2] - xx1[2];
				x31 = xx3[2] - xx1[2];
				x32 = xx3[2] - xx2[2];
				y21 = yy2[2] - yy1[2];
				y31 = yy3[2] - yy1[2];
				y32 = yy3[2] - yy2[2];
				
				//odleglosci mas od siebie
				r21 = Math.sqrt(Math.pow(x21, 2) + Math.pow(y21, 2));
				r31 = Math.sqrt(Math.pow(x31, 2) + Math.pow(y31, 2));
				r32 = Math.sqrt(Math.pow(x32, 2) + Math.pow(y32, 2));
				
				E = m1*(Math.pow(Vxx1[2], 2)+Math.pow(Vyy1[2],2))/2 + 
					m2*(Math.pow(Vxx2[2],2)+Math.pow(Vyy2[2],2))/2 + 
					m3*(Math.pow(Vxx3[2], 2)+Math.pow(Vyy3[2],2)) /2 -
					m1*m2/r21 - m1*m3/r31 - m2*m3/r32;
 	
				lEnergia.setText("" + String.valueOf(E));
				
				px = m1*Vxx1[2]+m2*Vxx2[2]+m3*Vxx3[2];
				lpx.setText(""+String.valueOf(px));
	
				py = m1*Vyy1[2]+m2*Vyy2[2]+m3*Vyy3[2];
				lpy.setText(""+String.valueOf(py));

				//ca�kowity moment pedu
				L = m1*(xx1[0]*Vyy1[0]-yy1[0]*Vxx1[0])+ 
						m2*(xx2[0]*Vyy2[0]-yy2[0]*Vxx2[0])+
						m3*(xx3[0]*Vyy3[0]-yy3[0]*Vxx3[0]);
				lL.setText(""+String.valueOf(L));
				
				//a(r2)
				ax1 = m2 *x21 / Math.pow(r21, 3) +  m3 * x31 / Math.pow(r31, 3);
				ay1 = m2 *y21 / Math.pow(r21, 3) +  m3 * y31 / Math.pow(r31, 3);
				ax2 = - m1 *x21 / Math.pow(r21, 3) +  m3 * x32 / Math.pow(r32, 3);
				ay2 = - m1 *y21 / Math.pow(r21, 3) +  m3 * y32 / Math.pow(r32, 3);
				ax3 = - m1 *x31 / Math.pow(r31, 3) -  m2 * x32 / Math.pow(r32, 3);
				ay3 = - m2 *y31 / Math.pow(r31, 3) -  m2 * y32 / Math.pow(r32, 3);
				
				//V3 = V1 + 2*dt*a(r2)
				//r4 = r2 + 2*dt*V3
				Vxx1[3] = Vxx1[1] + 2*dt*ax1;
				Vxx2[3] = Vxx2[1] + 2*dt*ax2;
				Vxx3[3] = Vxx3[1] + 2*dt*ax3;
				Vyy1[3] = Vyy1[1] + 2*dt*ay1;
				Vyy2[3] = Vyy2[1] + 2*dt*ay2;
				Vyy3[3] = Vyy3[1] + 2*dt*ay3;
				
				xx1[0] = xx1[2] + 2*dt*Vxx1[3];
				xx2[0] = xx2[2] + 2*dt*Vxx2[3];
				xx3[0] = xx3[2] + 2*dt*Vxx3[3];
				yy1[0] = yy1[2] + 2*dt*Vyy1[3];
				yy2[0] = yy2[2] + 2*dt*Vyy2[3];
				yy3[0] = yy3[2] + 2*dt*Vyy3[3];
				
				/**************************************/
				
				x21 = xx2[3] - xx1[3];
				x31 = xx3[3] - xx1[3];
				x32 = xx3[3] - xx2[3];
				y21 = yy2[3] - yy1[3];
				y31 = yy3[3] - yy1[3];
				y32 = yy3[3] - yy2[3];
				
				//odleglosci mas od siebie
				r21 = Math.sqrt(Math.pow(x21, 2) + Math.pow(y21, 2));
				r31 = Math.sqrt(Math.pow(x31, 2) + Math.pow(y31, 2));
				r32 = Math.sqrt(Math.pow(x32, 2) + Math.pow(y32, 2));
				
				E = m1*(Math.pow(Vxx1[3], 2)+Math.pow(Vyy1[3],2))/2 + 
						m2*(Math.pow(Vxx2[3],2)+Math.pow(Vyy2[3],2))/2 + 
						m3*(Math.pow(Vxx3[3], 2)+Math.pow(Vyy3[3],2)) /2 -
						m1*m2/r21 - m1*m3/r31 - m2*m3/r32;
	
				lEnergia.setText("" + String.valueOf(E));			
				
				px = m1*Vxx1[3]+m2*Vxx2[3]+m3*Vxx3[3];
				lpx.setText(""+String.valueOf(px));

				py = m1*Vyy1[3]+m2*Vyy2[3]+m3*Vyy3[3];
				lpy.setText(""+String.valueOf(py));

				//ca�kowity moment pedu
				L = m1*(xx1[0]*Vyy1[0]-yy1[0]*Vxx1[0])+ 
						m2*(xx2[0]*Vyy2[0]-yy2[0]*Vxx2[0])+
						m3*(xx3[0]*Vyy3[0]-yy3[0]*Vxx3[0]);
				lL.setText(""+String.valueOf(L));

				//a(r3)
				ax1 = m2 *x21 / Math.pow(r21, 3) +  m3 * x31 / Math.pow(r31, 3);
				ay1 = m2 *y21 / Math.pow(r21, 3) +  m3 * y31 / Math.pow(r31, 3);
				ax2 = - m1 *x21 / Math.pow(r21, 3) +  m3 * x32 / Math.pow(r32, 3);
				ay2 = - m1 *y21 / Math.pow(r21, 3) +  m3 * y32 / Math.pow(r32, 3);
				ax3 = - m1 *x31 / Math.pow(r31, 3) -  m2 * x32 / Math.pow(r32, 3);
				ay3 = - m2 *y31 / Math.pow(r31, 3) -  m2 * y32 / Math.pow(r32, 3);
	
				//V4 = V2 + 2*dt*a(r3)
				//r5 = r3 + 2*dt*V4
				Vxx1[0] = Vxx1[2] + 2*dt*ax1;
				Vxx2[0] = Vxx2[2] + 2*dt*ax2;
				Vxx3[0] = Vxx3[2] + 2*dt*ax3;
				Vyy1[0] = Vyy1[2] + 2*dt*ay1;
				Vyy2[0] = Vyy2[2] + 2*dt*ay2;
				Vyy3[0] = Vyy3[2] + 2*dt*ay3;

				xx1[1] = xx1[3] + 2*dt*Vxx1[0];				
				xx2[1] = xx2[3] + 2*dt*Vxx2[0];				
				xx3[1] = xx3[3] + 2*dt*Vxx3[0];
				yy1[1] = yy1[3] + 2*dt*Vyy1[0];				
				yy2[1] = yy2[3] + 2*dt*Vyy2[0];
				yy3[1] = yy3[3] + 2*dt*Vyy3[0];

				/**************************************/
				
				x21 = xx2[0] - xx1[0];
				x31 = xx3[0] - xx1[0];
				x32 = xx3[0] - xx2[0];
				y21 = yy2[0] - yy1[0];
				y31 = yy3[0] - yy1[0];
				y32 = yy3[0] - yy2[0];
				
				//odleglosci mas od siebie
				r21 = Math.sqrt(Math.pow(x21, 2) + Math.pow(y21, 2));
				r31 = Math.sqrt(Math.pow(x31, 2) + Math.pow(y31, 2));
				r32 = Math.sqrt(Math.pow(x32, 2) + Math.pow(y32, 2));
				
				E = m1*(Math.pow(Vxx1[0], 2)+Math.pow(Vyy1[0],2))/2 + 
						m2*(Math.pow(Vxx2[0],2)+Math.pow(Vyy2[0],2))/2 + 
						m3*(Math.pow(Vxx3[0], 2)+Math.pow(Vyy3[0],2)) /2 -
						m1*m2/r21 - m1*m3/r31 - m2*m3/r32;
	
			
				lEnergia.setText("" + String.valueOf(E));
	
				px = m1*Vxx1[0]+m2*Vxx2[0]+m3*Vxx3[0];
				lpx.setText(""+String.valueOf(px));

				py = m1*Vyy1[0]+m2*Vyy2[0]+m3*Vyy3[0];
				lpy.setText(""+String.valueOf(py));
	
				//ca�kowity moment pedu
				L = m1*(xx1[0]*Vyy1[0]-yy1[0]*Vxx1[0])+ 
						m2*(xx2[0]*Vyy2[0]-yy2[0]*Vxx2[0])+
						m3*(xx3[0]*Vyy3[0]-yy3[0]*Vxx3[0]);
				lL.setText(""+String.valueOf(L));
			
				//a(r4)			
				ax1 = m2 *x21 / Math.pow(r21, 3) +  m3 * x31 / Math.pow(r31, 3);
				ay1 = m2 *y21 / Math.pow(r21, 3) +  m3 * y31 / Math.pow(r31, 3);
				ax2 = - m1 *x21 / Math.pow(r21, 3) +  m3 * x32 / Math.pow(r32, 3);
				ay2 = - m1 *y21 / Math.pow(r21, 3) +  m3 * y32 / Math.pow(r32, 3);
				ax3 = - m1 *x31 / Math.pow(r31, 3) -  m2 * x32 / Math.pow(r32, 3);
				ay3 = - m2 *y31 / Math.pow(r31, 3) -  m2 * y32 / Math.pow(r32, 3);
	
				Vxx1[1] = Vxx1[3] + 2*dt*ax1;
				Vxx2[1] = Vxx2[3] + 2*dt*ax2;
				Vxx3[1] = Vxx3[3] + 2*dt*ax3;
				Vyy1[1] = Vyy1[3] + 2*dt*ay1;
				Vyy2[1] = Vyy2[3] + 2*dt*ay2;
				Vyy3[1] = Vyy3[3] + 2*dt*ay3;
				
				xx1[2] = xx1[0] + 2*dt*Vxx1[1];
				xx2[2] = xx2[0] + 2*dt*Vxx2[1];
				xx3[2] = xx3[0] + 2*dt*Vxx3[1];
				yy1[2] = yy1[0] + 2*dt*Vyy1[1];
				yy2[2] = yy2[0] + 2*dt*Vyy2[1];
				yy3[2] = yy3[0] + 2*dt*Vyy3[1];
				
				/**************************************/
				
				x21 = xx2[1] - xx1[1];
				x31 = xx3[1] - xx1[1];
				x32 = xx3[1] - xx2[1];
				y21 = yy2[1] - yy1[1];
				y31 = yy3[1] - yy1[1];
				y32 = yy3[1] - yy2[1];
				
				//odleglosci mas od siebie
				r21 = Math.sqrt(Math.pow(x21, 2) + Math.pow(y21, 2));
				r31 = Math.sqrt(Math.pow(x31, 2) + Math.pow(y31, 2));
				r32 = Math.sqrt(Math.pow(x32, 2) + Math.pow(y32, 2));
				
				E = m1*(Math.pow(Vxx1[1], 2)+Math.pow(Vyy1[1],2))/2 + 
						m2*(Math.pow(Vxx2[1],2)+Math.pow(Vyy2[1],2))/2 + 
						m3*(Math.pow(Vxx3[1], 2)+Math.pow(Vyy3[1],2)) /2 -
						m1*m2/r21 - m1*m3/r31 - m2*m3/r32;
	
				lEnergia.setText("" + String.valueOf(E));			
				
				px = m1*Vxx1[1]+m2*Vxx2[1]+m3*Vxx3[1];
				lpx.setText(""+String.valueOf(px));
	
				py = m1*Vyy1[1]+m2*Vyy2[1]+m3*Vyy3[1];
				lpy.setText(""+String.valueOf(py));

				//ca�kowity moment pedu
				L = m1*(xx1[0]*Vyy1[0]-yy1[0]*Vxx1[0])+ 
						m2*(xx2[0]*Vyy2[0]-yy2[0]*Vxx2[0])+
						m3*(xx3[0]*Vyy3[0]-yy3[0]*Vxx3[0]);
				lL.setText(""+String.valueOf(L));

				ax1 = m2 *x21 / Math.pow(r21, 3) +  m3 * x31 / Math.pow(r31, 3);
				ay1 = m2 *y21 / Math.pow(r21, 3) +  m3 * y31 / Math.pow(r31, 3);
				ax2 = - m1 *x21 / Math.pow(r21, 3) +  m3 * x32 / Math.pow(r32, 3);
				ay2 = - m1 *y21 / Math.pow(r21, 3) +  m3 * y32 / Math.pow(r32, 3);
				ax3 = - m1 *x31 / Math.pow(r31, 3) -  m2 * x32 / Math.pow(r32, 3);
				ay3 = - m2 *y31 / Math.pow(r31, 3) -  m2 * y32 / Math.pow(r32, 3);
	
				Vxx1[2] = Vxx1[0] + 2*dt*ax1;
				Vxx2[2] = Vxx2[0] + 2*dt*ax2;
				Vxx3[2] = Vxx3[0] + 2*dt*ax3;
				Vyy1[2] = Vyy1[0] + 2*dt*ay1;
				Vyy2[2] = Vyy2[0] + 2*dt*ay2;
				Vyy3[2] = Vyy3[0] + 2*dt*ay3;
				
				xx1[3] = xx1[1] + 2*dt*Vxx1[2];
				xx2[3] = xx2[1] + 2*dt*Vxx2[2];				
				xx3[3] = xx3[1] + 2*dt*Vxx3[2];
				yy1[3] = yy1[1] + 2*dt*Vyy1[2];				
				yy2[3] = yy2[1] + 2*dt*Vyy2[2];				
				yy3[3] = yy3[1] + 2*dt*Vyy3[2];
				
				//rysuje uklad wspolrzednych
				if(fUklWsp)
				{
					g.setColor(Color.black);
					g.drawLine(0,h/2,w,h/2);
					g.drawLine(w/2,0,w/2,h);
				}
				else
				{
					g.setColor(Color.white);
					g.drawLine(0,h/2,w,h/2);
					g.drawLine(w/2,0,w/2,h);
				}
									
				try
				{
					Thread.sleep((int)(0.1*sDt.getValue()));
				}
				catch(InterruptedException e){}
			}
		}
		
		public void stop()
		{
			fStart = false;
		}		
	}
	public class GotoweZestawy {

		public void pusteDane() {
			tm1.setText("");
			tx1.setText("");
			ty1.setText("");
			tVx1.setText("");
			tVy1.setText("");

			tm2.setText("");
			tx2.setText("");
			ty2.setText("");
			tVx2.setText("");
			tVy2.setText("");

			tm3.setText("");
			tx3.setText("");
			ty3.setText("");
			tVx3.setText("");
			tVy3.setText("");
		}

		public void zestaw1() {
			tm1.setText("1");
			tx1.setText("-1");
			ty1.setText("-0.5773503");
			tVx1.setText("0.5");
			tVy1.setText("-0.866025");

			tm2.setText("1");
			tx2.setText("1");
			ty2.setText("-0.57773503");
			tVx2.setText("0.5");
			tVy2.setText("0.8660254");

			tm3.setText("1");
			tx3.setText("0");
			ty3.setText("1.154701");
			tVx3.setText("-1");
			tVy3.setText("0");
		}

		public void zestaw2() {
			tm1.setText("1");
			tx1.setText("-1");
			ty1.setText("-0.5773503");
			tVx1.setText("0.25");
			tVy1.setText("-0.4330127");

			tm2.setText("1");
			tx2.setText("1");
			ty2.setText("-0.57773503");
			tVx2.setText("0.25");
			tVy2.setText("0.4330127");

			tm3.setText("1");
			tx3.setText("0");
			ty3.setText("1.154701");
			tVx3.setText("-0.5");
			tVy3.setText("0");
		}

		public void zestaw3() {
			tm1.setText("1");
			tx1.setText("-2");
			ty1.setText("0");
			tVx1.setText("0");
			tVy1.setText("-2");

			tm2.setText("1");
			tx2.setText("2");
			ty2.setText("0");
			tVx2.setText("0");
			tVy2.setText("2");

			tm3.setText("20");
			tx3.setText("0");
			ty3.setText("0");
			tVx3.setText("0");
			tVy3.setText("0");
		}

		public void zestaw4() {
			tm1.setText("1");
			tx1.setText("-2");
			ty1.setText("0");
			tVx1.setText("0");
			tVy1.setText("-2");

			tm2.setText("0.1");
			tx2.setText("1");
			ty2.setText("0");
			tVx2.setText("0");
			tVy2.setText("4");

			tm3.setText("20");
			tx3.setText("0");
			ty3.setText("0");
			tVx3.setText("0");
			tVy3.setText("0");
		}

		public void zestaw5() {
			tm1.setText("0.1");
			tx1.setText("-2");
			ty1.setText("0");
			tVx1.setText("0");
			tVy1.setText("0.5");

			tm2.setText("1");
			tx2.setText("-1.5");
			ty2.setText("0");
			tVx2.setText("0");
			tVy2.setText("2");

			tm3.setText("10");
			tx3.setText("1");
			ty3.setText("0");
			tVx3.setText("0");
			tVy3.setText("0");
		}

		public void zestaw6() {
			tm1.setText("0.1");
			tx1.setText("-2");
			ty1.setText("1");
			tVx1.setText("0.5");
			tVy1.setText("0");

			tm2.setText("1");
			tx2.setText("-1.5");
			ty2.setText("0");
			tVx2.setText("0");
			tVy2.setText("2");

			tm3.setText("25");
			tx3.setText("1");
			ty3.setText("0");
			tVx3.setText("0");
			tVy3.setText("0");
		}

		public void zestaw7() {
			tm1.setText("0.1");
			tx1.setText("-2");
			ty1.setText("0");
			tVx1.setText("0");
			tVy1.setText("0.5");

			tm2.setText("1");
			tx2.setText("-1.5");
			ty2.setText("0");
			tVx2.setText("0");
			tVy2.setText("2");

			tm3.setText("30");
			tx3.setText("1");
			ty3.setText("0");
			tVx3.setText("0");
			tVy3.setText("0");
		}

		public void zestaw8() {
			tm1.setText("1");
			tx1.setText("-0.97000436");
			ty1.setText("0.24308753");
			tVx1.setText("-0.466203685");
			tVy1.setText("-0.43236573");

			tm2.setText("1");
			tx2.setText("0.97000436");
			ty2.setText("-0.24308753");
			tVx2.setText("-0.466203685");
			tVy2.setText("-0.43236573");

			tm3.setText("1");
			tx3.setText("0");
			ty3.setText("0");
			tVx3.setText("0.93240737");
			tVy3.setText("0.86473146");
		}
	}
}

