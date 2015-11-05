package evo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;


public class Frame_Evo extends JFrame{// implements Runnable{
	
	
	private Game_Evo pnlEvo;
	
	private Thread loop;
	private JButton start;
	private JButton step;
	private JPanel Box;
	private JPanel settingsPanel;
	private JPanel speedPanel;
	private JPanel rainPanel;
	private JPanel windPanel;
	private JPanel sunPanel;
	private JPanel top;
	private JPanel settingsPanel2;
	private JPanel statusPanel;
	private JRadioButton speed1;
	private JRadioButton speed2;
	private JRadioButton speed3;
	private JRadioButton speed4;
	private JRadioButton speed5;
	private JRadioButton rain1;
	private JRadioButton rain2;
	private JRadioButton rain3;
	private JRadioButton rain4;
	private JRadioButton rain5;
	private JRadioButton wind1;
	private JRadioButton wind2;
	private JRadioButton wind3;
	private JRadioButton wind4;
	private JRadioButton wind5; 
	private JRadioButton sun1;
	private JRadioButton sun2;
	private JRadioButton sun3;
	private JRadioButton sun4;
	private JRadioButton sun5;
	private ButtonGroup Speed;
	private ButtonGroup Rain;
	private ButtonGroup Wind;
	private ButtonGroup Sun;
	 private final JLabel SUN_LABEL;
	 private final JLabel SPEED_LABEL;
	 private final JLabel RAIN_LABEL;
	 private final JLabel WIND_LABEL;
	 private boolean timeOn;
	 private SoundFire soundF;
	 private SoundWind soundW;
	 private SoundRain soundR;
	 private SoundForest soundM;
	 
	
	

	
	public Frame_Evo()
	{
		this.setLayout(new BorderLayout());
		//pnlEvo = new Game_Evo();
		//blankPanel1 = new JPanel();
		//blankPanel2= new JPanel();
		start = new JButton("Start/Stop");
		step = new JButton("JodyBomb");
		start.addActionListener(new StartButtonListener());
		step.addActionListener(new StepButtonListener());
		SPEED_LABEL = new JLabel("Speed");
		SUN_LABEL = new JLabel("Sun");
		RAIN_LABEL= new JLabel("Rain");
		WIND_LABEL = new JLabel("Wind");
		speed1 = new JRadioButton("");
		speed2 = new JRadioButton("");
		speed3 = new JRadioButton("",true);
		speed4 = new JRadioButton("");
		speed5 = new JRadioButton("");
		rain1 = new JRadioButton("");
		rain2 = new JRadioButton("");
		rain3 = new JRadioButton("",true);
		rain4 = new JRadioButton("");
		rain5 = new JRadioButton("");
		wind1 = new JRadioButton("");
		wind2 = new JRadioButton("");
		wind3 = new JRadioButton("",true);
		wind4 = new JRadioButton("");
		wind5 = new JRadioButton("");
		sun1 = new JRadioButton("");
		sun2 = new JRadioButton("");
		sun3 = new JRadioButton("", true);
		sun4 = new JRadioButton("");
		sun5 = new JRadioButton("");
		pnlEvo = new Game_Evo();
		Speed = new ButtonGroup();
		Rain = new ButtonGroup();
		Wind = new ButtonGroup();
		Sun = new ButtonGroup();
		this.add(pnlEvo, BorderLayout.CENTER);
		pnlEvo.createStatusPanel();
		createSettingsPanel();
		//pack();
		this.setTitle("EVO");
		this.setSize(1020, 700);//pnlEvo.WIDTH, pnlEvo.HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		soundM = new SoundForest();
		soundF = new SoundFire();
		soundW = new SoundWind();
		soundR = new SoundRain();
		
		
		
		

	}
	private void createSettingsPanel()
	{
		Box = new JPanel();
		settingsPanel = new JPanel();
		speedPanel = new JPanel();
		rainPanel= new JPanel();
		windPanel = new JPanel();
		sunPanel = new JPanel();
		settingsPanel2 = new JPanel();
		statusPanel = new JPanel();
		Box.setLayout(new BoxLayout(Box, BoxLayout.PAGE_AXIS));
		//statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.PAGE_AXIS));
		settingsPanel2.setLayout(new BoxLayout(settingsPanel2, BoxLayout.PAGE_AXIS));
		top= new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.LINE_AXIS));
		speedPanel.setLayout(new FlowLayout());
		SpeedListener speedListener = new SpeedListener();
		RainListener rainListener = new RainListener();
		WindListener windListener = new WindListener();
		SunListener sunListener = new SunListener();
		speed1.addItemListener(speedListener);
		speed1.setBackground(Color.LIGHT_GRAY);
		speed2.addItemListener(speedListener);
		speed2.setBackground(Color.LIGHT_GRAY);
		speed3.addItemListener(speedListener);
		speed3.setBackground(Color.LIGHT_GRAY);
		speed4.addItemListener(speedListener);
		speed4.setBackground(Color.LIGHT_GRAY);
		speed5.addItemListener(speedListener);
		speed5.setBackground(Color.LIGHT_GRAY);
		rain1.addItemListener(rainListener);
		rain1.setBackground(Color.LIGHT_GRAY);
		rain2.addItemListener(rainListener);
		rain2.setBackground(Color.LIGHT_GRAY);
		rain3.addItemListener(rainListener);
		rain3.setBackground(Color.LIGHT_GRAY);
		rain4.addItemListener(rainListener);
		rain4.setBackground(Color.LIGHT_GRAY);
		rain5.addItemListener(rainListener);
		rain5.setBackground(Color.LIGHT_GRAY);
		wind1.addItemListener(windListener);
		wind1.setBackground(Color.LIGHT_GRAY);
		wind2.addItemListener(windListener);
		wind2.setBackground(Color.LIGHT_GRAY);
		wind3.addItemListener(windListener);
		wind3.setBackground(Color.LIGHT_GRAY);
		wind4.addItemListener(windListener);
		wind4.setBackground(Color.LIGHT_GRAY);
		wind5.addItemListener(windListener);
		wind5.setBackground(Color.LIGHT_GRAY);
		sun1.addItemListener(sunListener);
		sun1.setBackground(Color.LIGHT_GRAY);
		sun2.addItemListener(sunListener);
		sun2.setBackground(Color.LIGHT_GRAY);
		sun3.addItemListener(sunListener);
		sun3.setBackground(Color.LIGHT_GRAY);
		sun4.addItemListener(sunListener);
		sun4.setBackground(Color.LIGHT_GRAY);
		sun5.addItemListener(sunListener);
		sun5.setBackground(Color.LIGHT_GRAY);
		speed3.isSelected();
		rain3.isSelected();
		wind3.isSelected();
		sun3.isSelected();
		Speed.add(speed1);
		Speed.add(speed2);
		Speed.add(speed3);
		Speed.add(speed4);
		Speed.add(speed5);
		speedPanel.setBackground(Color.LIGHT_GRAY);
		speedPanel.add(SPEED_LABEL);
		speedPanel.add(speed1);
		speedPanel.add(speed2);
		speedPanel.add(speed3);
		speedPanel.add(speed4);
		speedPanel.add(speed5);		
		Rain.add(rain1);
		Rain.add(rain2);
		Rain.add(rain3);
		Rain.add(rain4);
		Rain.add(rain5);
		rainPanel.setBackground(Color.LIGHT_GRAY);
		rainPanel.add(RAIN_LABEL);
		rainPanel.add(rain1);
		rainPanel.add(rain2);
		rainPanel.add(rain3);
		rainPanel.add(rain4);
		rainPanel.add(rain5);
		Wind.add(wind1);
		Wind.add(wind2);
		Wind.add(wind3);
		Wind.add(wind4);
		Wind.add(wind5);
		windPanel.setBackground(Color.LIGHT_GRAY);
		windPanel.add(WIND_LABEL);
		windPanel.add(wind1);
		windPanel.add(wind2);
		windPanel.add(wind3);
		windPanel.add(wind4);
		windPanel.add(wind5);
		Sun.add(sun1);
		Sun.add(sun2);
		Sun.add(sun3);
		Sun.add(sun4);
		Sun.add(sun5);
		sunPanel.setBackground(Color.LIGHT_GRAY);
		sunPanel.add(SUN_LABEL);
		sunPanel.add(sun1);
		sunPanel.add(sun2);
		sunPanel.add(sun3);
		sunPanel.add(sun4);
		sunPanel.add(sun5);
		settingsPanel.setBackground(Color.LIGHT_GRAY);
		settingsPanel.add(start);
		settingsPanel.add(step);
		//settingsPanel.add(speedPanel, BorderLayout.CENTER);
		settingsPanel.setBorder(BorderFactory.createTitledBorder(" Game Settings"));
		settingsPanel2.setBorder(BorderFactory.createTitledBorder(" World Settings"));
		settingsPanel2.setBackground(Color.LIGHT_GRAY);
		settingsPanel2.add(rainPanel);
		settingsPanel2.add(windPanel);
		settingsPanel2.add(sunPanel);
		top.setBackground(Color.LIGHT_GRAY);
		Box.setBackground(Color.LIGHT_GRAY);
		Box.add(speedPanel);
		Box.add(settingsPanel);
		top.add(Box);
		top.add(settingsPanel2);
		statusPanel = pnlEvo.getStatusPanel();
		statusPanel.setBackground(Color.LIGHT_GRAY);
		top.add(statusPanel,BorderLayout.WEST);
		top.setBorder(BorderFactory.createTitledBorder("Settings:"));
		add(top, BorderLayout.NORTH);
		
		
	}
	
	
	public void setTimeOn(boolean status)
	{
		timeOn=status;
		
	}
	public boolean getTimeOn()
	{
		return timeOn;
	}
	
	
