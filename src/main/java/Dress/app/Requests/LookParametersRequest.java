package Dress.app.Requests;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LookParametersRequest {
    public List<String> seasonsNames = new ArrayList<>();
    public List<String> stylesNames = new ArrayList<>();
    public UUID itemId;
}
