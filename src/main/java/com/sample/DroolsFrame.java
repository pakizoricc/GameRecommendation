package com.sample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsFrame implements ActionListener {	 
	JTextField vOutput = new JTextField();
    JTextField vcategory = new JTextField();
    JTextField vsection = new JTextField();
    JTextField vplayersupport = new JTextField();
    JTextField vpaidgame = new JTextField();
    JTextField vtopseller = new JTextField();
    JTextField vdiscount = new JTextField();
    JTextField vmostplayed = new JTextField();
    JTextField vnameofgame = new JTextField();
    static JButton Button = new JButton("SEARCH");
    JFrame frame;
    
    public void createUI(){    	
    	 
         frame = new JFrame("PCGameRoom");
         frame.getContentPane().setLayout(null);
         frame.setSize(1022,751);
         frame.setVisible(true);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         JLabel lab2 = new JLabel("Category "); 
         lab2.setHorizontalAlignment(SwingConstants.CENTER);
         lab2.setFont(new Font("Tahoma", Font.BOLD, 22));
         JLabel lab3 = new JLabel("Section");   
         lab3.setHorizontalAlignment(SwingConstants.CENTER);
         lab3.setFont(new Font("Tahoma", Font.BOLD, 22));
         JLabel lab4 = new JLabel("Player support ");            
         lab4.setHorizontalAlignment(SwingConstants.CENTER);
         lab4.setFont(new Font("Tahoma", Font.BOLD, 22));
         JLabel lab5 = new JLabel("Paid game?");
         lab5.setHorizontalAlignment(SwingConstants.CENTER);
         lab5.setFont(new Font("Tahoma", Font.BOLD, 22));
         JLabel lab6 = new JLabel("Top seller?");
         lab6.setHorizontalAlignment(SwingConstants.CENTER);
         lab6.setFont(new Font("Tahoma", Font.BOLD, 22));
         JLabel lab7 = new JLabel("Discount?");
         lab7.setHorizontalAlignment(SwingConstants.CENTER);
         lab7.setFont(new Font("Tahoma", Font.BOLD, 22));
         JLabel lab8 = new JLabel("Most played?");
         lab8.setHorizontalAlignment(SwingConstants.CENTER);
         lab8.setFont(new Font("Tahoma", Font.BOLD, 22));
         JLabel vOutput = new JLabel("Output:");
         vOutput.setHorizontalAlignment(SwingConstants.CENTER);
         vOutput.setFont(new Font("Tahoma", Font.BOLD, 22));        

         lab2.setBounds(34,120,150,30);
         vcategory.setFont(new Font("Tahoma", Font.PLAIN, 22));
         vcategory.setBounds(203,112,294,47);         

         lab3.setBounds(34,201,150,30);
         vsection.setFont(new Font("Tahoma", Font.PLAIN, 22));
         vsection.setBounds(203,192,294,47); 
        
         lab4.setBounds(12,282,201,29);
         vplayersupport.setFont(new Font("Tahoma", Font.PLAIN, 22));
         vplayersupport.setBounds(203,272,294,47);
         
         lab5.setBounds(12,363,201,29);
         vpaidgame.setFont(new Font("Tahoma", Font.PLAIN, 22));
         vpaidgame.setBounds(203,352,294,47);
         
         lab6.setBounds(12,444,201,29);
         vtopseller.setFont(new Font("Tahoma", Font.PLAIN, 22));
         vtopseller.setBounds(203,432,294,47);
         
         lab7.setBounds(12,525,201,29);
         vdiscount.setFont(new Font("Tahoma", Font.PLAIN, 22));
         vdiscount.setBounds(203,512,294,47);
         
         lab8.setBounds(12,606,201,29);
         vmostplayed.setFont(new Font("Tahoma", Font.PLAIN, 22));
         vmostplayed.setBounds(203,592,294,47);
         
         vOutput.setBounds(663, 41, 201, 29);
         vnameofgame.setBounds(571, 94, 385, 316);
    	 vnameofgame.setColumns(10);
                  
         Button.setFont(new Font("Tahoma", Font.BOLD, 22));
         Button.setBounds(660, 484, 201, 47);
         Button.addActionListener(this);

         frame.getContentPane().add(lab2);        
         frame.getContentPane().add(vcategory);

         frame.getContentPane().add(lab3);
         frame.getContentPane().add(vsection);
        
         frame.getContentPane().add(lab4);
         frame.getContentPane().add(vplayersupport);
         
         frame.getContentPane().add(lab5);
         frame.getContentPane().add(vpaidgame);
         
         frame.getContentPane().add(lab6);
         frame.getContentPane().add(vtopseller);
         
         frame.getContentPane().add(lab7);
         frame.getContentPane().add(vdiscount);
         
         frame.getContentPane().add(lab8);
         frame.getContentPane().add(vmostplayed);
         
         frame.getContentPane().add(vOutput);
         frame.getContentPane().add(vnameofgame);   
         
         frame.getContentPane().add(Button);
         Button.addActionListener(this);
    }// createUI() method closed

    public void actionPerformed(ActionEvent e) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        JButton newButton = (JButton) e.getSource();

        VideoGames vg = new VideoGames();
        vg.setCategory(vcategory.getText());
        vg.setSection(vsection.getText());
        vg.setPlayersupport(vplayersupport.getText());
        vg.setPaidgame(vpaidgame.getText());
        vg.setTopseller(vtopseller.getText());
        vg.setDiscount(vdiscount.getText());
        vg.setMostplayed(vmostplayed.getText());
        vg.setNameofgame(vnameofgame.getText());

        kSession.insert(vg);
        kSession.fireAllRules();

        if (newButton.equals(Button)) {
            // check if nameofgame is not empty, if not print the result
            if (vg.getNameofgame() != null && !vg.getNameofgame().isEmpty()) {
                vnameofgame.setText("Igrica koju smo preporucili: " + vg.getNameofgame());
            } else {
                vnameofgame.setText("Nismo uspeli da preporucimo igricu za Vas");
            }
        }
    }
	/**
	 * Launch the application.
	 */	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DroolsFrame frame = new DroolsFrame();
                    frame.createUI();
                    Button.addActionListener(frame);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
