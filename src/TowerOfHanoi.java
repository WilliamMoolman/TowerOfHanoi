public class TowerOfHanoi {
    public static void main(String[] args) {

        TowerModel model = new TowerModel();

        View view = new View();

        Controller controller = new Controller(model, view);

        controller.run();
    }
}
