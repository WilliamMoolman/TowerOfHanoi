public class Controller {

    TowerModel model;
    View view;

    public Controller(TowerModel model, View view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        view.displayGreeter();
        close:
        while(true) {
            int choice = view.askForMenu();
            if(choice==-1) break;
            model.init(choice);
            long startTime = System.nanoTime();
            solve(model.getHeight(), 0,1,2);
            long endTime = System.nanoTime();
            if (choice < 10) view.displayMoves(model.getMoves(),model.getHeight());
            view.displayStats(model.getHeight(), model.getNumMoves(),endTime-startTime);
        }
    }

    private void solve(int n,int a, int b, int c) {
        if(n==3) {
            solveThree(a, b, c);
        } else {
            solve(n-1, a, c, b);
            model.step(a,c);
            solve(n-1,b,a,c);
        }
    }

    private void solveThree(int a, int b, int c) {
        model.step(a,c);
        model.step(a,b);
        model.step(c,b);
        model.step(a,c);
        model.step(b,a);
        model.step(b,c);
        model.step(a,c);
    }

    private void print() {
        view.displayTower(model.getTower());
    }

}
