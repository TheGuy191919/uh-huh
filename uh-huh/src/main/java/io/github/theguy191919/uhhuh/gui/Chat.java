/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.gui;

import io.github.theguy191919.uhhuh.Uhhuh;
import io.github.theguy191919.uhhuh.console.commands.Command;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * This is the basic chat gui where rooms can be created. Called by main class
 *
 * @author evan__000
 */
public class Chat implements Runnable {

    private String userName = Uhhuh.options.getProperty("UserName");
    private boolean visiable = true;

    private GroupLayout layout;
    private JFrame jFrame;
    private JMenuBar jMenuBar;
    private JMenu jMenuUhhuh;
    private JMenuItem jMenuUhhuhAbout;
    private JMenuItem jMenuUhhuhCreateroom;
    private JMenuItem jMenuUhhuhJoinroom;
    private JMenuItem jMenuUhhuhOptions;
    private JMenu jMenuExit;
    private JMenuItem jMenuExitChat;
    private JMenuItem jMenuExitProgram;
    private JTabbedPane jTabbedPane;
    private Map<JPanel, PaneTab> mapOfTabs = new ConcurrentHashMap();
    private StartTab start;

    public Chat() {

        this.initFrame();

        //LanTab room = new LanTab("Death", this, "234.235.236.237");
        //jFrame.setVisible(true);
        //this.createRoom("Death");
    }

