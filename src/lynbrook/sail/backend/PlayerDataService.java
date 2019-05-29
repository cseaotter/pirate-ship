package lynbrook.sail.backend;

import java.awt.Point;
import java.util.Map;


public interface PlayerDataService
{
    Point getCurrentLocation();


    void onUpdateData( PlayerData localPlayerData, PlayerData remotePlayerData );
    
}
