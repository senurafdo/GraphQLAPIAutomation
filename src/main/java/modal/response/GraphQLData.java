package modal.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphQLData {
    private Characters characters;
    private Location location;
    private List<Episode> episodesByIds;
}
