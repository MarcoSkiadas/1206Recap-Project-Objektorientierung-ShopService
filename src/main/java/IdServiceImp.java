import java.security.PublicKey;
import java.util.UUID;

public class IdServiceImp implements IdService {

    @Override
   public String generateId(){
        return UUID.randomUUID().toString();

    }

}

