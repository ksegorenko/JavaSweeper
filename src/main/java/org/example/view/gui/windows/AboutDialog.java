package org.example.view.gui.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutDialog extends JDialog{
    private static String info;

    public AboutDialog(JFrame owner) {
        super(owner, "About", true);
        generateInfo();
        add(new JLabel(info), BorderLayout.CENTER);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel panel = new JPanel();
        panel.add(okButton);
        add(panel, BorderLayout.SOUTH);
        setSize(360, 400);
        setLocationRelativeTo(null);
    }

    private static void generateInfo() {
        info = """
               <html>
               <h2>JavaSweeper 2023</h2>
               <h3>
                    Instruction:
                    To choose Gui or Tui interface you need to enter 1 or 2 in console.
                    1 - to choose GUI
                    2 - to choose TUI
                    Your aim is to open boxes that do not consist bomb inside.
                    The number in box show how many bombs are around this box.
                    You may flag boxes that are potential bombs.
                    If you open a box with bomb you will die(lose).
               </h3>
               <h3>
                    Click LMB to flag box.
                    Click RMB to open box.
               </h3>
               <h3> Good luck!!! </h3>
               </html>
               """;
    }
}
