package hu.duck;

import org.json.JSONObject;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(String json){
        JSONObject jsonObject = new JSONObject(json);
        this.x = jsonObject.getInt("x");
        this.y = jsonObject.getInt("y");
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", x);
        jsonObject.put("y", y);
        return jsonObject;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position other = (Position) obj;
            return other.x == x && other.y == y;
        }
        return false;
    }
}
