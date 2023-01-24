package jp.ac.uryukyu.ie.e225703;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * スタート画面のウィンドウ(GUI)を表示するクラス(未実装)。
 */
public class StartView extends JFrame{
    
    private StartModel model;
    private StartController controller;
    
    public StartView() {
        model = new StartModel();
        controller = new StartController(this, model);
        
        // ウィンドウの位置とサイズを指定
        setBounds(350, 100, 700, 700);
        // ウィンドウタイトル
        setTitle("オセロ");
        // レイアウトマネージャーの設定
        setLayout(new FlowLayout());
        // ウィンドウクローズでアプリケーションを終了する設定
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("オセロ");
        label.setBounds(275, 150, 600, 200);
        label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
        
        // スタートボタン
        JButton btnStart = new JButton("START");
        btnStart.setBounds(300, 500, 100, 30);
        
        // フレームにコンポーネントを追加
        add(label);
        add(btnStart);

        
        // ボタンにアクションリスナーを追加
        btnStart.addActionListener(controller);

        // ウィンドウの表示
        setVisible(true);
    }
}
