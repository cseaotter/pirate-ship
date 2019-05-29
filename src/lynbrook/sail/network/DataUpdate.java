package lynbrook.sail.network;

import java.awt.Point;
import java.util.Map;


public interface DataUpdate
{
    Point getCurrentLocation();


    void onUpdateData( Map<Integer, PlayerData> playerMap );
}
