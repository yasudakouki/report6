package jp.ac.uryukyu.ie.e225703;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartView extends JFrame{
    public StartView(String title) {
        // ウィンドウの位置とサイズを指定
        setBounds(350, 100, 700, 700);
        // ウィンドウタイトル
        setTitle(title);
        // レイアウトマネージャーの設定
        setLayout(new FlowLayout());
        // ウィンドウクローズでアプリケーションを終了する設定
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("オセロ");
        label.setBounds(300, 150, 600, 200);
        label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 30));
        
        // スタートボタン
        JButton btnStart = new JButton("START");
        btnStart.setBounds(300, 500, 100, 30);
        
        // フレームにコンポーネントを追加
        add(label);
        add(btnStart);

        
        // ボタンにアクションリスナーを追加
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // メッセージダイアログを表示
                JOptionPane.showMessageDialog(null, "ハロー");
            }
        });

        // ウィンドウの表示
        setVisible(true);
    }
}
