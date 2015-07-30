package com.brett.test.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignatureException;

/**
 * 数字签名，使用RSA私钥对对消息摘要签名，然后使用公鈅验证 测试
 */
public class DigitalSignature2Example {
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("Usage:java DigitalSignature2Example ");
      System.exit(1);
    }

    byte[] plainText = args[0].getBytes("UTF8");
    //形成RSA公钥对
    System.out.println(" Start generating RSA key");
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(1024);

    KeyPair key = keyGen.generateKeyPair();
    System.out.println("Finish generating RSA key");
    //使用私鈅签名
    Signature sig = Signature.getInstance("SHA1WithRSA");
    sig.initSign(key.getPrivate());
    sig.update(plainText);
    byte[] signature = sig.sign();
    System.out.println(sig.getProvider().getInfo());
    System.out.println(" Signature:");
    System.out.println(new String(signature, "UTF8"));

    //使用公鈅验证
    System.out.println(" Start signature verification");
    sig.initVerify(key.getPublic());
    sig.update(plainText);
    try {
      if (sig.verify(signature)) {
        System.out.println("Signature verified");
      } else System.out.println("Signature failed");
    } catch (SignatureException e) {
      System.out.println("Signature failed");
    }
  }
} 