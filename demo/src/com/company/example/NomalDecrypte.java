package com.company.example;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;

import java.util.Base64;

public class NomalDecrypte {

    public static final String iv = "Ou3fn+I9SVicGWMLkFEgZQ==";
    public static final String key = "45BLO2yoJkvBwz99kBEMlNkxvL40vUSGaqr/WBu3+Vg=";

    public static void main(String[] args) {
        String ansow = "100-model1,model2,modl3-customername-2023/09/20 16:51:37-2024/09/20 23:59:59";
        try {
            BASE64Decoder base64de=new BASE64Decoder();
            byte[] afterEnBt = base64de.decodeBuffer(("UWspEpOufk/AYJtohzUaydqHaB6gL7uG3UTlO1ry9SLxBwplYvQ6+xQ1oFujUzFuioQ/olRfTPcc/Hx4/tfs4vk6P/3kkrj3aU3MeRpFF8o="));

            String de = decrypt(afterEnBt);
            System.out.println("解码后：" + de);
            System.out.println(ansow.equals(de)? "解密成功": "解密失败");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String decrypt(byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
            SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
            // 设置偏移量参数
            IvParameterSpec ivSpec = new IvParameterSpec(Base64.getDecoder().decode(iv));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byte[] encryped = cipher.doFinal(data);
            byte[] decrypted=new byte[data.length];
            System.arraycopy(encryped, 0, decrypted, 0, encryped.length);
            return new String(decrypted, "utf-8").trim();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}

