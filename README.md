# JSP/Servletで作成するTODOリスト

## 作成のポイント

### 仕様について
仕様については、PHPのTODOリストに準じます。
こちら
https://github.com/miraino-katachi/todo_lists
の
- doc/01.仕様書/TODOリスト仕様書.pdf
- doc/02.テーブル設計書/テーブル定義書.pdf

をご参照ください。

### サンプルのソースではデータベースにMySQLを使っています

書籍「スッキリわかるサーブレット＆JSP」と同じように、H2データベースを使っていただいて構いません。

#### MySQLを使うには、
1. XAMPP（Windows）またはMAMP（macOS）をインストールしてください。
2. JavaからMySQLに接続するには、mysql-connector-java-5.1.48.jarが必要です。
WebContent/WEB-INF/lib/
に配置します。

### JSTLを使っています
1. JSPでの表示のために、JSTLを使っています。

### Javaのクラスの役割をわかりやすくするため、パッケージをうまく使う。
1. 下記のパッケージに分けています。
   - dao      Databaseに接続してクエリを実行し、結果を返却するクラス
   - filter   Servletの処理に割り込んで処理を行うクラス
   - logic    DAOのラッパーのクラス
   - model    テーブルのレコードを扱うためのクラス
   - servlet  クライアントからのリクエストを受けてレスポンスを返却するクラス

### TODOリストの作成順序は、こんな感じで（一例）
1. フォームの作成
   - JSPを作成する
   - ServletのdoGet()メソッドでJSPを表示できるようにする。
2. Modelの作成
3. レコードをインサートできるようにする
   - DAOにレコードをインサートするメソッドを作る。
   - LogicにDAOのインサートするメソッドを呼び出すためのメソッドを作る。
   - ServletのdoPost()メソッドで、フォームから受け取った値をテーブルにインサートできるようにする。
   - インサートに成功しているかどうかは、phpMyAdminで確認する。
4. 一覧表示できるようにする
   - DAOにレコードの一覧を取得するメソッドを作る。
   - Logicにレコードの一覧を取得するメソッドを呼び出すメソッドを作る。
   - ServletのdoGet()メソッドで、Logicのレコードを一覧するメソッドを呼び出し、JSPに表示できるようにする。
5. 完了・未完了の切り替え、削除できるようにする。
   - レコード更新用のServletを作る。
   - DAOにレコード更新のメソッドを作る。
   - Logicにレコード更新のメソッドを呼び出すメソッドを作る。
   - ServletのdoPost()メソッドで、Logicのレコード更新のメソッドを呼び出して、一覧表示のJSPにリダイレクトする。
6. 表示の調整
   - 「完了」になっているTODO項目に打ち消し2重線を入れる。

### 難しいと思うところは、サンプルのソースを参考にする
1. 他の人が作成したソースコードを参考にすることは、決して悪いことではありません。理解を深めて、ご自身のスキルアップにつなげてください。
