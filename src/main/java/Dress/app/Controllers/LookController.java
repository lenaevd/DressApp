package Dress.app.Controllers;

import Dress.app.Mappers.infoFromItems;
import Dress.app.Mappers.infoFromLook;
import Dress.app.Models.Look;
import Dress.app.Requests.LookParametersRequest;
import Dress.app.services.LookService;
import Dress.app.services.SeasonConverter;
import Dress.app.services.StyleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping()
    public ResponseEntity<List<infoFromLook>> getAllLooks(@RequestParam UUID userId) {
        List<Look> looks = lookService.getUsersLooks(userId);
        return ResponseEntity.ok(infoFromLook.createInfo(looks));
    }

    @GetMapping("/new") // используем когда обязательных нет
    public ResponseEntity<List<infoFromItems>> getNewLookNoParameters(@RequestParam UUID userId) {
        Look look = lookService.createLookNoParameters(userId);
        return ResponseEntity.ok(infoFromItems.createInfo(look.getParts()));
    }

    @PostMapping("/parameters")  // крч на это маппинг отправляем, только если есть обязательные сезон, стиль или вещи
    //т.к. если тело пустое - ругается, с пустым телом используем маппинг /new
    public ResponseEntity<List<infoFromItems>> getNewLookWithParameters(
            @RequestParam UUID userId, @RequestBody LookParametersRequest data) {
        Look look = lookService.createLookWithParameters(userId,
                seasonConverter.makeSeasons(data.getSeasonsNames()),
                styleConverter.makeStyles(data.getStylesNames()),
                data.getItemId());
        return ResponseEntity.ok(infoFromItems.createInfo(look.getParts()));
    }
}



