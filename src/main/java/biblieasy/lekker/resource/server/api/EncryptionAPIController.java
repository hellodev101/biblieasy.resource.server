package biblieasy.lekker.resource.server.api;

import biblieasy.lekker.resource.server.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EncryptionAPIController {

    @Autowired
    private EncryptionService encryptionService;

    @GetMapping("encrypt")
    public String encrypt(@RequestParam String encrypt){
        System.out.println("*****" + encrypt + ""+encryptionService.encrypt(encrypt));
        String result=encryptionService.encrypt(encrypt);

        String decrypt=encryptionService.decrypt(result);
        System.out.println("******decrypt "+ decrypt);
        return result;
    }

    @GetMapping("decrypt")
    public String decrypt(@RequestParam String decrypt){
        return encryptionService.decrypt(decrypt);
    }
}
