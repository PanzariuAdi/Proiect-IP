package IOT.Server.api;

import IOT.Server.dao.Conturi;
import IOT.Server.dao.InvalidNrOfArgsException;
import IOT.Server.model.CalitateSomn;
import IOT.Server.model.Data;
import IOT.Server.model.Oxigen;
import IOT.Server.service.DataService;
import IOT.Server.utility.CrateJSON;
import IOT.Server.utility.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "get/calorii/{cnp}")
    public String getCalorii(@PathVariable("cnp")String cnp){
            return CrateJSON.fromListOfMapsWithKey(dataService.getCalorii(cnp));

    }
    @GetMapping(path = "get/puls/{cnp}")
    public String getPuls(@PathVariable("cnp")String cnp){
        return CrateJSON.fromListOfMapsWithKey(dataService.getPuls(cnp));

    }
    @GetMapping(path = "get/pasi/{cnp}")
    public String getPasi(@PathVariable("cnp")String cnp){
        return CrateJSON.fromListOfMapsWithKey(dataService.getPasi(cnp));

    }
    @GetMapping(path = "get/calitateSomn/{cnp}")
    public String getCalitateSomn(@PathVariable("cnp")String cnp){
        return CrateJSON.fromListOfMapsWithKey(dataService.getCalitateSomn(cnp));

    }
    @GetMapping(path = "get/nivelOxigen/{cnp}")
    public String getNivelOxigen(@PathVariable("cnp")String cnp){
        return CrateJSON.fromListOfMapsWithKey(dataService.getNivelOxigen(cnp));

    }

}
