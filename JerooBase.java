
/**
 * The JerooBase class defines the basic state and behavior of all Jeroos.
 *
 * @author Cameron Christensen
 * @author Steve Aronson (modified)
 */
public abstract class JerooBase implements Directions{

    private int flowers;
    private CompassDirection direction = EAST;
    private int x = 0;
    private int y = 0;
    private int ops = 0;

    public JerooBase() {
        // New jeroos must be added to the map.
        Map.getInstance().addJeroo((Jeroo)this);
        Map.getInstance().saveMap();
    }

    public JerooBase(int flowers) {
        this();
        this.flowers = flowers;
    }

    public JerooBase(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public JerooBase(int x, int y, CompassDirection direction) {
        this();
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public JerooBase(int x, int y, int flowers) {
        this();
        this.x = x;
        this.y = y;
        this.flowers = flowers;
    }

    public JerooBase(int x, int y, CompassDirection direction, int flowers) {
        this();
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.flowers = flowers;
    }

    // "Getter" methods for private jeroo instance variables
    // Note: These were NOT part of the original Jeroo API
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CompassDirection getDirection() {
        return direction;
    }

    public int getFlowers() {
        return flowers;
    }

    // The map needs to be able to increase a jeroo's flowers if another
    // jeroo gave it a flower.
    public void recieveFlower() {
        flowers++;
    }

    // "Action methods" for making a jeroo DO things. Notice these methods have
    // void as a return type and that they don't contain return statements.
    public void hop() {
        ops++;
        int tempX = JerooHelper.findXRelative(AHEAD, direction, x);
        int tempY = JerooHelper.findYRelative(AHEAD, direction, y);
        Map map = Map.getInstance();
        if (JerooHelper.coordsInBounds(tempX, tempY)){
            if (map.isClear(tempX, tempY) || map.isFlower(tempX, tempY)) {
                x = tempX;
                y = tempY;

            } else {
                if (map.isNet(tempX, tempY)) {
                    throw new Error("Jeroo trapped in net!");
                } else if (map.isWater(tempX, tempY)) {
                    throw new Error("Jeroo drowned in water!");
                }
            }
            map.saveMap();
        } else {
            throw new Error("Jeroo drowned in water!");
        }
    }

    public void hop(int n) {
        for (int i = 0; i < n; i++) {
            hop();
        }
    }

    public void pick() {
        ops++;
        if (isFlower(HERE)) {
            flowers++;
            Map.getInstance().clearSpace(x, y);
        }
    }

    public void plant() {
        ops++;
        if (flowers > 0) {
            flowers--;
            Map.getInstance().placeFlower(x, y);
        }
    }

    public void toss() {
        ops++;
        if (flowers > 0) {
            flowers--;
            int tempX = JerooHelper.findXRelative(AHEAD, direction, x);
            int tempY = JerooHelper.findYRelative(AHEAD, direction, y);
            if (JerooHelper.coordsInBounds(tempX, tempY) && Map.getInstance().isNet(tempX, tempY)) {
                Map.getInstance().clearSpace(tempX, tempY);
            } else {
                Map.getInstance().saveMap();
            }
        }
    }

    public void give(RelativeDirection relDir) {
        ops++;
        if (flowers > 0) {
            int tempX = JerooHelper.findXRelative(relDir, direction, x);
            int tempY = JerooHelper.findYRelative(relDir, direction, y);
            if (JerooHelper.coordsInBounds(tempX, tempY) && Map.getInstance().isJeroo(tempX, tempY)) {
                flowers--;
                Map.getInstance().getJerooAt(tempX, tempY).recieveFlower();
                Map.getInstance().saveMap();
            }
        }
    }

    public void turn(RelativeDirection relDir) {
        ops++;
        if (relDir == RIGHT) {
            if (direction == NORTH) {
                direction = EAST;
            } else if (direction == EAST) {
                direction = SOUTH;
            } else if (direction == SOUTH) {
                direction = WEST;
            } else if (direction == WEST) {
                direction = NORTH;
            }
        } else if (relDir == LEFT) {
            if (direction == NORTH) {
                direction = WEST;
            } else if (direction == EAST) {
                direction = NORTH;
            } else if (direction == SOUTH) {
                direction = EAST;
            } else if (direction == WEST) {
                direction = SOUTH;
            }
        }
        Map.getInstance().saveMap();
    }

    // "Boolean methods" return either of the boolean values true or false.
    public boolean hasFlower() {
        return flowers > 0;
    }

    public boolean isFacing(CompassDirection compDir) {
        return direction.equals(compDir);
    }

    public boolean isFlower(RelativeDirection relDir) {
        int tempX = JerooHelper.findXRelative(relDir, direction, x);
        int tempY = JerooHelper.findYRelative(relDir, direction, y);
        if (JerooHelper.coordsInBounds(tempX, tempY)) {
            return Map.getInstance().isFlower(tempX, tempY);
        }
        return false;
    }

    public boolean isJeroo(RelativeDirection relDir) {
        int tempX = JerooHelper.findXRelative(relDir, direction, x);
        int tempY = JerooHelper.findYRelative(relDir, direction, y);
        if (JerooHelper.coordsInBounds(tempX, tempY)) {
            return Map.getInstance().isJeroo(tempX, tempY);
        }
        return false;
    }

    public boolean isNet(RelativeDirection relDir) {
        int tempX = JerooHelper.findXRelative(relDir, direction, x);
        int tempY = JerooHelper.findYRelative(relDir, direction, y);
        if (JerooHelper.coordsInBounds(tempX, tempY)) {
            return Map.getInstance().isNet(tempX, tempY);
        }
        return false;
    }

    public boolean isWater(RelativeDirection relDir) {
        int tempX = JerooHelper.findXRelative(relDir, direction, x);
        int tempY = JerooHelper.findYRelative(relDir, direction, y);
        if (JerooHelper.coordsInBounds(tempX, tempY)) {
            return Map.getInstance().isWater(tempX, tempY);
        }
        return false;
    }

    public boolean isClear(RelativeDirection relDir) {
        int tempX = JerooHelper.findXRelative(relDir, direction, x);
        int tempY = JerooHelper.findYRelative(relDir, direction, y);
        if (JerooHelper.coordsInBounds(tempX, tempY)) {
            return Map.getInstance().isClear(tempX, tempY);
        }
        return false;
    }

    public void getFlowerNearby() {
        if (isFlower(HERE) || isFlower(AHEAD) || isFlower(LEFT) || isFlower(RIGHT)) {
            if (isFlower(LEFT)) {
                turn(LEFT);
            } else if (isFlower(RIGHT)) {
                turn(RIGHT);
            }
            hop();
            pick();
        }
    }

    // The logic here is surprisingly simple:
    //   - go right whenever possible
    //   - go straight if possible when right is unavailable
    //   - turn left if that's the only option, otherwise turn around
    // NOTE: This will only work on a maze with single-width "hallways" and no loops
    public void moveTendingRight() {
        getFlowerNearby();
        if (isClear(RIGHT)) {
            turn(RIGHT);
        } else {
            if (isClear(AHEAD)) {
            } // no need to turn...
            else {
                if (isClear(LEFT)) {
                    turn(LEFT);
                } else {
                    turn(RIGHT);
                    turn(RIGHT);
                }
            }
        }

        hop();
    }

    public int getOps() {
        return ops;
    }

    public void resetOps() {
        ops = 0;
    }
}
