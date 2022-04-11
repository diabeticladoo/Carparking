package com.carpark.reg.helper;

import java.util.Random;

import com.carpark.helper.Mailer;
import com.carpark.helper.SMSer;

public class OTPsender {
	public static String generate(int len) {
		String otp = "";
		for(int i = 0; i < len; i++) {
			otp = otp + (new Random().nextInt(10));
		}
		return otp;
	}
	public static void send(String otp, String which,String to) {
		if(which.equals("mail")) {
			Mailer M = new Mailer(to,"Verify Your Account","Your OTP is "+otp);
			M.send();
		}else if(which.equals("sms")) {
			SMSer S = new SMSer(to,"Your OTP is "+otp+"\n\n Regards\nCar Parking Solutions");
			S.send();
		}
	}
}
