package com.zzq.paul_tools;

import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestString {
    public static void main(String[] args) {
        Student s1=new Student("小张");
        Student s2=new Student("小王");
        test(s1,s2);
//        System.out.print("s1:"+s1.getName()+"\n");
//        System.out.print("s2:"+s2.getName()+"\n");
        LabmdaInterFace temp=(number,s) -> System.out.println(number+s);

        temp.doSomthing(1,2);
//        writeTxtToFile()
    }
    @FunctionalInterface
    interface LabmdaInterFace{
        void doSomthing(int s,int e);
    }

    public static void test(Student x,Student y){
        x.setName("小王");
        y.setName("小张");
//        Student temp=x;
//        x=y;
//        y=temp;
//        System.out.print("x:"+x.getName()+"\n");
//        System.out.print("y:"+y.getName()+"\n");
    }
    public static int add(int number){
        return number+3;
    }


    /**
     * 将字符串写入到文本文件中
     * 需要makeFilePath方法
     */
    public static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }
    /**
     * 读取文件里面的内容
     * @return 内容
     */
    public static String getFile(String filePath, String fileName) {
        try {
            // 创建文件
            File file = new File(filePath + "/", fileName);
            // 创建FileInputStream对象
            FileInputStream fis = new FileInputStream(file);
            // 创建字节数组 每次缓冲1M
            byte[] b = new byte[1024];
            int len = 0;// 一次读取1024字节大小，没有数据后返回-1.
            // 创建ByteArrayOutputStream对象
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 一次读取1024个字节，然后往字符输出流中写读取的字节数
            while ((len = fis.read(b)) != -1) {
                baos.write(b, 0, len);
            }
            // 将读取的字节总数生成字节数组
            byte[] data = baos.toByteArray();
            // 关闭字节输出流
            baos.close();
            // 关闭文件输入流
            fis.close();
            // 返回字符串对象
            return new String(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 生成文件
     * 需要makeRootDirectory方法
     * 如果没有 filePath文件夹可自动生成
     */
    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e+"");
        }
    }
    /**
     * 删除已存储的文件或文件夹
     */
    public static void deletefile(String filePath, String fileName) {
        try {
            // 找到文件所在的路径并删除该文件
            File file = new File(filePath + "/", fileName);
            if (file.isFile()) {
                file.delete();
            } else {
                deleteDirectory(filePath + "/" + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }


    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
    /**
     * 文件或文件夹重命名
     * oldPath 和 newPath必须是新旧文件的绝对路径
     * */
    public static void renameFile(String oldPath, String newPath) {

        if(TextUtils.isEmpty(newPath)) {
            File file = new File(newPath);
            try{
                file.createNewFile();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file = new File(oldPath);
        file.renameTo(new File(newPath));
    }
}
