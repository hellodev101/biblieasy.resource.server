package biblieasy.lekker.resource.server.services;


import java.security.*;

import org.springframework.stereotype.Service;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@Service
public class EncryptionServiceImpl implements EncryptionService{

    private static final String AES_KEY="TOKEN_SECURITY_MOGLIX_AES_KEY_IN_JWT";


    @Override
    public String encrypt(String data) {
        AES aes=new AES(AES_KEY);
        return aes.encrypt(data);
    }

    @Override
    public String decrypt(String data) {
        AES aes=new AES(AES_KEY);
        return aes.decrypt(data);
    }

    private class AES{
        private SecretKeySpec secretKey;
        private byte[] key;

        AES(String secret){
            MessageDigest sha=null;
            try {
                key=secret.getBytes(StandardCharsets.ISO_8859_1);
                sha=MessageDigest.getInstance("SHA-1");
                key= sha.digest(key);
                key= Arrays.copyOf(key, 16);
                secretKey=new SecretKeySpec(key, "AES");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        String encrypt(String strToEncrypt) {
            try {
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                return Base64.getEncoder()
                        .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.ISO_8859_1)));


            } catch (Exception e) {
                e.printStackTrace();
            }
                return null;

            }//end of encrypt method

        String decrypt(String strToDecrypt){
        try{
            Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }catch (Exception e){
            e.printStackTrace();
        }
            return null;
        }
        }
}
