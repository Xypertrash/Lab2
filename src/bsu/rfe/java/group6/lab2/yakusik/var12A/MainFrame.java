package bsu.rfe.java.group6.lab2.yakusik.var12A;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")

public class MainFrame  extends JFrame{
	private static final int WIDTH = 400;
	private static final int HEIGHT = 320;
	
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldZ;
	
	private JTextField textFieldResult;
	
	private ButtonGroup radioButtons = new ButtonGroup();
	
	private Box hboxFormulaType = Box.createHorizontalBox();
	private int formulaId = 1;
	
	public double calculate1(double x, double y, double z) {
		return Math.pow( (Math.cos(Math.exp(x))+Math.log((1+y)*(1+y))+ Math.sqrt(Math.exp(Math.cos(x)) + Math.sin(Math.PI*z)*Math.sin(Math.PI*z)) + Math.sqrt(1/x) + Math.cos(y*y)), Math.sin(z));
	}
	
	public double calculate2(double x, double y, double z) {
		return (1+Math.sqrt(z*x))/(Math.pow((1+x*x*x), y));
	}
	
	private void addRadioButton(String buttonName, final int formulaId) {
		JRadioButton button = new JRadioButton(buttonName);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				MainFrame.this.formulaId = formulaId;
				imagePane.updateUI();
			}
		});
		radioButtons.add(button); 
		hboxFormulaType.add(button);
	}
	public MainFrame() {
		super("Вычисление формулы");
		setSize(WIDTH,HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH)/2,
		(kit.getScreenSize().height - HEIGHT)/2);
		hboxFormulaType.add(Box.createHorizontalGlue());
		addRadioButton("Формула 1", 1);
		addRadioButton("Формула 2", 2);
		radioButtons.setSelected(
				radioButtons.getElements().nextElement().getModel(), true);
		hboxFormulaType.add(Box.createHorizontalGlue());
		hboxFormulaType.setBorder(
				BorderFactory.createLineBorder(Color.YELLOW));
		JLabel labelForX = new JLabel("X: ");
		textFieldX = new JTextField("0", 10);
		textFieldX.setMaximumSize(textFieldX.getPreferredSize());
		JLabel labelForY = new JLabel("Y: ");
		textFieldY = new JTextField("0", 10);
		textFieldY.setMaximumSize(textFieldY.getPreferredSize());
		JLabel labelForZ = new JLabel("Z: ");
		textFieldZ  = new JTextField("0", 10);
		textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
		Box hboxVariables = Box.createHorizontalBox();
		hboxVariables.setBorder(
				BorderFactory.createLineBorder(Color.RED));
		//Creating fields for X
		hboxVariables.add(Box.createHorizontalGlue());
		hboxVariables.add(labelForX);
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(textFieldX);
		//Creating fields for Y
		hboxVariables.add(Box.createHorizontalStrut(100));
		hboxVariables.add(labelForY);
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(textFieldY);
		hboxVariables.add(Box.createHorizontalGlue());
		//Creating fields for Z
		hboxVariables.add(Box.createHorizontalStrut(100));
		hboxVariables.add(Box.createHorizontalGlue());
		hboxVariables.add(labelForZ);
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(textFieldZ);
		
		JLabel labelForResult = new JLabel("Результат: ");
		textFieldResult = new JTextField("0", 10);
		textFieldResult.setMaximumSize(
				textFieldResult.getPreferredSize());
		Box hboxResult = Box.createHorizontalBox();
		hboxResult.add(Box.createHorizontalGlue());
		hboxResult.add(labelForResult);
		hboxResult.add(Box.createHorizontalStrut(10));
		hboxResult.add(textFieldResult);
		hboxResult.add(Box.createHorizontalGlue());
		hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		JButton buttonCalc = new JButton ("Вычислить");
		buttonCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					double x = Double.parseDouble(textFieldX.getText());
					double y = Double.parseDouble(textFieldY.getText());
					double z = Double.parseDouble(textFieldZ.getText());
					double result;
					if (formulaId == 1)
						result = calculate1(x,y,z);
					else 
						result = calculate2(x,y,z);
					textFieldResult.setText(String.valueOf(result));
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		JButton buttonReset = new JButton ("Очистить поля");
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				textFieldX.setText("0");
				textFieldY.setText("0");
				textFieldZ.setText("0");
				textFieldResult.setText("0");
			}
		});
		Box hboxButtons = Box.createHorizontalBox();
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.add(buttonCalc);
		hboxButtons.add(Box.createHorizontalStrut(30));
		hboxButtons.add(buttonReset); // RESET BUTTON
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.setBorder(
				BorderFactory.createLineBorder(Color.GREEN));
		Box contentBox = Box.createVerticalBox();
		contentBox.add(hboxFormulaType);
		contentBox.add(hboxVariables);
		contentBox.add(hboxResult);
		contentBox.add(hboxButtons);
		contentBox.add(Box.createVerticalGlue());
		getContentPane().add(contentBox, BorderLayout.CENTER);		
	}
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
