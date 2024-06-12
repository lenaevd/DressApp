package Dress.app.services;

import Dress.app.Models.Season;
import Dress.app.repos.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonConverter {
    private SeasonRepository repo;

    @Autowired
    public SeasonConverter(SeasonRepository repo){
        this.repo = repo;
    }

    public List<Season> makeSeasons(List<String> seasonsNames) {
        if (seasonsNames == null) {
            return null;
        } else {
            List<Season> seasons = new ArrayList<>();
            for (String name: seasonsNames) {
                seasons.add(repo.findByName(name));
            }
            return seasons;
        }
    }
}