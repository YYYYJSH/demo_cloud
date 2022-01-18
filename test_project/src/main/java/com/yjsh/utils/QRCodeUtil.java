package com.yjsh.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码工具类
 * @author xuzhong
 */
public class QRCodeUtil {

    /**
     * 生成二维码的工具方法
     * @param content 二维码携带信息的内容
     * @param codeColor 二维码的颜色
     * @param bgColor 二维码的底色
     * @param w 二维码的宽度
     * @param h 二维码的高度
     * @return 二维码图片对象
     */
    public static BufferedImage qrEncode(String content,int codeColor,int bgColor,int w,int h){
        //实例二位码的元素（属性）
        Map<EncodeHintType,Object> hints=new HashMap<>();
        //二维码的属性：内容编码
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        //二维码的属性：纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //二维码的属性：上下左右的留白的宽度
        hints.put(EncodeHintType.MARGIN,1);

        //绘制二维码的像素点信息的对象。
        BitMatrix bitMatrix=null;

        //保存二维码结果的对象
        BufferedImage bufferedImage=null;

        try {
            bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,w,h,hints);

            //设置二维码的颜色
            MatrixToImageConfig config=new MatrixToImageConfig(codeColor,bgColor);

            //写入到具体的二维码图片中
            bufferedImage= MatrixToImageWriter.toBufferedImage(bitMatrix,config);
        } catch (WriterException e) {
            e.printStackTrace();
        }


        return bufferedImage;
    }


    public static void main(String [] args) throws IOException {
        BufferedImage bufferedImage=qrEncode("https://xuzhongcn.github.io/",
            0xff000000,0xffffffff,200,200);

        ImageIO.write(bufferedImage,"png",new File("C:\\TEMP\\GP06.png"));
    }

}
