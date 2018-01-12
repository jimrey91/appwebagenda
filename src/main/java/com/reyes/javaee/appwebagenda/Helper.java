package com.reyes.javaee.appwebagenda;

import org.apache.commons.codec.digest.DigestUtils;

public class Helper {
	
	public String encrypt(String texto){
		
		String key ="mhjdhjkshdjskhds";
		String textoEncriptado = DigestUtils.sha512Hex(texto + key);
		
		return textoEncriptado;
	}
	

}
