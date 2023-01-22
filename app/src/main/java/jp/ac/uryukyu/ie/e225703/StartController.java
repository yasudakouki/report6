package jp.ac.uryukyu.ie.e225703;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartController implements ActionListener, CommonConst{
    StartView view;
    StartModel model;
    

    public StartController(StartView _view, StartModel _model) {
        super();
        this.view = _view;
        this.model = _model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //JOptionPane.showMessageDialog(null, "ハロー");
        if (BTN_START.equals(e.getActionCommand())) {
            view.setVisible(false);
            JFrame boardFrame = new BoardView();
            boardFrame.setVisible(true);
            
        }
    }
}

