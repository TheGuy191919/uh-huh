/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.uhhuh.gui;

import io.github.theguy191919.uhhuh.Uhhuh;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.*;

/**
 * This is the basic chat gui where rooms can be created. Called by main class
 *
 * @author evan__000
 */
public class GUIChat implements Runnable {

    public String userName = String.valueOf((int) (Math.random() * 10000));
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
    private Map<JPanel, GUIPaneTab> mapOfTabs = new ConcurrentHashMap();
    private GUIStart start;

    public GUIChat() {

        this.initFrame();

        GUIRoom room = new GUIRoom("Death", this, "234.235.236.237");
        //jFrame.setVisible(true);
    }

    public void initFrame() {
        jFrame = new JFrame("Uh Huh");
        jFrame.setSize(800, 600);
        layout = new GroupLayout(jFrame.getContentPane());
        jFrame.getContentPane().setLayout(layout);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        start = new GUIStart(this);
    }

    public JFrame getFrame() {
        return this.jFrame;
    }

    public void createTab(GUIPaneTab tab) {
        this.mapOfTabs.put(tab.getTab(), tab);
        this.jTabbedPane.addTab(tab.getName(), tab.getTab());
        tab.tabAdded();
    }

    public GUIPaneTab getTab(String name) {
        return this.mapOfTabs.get((JPanel) this.jTabbedPane.getComponentAt(this.jTabbedPane.indexOfTab(name)));
    }

    public List<GUIPaneTab> getListOfTabs() {
        List<GUIPaneTab> listOfTab = new LinkedList<>(this.mapOfTabs.values());
        return listOfTab;
    }

    public void removeCurrentTab() {
        GUIPaneTab tab = this.mapOfTabs.get((JPanel) this.jTabbedPane.getComponentAt(this.jTabbedPane.getSelectedIndex()));
        tab.tabRemoved();
    }

    public void removeTab(GUIPaneTab tab) {
        this.jTabbedPane.remove(tab.getTab());
        this.mapOfTabs.remove(tab.getTab());
        tab.tabRemoved();
    }

    public void removeTab(String name) {
        GUIPaneTab tab = this.mapOfTabs.remove((JPanel) this.jTabbedPane.getComponentAt(this.jTabbedPane.indexOfTab(name)));
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
        GUIPaneTab tab = new GUIRoom(roomName, Uhhuh.guiChat, ip);
    }

}
