package jp.ac.uryukyu.ie.e225703;

/**
 * 定数を保存しておくためのクラス。
 * 継承(implements)することで定数宣言せずとも仕様可能。
 */
public interface CommonConst {
    /**
     * オセロウィンドウを表示させた時のボタンに表示される文字
     */
    final static String BTN_START = "START";
    /**
     * 黒の駒
     */
    final static String BLACK = "⚫️";
    /**
     * 白の駒
     */
    final static String WHITE = "⚪️";
    /**
     * 何も置いてないマス
     */
    final static String EMPTY = "🟩";
    /**
     * ボードのサイズ
     */
    final static int BOARD_SIZE = 8;
}
