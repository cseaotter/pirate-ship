package lynbrook.sail.network;

import java.awt.Point;
import java.util.Map;

import lynbrook.sail.actor.Weapon;


public interface DataUpdate
{
    Point getCurrentLocation();


    Weapon getBattleData();


    int getScenario();


    void onUpdateData( Map<Integer, PlayerData> playerMap );
}
