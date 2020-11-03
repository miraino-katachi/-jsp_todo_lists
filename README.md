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

### データベースにMySQLを使っています

MySQLを使うには、
1. XAMPP（Windows）またはMAMP（macOS）をインストールしてください。
2. JavaからMySQLに接続するには、mysql-connector-java-5.1.48.jarが必要です。
WebContent/WEB-INF/lib/
に配置します。

### SSTLを使っています
1. JSPでの表示のために、JSTLを使っています。

### Javaのクラスの役割をわかりやすくするため、パッケージをうまく使う。
1. 下記のパッケージに分けています。
   - dao      Databaseに接続してクエリを実行し、結果を返却するクラス
   - filter   Servletの処理に割り込んで処理を行うクラス
   - logic    DAOのラッパーのクラス
   - model    テーブルのレコードを扱うためのクラス
   - servlet  クライアントからのリクエストを受けてレスポンスを返却するクラス

### 難しいと思うところは、サンプルのソースを参考にする
1. 他の人が作成したソースコードを参考にすることは、決して悪いことではありません。理解を深めて、ご自身のスキルアップにつなげてください。
