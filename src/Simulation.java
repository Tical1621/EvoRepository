import entity.EntityRenderingRules;
import map.MapClass;
import map.MapFactory;
import map.MapRenderer;
import simulationProcess.SimulationProcess;

public class Simulation {
    public static void main(String[] args) throws InterruptedException {
        MapRenderer renderer = new MapRenderer();

        MapClass map = (new MapFactory()).fromFEN(
                        "t1/" +
                        "t1/" +
                        "t1/" +
                        "t1/" +
                        "t1/" +
                        "t1/" +
                        "t1/" +
                        "t1/" +
                        "t1/" +
                        "t1/" +
                        "t1/" +
                        "t1");
//        renderer.renderFromFEN(map);

        SimulationProcess process = new SimulationProcess(map);
        process.simulationLoop();
        renderer.renderFromFEN(map);

    }
}