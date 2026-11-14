<div align="center">

# Java PDF Scanner Enhancer

**把扫描版 PDF 逐页转成图片，并按页面类型分别做增强处理**

<a href="#-简体中文">简体中文</a> ·
<a href="#-繁體中文">繁體中文</a> ·
<a href="#-english">English</a> ·
<a href="#-日本語">日本語</a>

</div>

---

## 🇨🇳 简体中文

扫描件 PDF 通常「文字页」和「插图页」混在一起，用一套参数处理，不是文字发灰就是插图过曝。这个工具把 PDF 按 600 DPI 渲染成图片，先判断每一页是**纯文字页**还是**图文页**，再套用各自预设的增强参数，最后输出成 JPG。

### 环境要求

| 依赖 | 版本 | 说明 |
|---|---|---|
| JDK | 24 | `pom.xml` 中 `maven.compiler.source/target = 24` |
| Maven | 3.8+ | 拉依赖、构建 |
| OpenCV 原生库 | 由 `opencv-java` 依赖提供 | 部分环境需要额外配置 |

主要依赖：PDFBox 2.0.24、OpenCV（javacv-platform）、Tess4J、JavaFX。

### 快速开始

1. 把待处理的 PDF 命名为 `input.pdf`，放在**项目根目录**
2. 构建：

```bash
mvn -q package
```

3. 运行入口类：

```bash
# 在 IDE 中直接运行 Main.java 也可以
java -cp "target/classes;target/dependency/*" io.github.IronPlumeInk.Main
```

4. 结果输出到 `output/images/page_1.jpg`、`page_2.jpg`…（600 DPI，RGB）

### 处理流程

```
input.pdf
   ↓  PDFBox 按 600 DPI 渲染每一页
   ↓  PageClassifier.isTextPage() 判断页面类型
   ├── 文字页 → PresetParams.getTextPreset()
   └── 图文页 → PresetParams.getImagePreset()
   ↓  输出 JPEG
output/images/page_N.jpg
```

### 代码结构

| 文件 | 职责 |
|---|---|
| `Main.java` | 入口：读 PDF、逐页渲染、分派处理、写图 |
| `PageClassifier.java` | 判断当前页是文字页还是图文页 |
| `PdfImageProcessor.java` | 图像增强主流程 |
| `ImageProcessingParams.java` | 处理参数集合 |
| `PresetParams.java` | 文字页 / 图文页的预设参数 |
| `CurveAdjustmentParams.java` | 曲线调整参数 |
| `ImageUtils.java` | 图像工具方法 |

### 常见问题

- **找不到 `input.pdf`**：路径是相对工作目录的，请确认运行目录或改成绝对路径
- **OpenCV 报 native library 缺失**：`javacv-platform` 会带上各平台原生库，检查 Maven 是否完整下载
- **输出目录**：`output/images/` 不存在时会自动创建

## 🇹🇼 繁體中文

掃描版 PDF 通常把「文字頁」和「插圖頁」混在一起，用同一組參數處理，不是文字發灰就是插圖過曝。這個工具把 PDF 以 600 DPI 轉成圖片，先判斷每頁是**純文字頁**還是**圖文頁**，再套用各自的預設增強參數，最後輸出 JPG。

### 環境需求

| 依賴 | 版本 | 說明 |
|---|---|---|
| JDK | 24 | `pom.xml` 的 `maven.compiler.source/target = 24` |
| Maven | 3.8+ | 取得相依、建置 |
| OpenCV 原生庫 | 由 `opencv-java` 提供 | 部分環境需額外設定 |

主要相依：PDFBox 2.0.24、OpenCV（javacv-platform）、Tess4J、JavaFX。

### 快速開始

1. 把要處理的 PDF 命名為 `input.pdf`，放在**專案根目錄**
2. 建置：

```bash
mvn -q package
```

3. 執行進入點：

```bash
java -cp "target/classes;target/dependency/*" io.github.IronPlumeInk.Main
```

4. 輸出到 `output/images/page_1.jpg`、`page_2.jpg`…（600 DPI、RGB）

### 處理流程

```
input.pdf
   ↓  PDFBox 以 600 DPI 逐頁點陣化
   ↓  PageClassifier.isTextPage() 判斷頁面類型
   ├── 文字頁 → PresetParams.getTextPreset()
   └── 圖文頁 → PresetParams.getImagePreset()
   ↓  輸出 JPEG
output/images/page_N.jpg
```

### 程式結構

