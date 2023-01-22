package jp.ac.uryukyu.ie.e225703;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardView extends JFrame{
    public BoardView() {
        
        // ウィンドウの位置とサイズを指定
        setBounds(350, 100, 700, 700);
        // ウィンドウタイトル
        setTitle("オセロ");
        // レイアウトマネージャーの設定
        setLayout(new FlowLayout());
        // ウィンドウクローズでアプリケーションを終了する設定
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        
        ImageIcon imgOseroBoard = new ImageIcon("./app/image/OseroBoard.png");
        JLabel lblOseroBoard = new JLabel(imgOseroBoard);
        lblOseroBoard.setBounds(105, 50, 490,490);
        
        add(lblOseroBoard);


        
        // ボタンにアクションリスナーを追加
        //btnStart.addActionListener(controller);

        // ウィンドウの表示
        // setVisible(true);
    }
}
