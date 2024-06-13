package Dress.app.services;

import Dress.app.Models.Style;
import Dress.app.repos.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StyleConverter {
    private final StyleRepository repo;

    @Autowired
    public StyleConverter(StyleRepository repo){
        this.repo = repo;
    }

    public List<Style> makeStyles(List<String> stylesNames){
        if (stylesNames == null) {
            return null;
        } else {
            List<Style> styles = new ArrayList<>();
            for (String name: stylesNames) {
                styles.add(repo.findByName(name));
            }
            return styles;
        }
    }
}
