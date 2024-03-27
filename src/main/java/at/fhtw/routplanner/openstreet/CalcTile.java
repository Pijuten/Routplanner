package at.fhtw.routplanner.openstreet;

import java.util.Vector;

public class CalcTile {
    public static Vector<Integer> getTileNumber(final double lat, final double lon, final int zoom) {
        int xtile = (int)Math.floor( (lon + 180) / 360 * (1<<zoom) ) ;
        int ytile = (int)Math.floor( (1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1<<zoom) ) ;
        if (xtile < 0)
            xtile=0;
        if (xtile >= (1<<zoom))
            xtile=((1<<zoom)-1);
        if (ytile < 0)
            ytile=0;
        if (ytile >= (1<<zoom))
            ytile=((1<<zoom)-1);
        Vector<Integer> result = new Vector<>();
        result.add(0, xtile);
        result.add(1, ytile);
        return result;
    }
    static public Integer getZoom(Double x1Coordinate, Double y1Coordinate,Double x2Coordinate, Double y2Coordinate){
        if(x1Coordinate==null || y1Coordinate==null || x2Coordinate==null || y2Coordinate==null)
            return 0;
        for(int i = 18; i >= 1; i--){
            Vector<Integer> tiles1 = getTileNumber(x1Coordinate,y1Coordinate,i);
            Vector<Integer> tiles2 = getTileNumber(x2Coordinate,y2Coordinate,i);
            if(tiles1.get(0).equals(tiles2.get(0))&&tiles1.get(1).equals(tiles2.get(1)))
                return i;
        }
        return 0;
    }

}
