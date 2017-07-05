package com.member.controller;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MemberMail {

	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●須將myGmail的【安全性較低的應用程式存取權】打開
			final String myGmail = "bucktooth168@gmail.com";
			final String myGmail_password = "bucktooth";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
//			message.setText(messageText);
			message.setContent(messageText,"text/html; charset=UTF-8");
			
			
			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {

		// String to = "bucktooth888@gmail.com";
		//
		// String subject = "密碼通知";
		//
		// String ch_name = "peter1";
		// String passRandom = "111";
		// String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom +
		// "\n" +" (已經啟用)";
		// MemberMail membermail = new MemberMail();
		// membermail.sendMail(to, subject, messageText);
		// --------------------------老師的
//		int randCount = (int) Math.ceil(Math.random() * (10 - 6 + 1) + 6 - 1);
//		int randInt = 0;
//		String randStr = "";
//		System.out.println("randCount=" + randCount);
//		for (int i = 1; i <= randCount; i++) {
//			randInt = (int) Math.ceil(Math.random() * (122 - 48 + 1) + 48 - 1);
//
//			if ((randInt >= 48 && randInt <= 57) || (randInt >= 65 && randInt <= 90)
//					|| (randInt >= 97 && randInt <= 122)) {
//				String achar = new Character((char) randInt).toString();
//				randStr = randStr + achar;
//
//			} else {
//				i--;
//			}
//		}

//		MTIz
		
		
//		System.out.println("randStr= " + randStr);
		String randStr="456456";
		String MTIz="VFJpYUNzRA==";
		final BASE64Encoder encoder = new BASE64Encoder();
		final BASE64Decoder decoder = new BASE64Decoder();
		final byte[] textByte = randStr.getBytes("UTF-8");
		// 編碼
		final String encodedText = encoder.encode(textByte);
		System.out.println("編碼= " + encodedText);
		// 解碼
		System.out.println("解碼= " + new String(decoder.decodeBuffer(MTIz), "UTF-8"));

		
		
		
		
		Date dd=new Date();
		System.out.println(dd);
		System.out.println(dd.getTime());
		
		
		
		
		
	}

}
