import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class TwoFactorAuthenticationDriver {
   // @Autowired
    TwoFactorAuthentication_Email email;
    //@Autowired
    TwoFactorAuthentication_TextMessage sms;

    //Send Two FA code via email
    //Send Two FA code via text message (SMS)

    public static void main(String [] args) {

    }
}
