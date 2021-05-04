package IOT.Server.api;

import IOT.Server.dao.InvalidNrOfArgsException;
import IOT.Server.model.CalitateSomn;
import IOT.Server.model.Data;
import IOT.Server.model.Oxigen;
import IOT.Server.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RequestMapping("api/data")
@RestController
public class DataController {

    private final DataService dataService;
    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }


    @PostMapping(path = "import/bigData")
    public void importData(@RequestBody Data data
    ) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        dataService.importData(data.getUsername(),data.getPuls(), data.getCalorii(), data.getNr_pasi());
    }

    @PostMapping(path = "import/oxigen")
    public void importOxigen(@RequestBody Oxigen oxigen
    ) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        dataService.importOxigen(oxigen.getUsername(),oxigen.getNivel_oxigen());
    }
    @PostMapping(path = "import/calitate_somn")
    public void importCalitateSomn(@RequestBody CalitateSomn calitateSomn
    ) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        System.out.println("aici");
        dataService.importCalitateSomn(calitateSomn.getUsername(),calitateSomn.getCalitate_somn());
    }

}
