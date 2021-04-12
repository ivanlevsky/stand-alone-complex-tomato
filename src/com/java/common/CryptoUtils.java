package com.java.common;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;

public class CryptoUtils {
    public static void main(String[] args) {
		encode("胜多负少");
    }

	public static void encode(String string) {
//		System.out.println(Byte.string.getBytes());
	}

	@Deprecated // md5(), sha1() deprecate
	@SuppressWarnings("UnstableApiUsage") //remove this annotation in guava future update
    public static String fileCheckSum(String fileName, String checksumType)  {
		try {
			String hashCode;
			File file = new File(fileName);
			if(!file.exists()){
				return "file not exist";
			}
			ByteSource byteSource = Files.asByteSource(file);

			switch (checksumType){
				case "md5": hashCode = byteSource.hash(Hashing.md5()).toString().toUpperCase();break;
				case "sha1": hashCode = byteSource.hash(Hashing.sha1()).toString().toUpperCase();break;
				case "sha256": hashCode = byteSource.hash(Hashing.sha256()).toString().toUpperCase();break;
				case "crc32": hashCode = Long.toHexString(byteSource.hash(Hashing.crc32()).padToLong());break;
				default :hashCode = "wrong checksum type!!";break;
			}
			return hashCode;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}



}