private class StartButtonListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		
		if (timeOn==false) 
		{
			soundM.startSound();
			if (sun4.isSelected() || sun5.isSelected())
				soundF.startSound();
			
			if (rain4.isSelected() || rain5.isSelected())
				soundR.startSound();
			
			if (wind4.isSelected() || wind5.isSelected())
				soundW.startSound();
			pnlEvo.startTimer();
			setTimeOn(true);
		}
		else if (timeOn==true)
		{
			soundM.stopSound();
			soundF.stopSound();
			soundW.stopSound();
			soundR.stopSound();
			pnlEvo.stopTimer();
			
			pnlEvo.stopTimer();
			setTimeOn(false);
		}
	}
	
}
private class StepButtonListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		pnlEvo.DePopulate();
	}
}
private class SpeedListener implements ItemListener
{
	public void itemStateChanged(ItemEvent event)
	{
		
		if (speed1.isSelected())
		{
			pnlEvo.timer.setDelay(2000);
		}
		else if (speed2.isSelected())
		{
			pnlEvo.timer.setDelay(1000);
		}
		else if (speed3.isSelected())
		{
			pnlEvo.timer.setDelay(500);
		}
		else if (speed4.isSelected())
		{
			pnlEvo.timer.setDelay(250);
		}
		else if (speed5.isSelected())
		{
			pnlEvo.timer.setDelay(100);
		}

	}
}
private class RainListener implements ItemListener
{
	public void itemStateChanged(ItemEvent event)
	{
		
		if (rain1.isSelected())
		{
			if(soundR.isRunning())
			{
				soundR.stopSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()-10);
			pnlEvo.world.setSporeDistance(pnlEvo.world.getSporeDistance()-0);
			pnlEvo.world.setGrowChance(pnlEvo.world.getGrowChance()-20);
		}
		else if (rain2.isSelected())
		{
			if(soundR.isRunning())
			{
				soundR.stopSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()-5);
			pnlEvo.world.setSporeDistance(pnlEvo.world.getSporeDistance()-5);
			pnlEvo.world.setGrowChance(pnlEvo.world.getGrowChance()-10);
			
		}
		else if (rain3.isSelected())
		{
			if(soundR.isRunning())
			{
				soundR.stopSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()-0);
			pnlEvo.world.setSporeDistance(pnlEvo.world.getSporeDistance()-10);
			pnlEvo.world.setGrowChance(pnlEvo.world.getGrowChance()-0);
		}
		else if (rain4.isSelected())
		{
			if(!soundR.isRunning())
			{
				soundR.startSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()+5);
			pnlEvo.world.setSporeDistance(pnlEvo.world.getSporeDistance()-20);
			pnlEvo.world.setGrowChance(pnlEvo.world.getGrowChance()-5);
		}
		else if (rain5.isSelected())
		{
			if(!soundR.isRunning())
			{
				soundR.startSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()+10);
			pnlEvo.world.setSporeDistance(pnlEvo.world.getSporeDistance()-25);
			pnlEvo.world.setGrowChance(pnlEvo.world.getGrowChance()-20);
		}

	}
}
private class WindListener implements ItemListener
{
	public void itemStateChanged(ItemEvent event)
	{
		
		if (wind1.isSelected())
		{
			if(soundW.isRunning())
			{
				soundW.stopSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()-10);
			pnlEvo.world.setSporeDistance(pnlEvo.world.getSporeDistance()-5);
		}
		else if(wind2.isSelected())
		{
			if(soundW.isRunning())
			{
				soundW.stopSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()-5);
			pnlEvo.world.setSporeDistance(pnlEvo.world.getSporeDistance()-0);
		}
		else if(wind3.isSelected())
		{
			if(soundW.isRunning())
			{
				soundW.stopSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()-0);
			pnlEvo.world.setSporeDistance(pnlEvo.world.getSporeDistance()+10);
		}
		else if(wind4.isSelected())
		{
			if(!soundW.isRunning())
			{
				soundW.startSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()-0);
			pnlEvo.world.setSporeDistance(pnlEvo.world.getSporeDistance()+20);
		}
		else if(wind5.isSelected())
		{
			if(!soundW.isRunning())
			{
				soundW.startSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()-0);
			pnlEvo.world.setSporeDistance(pnlEvo.world.getSporeDistance()+25);
		}
		
	}
}
private class SunListener implements ItemListener
{
	public void itemStateChanged(ItemEvent event)
	{
		if(sun1.isSelected())
		{
			if(soundF.isRunning())
			{
				soundF.stopSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()-10);
			pnlEvo.world.setGrowChance(pnlEvo.world.getGrowChance()-20);
		}
		else if(sun2.isSelected())
		{
			if(soundF.isRunning())
			{
				soundF.stopSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()-5);
			pnlEvo.world.setGrowChance(pnlEvo.world.getGrowChance()-10);
		}
		else if(sun3.isSelected())
		{
			if(soundF.isRunning())
			{
				soundF.stopSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()-0);
			pnlEvo.world.setGrowChance(pnlEvo.world.getGrowChance()-0);
		}
		else if(sun4.isSelected())
		{
			if(!soundF.isRunning())
			{
				soundF.startSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()-5);
			pnlEvo.world.setGrowChance(pnlEvo.world.getGrowChance()+10);
		}
		else if(sun5.isSelected())
		{
			if(!soundF.isRunning())
			{
				soundF.startSound();
			}
			pnlEvo.world.setSporesReleased(pnlEvo.world.getSporesReleased()-10);
			pnlEvo.world.setGrowChance(pnlEvo.world.getGrowChance()-10);
		}
	}

}
	
	public static void main(String[] args) {
		Frame_Evo frame = new Frame_Evo();		
	}

}
