# Repository Guidelines

## プロジェクト構成とモジュール
- `composeApp/`: Kotlin Multiplatform 本体。`src/commonMain/kotlin` に共有ロジック・Compose UI、`composeResources/` に共有リソース、`androidMain` / `iosMain` / `desktopMain` / `wasmJsMain` に各プラットフォーム固有コード。Web 用シェルは `wasmJsMain/resources/index.html` / `styles.css`。
- `iosApp/`: SwiftUI エントリと Xcode プロジェクト (`iosApp/iosApp.xcodeproj`)。Gradle で生成した Compose フレームワークをリンクしてビルドする。
- `kotlin-js-store/`: Kotlin/Wasm が利用する Yarn キャッシュ。Gradle が管理するため原則手動で変更しない。
- `build/` や `*.gradle.kts` はビルド成果物・設定。不要な生成物はコミットしない。

## ビルド・テスト・開発コマンド
- Android: `./gradlew :composeApp:assembleDebug` でデバッグ APK を生成。Android Studio から `composeApp` 構成を実行してデバイスへデプロイ。
- Desktop: `./gradlew :composeApp:run` で Compose Desktop アプリを起動。
- Web/Wasm: `./gradlew :composeApp:wasmJsBrowserDevelopmentRun` で開発サーバー (ホットリロード) を開始。
- iOS: `./gradlew :composeApp:syncFramework` を実行し、`iosApp/iosApp.xcodeproj` を Xcode で開いて Run。Simulator/実機を問わずフレームワークを再同期してからビルドする。
- 共通: `./gradlew clean` でビルドキャッシュをクリア。長時間タスクは `--info` / `--stacktrace` でログを確認。

## コーディングスタイルと命名
- Kotlin 公式スタイル、4 スペース、行長目安 120。`val` を優先し、メンバーは可能な限り `private` にする。
- クラス/オブジェクト/Composable は PascalCase、メソッド・プロパティは lowerCamelCase。プレビュー用関数は `*Preview` 接尾辞。
- 共有コードは `commonMain` に置き、プラットフォーム差分は `expect/actual` で分離。リソースは `composeResources` に集約。
- 自動フォーマットは IDE 標準を使用。lint/ktlint は未導入のため、レビュー前に手元で再フォーマットして差分を整える。

## テスト指針
- テストフレームワークは `kotlin.test`。共通テストは `composeApp/src/commonTest`、固有挙動は `androidTest` / `desktopTest` / `iosTest` 等に配置。
- コマンド例: `./gradlew :composeApp:allTests` で全ターゲット、対象を絞る場合は `desktopTest` など個別タスクを実行。
- カバレッジ閾値は未設定だが、ビジネスロジックやデータ変換は分岐網羅を目安とし、プラットフォーム依存部分はモックや `expect/actual` で再現する。
- UI 変更を含む PR ではスクリーンショットや短い動画を添付して動作を共有する。

## コミットと Pull Request
- 現在の履歴は初期化のみ。コミットメッセージは命令形の短文 (例: `Add desktop entry window`) を推奨し、関連 Issue があれば `#番号` を付与。
- PR には目的、影響範囲 (Android/iOS/Desktop/Web)、主要変更点、実行したコマンドやテスト結果を記載。UI 変更はキャプチャを添付する。
- 新規依存追加時は理由と影響 (サイズ、ライセンス) を記述。`local.properties` や IDE 設定、ビルド成果物はコミットしない。

## セキュリティと設定
- 秘密情報・API キーをリポジトリに含めない。`local.properties` は SDK パスのみ記載し配布しない。
- Web/Wasm の静的リソースでは外部 CDN 利用を最小化し、必要な場合はライセンスを確認する。レビュー前に不要なキャッシュやビルド成果物を削除。

## ソースコードのコメントについて
- できるだけ細かい粒度でソースコード上に日本語のコメントを追加する
- 関数へのコメントは、利用例のサンプルコードも記載すること
