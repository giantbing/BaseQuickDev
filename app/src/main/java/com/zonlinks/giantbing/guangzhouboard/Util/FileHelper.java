package com.zonlinks.giantbing.guangzhouboard.Util;

import android.content.Context;
import android.os.Environment;
import android.os.Message;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


/**
 * Created by giant on 2017/3/20.
 */

public class FileHelper {
    /**
            * 复制res/raw中的文件到指定目录
    * @param context 上下文
    * @param id 资源ID
    * @param fileName 文件名
    * @param storagePath 目标文件夹的路径
    */
    public static void copyFilesFromRaw(Context context, int id, String fileName, String storagePath){
        InputStream inputStream=context.getResources().openRawResource(id);
        File file = new File(storagePath);
        if (!file.exists()) {//如果文件夹不存在，则创建新的文件夹
            file.mkdirs();
        }
        readInputStream(storagePath + "/"+fileName, inputStream);
    }

    /**
     * 读取输入流中的数据写入输出流
     *
     * @param storagePath 目标文件路径
     * @param inputStream 输入流
     */
    public static void readInputStream(String storagePath, InputStream inputStream) {
        File file = new File(storagePath);
        try {
            if (!file.exists()) {
                // 1.建立通道对象
                FileOutputStream fos = new FileOutputStream(file);
                // 2.定义存储空间
                byte[] buffer = new byte[inputStream.available()];
                // 3.开始读文件
                int lenght = 0;
                while ((lenght = inputStream.read(buffer)) != -1) {// 循环从输入流读取buffer字节
                    // 将Buffer中的数据写到outputStream对象中
                    fos.write(buffer, 0, lenght);
                }
                fos.flush();// 刷新缓冲区
                // 4.关闭流
                fos.close();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

     /*
    * 获取内置sdk路径
    * */
     public static String getInerSDPath(){
         return  Environment.getExternalStorageDirectory().getPath();
     }

    // 从sd卡获取图片资源
    public static List<FileBean> getImagePathFromSD(final String imagePath, final android.os.Handler uiHandler) {
        final List<FileBean> picList = new ArrayList<>();

        Thread l = new Thread(new Runnable() {

            @Override
            public void run() {
                // 图片列表




                // 得到sd卡内路径
                //String imagePath = C.IMGSAVEPATH;


                // 得到该路径文件夹下所有的文件
                File mfile = new File(imagePath);
                if (!mfile.exists()){
                    mfile.mkdirs();
                }
                else {
                    File[] files = mfile.listFiles();
                    Arrays.sort(files, new Comparator<File>() {
                        public int compare(File f1, File f2) {
                            long diff = f1.lastModified() - f2.lastModified();
                            if (diff > 0)
                                return -1;
                            else if (diff == 0)
                                return 0;
                            else
                                return 1;//如果 if 中修改为 返回-1 同时此处修改为返回 1  排序就会是递减
                        }

                        public boolean equals(Object obj) {
                            return true;
                        }

                    });
                    // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
                    for (int i = 0; i < files.length; i++) {
                        File file = files[i];
                        if (checkIsImageFile(file.getPath())) {
                            FileBean fileBean = new FileBean();
                            fileBean.setFilePath(file.getPath());
                            fileBean.setFileDate(new SimpleDateFormat("yyyy-MM-dd\thh:mm")
                                    .format(new Date(file.lastModified())));
                            fileBean.setFileName(file.getName());
                            fileBean.setFlieSize(ShowLongFileSzie(file.length()));
                            fileBean.setFiletype(FileBean.NOMALTYPE);
                            picList.add(fileBean);
                        }

                    }
                    //文件下载完成后更新UI
                    Message msg = new Message();
                    //虽然Message的构造函数式public的，我们也可以通过以下两种方式通过循环对象获取Message
                    //msg = Message.obtain(uiHandler);
                    //msg = uiHandler.obtainMessage();

                    //what是我们自定义的一个Message的识别码，以便于在Handler的handleMessage方法中根据what识别
                    //出不同的Message，以便我们做出不同的处理操作
                    msg.what = 1;

                    //我们可以通过arg1和arg2给Message传入简单的数据
                    //msg.arg1 = 123;
//               msg.arg2 = 321;
                    //我们也可以通过给obj赋值Object类型传递向Message传入任意数据
                    //msg.obj = null;
                    //我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
                    //msg.setData(null);
                    //Bundle data = msg.getData();

                    //将该Message发送给对应的Handler
                    uiHandler.sendMessage(msg);
                }


            }
        });
        l.run();
        // 返回得到的图片列表
        return picList;

    }

    /****
     * 计算文件大小
     *
     * @param length
     * @return
     */
    public static String ShowLongFileSzie(Long length) {
        if (length >= 1048576) {
            return (length / 1048576) + "MB";
        } else if (length >= 1024) {
            return (length / 1024) + "KB";
        } else if (length < 1024) {
            return length + "B";
        } else {
            return "0KB";
        }
    }

    // 检查扩展名，得到图片格式的文件
    private static boolean checkIsImageFile(String fName) {
        boolean isImageFile = false;

        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("gif")
                || FileEnd.equals("png") || FileEnd.equals("jpeg")
                || FileEnd.equals("bmp")) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }

        return isImageFile;

    }

    /**
     * 删除指定文件
     *
     * @param fileNames
     */
    public static void deleteFiles(String... fileNames) {
        if (fileNames.length <= 0)
            return;
        for (int i = 0; i < fileNames.length; i++) {
            File file = new File(fileNames[i]);
            if (file.exists())
                file.delete();

        }
    }
    /**
     * 将文件字节码转换为base63
     *
     * @param filePath 文件地址
     */
    public static String file2base64(String filePath){
        String mbase64 ="";
        File file = new File(filePath);
        try {
            mbase64 = Base64.encodeToString(getByte(file), Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mbase64;

    }

    /**
     * 把一个文件转化为字节
     * @param file
     * @return   byte[]
     * @throws Exception
     */
    public static byte[] getByte(File file) throws Exception
    {
        byte[] bytes = null;
        if(file!=null)
        {
            InputStream is = new FileInputStream(file);
            int length = (int) file.length();
            if(length>Integer.MAX_VALUE)   //当文件的长度超过了int的最大值
            {
                System.out.println("this file is max ");
                return null;
            }
            bytes = new byte[length];
            int offset = 0;
            int numRead = 0;
            while(offset<bytes.length&&(numRead=is.read(bytes,offset,bytes.length-offset))>=0)
            {
                offset+=numRead;
            }
            //如果得到的字节长度和file实际的长度不一致就可能出错了
            if(offset<bytes.length)
            {
                System.out.println("file length is error");
                return null;
            }
            is.close();
        }
        return bytes;
    }

}