| 檔案 | 職責 |
|---|---|
| `Main.java` | 進入點：讀 PDF、逐頁點陣化、分派處理、輸出 |
| `PageClassifier.java` | 判斷頁面類型 |
| `PdfImageProcessor.java` | 影像增強主流程 |
| `ImageProcessingParams.java` | 處理參數集合 |
| `PresetParams.java` | 文字頁 / 圖文頁預設參數 |
| `CurveAdjustmentParams.java` | 曲線調整參數 |
| `ImageUtils.java` | 影像工具方法 |

## 🇬🇧 English

Scanned PDFs usually mix **text pages** and **figure pages**. One set of parameters either leaves text washed out or blows out the figures. This tool renders the PDF at 600 DPI, classifies each page as text-only or mixed, applies the matching enhancement preset, and writes JPG output.

### Requirements

| Dependency | Version | Notes |
|---|---|---|
| JDK | 24 | `maven.compiler.source/target = 24` in `pom.xml` |
| Maven | 3.8+ | dependency resolution and build |
| OpenCV native libs | via `opencv-java` | may need extra setup on some machines |

Main dependencies: PDFBox 2.0.24, OpenCV (`javacv-platform`), Tess4J, JavaFX.

### Quick start

1. Put your PDF in the **project root** and name it `input.pdf`
2. Build:

```bash
mvn -q package
```

3. Run the entry point:

```bash
java -cp "target/classes;target/dependency/*" io.github.IronPlumeInk.Main
```

4. Output goes to `output/images/page_1.jpg`, `page_2.jpg`, … (600 DPI, RGB)

### Pipeline

```
input.pdf
   ↓  PDFBox renders each page at 600 DPI
   ↓  PageClassifier.isTextPage() classifies the page
   ├── text page  → PresetParams.getTextPreset()
   └── mixed page → PresetParams.getImagePreset()
   ↓  JPEG output
output/images/page_N.jpg
```

### Code map

| File | Responsibility |
|---|---|
| `Main.java` | entry point: load PDF, render pages, dispatch, write output |
| `PageClassifier.java` | decides whether a page is text-only |
| `PdfImageProcessor.java` | main enhancement pipeline |
| `ImageProcessingParams.java` | parameter container |
| `PresetParams.java` | presets for text / figure pages |
| `CurveAdjustmentParams.java` | tone-curve parameters |
| `ImageUtils.java` | image helpers |

### Troubleshooting

- **`input.pdf` not found** — the path is relative to the working directory
- **OpenCV native library missing** — make sure `javacv-platform` downloaded all native artifacts
- **Output folder** — `output/images/` is created automatically if absent

## 🇯🇵 日本語

スキャンした PDF は「文字ページ」と「図版ページ」が混在しがちで、同じパラメータでは文字が薄くなったり図版が飛んだりします。本ツールは PDF を 600 DPI で画像化し、各ページが**文字のみ**か**図版入り**かを判定して、それぞれのプリセットで補正し、JPG として出力します。

### 必要なもの

| 依存 | バージョン | 備考 |
|---|---|---|
| JDK | 24 | `pom.xml` の `maven.compiler.source/target` |
| Maven | 3.8+ | 依存取得とビルド |
| OpenCV ネイティブ | `opencv-java` 経由 | 環境によっては追加設定が必要 |

主な依存：PDFBox 2.0.24、OpenCV（javacv-platform）、Tess4J、JavaFX。

### クイックスタート

1. 処理したい PDF を `input.pdf` にリネームし、**プロジェクト直下**に置く
2. ビルド：

```bash
mvn -q package
```

3. エントリポイントを実行：

```bash
java -cp "target/classes;target/dependency/*" io.github.IronPlumeInk.Main
```

4. `output/images/page_1.jpg`、`page_2.jpg`… に出力（600 DPI、RGB）

### 処理の流れ

```
input.pdf
   ↓  PDFBox が 600 DPI でページを描画
   ↓  PageClassifier.isTextPage() でページ種別を判定
   ├── 文字ページ → PresetParams.getTextPreset()
   └── 図版ページ → PresetParams.getImagePreset()
   ↓  JPEG 出力
output/images/page_N.jpg
```

### ファイル構成

| ファイル | 役割 |
|---|---|
| `Main.java` | エントリポイント（読み込み・描画・振り分け・出力） |
| `PageClassifier.java` | ページ種別の判定 |
| `PdfImageProcessor.java` | 画質補正の本体 |
| `ImageProcessingParams.java` | パラメータ保持 |
| `PresetParams.java` | 文字／図版のプリセット |
| `CurveAdjustmentParams.java` | トーンカーブ |
| `ImageUtils.java` | 画像ユーティリティ |
