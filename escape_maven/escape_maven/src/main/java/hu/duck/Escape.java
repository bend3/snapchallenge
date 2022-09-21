package hu.duck;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Escape {
    
    private String[][] map;
    private List<Position> escapeRoute;
    private List<Position> visited = new ArrayList<>();

    public Escape(String[][] map) {
        this.map = map;
        for(int x= 0; x < map.length; x++){
            // System.out.println(Arrays.toString(map[x]));
        }
    }

    public List<Position> getEscapeRoute() {
        if (escapeRoute == null) {
            escapeRoute = findEscapeRoute();
        }
        return escapeRoute;
    }

    private List<Position> findEscapeRoute() {
        List<Position> route = new ArrayList<>();
        Position startPosition = findPosition("S");
        if (startPosition == null) {
            return null;
        }
        Position currentPosition = startPosition;
        route.add(currentPosition);
        // find the Key
        Position keyPosition = findPosition("K");
        if (keyPosition == null) {
            return null;
        }
        // find route to key
        route.addAll(findRoute(currentPosition, keyPosition));
        visited.clear();
        // find the Exit
        Position exitPosition = findPosition("E");
        if (exitPosition == null) {
            return null;
        }
        // find route to exit
        route.addAll(findRoute(keyPosition, exitPosition));

        return route;
    }


    private Collection<? extends Position> findRoute(Position startPosition, Position targetPosition) {
        List<Position> route = new ArrayList<>();
        visited.add(startPosition);
        route.add(startPosition);
        Position currentPosition = startPosition;
        loop: while (true) {
            Position closestNeighbour = getClosestNeighbour(currentPosition, targetPosition);
            // System.out.println("closestNeighbour: " + closestNeighbour);
            if (closestNeighbour == null) {
                route.remove(route.size() - 1);
                if (route.size() == 0) {
                    // System.out.println("No route found from " + startPosition + " to " + targetPosition);
                    break loop;
                }
                currentPosition = route.get(route.size() - 1);
            } else {
                // System.out.println("ClosestNeighbour sign: " + map[closestNeighbour.getY()][closestNeighbour.getX()]);
                currentPosition = closestNeighbour;
                route.add(currentPosition);
                visited.add(currentPosition);
                if (currentPosition.equals(targetPosition)) {
                    break loop;
                }
            }
        }
        if (route.size() > 0) {
            route.remove(0);
        }
        return route;
    }

    private List<Position> getNeighbours(Position currentPosition) {
        List<Position> neighbours = new ArrayList<>();
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        if (x > 0 && !map[y][x - 1].equals("#") && !visited.contains(new Position(x - 1, y))) {
            neighbours.add(new Position(x - 1, y));
        }
        if (x < map[y].length - 1 && !map[y][x + 1].equals("#") && !visited.contains(new Position(x + 1, y))) {
            neighbours.add(new Position(x + 1, y));
        }
        if (y > 0 && !map[y - 1][x].equals("#") && !visited.contains(new Position(x, y - 1))) {
            neighbours.add(new Position(x, y - 1));
        }
        if (y < map.length - 1 && !map[y + 1][x].equals("#") && !visited.contains(new Position(x, y + 1))) {
            neighbours.add(new Position(x, y + 1));
        }
        return neighbours;
    }

    private Position getClosestNeighbour(Position currentPosition, Position targetPosition) {
        List<Position> neighbours = getNeighbours(currentPosition);
        Position closestNeighbour = null;
        int minDistance = Integer.MAX_VALUE;
        for (Position neighbour : neighbours) {
            int distance = getDistance(neighbour, targetPosition);
            if (distance < minDistance) {
                minDistance = distance;
                closestNeighbour = neighbour;
            }
        }
        return closestNeighbour;
    }

    private int getDistance(Position neighbour, Position targetPosition) {
        return Math.abs(neighbour.getX() - targetPosition.getX()) + Math.abs(neighbour.getY() - targetPosition.getY());
    }

    private Position findPosition(String symbol) {
        for(int y= 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                if(map[y][x].equals(symbol)){
                    return new Position(x, y);
                }
            }
        }
        return null;
    }
}
