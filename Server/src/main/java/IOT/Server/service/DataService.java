package IOT.Server.service;

import IOT.Server.dao.Conturi;
import IOT.Server.dao.InvalidNrOfArgsException;
import IOT.Server.dao.Pacient;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class DataService {
    public void importData(String username ,String puls,String calorii, String nr_pasi) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        new Pacient().insertCollectionByName(new Conturi().getCNP(username),"date",puls,calorii,nr_pasi);
    }

    public void importOxigen(String username, String oxigen) throws ExecutionException, InterruptedException {
        new Pacient().insertCollectionByName(new Conturi().getCNP(username),"nivel_oxigen",oxigen);
    }
    public void importCalitateSomn(String username, String calitate_somn) throws ExecutionException, InterruptedException {
        new Pacient().insertCollectionByName(new Conturi().getCNP(username),"calitate_somn",calitate_somn);
    }
}
