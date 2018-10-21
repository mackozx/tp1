package blackjack.app.cards;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BFrame extends JFrame implements ActionListener{
	
	private Blackjack blackjack;
	
	private JPanel panel;
	private JPanel panelb;
	private JButton hitbutton;
	private JButton standbutton;
	private JLabel pscore;
	private JLabel gamestatus;
	private JPanel cardPanel;
	private JPanel dealerCardPanel;
	private boolean gameEnded;
	
	public BFrame(Blackjack b) {
		setTitle("Blackjack");
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		blackjack = b;
		buildComponents();
		gameEnded = false;
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(hitbutton)) {
			if(!gameEnded) {
				blackjack.hit();
				addCards("p");
				addCards("d");
				setScores();
				checkScore();
			} else {
				gameEnded = false;
				blackjack.reset();
				hitbutton.setText("Hit");
				standbutton.setEnabled(true);
				
				setScores();
				addCards("p");
				addCards("d");
				
				gamestatus.setText("");
			}
			
		} else if (ae.getSource().equals(standbutton)) {
			while(blackjack.dealer.score < 17) {
				blackjack.dealer.hit();
			}
			addCards("p");
			addCards("d");
			setScores();
			if(blackjack.getScore() < blackjack.dealer.score && blackjack.dealer.score <= 21) {
				gameEnd("L");
			} else if (blackjack.getScore() > blackjack.dealer.score && blackjack.dealer.score <= 21) {
				gameEnd("W");
			} else if (blackjack.dealer.score > 21) {
				gameEnd("W");
			} else if (blackjack.getScore() == blackjack.dealer.score) {
				gameEnd("D");
			} else {
				gameEnd("W");
			}
		}
		repaint();
	}
	
	private void buildComponents() {
		panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panelb = new JPanel();
		panelb.setBackground(Color.BLUE);
		hitbutton = new JButton("Hit");
		standbutton = new JButton("Stand");
		pscore = new JLabel("pscore");
		gamestatus = new JLabel("gamestatus");
		cardPanel = new JPanel();
		dealerCardPanel = new JPanel();
		cardPanel.setBackground(Color.BLUE);
		dealerCardPanel.setBackground(Color.BLUE);
		
		
		hitbutton.addActionListener(this);
		standbutton.addActionListener(this);
		hitbutton.setBackground(Color.GREEN);
		standbutton.setBackground(Color.GREEN);
		
		setLayout(new BorderLayout());
		panel.setLayout(new GridLayout(1, 2, 20, 20));
		panel.add(cardPanel);
		panel.add(dealerCardPanel);
		add(panel, BorderLayout.CENTER);
		panelb.setLayout(new GridLayout(1, 4, 10, 10));
		panelb.add(hitbutton);
		panelb.add(standbutton);
		panelb.add(pscore);
		panelb.add(gamestatus);
		add(panelb, BorderLayout.SOUTH);
		
		setScores();
		addCards("p");
		addCards("d");
	}
	
	private void setScores() {
		pscore.setText("Score : " + blackjack.getScore());
		gamestatus.setText("");
	}
	
	public void addCards(String s) {
		if(s == "p") {
			cardPanel.removeAll();
			cardPanel.repaint();
			cardPanel.setLayout(new GridLayout(blackjack.getHandSize() , 1, 10, 10));
			JPanel[] cards = new JPanel[blackjack.getHandSize()];
			Card[] handinfo = blackjack.handToArray();
			for(int i = 0; i < blackjack.getHandSize(); i++) {
				cards[i] = new JPanel();
				JLabel cardlabel = new JLabel(handinfo[i].getInfo());
				if(handinfo[i].getColor() < 2) {
					cardlabel.setForeground(Color.RED);
				}
				cards[i].add(cardlabel);
				cards[i].setBackground(Color.WHITE);
				cardPanel.add(cards[i]);
			}
		} else if(s == "d") {
			dealerCardPanel.removeAll();
			dealerCardPanel.repaint();
			dealerCardPanel.setLayout(new GridLayout(blackjack.dealer.getHandSize() , 1, 10, 10));
			JPanel[] cards = new JPanel[blackjack.dealer.getHandSize()];
			Card[] handinfo = blackjack.dealer.handToArray();
			if(gameEnded) {
				cards[0] = new JPanel();
				cards[0].setBackground(Color.GRAY);
				cards[0].add(new JLabel(handinfo[0].getInfo()));
			} else {
				cards[0] = new JPanel();
				cards[0].setBackground(Color.GRAY);
			}
			dealerCardPanel.add(cards[0]);
			for(int i = 1; i < blackjack.dealer.getHandSize(); i++) {
				cards[i] = new JPanel();
				JLabel cardlabel = new JLabel(handinfo[i].getInfo());
				if(handinfo[i].getColor() < 2) {
					cardlabel.setForeground(Color.RED);
				}
				cards[i].add(cardlabel);
				cards[i].setBackground(Color.WHITE);
				dealerCardPanel.add(cards[i]);
			}
		}
		dealerCardPanel.repaint();
	}
	
	public void checkScore() {
		if(blackjack.getScore() == 21 && blackjack.dealer.score != 21) {
			gameEnd("W");
		} else if(blackjack.getScore() == 21 && blackjack.dealer.score == 21) {
			gameEnd("D");
		} else if(blackjack.getScore() > 21 && blackjack.dealer.score <= 21) {
			gameEnd("L");
		} else if(blackjack.getScore() > 21 && blackjack.dealer.score > 21) {
			gameEnd("D");
		}
	}
	
	public void gameEnd(String s) {
		gameEnded = true;
		standbutton.setEnabled(false);
		hitbutton.setText("Reset");
		addCards("p");
		addCards("d");
		if(s.equals("W")) {
			gamestatus.setForeground(Color.GREEN);
			gamestatus.setText("Game Won");
		} else if(s.equals("L")) {
			gamestatus.setForeground(Color.RED);
			gamestatus.setText("Game Lost");
		} else if(s.equals("D")) {
			gamestatus.setForeground(Color.BLACK);
			gamestatus.setText("Draw");
		}
	}
}
