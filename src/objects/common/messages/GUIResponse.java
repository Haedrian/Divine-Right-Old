package objects.common.messages;

import java.util.List;

/**
 * User: Yury
 * Date: 14/07/12
 * Time: 18:21
 */
public class GUIResponse {
    long mapItemId = 0;
    String graphic = "";
    List<String> parameters = null;
    List<String> possibleActions = null;

    public GUIResponse(long mapItemId, String graphic, List<String> parameters, List<String> possibleActions) {
        this.mapItemId = mapItemId;
        this.graphic = graphic;
        this.parameters = parameters;
        this.possibleActions = possibleActions;
    }

    public long getMapItemId() {
        return mapItemId;
    }

    public void setMapItemId(long mapItemId) {
        this.mapItemId = mapItemId;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public String getGraphic() {
        return graphic;
    }

    public void setGraphic(String graphic) {
        this.graphic = graphic;
    }

    public List<String> getPossibleActions() {
        return possibleActions;
    }

    public void setPossibleActions(List<String> possibleActions) {
        this.possibleActions = possibleActions;
    }
}
