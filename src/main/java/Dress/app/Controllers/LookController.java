package Dress.app.Controllers;

import Dress.app.Models.Look;
import Dress.app.services.LookService;
import Dress.app.services.SeasonConverter;
import Dress.app.services.StyleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/looks")
public class LookController {
    private LookService lookService;
    private StyleConverter styleConverter;
    private SeasonConverter seasonConverter;

    @Autowired
    public LookController(LookService lookService, SeasonConverter seasonConverter, StyleConverter styleConverter) {
        this.lookService = lookService;
        this.seasonConverter = seasonConverter;
        this.styleConverter = styleConverter;
    }

 /* достать предыдущие луки юзера
 * получить лук(в сервисе после создания сохранить его)  */

    @GetMapping()
    public ResponseEntity<List<Look>> getAllLooks() {
        List<Look> looks = lookService.getAll();
        return ResponseEntity.ok(looks);
    }

    @GetMapping("/new") // используем когда обязательных нет
    public ResponseEntity<Look> getNewLookNoParameters() {
        Look look = lookService.createLookNoParameters();
        return ResponseEntity.ok(look);
    }

    @PostMapping("/parameters")  // крч на это маппинг отправляем, только если есть обязательные сезон, стиль или вещи
    //т.к. если тело пустое - ругается, с пустым телом используем маппинг /new
    public ResponseEntity<Look> getNewLookWithParameters(@RequestBody LookInfo data) {
        Look look = lookService.createLookWithParameters(
                seasonConverter.makeSeasons(data.getSeasonsNames()),
                styleConverter.makeStyles(data.getStylesNames()),
                data.getItemId());
        return ResponseEntity.ok(look);
    }

//    @PostMapping("/test") //проверяла, что сезоны и стили парвильно находятся
//    public ResponseEntity<String> getNew(@RequestBody LookInfo data) {
//        Season season = seasonConverter.makeSeasons(data.getSeasonsNames()).get(0);
//        return ResponseEntity.ok(season.toString());
//    }
}



