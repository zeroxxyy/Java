package com.shangguigu.sparsearray;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class SparseArrayIO {

    public static void main(String[] args) throws Exception {
        // 先创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子,1: 表示黑子 2: 表示白字
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        // 输出原始的二维数组
        System.out.println("原始的二维数组~~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将二维数组 转 稀疏数组
        // 1. 先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        System.out.println("sum=" + sum);

        // 2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值 初始化
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 遍历二维数组,将非0的值存放到 sparseArr中
        int count = 0;
        // 用于记录是第几个非0的数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        // 保存稀疏数组
        File file = new File("D:\\StudyAndClassMaterials\\developer_tools\\IDEA\\JavaDataStructures\\src\\com\\atguigu\\sparsearray\\map.data");
        FileOutputStream fos = new FileOutputStream(file);

        OutputStreamWriter write = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);

            if (i == sparseArr.length - 1) {
                write.append(String.valueOf(sparseArr[i][0])).append(",").append(String.valueOf(sparseArr[i][1])).append(",").append(String.valueOf(sparseArr[i][2]));
            } else {
                write.append(String.valueOf(sparseArr[i][0])).append(",").append(String.valueOf(sparseArr[i][1])).append(",").append(String.valueOf(sparseArr[i][2])).append(",");
            }
        }

        System.out.println("写入文件中...");
        write.close();
        fos.close();

        System.out.println("打开文件中...");
        Desktop.getDesktop().open(file);

        System.out.println("------------------------------先读取_map.data");
        // 创建 FileReader 对象
        FileInputStream fis = new FileInputStream(file);

        InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuffer sb = new StringBuffer();
        while (reader.ready()) {
            sb.append((char) reader.read());// 转成char加到StringBuffer对象中
        }

        System.out.println(sb.toString());
        reader.close();// 关闭读取流
        fis.close();// 关闭输入流,释放系统资源

        System.out.println("------------------------------恢复成稀疏数组_sparseArrHf");
        // 2.创建对应的稀疏数组
        String[] str = sb.toString().split(",");
        int[][] sparseArrHf = new int[str.length / 3][3];
        // 给稀疏数组赋值
        int i = 0;
        for (String s : str) {
            sparseArrHf[(i - (i % 3)) / 3][i % 3] = Integer.parseInt(s);
            i++;
        }

        System.out.println("------------------------------再恢复成二维数组_chessArr22");
        // 将稀疏数组 -->恢复成 原始的二维数组
        /*
         * 1. 读取稀疏数组的第一行,根据第一行的数据,创建原始的二维数组,比如上面的 chessArr2 = int[11][11];
         *
         * 2. 在读取稀疏数组后几行的数据,并赋给 原始的二维数组 即可.
         */

        // 1. 读取稀疏数组的第一行,根据第一行的数据,创建原始的二维数组
        int[][] chessArr22 = new int[sparseArrHf[0][0]][sparseArrHf[0][1]];

        // 2. 在读取稀疏数组后几行的数据,并赋给 原始的二维数组 即可.
        for (int i3 = 1; i3 < sparseArrHf.length; i3++) {
            chessArr22[sparseArrHf[i3][0]][sparseArrHf[i3][1]] = sparseArrHf[i3][2];
        }

        // 输出恢复的二维数组
        System.out.println();
        for (int[] row : chessArr22) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        System.out.println("--------------------------------------------------------恢复完成");

        // 将稀疏数组 -->恢复成 原始的二维数组
        /*
         * 1. 读取稀疏数组的第一行,根据第一行的数据,创建原始的二维数组,比如上面的 chessArr2 = int[11][11];
         *
         * 2. 在读取稀疏数组后几行的数据,并赋给 原始的二维数组 即可.
         */

        // 1. 读取稀疏数组的第一行,根据第一行的数据,创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        // 2. 在读取稀疏数组后几行的数据,并赋给 原始的二维数组 即可.
        for (int i3 = 1; i3 < sparseArr.length; i3++) {
            chessArr2[sparseArr[i3][0]][sparseArr[i3][1]] = sparseArr[i3][2];
        }

        // 输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
            System.out.println("提交本地库");
            System.out.println("3test");
            System.out.println("hot-fix");
        }

    }
}


