package hu.duck;

public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
        // String[][] map = {
        //     {"#", "E", "#", "#", "#"},
        //     {"#", ".", "#", "K", "#"},
        //     {"#", ".", "#", ".", "#"},
        //     {"#", ".", ".", ".", "#"},
        //     {"#", "S", "#", "#", "#"},

        // };
        // Escape escape = new Escape(map);
        // escape.getEscapeRoute().forEach(System.out::println);
    }
}
