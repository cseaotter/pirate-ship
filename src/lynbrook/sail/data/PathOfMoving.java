 package lynbrook.sail.data;

import java.awt.Point;


public class PathOfMoving
{
    private Point point;

    private PathOfMoving next;


    public PathOfMoving( Point point, PathOfMoving next )
    {
        this.point = point;
        this.next = next;
    }


    public Point getPoint()
    {
        return point;
    }


    public PathOfMoving getNext()
    {
        return next;
    }

}
