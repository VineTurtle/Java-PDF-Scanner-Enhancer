
/**
 * 图像切边处理
 * @param mat 输入图像矩阵
 * @param cutRegion 指定裁剪区域（可为null）
 * @param spotCompensation 斑点补偿参数
 * @return 裁剪后的图像矩阵
 */
private static Mat edgeCutting(Mat mat, Rect cutRegion, double spotCompensation) {
    if (cutRegion != null) {
        return mat.submat(cutRegion);
    } else {
        // 基于内容自动切边
        Mat gray = new Mat();
        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY);

        // 使用自适应阈值获取前景掩模
        Mat binary = new Mat();
        Imgproc.adaptiveThreshold(gray, binary, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,
                Imgproc.THRESH_BINARY, 11, 2);

        // 寻找轮廓
        List<MatOfPoint> contours = new ArrayList<>(); // 修改此处
        Mat hierarchy = new Mat();
        Imgproc.findContours(binary, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // 找出最大轮廓并提取边界框
        Rect boundingBox = new Rect();
        for (MatOfPoint contour : contours) {
            Rect rect = Imgproc.boundingRect(contour);
            if (rect.area() > boundingBox.area()) {
                boundingBox = rect;
            }
        }

        // 应用斑点补偿
        int padding = (int) spotCompensation;
        boundingBox.x += padding;
        boundingBox.y += padding;
        boundingBox.width -= 2 * padding;
        boundingBox.height -= 2 * padding;

        return mat.submat(boundingBox);
    }
}