    public void initFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jFrame = new JFrame("Uh Huh");
        jFrame.setSize(800, 600);
        layout = new GroupLayout(jFrame.getContentPane());
        jFrame.getContentPane().setLayout(layout);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                Uhhuh.console.logger.print("Stoping");
                Command.getCommand("stop");
                //jFrame.dispose();
            }
        });

        layout.setAutoCreateContainerGaps(false);
        layout.setAutoCreateGaps(true);

        jMenuBar = new JMenuBar();
        jFrame.add(jMenuBar);
        //jMenuBar.setBounds(0, jFrame.getHeight(), jFrame.getWidth(), 100);
        jMenuUhhuh = new JMenu("Uh Huh");
        jMenuUhhuhAbout = new JMenuItem("About");
        jMenuUhhuhAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JDialog.setDefaultLookAndFeelDecorated(true);
                final JDialog dia = new JDialog(jFrame, "About", true);
                dia.getContentPane().setLayout(new BoxLayout(dia.getContentPane(), BoxLayout.PAGE_AXIS));
                dia.setSize(300, 130);
                dia.setLocation((jFrame.getWidth() - dia.getWidth()) / 2, (jFrame.getHeight() - dia.getHeight()) / 2);

                dia.add(Box.createRigidArea(new Dimension(10, 20)));

                JLabel label = new JLabel("This is made by TheGuy191919");
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                dia.getContentPane().add(label, BorderLayout.CENTER);

                dia.add(Box.createRigidArea(new Dimension(10, 20)));

                JButton button = new JButton("Close");
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dia.dispose();
                    }
                });
                dia.getContentPane().add(button, BorderLayout.CENTER);
                dia.setVisible(true);

            }
        });
        jMenuUhhuhCreateroom = new JMenuItem("Create Room");
        jMenuUhhuhCreateroom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JDialog dia = new JDialog(jFrame, "Create Room", true);
                dia.getContentPane().setLayout(new BoxLayout(dia.getContentPane(), BoxLayout.LINE_AXIS));
                dia.setSize(300, 130);
                dia.setLocation((jFrame.getWidth() - dia.getWidth()) / 2, (jFrame.getHeight() - dia.getHeight()) / 2);

                dia.add(Box.createRigidArea(new Dimension(10, 20)));

                JLabel label = new JLabel("Name: ");
                label.setAlignmentY(Component.CENTER_ALIGNMENT);
                dia.add(label);

                dia.add(Box.createRigidArea(new Dimension(10, 20)));

                final JTextField field = new JTextField();
                field.setMaximumSize(new Dimension(field.getMaximumSize().width, field.getPreferredSize().height));
                field.setAlignmentY(Component.CENTER_ALIGNMENT);
                dia.add(field);

                dia.add(Box.createRigidArea(new Dimension(10, 20)));

                JButton botton = new JButton("Create");
                botton.setAlignmentY(Component.CENTER_ALIGNMENT);
                botton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createRoom(field.getText());
                        dia.dispose();
                    }
                });
                dia.add(botton);

                dia.add(Box.createRigidArea(new Dimension(10, 20)));
                
                dia.setVisible(true);
            }
        });
        jMenuUhhuhJoinroom = new JMenuItem("Join Room");
        jMenuUhhuhJoinroom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JDialog dia = new JDialog(jFrame, "Join Room", true);
                dia.getContentPane().setLayout(new BoxLayout(dia.getContentPane(), BoxLayout.LINE_AXIS));
                dia.setSize(300, 130);
                dia.setLocation((jFrame.getWidth() - dia.getWidth()) / 2, (jFrame.getHeight() - dia.getHeight()) / 2);

                dia.add(Box.createRigidArea(new Dimension(10, 20)));

                JLabel label = new JLabel("Name: ");
                label.setAlignmentY(Component.CENTER_ALIGNMENT);
                dia.add(label);

                dia.add(Box.createRigidArea(new Dimension(10, 20)));

                final JTextField field = new JTextField();
                field.setMaximumSize(new Dimension(field.getMaximumSize().width, field.getPreferredSize().height));
                field.setAlignmentY(Component.CENTER_ALIGNMENT);
                dia.add(field);

                dia.add(Box.createRigidArea(new Dimension(10, 20)));

                JButton botton = new JButton("Create");
                botton.setAlignmentY(Component.CENTER_ALIGNMENT);
                botton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createRoom(field.getText());
                        dia.dispose();
                    }
                });
                dia.add(botton);

                dia.add(Box.createRigidArea(new Dimension(10, 20)));
                
                dia.setVisible(true);
            }
        });
        jMenuUhhuhOptions = new JMenuItem("Options");
        jMenuUhhuh.add(jMenuUhhuhAbout);
        jMenuUhhuh.addSeparator();
        jMenuUhhuh.add(jMenuUhhuhCreateroom);
        jMenuUhhuh.add(jMenuUhhuhJoinroom);
        jMenuUhhuh.addSeparator();
        jMenuUhhuh.add(jMenuUhhuhOptions);
        jMenuBar.add(jMenuUhhuh);
        jMenuExit = new JMenu("Exit");
        jMenuExitChat = new JMenuItem("Exit This Chat");
        jMenuExit.add(jMenuExitChat);
        jMenuExitProgram = new JMenuItem("Exit Uh huh");
        jMenuExit.add(jMenuExitProgram);
        jMenuBar.add(jMenuExit);

        jTabbedPane = new JTabbedPane();
        jFrame.add(jTabbedPane);
        //jTabbedPane.setBounds(0, jFrame.getHeight() - 100, jFrame.getWidth(), jFrame.getHeight() - 100);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jMenuBar, 0, jFrame.getWidth(), Short.MAX_VALUE)
                        .addComponent(jTabbedPane, 0, jFrame.getWidth(), Short.MAX_VALUE)
                )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addComponent(jMenuBar, 30, 30, 30)
                .addComponent(jTabbedPane, 0, jFrame.getHeight() - 80, Short.MAX_VALUE)
        );

        start = new StartTab(this);
    }

    public JFrame getFrame() {
        return this.jFrame;
    }

    public void createTab(PaneTab tab) {
        this.mapOfTabs.put(tab.getTab(), tab);
        this.jTabbedPane.addTab(tab.getName(), tab.getTab());
        tab.tabAdded();
    }

    public PaneTab getTab(String name) {
        return this.mapOfTabs.get((JPanel) this.jTabbedPane.getComponentAt(this.jTabbedPane.indexOfTab(name)));
    }

    public List<PaneTab> getListOfTabs() {
        List<PaneTab> listOfTab = new LinkedList<>(this.mapOfTabs.values());
        return listOfTab;
    }

    public void removeCurrentTab() {
        PaneTab tab = this.mapOfTabs.get((JPanel) this.jTabbedPane.getComponentAt(this.jTabbedPane.getSelectedIndex()));
        tab.tabRemoved();
    }

    public void removeTab(PaneTab tab) {
        this.jTabbedPane.remove(tab.getTab());
        this.mapOfTabs.remove(tab.getTab());
        tab.tabRemoved();
    }

    public void removeTab(String name) {
        PaneTab tab = this.mapOfTabs.remove((JPanel) this.jTabbedPane.getComponentAt(this.jTabbedPane.indexOfTab(name)));
        tab.tabRemoved();
    }

    @Override
    public void run() {
        this.jFrame.setVisible(true);
    }

    public void createRoom(String roomName) {
        String ip = "234.235.236.";
        int ipSubAddress = 0;
        for (byte number : roomName.getBytes()) {
            ipSubAddress = ipSubAddress + number;
        }
        ipSubAddress = ipSubAddress / roomName.getBytes().length;
        ipSubAddress = Math.abs(ipSubAddress);
        ip = ip + ipSubAddress;
        PaneTab tab = new LanTab(roomName, Uhhuh.guiChat, ip);
    }
    
    public void stop(){
        this.jFrame.dispose();
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
        Uhhuh.options.setProperty("UserName", userName);
    }
    
}
