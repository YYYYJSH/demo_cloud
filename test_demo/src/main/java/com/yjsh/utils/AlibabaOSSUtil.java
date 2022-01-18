package com.yjsh.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AlibabaOSSUtil {
    /**
     * 阿里云服务器地址：
     */
    public static final String endpoint="oss-cn-beijing.aliyuncs.com";
    /**
     * accessKey
     */
    public static final String accessKey="";
    /**
     * accessSecretKey
     */
    public static final String accessSecretKey="";
    /**
     * bucketName
     */
    public static final String bucketName="yyyyjsh";


    /**
     * 初始化OSS客户端
     */
    private static OSS ossClient=new OSSClientBuilder().build(
            "https://"+endpoint,accessKey,accessSecretKey
    );

    /**
     * 如果上传了一个文件，文件的url是什么？
     * 获取url
     */
    public static String getUrl(String fileName){
        String uploadUrl="https://"+bucketName+"."+endpoint+"/"+fileName;
        return uploadUrl;
    }

    /**
     * 上传文件的方法
     * @param fileName 文件名
     * @param file 文件
     * @return 文件的保存的url地址
     */
    public static String uploadFile(String fileName, File file){
        //创建上传文件的请求对象
        PutObjectRequest putObjectRequest=new PutObjectRequest(bucketName,fileName,file);
        //上传文件
        ossClient.putObject(putObjectRequest);
        return getUrl(fileName);
    }


    /**
     * 上传文件以流的形式上传
     * @param fileName 文件名
     * @param in 输入流
     */
    public static String uploadFile(String fileName, InputStream in){
        //创建文件上传的请求对象
        PutObjectRequest putObjectRequest=new PutObjectRequest(bucketName,fileName,in);
        //上传文件
        ossClient.putObject(putObjectRequest);
        return getUrl(fileName);
    }




    public static void main(String[] args) throws FileNotFoundException {
//        String url=uploadFile("aaa.jpg",new File("C:\\FACE\\yueyunpeng3.jpg"));
//        System.out.println(url);

        String url=uploadFile("bbb.jpg",new FileInputStream(new File("C:\\FACE\\yueyunpeng2.jpg")));
        System.out.println(url);
    }

}
