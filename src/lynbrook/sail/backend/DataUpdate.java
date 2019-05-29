package lynbrook.sail.backend;

import java.awt.Point;
import java.util.Map;

import lynbrook.sail.network.PlayerData;


public interface DataUpdate
{
    Point getCurrentLocation();


    void onUpdateData( Map<Integer, PlayerData> playerMap );
}
