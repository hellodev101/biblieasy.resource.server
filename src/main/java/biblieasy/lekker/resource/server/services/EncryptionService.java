package biblieasy.lekker.resource.server.services;

//encryption decrypion using secret key cipher
public interface EncryptionService {

    public String encrypt(String data);
    public String decrypt(String data);
}
